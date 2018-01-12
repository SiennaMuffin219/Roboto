from ev3dev.ev3 import *
motorDroite = LargeMotor('outC') #DROITE
while True:
    print(int(motorDroite.speed_sp))
    print(motorDroite.speed_sp)    
