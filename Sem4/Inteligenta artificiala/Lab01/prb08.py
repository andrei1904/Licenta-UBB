import queue


def generaza_binar(n):
    coada = queue.Queue()
    coada.put('1')

    while n > 0:
        numar = coada.get()
        print(numar)

        coada.put(numar + '0')
        coada.put(numar + '1')

        n -= 1


if __name__ == '__main__':
    n = int(input('n = '))
    generaza_binar(n)

