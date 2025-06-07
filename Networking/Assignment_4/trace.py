from socket import *
import os
import sys
import struct
import time
import select
import socket

ICMP_ECHO_REQUEST = 8
MAX_HOPS = 30
TIMEOUT = 5.0
TRIES = 1

def checksum(source_string):
    countTo = (len(source_string) // 2) * 2
    sum = 0
    count = 0

    while count < countTo:
        thisVal = (source_string[count + 1]) * 256 + (source_string[count])
        sum = sum + thisVal
        sum = sum & 0xffffffff
        count = count + 2

    if countTo < len(source_string):
        sum = sum + (source_string[len(source_string) - 1])
        sum = sum & 0xffffffff

    sum = (sum >> 16) + (sum & 0xffff)
    sum = sum + (sum >> 16)
    answer = ~sum
    answer = answer & 0xffff
    answer = answer >> 8 | (answer << 8 & 0xff00)
    return answer

def build_packet():
    myChecksum = 0
    myID = os.getpid() & 0xFFFF
    header = struct.pack("bbHHh", ICMP_ECHO_REQUEST, 0, myChecksum, myID, 1)
    data = struct.pack("d", time.time())
    myChecksum = checksum(header + data)

    header = struct.pack("bbHHh", ICMP_ECHO_REQUEST, 0, htons(myChecksum), myID, 1)
    packet = header + data
    return packet

def get_route(hostname):
    try:
        destAddr = gethostbyname(hostname)
    except:
        print(f"Unable to resolve hostname: {hostname}")
        return

    print(f"Tracing route to {hostname} [{destAddr}] with max {MAX_HOPS} hops:\n")

    timeLeft = TIMEOUT

    for ttl in range(1, MAX_HOPS + 1):
        for tries in range(TRIES):
            # Create raw socket
            mySocket = socket.socket(AF_INET, SOCK_RAW, IPPROTO_ICMP)

            mySocket.setsockopt(IPPROTO_IP, IP_TTL, struct.pack('I', ttl))
            mySocket.settimeout(TIMEOUT)

            try:
                d = build_packet()
                mySocket.sendto(d, (hostname, 0))
                t = time.time()
                startedSelect = time.time()
                whatReady = select.select([mySocket], [], [], timeLeft)
                howLongInSelect = (time.time() - startedSelect)

                if whatReady[0] == []:  # Timeout
                    print(f"{ttl}  * * * Request timed out.")
                    continue

                recvPacket, addr = mySocket.recvfrom(1024)
                timeReceived = time.time()
                timeLeft = timeLeft - howLongInSelect

                if timeLeft <= 0:
                    print(f"{ttl}  * * * Request timed out.")
                    continue

            except timeout:
                print(f"{ttl}  * * * Request timed out.")
                continue

            else:
                icmpHeader = recvPacket[20:28]
                types, code, checksum_recv, packetID, sequence = struct.unpack("bbHHh", icmpHeader)

                if types == 11 or types == 3:
                    bytes = struct.calcsize("d")
                    timeSent = struct.unpack("d", recvPacket[28:28 + bytes])[0]
                    try:
                        router_hostname = gethostbyaddr(addr[0])[0]
                    except:
                        router_hostname = "hostname not found"
                    print(f"{ttl}  rtt={round((timeReceived - t) * 1000)} ms  {addr[0]}  [{router_hostname}]")

                elif types == 0:
                    bytes = struct.calcsize("d")
                    timeSent = struct.unpack("d", recvPacket[28:28 + bytes])[0]
                    rtt = (timeReceived - timeSent) * 1000
                    try:
                        final_hostname = gethostbyaddr(addr[0])[0]
                    except:
                        final_hostname = "hostname not found"

                    print(f"{ttl}  rtt={round(rtt)} ms  {addr[0]}  [{final_hostname}]")
                    print(f"\nTrace complete: destination {addr[0]} reached at TTL {ttl} with RTT = {round(rtt)} ms.")
                    return

                else:
                    print(f"{ttl}  Error: unexpected ICMP type {types}")
                    break

            finally:
                mySocket.close()

get_route("www.iitm.ac.in")
