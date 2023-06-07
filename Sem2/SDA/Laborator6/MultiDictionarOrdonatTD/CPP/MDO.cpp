#include "../Header/IteratorMDO.h"
#include "../Header/MDO.h"

#include <iostream>
#include <vector>
#include <cmath>

#include <exception>

using namespace std;

/*
 * Complexitate timp: ϴ(1)
 */
int hash_code(TCheie cheie) {
    return abs(cheie);
}

/*
 * Complexitate timp: ϴ(1)
 */
Nod::Nod(TCheie cheie, PNod urm) {
    this->urm = urm;
    this->cheie = cheie;
}

/*
 * Complexitate timp: ϴ(1)
 */
int MDO::d(TCheie cheie) {
    return hash_code(cheie) % m;
}

/*
 * Complexitate timp: ϴ(n)
 */
MDO::MDO(Relatie r) {
    m = MAX;
    for (int i = 0; i < m; i++) {
        l[i] = nullptr;
    }
    rel = r;
}

/*
 * Complexitate timp: O(n)
 */
void MDO::adauga(TCheie c, TValoare v) {
    int i = d(c);

    if (l[i] == nullptr) { // daca nu mai este o cheie la acest indice
        PNod nod = new Nod(c, nullptr);
        nod->valori.push_back(v);
        l[i] = nod;
        return;
    }

    if (!rel(l[i]->cheie, c)) { // cheia se afla inaintea primei chei in relatie
        PNod nod = new Nod(c, l[i]);
        nod->valori.push_back(v);
        l[i] = nod;
        return;
    }

    PNod p;
    for (p = l[i]; p != nullptr; p = p->urm) { // daca mai exista cheie la acest indice
        if (p->cheie == c) { // cheia exista deja
            p->valori.push_back(v);
            return;
        }

        if (p->urm != nullptr) { // daca nu exista cheia si exista un mai mult de una in lista
            if (!rel(p->urm->cheie, c)) {
                PNod nod = new Nod(c, p->urm);
                p->urm = nod;
                nod->valori.push_back(v);
                return;
            }
        } else { // daca nu exista cheia si exista doar una in lista
            if (rel(p->cheie, c)) {
                PNod nod = new Nod(c, nullptr);
                p->urm = nod;
                nod->valori.push_back(v);
                return;
            }
        }
    }
}

/*
 * Complexitate timp: O(n)
 */
vector<TValoare> MDO::cauta(TCheie c) {
    int i = d(c);

    for (PNod p = l[i]; p != nullptr && rel(p->cheie, c); p = p->urm) {
        if (p->cheie == c) {
            return p->valori;
        }
    }

    return vector<TValoare>();
}

/*
 * Complexitate timp: O(n)
 */
bool MDO::sterge(TCheie c, TValoare v) {
    int i = d(c);

    for (PNod p = l[i]; p != nullptr && rel(p->cheie, c); p = p->urm) { // parcurg cheile
        if (p->cheie == c) {
            for (auto it = p->valori.begin(); it != p->valori.end(); ++it) { // parcurg valorile
                if (*it == v) {
                    p->valori.erase(it);
                    if (p->valori.empty()) { // daca nu mai exista alte valori atribuite cheii
                        PNod nod;
                        if (l[i] == p) { //  daca nodul ce trebuie sters este pe prima pozitie
                            l[i] = l[i]->urm;
                            delete p;
                        } else {
                            for (nod = l[i]; nod->urm != p; nod = nod->urm);
                            nod->urm = p->urm;
                            delete p;
                        }
                    }
                    return true;
                }
            }
        }
    }

    return false;
}

/*
 * Complexitate timp: O(n*n)
 */
int MDO::dim() const {
    int dimensiune = 0;

    for (int i = 0; i < m; i++) { // parcurg lista
        if (l[i] != nullptr) {
            for (PNod p = l[i]; p != nullptr; p = p->urm) {
                dimensiune += p->valori.size();
            }
        }
    }
    return dimensiune;
}

/*
 * Complexitate timp: O(n)
 */
bool MDO::vid() const {
    for (int i = 0; i < m; i++) { // parcug lista
        if (l[i] != nullptr) {
            return false;
        }
    }
    return true;
}

/*
 * Complexitate timp: tETA(n)
 */
void MDO::goleste() {
    for (int i = 0; i < m; i++) {
        while (l[i] != nullptr) {
            PNod p = l[i];
            l[i] = l[i] -> urm;
            delete p;
        }
    }
}

IteratorMDO MDO::iterator() const {
    return IteratorMDO(*this);
}

/*
 * Complexitate timp: O(n*n)
 */
MDO::~MDO() {
    for (int i = 0; i < m; i++) {
        while (l[i] != nullptr) {
            PNod p = l[i];
            l[i] = l[i]->urm;
            delete p;
        }
    }
}
