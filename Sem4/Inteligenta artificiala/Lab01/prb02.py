import math


def distanta_euclidiana(x1, y1, x2, y2):
    distanta = math.sqrt((x2 - x1) ** 2 + (y2 - y1) ** 2)
    print('Distanta intre (', x1, ',', y1, ') si (', x2, ',', y2, ') este ', distanta)


if __name__ == '__main__':
    x1 = int(input('x1 = '))
    y1 = int(input('y1 = '))
    x2 = int(input('x2 = '))
    y2 = int(input('y2 = '))

    distanta_euclidiana(x1, y1, x2, y2)
