#!/usr/bin/env python3
"""

Pour le controlleur (rasp) à destination de roboto


"""

from tkinter import *

import serial
import socket
import colorama

ROBOTO_HOST = 'elyspio-rama-vi' #Roboto (Lego)
SERVER_HOST  = 'pc-omen' #Gui


class Gui:

    class Connection:

        def __init__(self, host, port):
            colorama.init()
            self.IP = self.findIp(host)
            #self.IP = '127.0.0.1'
            self.PORT = port
            self.BUFFER = 20
            self._serv = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            self._serv.connect((self.IP, self.PORT))
            print(colorama.Fore.GREEN + "Connection Done")

        def findIp(self, name):
            return socket.gethostbyname(name)


        def send(self, str):
            self._serv.send(str)
            return (self._serv.recv(self.BUFFER)).decode('utf-8')

    def __init__(self):
        #self._ser = serial.Serial('COM3', baudrate=115200)
        self._ser = serial.Serial(port='/dev/ttyACM0', baudrate='9600', timeout=1)
        self._conRoboto = self.Connection(ROBOTO_HOST, 4242)
        self._conServer = self.Connection(SERVER_HOST, 4243)




    def parseValues(self):
        strr = self._ser.readline()
        #print(strr)
        x, y = -1, -1
        try:
            strr = strr.decode('utf-8')
            #print(strr)
            x,y = strr.split(" ")
        except:
            pass
        dataSended = "Bien Reçu Manette"
        #print("Envoi de " + dataSended)
        self._ser.write(dataSended.encode("utf-8"))

        return x, y

    def update(self):
        x, y = self.parseValues()
        if((x, y) != (-1, -1)):

            self.sendData(x, y)
        # self._fen.

    """
    On envoie a roboto les vitesse a roboto
    Il nous renvoie son niveau de batterie, sa vitesse effective G/D, si il avance ou non
    """
    def sendData(self, x, y):
        if((x, y) != (-1, -1)):
            bat, leftS, rightS, isForward = self._conRoboto.send(x, y)
            tab = {bat, leftS, rightS, isForward}
            self.sendToServ(tab)
        #print("Message reçu : " + self._con.send(x, y))

    def sendToServ(self, tab):
        strr = ""
        for elem in tab:
            strr += str(elem) + " "
        return self._conServer.send(strr)


f = Gui()
print("Launching")
while True:
    f.update()


