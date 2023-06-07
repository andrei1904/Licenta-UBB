import socket
import time

TCP_IP = "127.0.0.1"
TCP_PORT = 8888

lst = []
n = int(input("Dati numarul de numere: "))

for i in range(0, n):
	elem = int(input("Dati un numar: "))
	lst.append(elem)


s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect((TCP_IP, TCP_PORT))

lst = str(lst)
lst = lst.encode()
s.send(lst)

time.sleep(.300)

lst_sum = s.recv(4096)
lst_sum = lst_sum.decode()
print("Suma: ", lst_sum)

s.close()
