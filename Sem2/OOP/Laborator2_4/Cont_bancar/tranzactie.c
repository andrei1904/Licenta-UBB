#include <string.h>
#include <stdlib.h>
#include <assert.h>

#include "tranzactie.h"


/*
 * creeaza tranzactie
 */
Tranzactie* creeaza_tranzactie(int zi, double suma, char* tip, char* descriere) {
    Tranzactie* tr = malloc(sizeof(Tranzactie));

    tr->zi = zi;
    tr->suma = suma;

    int nr_caractere = strlen(tip) + 1; // retine numarul de caractere pentru a putea aloca memorie ulterior
    tr->tip = malloc(sizeof(char) * nr_caractere);
    strcpy(tr->tip, tip);

    nr_caractere = strlen(descriere) + 1; // retine numarul de caractere pentru a putea aloca memorie ulterior
    tr->descriere = malloc(sizeof(char) * nr_caractere);
    strcpy(tr->descriere, descriere);

    return tr;
}

/*
 * elibereaza memoria ocupata de o tranzactie
 */
void distruge_tranzactie(Tranzactie* tr) {
    free(tr->tip);
    free(tr->descriere);
    free(tr);
}

/*
 * creeaza o copie a unei tranzactii
 */
Tranzactie* copiaza_tranzactie(Tranzactie *tr) {
    return creeaza_tranzactie(tr->zi, tr->suma, tr->tip, tr->descriere);
}

/*
 * valideaza o tranzactie
 */
int valideaza_tranzactie(Tranzactie* tr) {
    if (tr->zi < 1 || tr->zi > 31)
        return 1;
    if (tr->suma <= 0)
        return 1;
    if (strcmp(tr->tip, "intrare") != 0 && strcmp(tr->tip, "iesire") != 0)
        return 1;
    if (strlen(tr->descriere) == 0)
        return 1;
    return 0; // daca tranzactia este valida
}

void test_creeaza_distruge() {
    Tranzactie* tr = creeaza_tranzactie(21, 250.1, "iesire", "da");
    assert(tr->zi == 21);
    assert(tr->suma == 250.1);
    assert(strcmp(tr->tip, "iesire") == 0);
    assert(strcmp(tr->descriere, "da") == 0);

    assert(valideaza_tranzactie(tr) == 0);

    distruge_tranzactie(tr);

    tr = creeaza_tranzactie(-1, -50.1, "da", "");
    assert(valideaza_tranzactie(tr) == 1);
    distruge_tranzactie(tr);

    tr = creeaza_tranzactie(20, -50.1, "da", "");
    assert(valideaza_tranzactie(tr) == 1);
    distruge_tranzactie(tr);

    tr = creeaza_tranzactie(20, 50.1, "da", "");
    assert(valideaza_tranzactie(tr) == 1);
    distruge_tranzactie(tr);

    tr = creeaza_tranzactie(20, 50.1, "intrare", "");
    assert(valideaza_tranzactie(tr) == 1);
    distruge_tranzactie(tr);
}

