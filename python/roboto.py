import urllib.request as web

def get_values():
    data = web.urlopen("http://roboto.elyspioweb.xyz/speed.txt").read()
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


data = get_values()
print()
