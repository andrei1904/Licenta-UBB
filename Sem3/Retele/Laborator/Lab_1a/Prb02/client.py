import socket
import time

TCP_IP = "127.0.0.1"
TCP_PORT = 8888

n = str(input("Introduceti un sir de caractere: "))

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect((TCP_IP, TCP_PORT))

s.send(n.encode('utf8'))

time.sleep(.300)

nr_spaces = s.recv(4096)
nr_spaces = nr_spaces.decode('utf8')
print("Numarul de spatii este: ", nr_spaces)

s.close()
