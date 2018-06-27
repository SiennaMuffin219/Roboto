#!/usr/bin/env python

import socket
from colorama import init, Fore, Back

TCP_IP = ''
TCP_PORT = 4242
BUFFER_SIZE = 20  # Normally 1024, but we want fast response

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind((TCP_IP, TCP_PORT))
s.listen(1)

print(Fore.BLUE + 'Lancement du programme')
conn, addr = s.accept()
print (Fore.BLUE + 'Connection address:', addr)
while 1:
    data = conn.recv(BUFFER_SIZE)
    if not data: break
    strr = data.decode('utf-8')
    print (Fore.GREEN + "received data:", strr)

    conn.send(data)  # echo
conn.close()