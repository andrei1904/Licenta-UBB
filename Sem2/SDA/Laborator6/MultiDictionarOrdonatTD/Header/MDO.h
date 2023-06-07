#pragma once

#define MAX 13

#include <vector>

using std::vector;

typedef int TCheie;
typedef int TValoare;

#include <utility>
typedef std::pair<TCheie, TValoare> TElem;

using namespace std;

class IteratorMDO;

class Nod;

typedef Nod* PNod;

typedef bool(*Relatie)(TCheie, TCheie);

class Nod {
private:
    TCheie cheie;
    vector<int> valori;
    PNod urm;

public:
    friend class MDO;
    friend class IteratorMDO;

    Nod(TCheie cheie, PNod urm);
};

class MDO {
    friend class IteratorMDO;
private:

    int m; // numarul de locatii din TD
    PNod l[MAX]{};
    Relatie rel; // relatia de ordine

    int d(TCheie cheie); // functia de disperise

public:

    // constructorul implicit al MultiDictionarului Ordonat
    MDO(Relatie r);

    // adauga o pereche (cheie, valoare) in MDO
    void adauga(TCheie c, TValoare v);

    //cauta o cheie si returneaza vectorul de valori asociate
    vector<TValoare> cauta(TCheie c);

    //sterge o cheie si o valoare
    //returneaza adevarat daca s-a gasit cheia si valoarea de sters
    bool sterge(TCheie c, TValoare v);

    //returneaza numarul de perechi (cheie, valoare) din MDO
    int dim() const;

    //verifica daca MultiDictionarul Ordonat e vid
    bool vid() const;

    // elimina toate elementele din multidictionar
    void goleste();

    // se returneaza iterator pe MDO
    // iteratorul va returna perechile in ordine in raport cu relatia de ordine
    IteratorMDO iterator() const;

    // destructorul
    ~MDO();

};
