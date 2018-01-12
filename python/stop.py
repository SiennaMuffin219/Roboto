#!/usr/bin/env python3
from ev3dev.ev3 import *
import time

def stop_robot():
    motorGauche = LargeMotor('outB') #GAUCHE
    motorDroite = LargeMotor('outC') #DROITE
    while ( motorGauche.speed_sp > 0 and motorDroite.speed_sp > 0):
        decrease_speed(motorGauche)
        decrease_speed(motorDroite)
        print(motorDroite.speed_sp)
        if(motorGauche.speed_sp == 0 or motorDroite.speed_sp == 0):
            motorGauche.speed_sp = 0
            motorDroite.speed_sp = 0


stop_robot()
