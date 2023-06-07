from collections import defaultdict


def aparitie1(text):
    text = text.split(" ")
    dict_cuvinte = defaultdict(int)

    for cuvant in text:
        dict_cuvinte[cuvant] += 1

    for cuvant in dict_cuvinte:
        if dict_cuvinte[cuvant] == 1:
            print(cuvant)


aparitie1("da da nu")
