//
// Created by radua on 6/18/2020.
//

#include "Melodie.h"

// returneaza idul melodiei - int
int Melodie::getId() const {
    return id;
}

// modifica idul melodiei - int
void Melodie::setId(int id) {
    Melodie::id = id;
}

// returneaza titlul melodiei - string
const string &Melodie::getTitlu() const {
    return titlu;
}

// modifica titlul melodiei - string
void Melodie::setTitlu(const string &titlu) {
    Melodie::titlu = titlu;
}

// retunreaza artistul melodiei - string
const string &Melodie::getArtist() const {
    return artist;
}

// modifica artistul melodiei - string
void Melodie::setArtist(const string &artist) {
    Melodie::artist = artist;
}

// retunreaza genul melodiei - string
const string &Melodie::getGen() const {
    return gen;
}

// modifica genul melodiei - string
void Melodie::setGen(const string &gen) {
    Melodie::gen = gen;
}


void test_melodie() {
    Melodie m{ 1, "da", "da", "pop"};
    assert(m.getId() == 1);
    assert(m.getTitlu() == "da");
    assert(m.getArtist() == "da");
    assert(m.getGen() == "pop");

    m.setArtist("nu");
    m.setTitlu("nu");
    m.setGen("rock");
    m.setId(2);

    assert(m.getId() == 2);
    assert(m.getTitlu() == "nu");
    assert(m.getArtist() == "nu");
    assert(m.getGen() == "rock");
}