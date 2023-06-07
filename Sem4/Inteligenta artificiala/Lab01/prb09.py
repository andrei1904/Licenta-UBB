

def suma_matrice(matrice, lista):
    suma = 0
    for i in range(lista[0][0], lista[1][0] + 1):
        for j in range(lista[0][1], lista[1][1] + 1):
            suma += matrice[i][j]

    print(suma)


matr = [[0, 2, 5, 4, 1],
        [4, 8, 2, 3, 7],
        [6, 3, 4, 6, 2],
        [7, 3, 1, 8, 3],
        [1, 5, 7, 9, 4]]

suma_matrice(matr, [[2, 2], [4, 4]])
