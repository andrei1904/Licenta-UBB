

def sortare(lista):
    n = len(lista)

    for i in range(0, n):
        for j in range(i + 1, n):
            if lista[i] > lista[j]:
                aux = lista[i]
                lista[i] = lista[j]
                lista[j] = aux


def k_mare(k, lista):
    sortare(lista)
    print(lista[-k])


k_mare(2, [7, 4, 6, 3, 9, 1])
