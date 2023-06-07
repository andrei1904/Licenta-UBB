#pragma once

typedef int TElem;

typedef bool(*Relatie)(TElem, TElem);

//in implementarea operatiilor se va folosi functia (relatia) rel (de ex, pentru <=)
// va fi declarata in .h si implementata in .cpp ca functie externa colectiei
bool rel(TElem, TElem);

class Nod;

class Multime;

typedef Nod *PNod;

class Nod {

    friend class Multime;

public:

    Nod(TElem e, PNod st, PNod dr, PNod parinte);

    TElem get_element();

private:
    TElem e;
    PNod st, dr, parinte;
};

class IteratorMultime;

class Multime {

    friend class IteratorMultime;

private:

    PNod radacina;
    Relatie relatie;

    PNod adauga_recusriv(PNod p, TElem e, PNod precedent);

    static void distruge_recursiv(PNod p);

    PNod cauta_recursciv(PNod p, TElem e);

    static int dimensiune_recursiv(PNod p);

    PNod sterge_recursiv(PNod p, TElem e, PNod precedent);

    static PNod minim(PNod p);

    static PNod succesor(PNod p);

    static PNod maxim(PNod p);

public:
    //constructorul implicit
    Multime();

    //adauga un element in multime
    //returneaza adevarat daca elementul a fost adaugat (nu exista deja in multime)
    bool adauga(TElem e);

    //sterge un element din multime
    //returneaza adevarat daca elementul a existat si a fost sters
    bool sterge(TElem e);

    //verifica daca un element se afla in multime
    bool cauta(TElem elem);

    //intoarce numarul de elemente din multime;
    int dim() const;

    //verifica daca multimea e vida;
    bool vida() const;

    int diferentaMaxMin() const;

    //returneaza un iterator pe multime
    IteratorMultime iterator() const;

    // destructorul multimii
    ~Multime();

};


