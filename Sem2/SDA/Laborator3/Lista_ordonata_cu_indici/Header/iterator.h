#pragma once

#include "lista_ordonata.h"

class LO;


class Iterator{
    friend class LO;
private:
    //constructorul primeste o referinta catre Container
    //iteratorul va referi primul element din container
    explicit Iterator(const LO& lo);

    // contine o referinta catre containerul pe care il itereaza
    const LO& lista;

    PNod curent; // pozitia curenta

public:

    // reseteaza pozitia iteratorului la inceputul containerului
    void prim();

    // muta iteratorul in container pe pozitia urmatoare
    // arunca exceptie daca iteratorul nu e valid
    void urmator();

    // verifica daca iteratorul e valid (indica un element al containerului)
    bool valid() const;

    // muta iteratorul in container pe pozitia anterioara
    // arunca exceptie daca iteratorul nu e valid
    void anterior();

    // returneaza valoarea elementului din container referit de iterator
    // arunca exceptie daca iteratorul nu e valid
    TElement element() const;
};


