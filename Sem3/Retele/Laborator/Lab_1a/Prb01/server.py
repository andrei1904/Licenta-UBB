# Enunt: Clientul trimite serverului un numar. Serverul il primeste si il afiseaza pe ecran.
#
# Rulare in doua terminale diferite:
#	python server.py
#	python client.py
import socket

TCP_IP = "127.0.0.1"
TCP_PORT = 8888

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind((TCP_IP, TCP_PORT))
s.listen(1)


def sum(lst):
	lst_sum = 0
	for number in lst:
		lst_sum += number

	return lst_sum

while 1:
	conn, addr = s.accept()
	print('Connection addres: ', addr)
	data = conn.recv(4096)

	if not data:
		break

	data = data.decode('utf8')
	data = eval(data)

	print("Am primit de la client: ", data)

	lst_sum = sum(data)
	print("Suma este: ", lst_sum)

	conn.send(str(lst_sum).encode())

	conn.close()
