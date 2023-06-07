#ifndef MAGAZIN_LISTA_H
#define MAGAZIN_LISTA_H

#include <utility>
#include "produs.h"

// lista reprezenata prin lista simplu inlantuita

template<typename T>
struct Nod {
    T element;
    Nod *urmator;

    explicit Nod(T element) : element{element}, urmator{nullptr} {};

    Nod(T element, Nod *urmator) : element{element}, urmator{urmator} {};
};

template<typename T>
class IteratorLista;

template<typename T>
class Lista {
private:
    Nod<T> *prim; // primul element din lista

    // dealoca nodurile
    void elibereaza_noduri();

    // copie a listei inlantuite
    Nod<T> *copiaza(Nod<T> *curent);

public:
    // constructor
    Lista() : prim{nullptr} {};

    // constructor de copiere
    Lista(const Lista &ot);

    // pentru operatorul de asignare
    void operator=(const Lista &ot);

    // destructor
    ~Lista();

    // returneaza numarul de elemente
    size_t dimensiune() const;

    // adauga la inceputul listei
//    void adauga_inceput(T element);

    // adauga la final
    void adauga_final(T element);

    // sterge un element de pe o anumita pozitie
    void sterge(int pozitie);

    // returneaza primul element
    T &primul() const;

    // returneaza un iterator pozitionat pe primul element
    IteratorLista<T> begin() const;

    // returneaza un iterator pozitionat pe ultimul element
    IteratorLista<T> end() const;

    // acces dupa pozitie
    T &operator[](size_t poz);

};

template<typename T>
Lista<T>::Lista(const Lista &ot) {
    // creeaza si copiaza din ot
    prim = copiaza(ot.prim);
};

template<typename T>
void Lista<T>::operator=(const Lista &ot) {
    // elibereaza nodurile existente
    elibereaza_noduri();
    // copiaza din ot in this
    prim = copiaza(ot.prim);
}

template<typename T>
Lista<T>::~Lista() {
    elibereaza_noduri();
}

template<typename T>
Nod<T> *Lista<T>::copiaza(Nod<T> *curent) {
    if (curent == nullptr) {
        return nullptr;
    }
    auto nod = new Nod<T>{curent->element};
    nod->urmator = copiaza(curent->urmator);
    return nod;
}

//template<typename T>
//void Lista<T>::adauga_inceput(T element) {
//    Nod<T> *nod = new Nod<T>{element, prim};
//    prim = nod;
//}

template<typename T>
void Lista<T>::adauga_final(T element) {
    auto nod_curent = prim;

    while (nod_curent != nullptr && nod_curent->urmator != nullptr) { // merg pe ultimul element
        nod_curent = nod_curent->urmator;
    }

    if (nod_curent == nullptr) { // lista e vida
        prim = new Nod<T>{element, nullptr};
    } else { // lista nu e vida
        nod_curent->urmator = new Nod<T>{element, nullptr};
    }
}

template <typename T>
void Lista<T>::sterge(int pozitie) {
    if (prim == nullptr) { // daca lista e vida
        return;
    }

    auto nod_curent = prim;

    if (pozitie == 0 && nod_curent->urmator == nullptr) { // daca trebuie sters primul element si ramane o lista vida
        prim = nullptr;
        return;
    }

    if (pozitie == 0) { // daca trebuie sters primul element intr o lista cu mai multe elemente
        auto aux = prim;
        prim = nod_curent->urmator;
        delete aux;
        return;
    }

    // daca trebuie sters un element din mijlocul listei
    for (int pozitie_curenta = 0; pozitie_curenta != pozitie - 1 && nod_curent != nullptr &&
        nod_curent->urmator != nullptr; pozitie_curenta++) {
        nod_curent = nod_curent->urmator;
    }
    if (nod_curent != nullptr) {
        auto aux = nod_curent->urmator;
        nod_curent->urmator = aux->urmator;
        delete aux;
    }
}

template<typename T>
size_t Lista<T>::dimensiune() const {
    auto nod_curent = prim;
    size_t lungime = 0;

    while (nod_curent != nullptr) {
        lungime++;
        nod_curent = nod_curent->urmator;
    }

    return lungime;
}

template<typename T>
T &Lista<T>::operator[](size_t pozitie) {
    auto nod_curent = prim;
    int lungime = 0;

    while (lungime < pozitie) {
        lungime++;
        nod_curent = nod_curent->urmator;
    }

    return nod_curent->element;
}

template<typename T>
T &Lista<T>::primul() const {
    return prim->element;
}

template<typename T>
void Lista<T>::elibereaza_noduri() {
    auto nod_curent = prim;

    while (nod_curent != nullptr) {
        auto aux = nod_curent->urmator;
        delete nod_curent;
        nod_curent = aux;
    }
    prim = nullptr;
}

// iterator
template<typename T>
class IteratorLista {
private:
    Nod<T> *curent;
public:
    // constructor
    IteratorLista(Nod<T>* curent) :curent{ curent } {};

    // operator != pentru range for
    bool operator!=(const IteratorLista& ot);

    // urmatorul element in iteratire, operator ++
    void operator++();

    // returneaza elementul curent din iteratie, operator *
    T& operator*();
};

template <typename T>
IteratorLista<T> Lista<T>::begin() const {
    return IteratorLista<T>{ prim };
}

template <typename T>
IteratorLista<T> Lista<T>::end() const {
    return IteratorLista<T>{ nullptr };
}

template <typename T>
bool IteratorLista<T>::operator!=(const IteratorLista& ot) {
    return curent != ot.curent;
}

template <typename T>
void IteratorLista<T>::operator++() {
    curent = curent->urmator;
}

template <typename T>
T& IteratorLista<T>::operator*() {
    return curent->element;
}

#endif //MAGAZIN_LISTA_H
