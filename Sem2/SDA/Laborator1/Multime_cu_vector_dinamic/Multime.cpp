#include "Header/Multime.h"
#include "Header/IteratorMultime.h"

#include <iostream>

Multime::Multime() {
    capacitate = 10;
    dimensiune = 0;
    vector = new TElem[10];
}

/*
 *  Complexitate timp: O(n)
 */
bool Multime::adauga(TElem elem) {
    for (int i = 0; i < dimensiune; i++) {
        if (vector[i] == elem) {
            return false;
        }
    }

    if (dimensiune == capacitate) {
        redim();
    }

    vector[dimensiune++] = elem;
    return true;
}

/*
 * Complexitate timp: ϴ(n)
 */
bool Multime::sterge(TElem elem) {
    for (int i = 0; i < dimensiune; i++) {
        if (vector[i] == elem) {
            for (int j = i + 1; j < dimensiune; j++)
                vector[j - 1] = vector[j];
            dimensiune--;
            return true;
        }
    }
    return false;
}

/*
 *  Complexitate timp: O(n)
 */
bool Multime::cauta(TElem elem) const {
    for (int i = 0; i < dimensiune; i++) {
        if (vector[i] == elem)
            return true;
    }
    return false;
}

/*
 * Complexitate timp: ϴ(1)
 */
int Multime::dim() const {
    return dimensiune;
}

/*
 * Complexitate timp: ϴ(1)
 */
bool Multime::vida() const {
    if (dimensiune == 0)
        return true;
    return false;
}

/*
 * Complexitate timp: ϴ(n), Tn = 2n
 */
void Multime::redim() {
    auto *vector_aux = new TElem[capacitate];
    for (int i = 0; i < dimensiune; i++) {
        vector_aux[i] = vector[i];
    }

    capacitate *= 2;
    vector = new TElem[capacitate];

    for (int i = 0; i < dimensiune; i++) {
        vector[i] = vector_aux[i];
    }
    delete vector_aux;
}

/*
 * Complecitate timp: ϴ(1)
 */
Multime::~Multime() {
    delete vector;
}

/*
 * Complexitate timp: ϴ(1)
 */
IteratorMultime Multime::iterator() const {
    return IteratorMultime(*this);
}

