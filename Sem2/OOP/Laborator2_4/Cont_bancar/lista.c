#include "lista.h"

#include <string.h>
#include <stdlib.h>
#include <assert.h>

/*
 * creeaza lista vida
 */
Lista* creeaza_lista() {
    Lista* l = malloc(sizeof(Lista));
    l->lg = 0;
    l->capacitate = 2;
    l->elemente = malloc(sizeof(TipElem) * l->capacitate);
    return l;
}

/*
 * distruge lista si tranzactiile
 */
void distruge_lista_tranzactie(Lista* l) {
    distruge_lista(l, (FunctieDistruge) distruge_tranzactie);
}

/*
 * distruge lista
 */
void distruge_lista(Lista* l, FunctieDistruge f_distruge) {
    for (int i = 0; i < l->lg; i++) {
        TipElem tr = l->elemente[i];
        f_distruge(tr);
    }
    free(l->elemente);
    free(l);
}

/*
 * copieaza o lista
 */
Lista* copiaza_lista(Lista* l) {
    Lista* l_noua = creeaza_lista();
    for (int i = 0; i < dimensiune(l); i++) {
        TipElem tr = get(l, i);
        adauga(l_noua, copiaza_tranzactie(tr));
    }
    return l_noua;
}

/*
 * returneaza element din lista de pe pozitia poz
 * poz - pozitia elementului, trebuie sa fie valida
 */
TipElem get(Lista* l, int poz) {
    return l->elemente[poz];
}

/*
 * modifica elementul de pe pozitia poz cu elementul tr
 * returneaza elementul suprascris
 */
TipElem set(Lista*l, int poz, Tranzactie* tr) {
    TipElem tr_anterior = l->elemente[poz];
    l->elemente[poz] = tr;
    return tr_anterior;
}

/*
 * returneaza numarul de elemente din lista
 */
int dimensiune(Lista* l) {
    return l->lg;
}

/*
 * aloca mai multa memorie daca este nevoie
 */
void redimensionare(Lista* l) {
    if (l->lg < l->capacitate)
        return;

    // aloca memorie
    TipElem* n_elemente = malloc(sizeof(TipElem) * (l->capacitate + 2));

    // copiaza elementele
    for (int i = 0; i < l->lg; i++) {
        n_elemente[i] = l->elemente[i];
    }

    // elibereaza memoria listei precedente
    free(l->elemente);
    l->elemente = n_elemente;
    l->capacitate += 2;
}

/*
 * adauga un element in lista la final
 */
void adauga(Lista* l, TipElem el) {
    redimensionare(l);
    l->elemente[l->lg] = el;
    l->lg++;
}

/*
 * sterge elementul de pe pozitia poz
 */
void sterge(Lista *l, int poz) {
    for (int i = poz; i < l->lg - 1; i++)
        l->elemente[i] = l->elemente[i + 1];
    l->lg--;
}

/*
 * sterge ultimul element dintr o lista si returneaz valoarea acestuia
 */
TipElem sterge_ultimul(Lista* l) {
    TipElem ultimul = l->elemente[l->lg - 1];
    l->lg -= 1;
    return ultimul;
}

void test_lista_de_liste() {
    Lista* l = creeaza_lista();
    adauga(l, creeaza_tranzactie(21, 24.0, "intrare", "da"));
    adauga(l, creeaza_tranzactie(21, 24.0, "intrare", "da"));

    Lista* l2 = creeaza_lista();

    Lista* undo_l = creeaza_lista();
    adauga(undo_l, l);
    assert(dimensiune(undo_l) == 1);

    adauga(undo_l, l2);
    assert(dimensiune(undo_l) == 2);

    distruge_lista(undo_l, (FunctieDistruge) distruge_lista_tranzactie);
}

void test_lista() {
    test_lista_de_liste();
//    Lista* l = creeaza_lista();
//
//    assert(l->lg == 0);
//    assert(l->capacitate == 2);
//
//    Tranzactie* x = creeaza_tranzactie(20, 10, "intrare", "x");
//    adauga(l, x);
//    adauga(l, x);
//    adauga(l, x);
//
//    assert(l->lg == 3);
//    assert(l->capacitate == 4);
//
//    Tranzactie* y = get(l, 0);
//    assert(y->zi == x->zi);
//
//    assert(dimensiune(l) == 3);
//
//    Tranzactie* z = creeaza_tranzactie(20, 20, "intrare", "x");
//    set(l, 0, z);
//    x = get(l, 0);
//    assert(x->suma == 20);
//
//    sterge(l, 0);
//    assert(l->lg == 2);
//
//    Lista* l_noua = copiaza_lista(l);
//    assert(dimensiune(l_noua) == dimensiune(l));
//
//    distruge_lista(l);
//    distruge_lista(l_noua);
//    distruge_tranzactie(z);
}
