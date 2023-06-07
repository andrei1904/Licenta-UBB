#pragma once

class Nod; // referinta la clasa Nod
class Iterator; // referinta la clasa Iterator

typedef Nod *PNod; // adresa unui Nod

typedef int TComparabil;
typedef TComparabil TElement;

typedef bool (*Relatie)(TElement, TElement);

#define NULL_TELEMENT -1


class Nod {
private:
    TElement e; // intformatia nodului, este de tip TComparabil
    PNod urm, prec; // adresa la care este memorat nodul urmator sau cel precedent

public:
    friend class LO;

    /*
     * constructor nod
     */
    Nod(TElement e, PNod urm, PNod prec);

    /*
    * returneaza un element de tip TElement
    */
    TElement element();

    /*
    * returneaza un pointer catre un Nod
    */
    PNod urmator();

    /*
    * returneaza un pointer catre un Nod
    */
    PNod precedent();
};


class LO {
private:
    friend class Iterator;
private:
    PNod prim, ultim; // referinta la primul si ultimul nod
    Relatie rel; // relatia de ordine

public:
    // constructor
    explicit LO(Relatie r);

    // returnare dimensiune
    int dim() const;

    // verifica daca LO e vida
    bool vida() const;

    // returnare element
    //arunca exceptie daca i nu e valid
    TElement element(int i) const;

    // adaugare element in LO
    void adauga(TElement e);

    // sterge element de pe o pozitie i si returneaza elementul sters
    //arunca exceptie daca i nu e valid
    TElement sterge(int i);

    // cauta element si returneaza prima pozitie pe care apare (sau -1)
    int cauta(TElement e) const;

    // returnare iterator
    Iterator iterator();

    //destructor
    ~LO();

};
