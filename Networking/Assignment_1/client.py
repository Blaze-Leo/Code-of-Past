import socket

CLIENT_NAME = "Client of Anurang Gupta"
port_number = 7435


def start_client():
    server_host = "172.17.100.78"
    server_port = port_number
    client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    client_socket.connect((server_host,  server_port))

    client_number = int(input("Enter an integer between 1 and 100: "))

    message = f"{CLIENT_NAME}:{client_number}"
    client_socket.send(message.encode())

    data = client_socket.recv(1024).decode()
    server_name, server_number = data.split(":")
    server_number = int(server_number)

    print(f"Client Name: {CLIENT_NAME}")
    print(f"Server Name: {server_name }")
    print(f"Client Number: {client_number }, Server Number: { server_number}")
    print(f"Sum: {client_number+server_number }")

    client_socket.close()


if __name__ == "__main__":
    start_client()
