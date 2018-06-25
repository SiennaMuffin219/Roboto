#!/usr/bin/env python3
from tkinter import *

import serial
import time



class Frame:

    def parseValues(self):
        strr = self._ser.readline()
        print(strr)
        x, y = 500,500
        try:
            strr = strr.decode('utf-8')
            x,y = strr.split(" ")
        except:
            pass

        return x, y

    def __init__(self):
        self._ser = serial.Serial(port='/dev/ttyACM0', baudrate='9600')
        self._fen = Tk()
        self._labelX = Label(self._fen, text=0)
        self._labelX.pack()
        self._labelY = Label(self._fen, text=0)
        self._labelY.pack()



    def update(self):

        x, y = self.parseValues()
        self._labelX['text'] = x
        self._labelY['text'] = y
        self._fen.update()


f = Frame()
print("Launching")
while True:
    f.update()


