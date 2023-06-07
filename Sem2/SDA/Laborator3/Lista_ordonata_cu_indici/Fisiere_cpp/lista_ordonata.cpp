#include "../Header/iterator.h"
#include "../Header/lista_ordonata.h"

#include <iostream>
#include <exception>

using namespace std;

/*
 * constructor nod
 */
Nod::Nod(TElement e, PNod urm, PNod prec) {
    this->e = e;
    this->urm = urm;
    this->prec = prec;
}

/*
 * returneaza un element de tip TElement
 * Complexitate timp: ϴ(1)
 */
TElement Nod::element() {
    return e;
}

/*
 * returneaza un pointer catre un Nod
 * Complexitate timp: ϴ(1)
 */
PNod Nod::urmator() {
    return urm;
}

/*
 * returneaza un pointer catre un Nod
 * Complexitate timp: ϴ(1)
 */
PNod Nod::precedent() {
    return prec;
}

LO::LO(Relatie r) {
    this->prim = nullptr;
    this->ultim = nullptr;
    rel = r;
}

// returnare dimensiune
// returneaza numarul de perechi (cheie, valoare) din dictionar
// Complexitate timp: ϴ(n)
int LO::dim() const {
    int nr = 0;
    for (PNod p = prim; p != nullptr; p = p->urmator()) {
        nr++;
    }
    return nr;
}

// verifica daca LO e vida
// Complexitate timp: ϴ(1)
bool LO::vida() const {
    return prim == nullptr;
}

// returnare element
// arunca exceptie daca i nu e valid
// Complexitate timp: O(n)
TElement LO::element(int i) const {
    int poz = 0;
    for (PNod p = prim; p != nullptr; p = p->urmator()) {
        if (i == poz) {
            return p->element();
        }
        poz++;
    }
    throw (exception());
}

// sterge element de pe o pozitie i si returneaza elementul sters
// arunca exceptie daca i nu e valid
// Complexitate timp: O(n)
TElement LO::sterge(int i) {
    int poz = 0;

    for (PNod p = prim; p != nullptr; p = p->urmator()) {
        if (poz == i) {
            if (p == prim) { // trebuie sters primul element
                TElement e_sters = p->e;
                if (p->urm == nullptr) { // lista contine doar un element
                    prim = ultim = nullptr;
                } else { // lista contine mai mult de un element
                    prim = p->urm;
                }
                delete p;
                return e_sters;
            } else if (p == ultim) { // trebuie sters ultimul element
                TElement e_sters = p->e;
                ultim = p->prec;
                ultim->urm = nullptr;
                delete p;
                return e_sters;
            } else { // trebuie sters un element care nu se afla in capetele listei
                TElement e_sters = p->e;
                p->prec->urm = p->urm;
                p->urm->prec = p->prec;
                delete p;
                return e_sters;
            }
        }
        poz++;
    }
    throw exception();
}

// cauta element si returneaza prima pozitie pe care apare (sau -1)
// Complexitate timp: O(n)
int LO::cauta(TElement e) const {
    int poz = 0;
    for (PNod p = prim; p != nullptr && rel(p->e, e); p = p->urm) {
        if (p->e == e) {
            return poz;
        }
        poz++;
    }
    return -1;
}

// adaugare element in LO
// Complexitate timp: O(n)
void LO::adauga(TElement e) {
    PNod nou = new Nod(e, nullptr, nullptr);

    if (prim == nullptr) { // lista este nula
        prim = nou;
        ultim = nou;
    } else if (rel(e, prim->e)) { // TElement e se afla inaintea TElementului din "prim" in relatie "rel"
        nou->urm = prim;
        prim->prec = nou;
        prim = nou;
    } else if (rel(ultim->e, e)) { // TElement e se afla dupa TElementul din "ultim" in relatia "rel"
        nou->prec = ultim;
        ultim->urm = nou;
        ultim = nou;
    } else { // TElement e se afla in mijlocul listei in relatia "rel"
        PNod p;
        for (p = prim->urm; p->urm != nullptr && rel(p->e, e); p = p->urm) {}
        PNod nod_precedent = p->prec;
        nod_precedent->urm = nou;
        nou->prec = nod_precedent;
        p->prec = nou;
        nou->urm = p;
    }
}

// returnare iterator
// Complexitate timp: ϴ(1)
Iterator LO::iterator() {
    return Iterator(*this);
}


// destructor
// Complexitate timp: ϴ(n)
LO::~LO() {
    while (prim != nullptr) {
        PNod p = prim;
        prim = prim->urm;
        delete p;
    }
}
