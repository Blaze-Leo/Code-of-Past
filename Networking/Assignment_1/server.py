import socket
import random

SERVER_NAME = "Server of Anurang Gupta"
port_number = 12345


def start_server():
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    server_socket.bind(("127.0.0.1", port_number))
    server_socket.listen(5)

    print(f"{SERVER_NAME} is listening on port {port_number}...")

    out_of_range = True

    while out_of_range:
        client_socket, client_address = server_socket.accept()
        print(f"Connection from {client_address}")

        data = client_socket.recv(1024).decode()
        if not data:
            break

        try:
            client_name, client_number = data.split(":")
            client_number = int(client_number)

            if not (1 <= client_number <= 100):
                out_of_range = False

            SERVER_NUMBER = random.randint(1, 100)

            print(f"Client Name: {client_name}")
            print(f"Server Name: {SERVER_NAME}")
            print(
                f"Client Number: {client_number}, Server Number: {SERVER_NUMBER}")
            print(f"Sum: {client_number + SERVER_NUMBER}")

            response = f"{SERVER_NAME}:{SERVER_NUMBER}"
            client_socket.send(response.encode())

        except ValueError:
            print("Invalid data format received.")

        client_socket.close()

    print("Received an out-of-range number. Shutting down the server.")
    server_socket.close()


if __name__ == "__main__":
    start_server()
