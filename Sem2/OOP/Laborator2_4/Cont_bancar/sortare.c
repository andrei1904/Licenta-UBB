#include "sortare.h"

#include <assert.h>

/*
 * sorteaza o lista de tranzactii dupa suma
 * daca ordinea este 0 este sortata crescator si daca ordinea este 1 este sortata descrescator
 */
void sort_suma(Lista *l, int ordine) {
    for (int i = 0; i < dimensiune(l) - 1; i++)
        for (int j = i + 1; j < dimensiune(l); j++) {
            Tranzactie* tr1 = get(l, i);
            Tranzactie* tr2 = get(l, j);
            if (tr1->suma < tr2->suma == ordine) {
                set(l, i, tr2);
                set(l, j, tr1);
            }
        }
}

/*
 * sorteaza o lista de tranzactii dupa zi
 * daca ordinea este 0 este sortata crescator si daca ordinea este 1 este sortata descrescator
 */
void sort_zi(Lista *l, int ordine) {
    for (int i = 0; i < dimensiune(l) - 1; i++)
        for (int j = i + 1; j < dimensiune(l); j++) {
            Tranzactie* tr1 = get(l, i);
            Tranzactie* tr2 = get(l, j);
            if (tr1->zi < tr2->zi == ordine) {
                set(l, i, tr2);
                set(l, j, tr1);
            }
        }
}

void test_sortare() {
    Lista* l = creeaza_lista();

    Tranzactie* x = creeaza_tranzactie(20, 100.11, "intrare", "da");
    adauga(l, x);

    Tranzactie* y = creeaza_tranzactie(10, 50.12, "iesire", "da");
    adauga(l, y);

    Tranzactie* z = creeaza_tranzactie(28, 10.59, "iesire", "da");
    adauga(l, z);

    sort_zi(l, 0);
    Tranzactie* tr = get(l, 0);
    Tranzactie* tr2 = get(l, 1);
    Tranzactie* tr3 = get(l, 2);
    assert(tr->zi == y->zi && tr2->zi == x->zi && tr3->zi == z->zi);

    sort_suma(l, 0);
    tr = get(l, 0);
    tr2 = get(l, 1);
    tr3 = get(l, 2);
    assert(tr->suma == z->suma && tr2->suma == y->suma && tr3->suma == x->suma);

    distruge_lista(l, (FunctieDistruge) distruge_tranzactie);
}
