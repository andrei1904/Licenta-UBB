from collections import defaultdict


def element_majoritar(lista):
    n = len(lista)
    numar_aparitii = defaultdict(int)

    for elem in lista:
        numar_aparitii[elem] += 1

        if numar_aparitii[elem] > n / 2:
            print(elem)
            break


element_majoritar([2, 8, 7, 2, 2, 5, 2, 3, 1, 2, 2])
