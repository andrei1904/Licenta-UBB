
def produs_scalar(v1, v2):
    rez = 0

    for index in range(0, len(v1)):
        if index >= len(v2):
            break

        rez += v1[index] * v2[index]

    print(rez)


if __name__ == '__main__':
    vect1 = [1, 0, 2, 0, 3]
    vect2 = [1, 2, 0, 3, 1]

    produs_scalar(vect1, vect2)
