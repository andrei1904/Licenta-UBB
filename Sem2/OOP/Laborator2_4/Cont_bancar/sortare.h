#ifndef CONT_BANCAR_SORTARE_H
#define CONT_BANCAR_SORTARE_H

#include "lista.h"

/*
 * sorteaza o lista de tranzactii dupa suma
 * daca ordinea este 0 este sortata crescator si daca ordinea este 1 este sortata descrescator
 */
void sort_suma(Lista *l, int ordine);

/*
 * sorteaza o lista de tranzactii dupa zi
 * daca ordinea este 0 este sortata crescator si daca ordinea este 1 este sortata descrescator
 */
void sort_zi(Lista *l, int ordine);

void test_sortare();

#endif //CONT_BANCAR_SORTARE_H
