from socket import *
import os
import sys
import struct
import time
import select
import signal

ICMP_ECHO_REQUEST = 8

# global varibales to store RTTs and loss
rtts = []
sent_packets = 0
received_packets = 0

def checksum(source_string):
    sum = 0
    countTo = (len(source_string) // 2) * 2
    count = 0

    while count < countTo:
        thisVal = source_string[count + 1] * 256 + source_string[count]
        sum = sum + thisVal
        sum = sum & 0xffffffff
        count = count + 2

    if countTo < len(source_string):
        sum = sum + source_string[len(source_string) - 1]
        sum = sum & 0xffffffff

    sum = (sum >> 16) + (sum & 0xffff)
    sum = sum + (sum >> 16)
    answer = ~sum
    answer = answer & 0xffff
    answer = answer >> 8 | (answer << 8 & 0xff00)
    return answer

def receiveOnePing(mySocket, ID, timeout, destAddr):
    global received_packets
    timeLeft = timeout

    while True:
        startedSelect = time.time()
        whatReady = select.select([mySocket], [], [], timeLeft)
        howLongInSelect = (time.time() - startedSelect)
        if whatReady[0] == []:
            return "Request timed out."

        timeReceived = time.time()
        recPacket, addr = mySocket.recvfrom(1024)

        # fetch the ICMP header from the IP packet
        icmpHeader = recPacket[20:28]
        type, code, checksum_recv, packetID, sequence = struct.unpack("bbHHh", icmpHeader)

        if packetID == ID:
            received_packets += 1
            timeSent = struct.unpack("d", recPacket[28:28 + struct.calcsize("d")])[0]
            rtt = (timeReceived - timeSent) * 1000
            rtts.append(rtt)
            return f"Reply from {destAddr}: bytes={len(recPacket)} time={round(rtt, 2)}ms"

        timeLeft = timeLeft - howLongInSelect
        if timeLeft <= 0:
            return "Request timed out."

def sendOnePing(mySocket, destAddr, ID):
    global sent_packets
    myChecksum = 0
    header = struct.pack("bbHHh", ICMP_ECHO_REQUEST, 0, myChecksum, ID, 1)
    data = struct.pack("d", time.time())
    myChecksum = checksum(header + data)

    if sys.platform == 'darwin':
        myChecksum = htons(myChecksum) & 0xffff
    else:
        myChecksum = htons(myChecksum)

    header = struct.pack("bbHHh", ICMP_ECHO_REQUEST, 0, myChecksum, ID, 1)
    packet = header + data
    mySocket.sendto(packet, (destAddr, 1))
    sent_packets += 1

def doOnePing(destAddr, timeout):
    icmp = getprotobyname("icmp")
    mySocket = socket(AF_INET, SOCK_RAW, icmp)
    myID = os.getpid() & 0xFFFF
    sendOnePing(mySocket, destAddr, myID)
    delay = receiveOnePing(mySocket, myID, timeout, destAddr)
    mySocket.close()
    return delay

def print_statistics(host):
    print("\n--- Ping statistics for {} ---".format(host))
    print(f"{sent_packets} packets transmitted, {received_packets} received, "
          f"{((sent_packets - received_packets) / sent_packets) * 100:.2f}% packet loss")

    if rtts:
        print(f"rtt min/avg/max = {min(rtts):.2f}/{sum(rtts)/len(rtts):.2f}/{max(rtts):.2f} ms")
    else:
        print("No RTT data available.")

def ping(host, timeout=1):
    dest = gethostbyname(host)
    print(f"Pinging \"{host}\" [{dest}] using Python:\n")

    try:
        while True:
            print(doOnePing(dest, timeout))
            time.sleep(1)
    except KeyboardInterrupt:
        print_statistics(host)

ping("google.com")
