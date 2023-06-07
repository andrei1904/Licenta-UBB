import socket
import time

TCP_IP = "127.0.0.1"
TCP_PORT = 8888

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind((TCP_IP, TCP_PORT))
s.listen(1)


while 1:
    conn, adr = s.accept()
    data = conn.recv(4096)
    data = data.decode('utf8')

    if data == "stop":
        break

    if not data:
        break


    print("Am primit de la client: ", data)
    nr_spaces = data.count(' ')

    conn.send(str(nr_spaces).encode('utf8'))

    conn.close()
