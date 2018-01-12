import urllib.request as web
import time
def get_values():
    data = web.urlopen("http://roboto.elyspioweb.xyz/assets/data/speed.txt").read()
    return str(data)

def parse_values(data):
    #'b' 'v  w'
    left_str = ""
    right_str = ""
    first = True
    for i in data:
        if First:
            if i != " ":
                left_str += i
            else:
                first = False
        else:
            right_str += i

while (1):
    data = get_values()
    print(data)
    time.sleep(0.2)
