#!/usr/bin/env python3
from ev3dev.ev3 import *
import time
speedP = 50
speedM = 50
speed_max = 1000

motorGauche = LargeMotor('outB') #GAUCHE
motorDroite = LargeMotor('outC') #DROITE



def init():
    motorGauche = LargeMotor('outB') #GAUCHE
    motorDroite = LargeMotor('outC') #DROITE

    motorGauche.stop(stop_action='brake')
    motorDroite.stop(stop_action='brake')

def sleep(x):
    for i in range(0, x):
        time.sleep(1)
        print(x-i)

def increase_speed(motor, speed):
    motorGauche = LargeMotor('outB') #GAUCHE
    motorDroite = LargeMotor('outC') #DROITE
    if (motor.speed_sp + speedP > speed_max):

         motor.run_forever(speed_sp = speed_max)
    else:
         val = motor.speed_sp + speedP
         motor.run_forever(speed_sp = val)


def decrease_speed(motor):
    motorGauche = LargeMotor('outB') #GAUCHE
    motorDroite = LargeMotor('outC') #DROITE
    if ( motor.speed_sp - speedM < 0):
        motor.run_forever(speed_sp = 0)
        motor.stop(stop_action='brake')
    else:

         motor.run_forever(speed_sp = motor.speed_sp - speedM)


def stop_turn():
    motorGauche = LargeMotor('outB') #GAUCHE
    motorDroite = LargeMotor('outC') #DROITE
    motorDroite.run_forever(speed_sp = motorGauche.speed_sp)


def turn_right():

    decrease_speed(motorGauche)
    increase_speed(motorDroite, 50)


def turn_left():
    decrease_speed(motorDroite)
    increase_speed(motorGauche, 50)

def stop_robot():
    motorGauche = LargeMotor('outB') #GAUCHE
    motorDroite = LargeMotor('outC') #DROITE
    while ( motorGauche.speed_sp > 30 or motorDroite.speed_sp > 30):
        decrease_speed(motorGauche)
        decrease_speed(motorDroite)
        print(motorDroite.speed_sp)
        if(motorGauche.speed_sp == 0 or motorDroite.speed_sp == 0):
            motorGauche.speed_sp = 0
            motorDroite.speed_sp = 0

def forward():
    for i in range(1):
        increase_speed(motorGauche, 100)
        increase_speed(motorDroite, 100)

def backward():
    for i in range(1):
        increase_speed(motorGauche, -50)
        increase_speed(motorDroite, -50)


print("JE ME LANCE")
while True :

    ch = int(input())
    print(ch)
    if (ch == 0):
        forward()
    if ch == 1:
        turn_right()

    if (ch == 2):
        backward()

    if ch == 3:
        turn_left()



    if ch == 4:
        stop_robot()


    if ch == 5:
        stop_turn()
