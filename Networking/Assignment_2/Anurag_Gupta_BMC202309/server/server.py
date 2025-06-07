import socket

serverPort = 6789
serverSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
serverSocket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)

serverSocket.bind(('', serverPort))
serverSocket.listen(1)

print("The web server is running on port", serverPort)

while True:
    connectionSocket, addr = serverSocket.accept()
    
    try:
        message = connectionSocket.recv(1024).decode()
        
        if not message:
            connectionSocket.close()
            continue
        
        filename = message.split()[1][1:]
        
        with open(filename, 'r') as file:
            content = file.read()
        
        response = "HTTP/1.1 200 OK\r\n"
        response += "Content-Type: text/html\r\n"
        response += "\r\n"
        response += content
        
    except FileNotFoundError:
        response = "HTTP/1.1 404 Not Found\r\n"
        response += "Content-Type: text/html\r\n"
        response += "\r\n"
        response += "<html><body><h1>404 Not Found</h1></body></html>"
    
    connectionSocket.sendall(response.encode())
    connectionSocket.close()
