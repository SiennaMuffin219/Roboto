import urllib.request as web
import time
leftSpeed = 0
rightSpeed = 0

contact = False
proximity = 0
luminosity = 0
gyroscope = 0


def get_values():
    dataIn = web.urlopen("http://roboto.elyspioweb.xyz/assets/data/speed.txt").read()
    return str(dataIn)

def put_values(contact, proximity, luminosity, gyroscope):
    dataOut = web.urlopen("http://roboto.elyspioweb.xyz/assets/php/roboto.php?" + "contact=" + str(contact) + "&proximity=" + str(proximity) +  "&luminosity=" + str(luminosity) + "&gyroscope=" + str(gyroscope))


def parse_values(data):
    indFirstN = 4
    cpt = 0
    for c in data:
        if c == 'n' and c != 4:
            indSecondN = cpt
        else:
            cpt += 1

    leftSpeed = data[indFirstN:indSecondN]
    rightSpeed =data[indSecondN+2:len(data)-1]
    return leftSpeed, rightSpeed

print( "Sending Values")
put_values(False, 2.5, 39, 42)
print("Done")

while True :
    print(" ")

    dataIn = get_values()
    leftSpeed, rightSpeed = parse_values(dataIn)

    print("leftSpeed = "  + leftSpeed)
    print("rightSpeed = " + rightSpeed)
    time.sleep(1)
