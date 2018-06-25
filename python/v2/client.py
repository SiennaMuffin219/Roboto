#!/usr/bin/env python

import socket


TCP_IP = '127.0.0.1'
TCP_PORT = 25565
BUFFER_SIZE = 1024
MESSAGE = "Hello, World!".encode('utf-8')

s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
print("Connexion a ", TCP_IP)
s.connect((TCP_IP, TCP_PORT))
while 1:
    s.send(MESSAGE)
    data = s.recv(BUFFER_SIZE)
    print("received data:", data.decode('utf-8'))
s.close()

