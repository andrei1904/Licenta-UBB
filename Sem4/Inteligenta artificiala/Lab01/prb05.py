
def suma(lista):
    suma_elemente = 0
    for elem in lista:
        suma_elemente += elem
    return suma_elemente


def val_repeta(lista):
    n = len(lista) - 1

    suma_totala = suma(lista)
    suma_teoretica = n * (n + 1) / 2

    print(suma_totala - suma_teoretica)


val_repeta([1, 2, 3, 4, 2])
