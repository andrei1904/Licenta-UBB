//
// Created by radua on 6/18/2020.
//

#ifndef EXAMEN_MELODIECONTROLLER_H
#define EXAMEN_MELODIECONTROLLER_H

#include "MelodieRepo.h"
#include "MelodieValid.h"
#include <algorithm>
#include <random>
#include "Observer.h"

// gesioneaza operatiile cu melodii
class MelodieController : public Observabil{
private:
    MelodieRepoFile repo;
    MelodieValid valid;

public:
    // constructor
    MelodieController(MelodieRepoFile& repo, MelodieValid& valid) : repo{repo}, valid{valid} {};

    // returneaza toate melodiile
    vector<Melodie> getAll() const;

    // adauga melodie
    void adauga(int id, const string& titlu, const string& artist, const string& gen);

    // sorteaza dupa autor
    vector<Melodie> sortAutor() const;

    // genereaza un id unic pentru o melodie
    int genereazaId();

    // verifica daca idul exista
    bool exista(int id);

    // sterge melodie
    void sterge(int id);
};

void test_service();


#endif //EXAMEN_MELODIECONTROLLER_H
