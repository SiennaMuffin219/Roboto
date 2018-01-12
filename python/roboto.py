import urllib.request as web

leftSpeed = 0
rightSpeed = 0

def get_values():
    data = web.urlopen("http://roboto.elyspioweb.xyz/assets/data/speed.txt").read()
    return str(data)

def parse_values(data):
    deb = 0
    med = 0
    fin = 0
    strTemp = ""
    if (i != len(data) -1 )
    while data[deb] != '\ ' and data[deb+1] != 'n':
        deb += 1
    while data[med] != '\ ' and data[med+1] != 'n' and med == deb:
        med += 1

    for c in range(deb, med, 1):
        strTemp += c
    leftSpeed = strTemp
    strTemp = ""


    for c in range(med, len(data) -1    , 1):
        strTemp += c




data = get_values()
print(data)
parse_values(data)

print(leftSpeed + " " + rightSpeed)
