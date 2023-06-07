
def ultimul_cuvant(text):
    lista = text.split()
    lista.sort()
    print(lista[-1])


if __name__ == '__main__':
    text = input()
    ultimul_cuvant(text)
