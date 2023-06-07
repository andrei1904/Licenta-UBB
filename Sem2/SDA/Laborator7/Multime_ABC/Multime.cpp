#include "Multime.h"
#include "IteratorMultime.h"
#include <iostream>

using namespace std;

//o posibila relatie
bool rel(TElem e1, TElem e2) {
    return e1 <= e2;
}

/*
 * Complexitate timp: ϴ(1)
 */
Nod::Nod(TElem e, PNod st, PNod dr, PNod parinte) {
    this->e = e;
    this->st = st;
    this->dr = dr;
    this->parinte = parinte;
}

/*
 * Complexitate timp: ϴ(1)
 */
TElem Nod::get_element() {
    return e;
}

/*
 * Complexitate timp: ϴ(1)
 */
Multime::Multime() {
    radacina = nullptr;
    relatie = rel;
}

/*
 * Complexitate timp: O(h), h = inaltimea arborelui
 */
bool Multime::adauga(TElem elem) {
    try {
        radacina = adauga_recusriv(radacina, elem, radacina);
        return true;
    } catch (exception& ex) {
        return false;
    }
}

/*
 * Complexitate timp: O(h), h = inaltimea arborelui
 */
bool Multime::sterge(TElem elem) {
    try {
        radacina = sterge_recursiv(radacina, elem, radacina);
        return true;
    } catch (exception& ex) {
        return false;
    }
}

/*
 * Complexitate timp: O(h), h = inaltimea arborelui
 */
bool Multime::cauta(TElem elem) {
    return cauta_recursciv(radacina, elem) != nullptr;
}

/*
 * Complexitate timp: ϴ(n), n = numarul de noduri
 */
int Multime::dim() const {
    return dimensiune_recursiv(radacina);
}

/*
 * Complexitate timp: ϴ(1)
 */
bool Multime::vida() const {
    return radacina == nullptr;
}

/*
 * Complexitate timp: ϴ(1)
 */
IteratorMultime Multime::iterator() const {
    return IteratorMultime{*this};
}

/*
 * Complexitate timp: ϴ(n), n = numarul de noduri
 */
Multime::~Multime() {
    distruge_recursiv(radacina);
}

/*
 * Complexitate timp: O(h), h = inaltimea arborelui
 */
PNod Multime::adauga_recusriv(PNod p, TElem e, PNod precedent) {
    if (p == nullptr) {
        p = new Nod(e, nullptr, nullptr, precedent);
    } else {
        if (e == p->e) {
            throw std::exception();
        }
        if (relatie(e, p->e)) {
            p->st = adauga_recusriv(p->st, e, p);
        } else {
            p->dr = adauga_recusriv(p->dr, e, p);
        }
    }
    return p;
}

/*
 * Complexitate timp: ϴ(n), n = numarul de noduri
 */
void Multime::distruge_recursiv(PNod p) {
    if (p != nullptr) {
        distruge_recursiv(p->st);
        distruge_recursiv(p->dr);
        delete p;
    }
}

/*
 * Complexitate timp: O(h), h = inaltimea arborelui
 */
PNod Multime::cauta_recursciv(PNod p, TElem e) {
    if (p == nullptr || p->e == e) {
        return p;
    } else {
        if (relatie(e, p->e)) {
            p = cauta_recursciv(p->st, e);
        } else {
            p = cauta_recursciv(p->dr, e);
        }
    }
    return p;
}

/*
 * Complexitate timp: ϴ(n), n = numarul de noduri
 */
int Multime::dimensiune_recursiv(PNod p) {
    if (p == nullptr) {
        return 0;
    } else {
        return (dimensiune_recursiv(p->st) + dimensiune_recursiv(p->dr) + 1);
    }
}

/*
 * Complexitate timp: O(h), h = inaltimea arborelui
 */
PNod Multime::sterge_recursiv(PNod p, TElem e, PNod precedent) {
    if (p == nullptr) { // daca elementul nu exista
        throw std::exception();
    } else {
        if (e == p->e) { // am ajuns la valoarea care trebuie stearsa
            PNod temp = nullptr;
            if (p->st != nullptr && p->dr != nullptr) { // daca are elemente si in stanga si in dreapta
                temp = minim(p);
                p->e = temp->e;
                p->st = nullptr;
                p->dr = sterge_recursiv(p->dr, p->e, p);
                return p;
            } else {
                PNod repl;
                if (p->st == nullptr) {
                    repl = p->dr;
                    if (repl != nullptr)
                        repl->parinte = precedent;
                } else {
                    repl = p->st;
                    if (repl != nullptr)
                        repl->parinte = precedent;
                }
                delete temp;
                delete p;
                return repl;
            }
        } else { // nu am ajuns la valoarea care trebuie stearsa
            if (relatie(e, p->e)) {
                p->st = sterge_recursiv(p->st, e, p);
                return p;
            } else {
                p->dr = sterge_recursiv(p->dr, e, p);
                return p;
            }
        }
    }
}

/*
 * Complexitate timp: O(h), h = inaltimea arborelui
 */
PNod Multime::minim(PNod p) {
    if (p == nullptr) {
        return nullptr;
    }
    while (p->st != nullptr) {
        p = p->st;
    }
    return p;
}

/*
 * Complexitate timp: O(h), h = inaltimea arborelui
 */
PNod Multime::maxim(PNod p) {
    if (p == nullptr) {
        return nullptr;
    }
    while (p->dr != nullptr) {
        p = p->dr;
    }
    return p;
}

/*
 * Complexitate timp: O(h), h = inaltimea arborelui
 */
PNod Multime::succesor(PNod p) {
    if (p->dr != nullptr) { // minimul din dreapta este elementul urmator
        return minim(p->dr);
    }

    PNod urmator = p->parinte;
    while (urmator != nullptr && p == urmator->dr) { // pana cand este primul nod dintr-un subarbore stang
        p = urmator;
        urmator = p->parinte;
    }
    return urmator;
}

/*
 * Complexitate timp: O(h), h = inaltimea arborelui
 */
int Multime::diferentaMaxMin() const {
    PNod min = minim(radacina);
    PNod max = maxim(radacina);

    if (max == nullptr || min == nullptr) {
        return -1;
    }
    return max->e - min->e;
}
