//
// Created by radua on 6/18/2020.
//

#ifndef EXAMEN_MELODIEREPO_H
#define EXAMEN_MELODIEREPO_H

#include "Melodie.h"

#include <utility>
#include <vector>
#include <fstream>

using std::vector;

// gesioneaza melodiile din memorie
class MelodieRepo {
private:
    vector<Melodie> melodii;

public:
    // returneaza toate melodiile
    vector<Melodie> getAll() const;

    // verifica daca melodia exista returneaza true daca da si false daca nu
    bool exista(const Melodie& melodie);

    // adauga o melodie in memorie
    virtual void adauga(const Melodie& melodie);

    // sterge o melodie dupa id
    virtual void sterge(int id);
};

// gestioneaza melodiile din fiiser
class MelodieRepoFile : public MelodieRepo {
private:
    string numeFisier;

public:
    explicit MelodieRepoFile(string nume) : MelodieRepo(), numeFisier{std::move(nume)} {
        incarcaDinFisier();
    };

    // functie care incarca melodiile dintr-un fisier
    void incarcaDinFisier();

    // scrie in fisier lista de melodii
    void scrieInFisier();

    // adauga o melodie si in memeorie si in fisier
    void adauga(const Melodie& melodie) override ;

    // sterge o melodie si din memorie si din fisier
    void sterge(int id) override ;
};


#endif //EXAMEN_MELODIEREPO_H
