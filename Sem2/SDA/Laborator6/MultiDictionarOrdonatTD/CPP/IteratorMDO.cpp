#include "../Header/IteratorMDO.h"
#include "../Header/MDO.h"

/*
 * Complexitate timp: ϴ(n)
 */
IteratorMDO::IteratorMDO(const MDO& d) : dict(d){
    for (int i = 0; i < MAX; i++) {
        l[i] = copiaza_lista(dict.l[i]);
    }
    poz_val = 0;
    lista_sortata = nullptr;
    interclasare_n_liste();
    primul_element = lista_sortata;
}

/*
 * Complexitate timp: ϴ(1)
 */
void IteratorMDO::prim(){
    lista_sortata = primul_element;
    poz_val = 0;
}

/*
 * Complexitate timp: ϴ(1)
 */
void IteratorMDO::urmator(){
    if (poz_val < lista_sortata->valori.size() - 1) {
        poz_val++;
        return;
    }

    if (valid()) {
        lista_sortata = lista_sortata->urm;
        poz_val = 0;
    }
}

/*
 * Complexitate timp: ϴ(1)
 */
bool IteratorMDO::valid() const{
    return lista_sortata != nullptr;
}

/*
 * Complexitate timp: ϴ(1)
 */
TElem IteratorMDO::element() const{
    return pair <TCheie, TValoare>  {lista_sortata->cheie, lista_sortata->valori[poz_val]};
}

/*
 * Complexitate timp: ϴ(n1 + n2)
 */
PNod IteratorMDO::interclasare(PNod a, PNod b) {
    PNod rezultat = nullptr;

    if (a == nullptr) {
        return b;
    } else if (b == nullptr) {
        return a;
    }

    if (dict.rel(a->cheie, b->cheie)) {
        rezultat = a;
        rezultat->urm = interclasare(a->urm, b);
    } else {
        rezultat = b;
        rezultat->urm = interclasare(a, b->urm);
    }

    return rezultat;
}

/*
 * Complexitate timp: ϴ(n * m)
 */
PNod IteratorMDO::interclasare_n_liste() {
    for (int i = 0; i < dict.m; i++) {
        lista_sortata = interclasare(lista_sortata, l[i]);
    }

    return lista_sortata;
}

/*
 * Complexitate timp: ϴ(n)
 */
IteratorMDO::~IteratorMDO() {
    while (primul_element != nullptr) {
        PNod p = primul_element;
        primul_element = primul_element->urm;
        delete p;
    }
}

/*
 * Complexitate timp: ϴ(n)
 */
PNod IteratorMDO::copiaza_lista(PNod lista) {
    if (lista == nullptr) {
        return nullptr;
    }

    PNod nod = new Nod(lista->cheie, nullptr);
    nod->valori = lista->valori;

    PNod prim = nod;
    PNod curent = nod;
    lista = lista->urm;

    while (lista != nullptr) {
        curent->urm = new Nod(lista->cheie, nullptr);
        curent->urm->valori = lista->valori;

        lista = lista->urm;
        curent = curent->urm;
    }
    return prim;
}






