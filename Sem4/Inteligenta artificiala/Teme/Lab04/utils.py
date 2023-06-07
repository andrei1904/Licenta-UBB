from random import randint, shuffle
import collections


def generate_new_value(lim1, lim2):
    return randint(lim1, lim2)


def bin_to_int(x):
    val = 0
    for bit in x:
        val = val * 2 + bit
    return val


def generate_random_permutation(n):
    perm = [i for i in range(n)]
    pos1 = randint(0, n - 1)
    pos2 = randint(0, n - 1)
    perm[pos1], perm[pos2] = perm[pos2], perm[pos1]
    return perm
