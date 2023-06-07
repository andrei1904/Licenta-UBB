#include "../Header/dictionar.h"
#include "../Header/iterator_dictionar.h"

#include <iostream>

/*
 * Complexitate timp: ϴ(n)
 */
Dictionar::Dictionar() {
    capacitate = 10;
    prim = -1;

    e = new TElem[capacitate];
    urm = new int[capacitate];

    for (int i = 0; i < capacitate - 1; i++) { // initializare spatiu liber
        urm[i] = i + 1;
    }
    urm[capacitate - 1] = -1;

    prim_liber = 0;
}

/*
 * Complexitate timp: ϴ(1)
 */
int Dictionar::aloca() {
    int i = prim_liber;
    prim_liber = urm[prim_liber];
    return i;
}

/*
 * Complexitate timp: ϴ(1)
 */
void Dictionar::dealoca(int i) {
    urm[i] = prim_liber;
    prim_liber = i;
}

/*
 * Complexitate timp: ϴ(1)
 */
int Dictionar::creeaza_nod(TElem element) {
    if (prim_liber == -1) { // redimensioneaza daca nu mai e spatiu liber
        prim_liber = capacitate;
        redimensionare();
    }
    int i = aloca();

    e[i] = element;
    urm[i] = -1;

    return i;
}

/*
 * Complexitate timp: ϴ(n)
 */
void Dictionar::redimensionare() {
    int capacitate_aux = capacitate * 2;
    auto *e_aux = new TElem[capacitate_aux];
    auto *urm_aux = new int[capacitate_aux];

    for (int i = 0; i < capacitate; i++) { // copiaza
        e_aux[i] = e[i];
        urm_aux[i] = urm[i];
    }

    for (int i = capacitate; i < capacitate_aux - 1; i++) { // initializeaza
        urm_aux[i] = i + 1;
    }
    urm_aux[capacitate_aux - 1] = -1;

    delete[] e;
    delete[] urm;

    capacitate = capacitate_aux;
    e = e_aux;
    urm = urm_aux;
}

/*
 * Complexitate timp: ϴ(1)
 */
Dictionar::~Dictionar() {
    delete[] e;
    delete[] urm;
}

/*
 * Complexitate timp: O(n)
 */
TValoare Dictionar::adauga(TCheie c, TValoare v) {
    if (!vid()) {
        for (int i = prim; i != -1; i = urm[i]) { // verifica daca exista deja cheia
            if (e[i].first == c) {
                TValoare v_veche = e[i].second;
                e[i].second = v;
                return v_veche;
            }
        }
    }

    TElem element(c, v);
    int i = creeaza_nod(element);
    urm[i] = prim;
    prim = i;

    return NULL_TVALOARE;
}

/*
 * Complexitate timp: O(n)
 */
TValoare Dictionar::cauta(TCheie c) const {
    if (!vid()) {
        for (int i = prim; i != -1; i = urm[i]) {
            if (e[i].first == c) {
                return e[i].second;
            }
        }
    }
    return NULL_TVALOARE;
}

/*
 * Complexitate timp: O(n)
 */
TValoare Dictionar::sterge(TCheie c) {
    if (!vid()) {
        if (e[prim].first == c) { // daca e primul element
            int i = prim;
            TValoare val_veche = e[prim].second;

            prim = urm[prim];
            dealoca(i);

            return val_veche;
        }
        for (int i = prim; urm[i] != -1; i = urm[i]) {
            if (e[urm[i]].first == c) {
                TValoare val_veche = e[urm[i]].second;
                int urm_vechi = urm[i];

                urm[i] = urm[urm[i]];
                dealoca(urm_vechi);

                return val_veche;
            }
        }
    }
    return NULL_TVALOARE;
}

/*
 * Complexitate timp: ϴ(n)
 */
int Dictionar::dim() const {
    int numar = 0;
    if (!vid()) {
        for (int i = prim; i != -1; i = urm[i]) {
            numar++;
        }
    }
    return numar;
}

/*
 * Complexitate timp: ϴ(1)
 */
bool Dictionar::vid() const {
    return prim == -1;
}


IteratorDictionar Dictionar::iterator() const {
    return IteratorDictionar(*this);
}

/*
 * Complexitate timp: ϴ(n)
 */
vector<TValoare> Dictionar::colectia_valorilor() const{
    vector<TValoare> valori;

    for (int i = prim; i != -1; i = urm[i]) {
        valori.push_back(e[i].second);
    }

    return valori;
}


