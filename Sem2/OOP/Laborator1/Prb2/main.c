#include <stdio.h>

void ciur(int n, int prim[n + 1]) {
    /*
     *  functie care calculeaza numerele prime mai mici decat un numar n
     *  n - int, capatul superior al multimii in care se cauta numerele prime
     *  prim - vector (int), se marcheaza daca indicele este prim (1) sau daca nu este prim (0)
     *  n trebuie sa fie pozitiv
     */
    for (int i = 0; i <= n + 1; i++)
        prim[i] = 1;

    for (int i = 2; i * i <= n; i++) {
        if (prim[i] == 1)
            for (int p = i * i; p <= n; p += i) // marcheaza numerele care nu sunt prime
                prim[p] = 0;
    }
}

int main() {
    /*
     * functie care primeste input de la utilizator (un numar natural pozitiv)
     * afiseaza numerele prime mai mici decat numarul natural primit de la utilizator
     */
    int n;
    printf("Introduceti numarul: ");
    scanf("%d", &n);

    while (n > 0) {
        int prim[n + 1];
        ciur(n, prim);
        for (int i = 2; i <= n; i++) {
            if (prim[i])
                printf("%d ", i);
        }
        printf("\nIntroduceti numarul: ");
        scanf("%d", &n);
    }
    return 0;
}