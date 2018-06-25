import serial
import time
ser = serial.Serial(port='/dev/ttyACM0', baudrate='9600')
time.sleep(0.5)

while True:

    x = ser.readline()
    print(x)
    try:
        x = x.decode('utf-8')
        z, y = x.split(" ")
        # time.sleep(0.2)
        print(str(z) + "\t Y : "+ str(y))
    except:
        pass

