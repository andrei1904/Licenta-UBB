#pragma once


#define NULL_TVALOARE -1

typedef int TCheie;
typedef int TValoare;

class IteratorDictionar;

#include <utility>
#include <vector>

using std::vector;

typedef std::pair<TCheie,TValoare> TElem;

class Dictionar {
    friend class IteratorDictionar;

private:
    int capacitate; // capacitatea listei
    int prim; // primul element
    int prim_liber; // primul element din spatiul liber

    TElem *e; // elementele
    int *urm; // legaturi

    // returneaza pozitia unui spatiu liber in lista
    int aloca();

    // dealoca spatiul de indice i
    void dealoca(int i);

    // creeaza nod
    int creeaza_nod(TElem element);

    // mareste capacitatea
    void redimensionare();

public:

    // constructorul implicit al dictionarului
    Dictionar();

    // adauga o pereche (cheie, valoare) in dictionar
    //daca exista deja cheia in dictionar, inlocuieste valoarea asociata cheii si returneaza vechea valoare
    // daca nu exista cheia, adauga perechea si returneaza null: NULL_TVALOARE
    TValoare adauga(TCheie c, TValoare v);

    //cauta o cheie si returneaza valoarea asociata (daca dictionarul contine cheia) sau null: NULL_TVALOARE
    TValoare cauta(TCheie c) const;

    //sterge o cheie si returneaza valoarea asociata (daca exista) sau null: NULL_TVALOARE
    TValoare sterge(TCheie c);

    //returneaza numarul de perechi (cheie, valoare) din dictionar
    int dim() const;

    //verifica daca dictionarul e vid
    bool vid() const;

    // se returneaza iterator pe dictionar
    IteratorDictionar iterator() const;

    // returneaza un vector cu toate valorile dictionarului
    vector<TValoare> colectia_valorilor() const;

    // destructorul dictionarului
    ~Dictionar();

};


