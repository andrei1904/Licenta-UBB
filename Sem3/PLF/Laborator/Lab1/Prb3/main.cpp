#include "lista.h"


/*
 * a)                                            { (); daca n este 0
 * substituire(l1 l2 ... ln, pozitia, element) = { (element l2 ... ln); daca pozitia este 1
 *                                               { l1 u substituire(l2 l3 ... ln, pozitie-1, element); altfel
 *
 *
 * b)
 *                                            { fasle; daca n = 0
 *     apare_element(l1 l2 ... ln, element) = { true; daca l1 = element
 *                                            { apare_element(l2 l3 ... ln, element); altfel
 *
 *     l - k
 *                                              { (); daca m este 0
 *     diferenta(l1 l2 ... ln, k1 k2 ... km) =  { l1 u diferenta(l2 ... ln, k2 ... kn) ; daca apare_element(l1, k1...kn) = false
 *                                              { diferenta(l2 l3 ... ln, k1 k2 ... kn); altfel
 */



Lista substituire(Lista l, int pozitia, int element) {
    if (vida(l)) {
        return l;
    }
    if (pozitia == 1) {
        schimba_primul_element(l, element);
        return l;
    } else {
        return adauga_inceput(substituire(urmatorul_este_prim(l), pozitia - 1, element), primul_nod(l));
    }
}

bool apare_element(Lista l, int element) {
    if (vida(l)) {
        return false;
    }
    if (primul_element(l) == element) {
        return true;
    } else {
        return apare_element(urmatorul_este_prim(l), element);
    }
}

Lista diferenta(Lista l, Lista k) {
    if (vida(l)) {
        return l;
    }
    if (!apare_element(k, primul_element(l))) {
        return adauga_inceput(diferenta(urmatorul_este_prim(l), k), primul_nod(l));
    }
    return diferenta(urmatorul_este_prim(l), k);
}


int main() {
    Lista l;
    l = creare();

    // substituire
    substituire(l, 3, 5);
    tipar(l);

    // diferenta
//    Lista k;
//    k = creare();
//    tipar(diferenta(l, k));
//    distruge(k);

    distruge(l);
}
