import sys


def index_maxim(matrice):
    index = 0
    poz_minima = sys.maxsize

    for i in range(0, len(matrice)):
        for j in range(0, len(matrice[i])):
            if matrice[i][j] == 1:
                if j < poz_minima:
                    poz_minima = j
                    index = i

    print(index + 1)


matr = [[0, 0, 0, 1, 1],
        [0, 1, 1, 1, 1],
        [0, 0, 1, 1, 1]]

index_maxim(matr)
