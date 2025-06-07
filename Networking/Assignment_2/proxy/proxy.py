import socket
import threading
import os
import signal
import sys

CONFIG = {
    'HOST': 'localhost',
    'PORT': 7314,
    'BUFFER_SIZE': 4096,
    'TIMEOUT': 10,
}
CACHE_FOLDER = "cache"

if not os.path.exists(CACHE_FOLDER):
    os.makedirs(CACHE_FOLDER)

class SimpleProxyServer:
    def __init__(self, config):
        signal.signal(signal.SIGINT, self.shutdown_server)
        self.server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.server_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
        self.server_socket.bind((config['HOST'], config['PORT']))
        self.server_socket.listen(10)
        print(f"Proxy server is running at http://{config['HOST']}:{config['PORT']}/")

    def generate_cache_path(self, url):
        safe_filename = url.replace("/", "_").replace(":", "_")
        return os.path.join(CACHE_FOLDER, safe_filename + ".html")

    def fetch_from_origin(self, host, port, request):
        try:
            remote_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            remote_socket.settimeout(CONFIG['TIMEOUT'])
            remote_socket.connect((host, port))
            remote_socket.sendall(request)

            response = b""
            while True:
                chunk = remote_socket.recv(CONFIG['BUFFER_SIZE'])
                if not chunk:
                    break
                response += chunk

            remote_socket.close()
            return response
        except Exception as e:
            print(f"Error fetching data from server: {e}")
            return None

    def handle_client_request(self, client_conn):
        try:
            request = client_conn.recv(CONFIG['BUFFER_SIZE']).decode()
            if not request:
                return
            
            first_line = request.split('\n')[0]
            url = first_line.split(' ')[1].lstrip('/')
            print(f"Requested URL: {url}")

            cache_path = self.generate_cache_path(url)

            if os.path.exists(cache_path):
                print(f"Cache hit for {url}")
                with open(cache_path, "rb") as cache_file:
                    response = cache_file.read()
            else:
                print(f"Cache miss for {url}. Fetching from the origin server...")

                http_pos = url.find("://")
                if http_pos != -1:
                    url = url[(http_pos + 3):]

                port_pos = url.find(":")
                path_pos = url.find("/")
                if path_pos == -1:
                    path_pos = len(url)

                host = ""
                port = 80

                if port_pos == -1 or path_pos < port_pos:
                    host = url[:path_pos]
                else:
                    port = int(url[(port_pos + 1):path_pos - port_pos - 1])
                    host = url[:port_pos]

                proxy_request = f"GET / HTTP/1.1\r\nHost: {host}\r\nConnection: close\r\n\r\n".encode()
                response = self.fetch_from_origin(host, port, proxy_request)

                if response:
                    with open(cache_path, "wb") as cache_file:
                        cache_file.write(response)
                else:
                    response = b"HTTP/1.1 502 Bad Gateway\r\n\r\nCould not fetch the page."

            client_conn.sendall(response)
        except Exception as e:
            print(f"Error handling client request: {e}")
        finally:
            client_conn.close()

    def start_server(self):
        try:
            while True:
                client_conn, client_addr = self.server_socket.accept()
                print(f"[+] Connection from {client_addr}")
                client_thread = threading.Thread(target=self.handle_client_request, args=(client_conn,))
                client_thread.setDaemon(True)
                client_thread.start()
        except KeyboardInterrupt:
            self.shutdown_server(None, None)

    def shutdown_server(self, signum, frame):
        print("\nShutting down proxy server...")
        self.server_socket.close()
        sys.exit(0)

if __name__ == "__main__":
    proxy_server = SimpleProxyServer(CONFIG)
    proxy_server.start_server()
