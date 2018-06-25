#!/usr/bin/env python

import socket

HOST = "192.168.43.194"
PORT = 8080

sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
sock.connect((HOST, PORT))
while 1:
    sock.sendall(b"Hello\n")
    data = sock.recv(1024)
    print ("1)", data)

    if ( data == "olleH\n" ):
        print("pute")
        sock.sendall(b"Bye\n")
        data = sock.recv(1024)
        print ("2)", data)
