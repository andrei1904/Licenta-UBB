import socket
import time

TCP_IP = "127.0.0.1"
TCP_PORT = 8888

data = str(input("Introduceti un sir de caractere: "))

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect((TCP_IP, TCP_PORT))

s.send(data.encode('utf8'))

time.sleep(.300)

data = s.recv(4096)
data = data.decode('utf8')
print("Sirul inversat este: ", data)

s.close()
