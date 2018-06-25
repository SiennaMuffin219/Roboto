import urllib.request as web
import time
import os
os.system("cls")
leftSpeed = 0
rightSpeed = 0

contact = False
proximity = 0
luminosity = 0
gyroscope = 0

currentSite = 0

def get_values(currentSite):
    currentSite += 1
    if currentSite == 1:
        dataIn = web.urlopen("http://roboto.elyspioweb.xyz/assets/data/speed.txt").read()

    elif currentSite == 2:
        dataIn = web.urlopen("http://roboto.elyspioweb.xyz/assets/data/speed2.txt").read()

    elif currentSite == 3:
        dataIn = web.urlopen("http://roboto.elyspioweb.xyz/assets/data/speed3.txt").read()

    elif currentSite == 4:
        dataIn = web.urlopen("http://roboto.elyspioweb.xyz/assets/data/speed4.txt").read()
        currentSite = 0




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
compt = 1
while True :
    if (compt % 10 == 0):
        os.system("cls")
    dataIn = get_values(currentSite)
    leftSpeed, rightSpeed = parse_values(dataIn)

    print("leftSpeed = "  + leftSpeed)
    print("rightSpeed = " + rightSpeed)
    print("Compt : " + str(compt))
    compt += 1
    time.sleep(0.2)

    # time.sleep(0.01)
