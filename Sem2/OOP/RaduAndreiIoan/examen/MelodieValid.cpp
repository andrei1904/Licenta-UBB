//
// Created by radua on 6/18/2020.
//

#include "MelodieValid.h"

void MelodieValid::valideazaMelodie(const Melodie &m) {
    if (m.getId() < 1) {
        throw MelodieException("ID invalid!");
    }
    if (m.getTitlu().empty()) {
        throw MelodieException("Titlu invalid!");
    }
    if (m.getArtist().empty()) {
        throw MelodieException("Artist invalid!");
    }
    if(m.getGen() != "rock" && m.getGen() != "pop" && m.getGen() != "folk" && m.getGen() != "disco") {
        throw MelodieException("Gen invalid!");
    }
}

void test_valid() {
    MelodieValid v;

    Melodie m{ -1, "da", "da", "pop"};
    try {
        v.valideazaMelodie(m);
        assert(false);
    } catch (const MelodieException& ex) {
        assert(true);
    }

    Melodie m1{ 1, "", "da", "pop"};
    try {
        v.valideazaMelodie(m);
        assert(false);
    } catch (const MelodieException& ex) {
        assert(true);
    }

    Melodie m2{ 2, "da", "", "pop"};
    try {
        v.valideazaMelodie(m);
        assert(false);
    } catch (const MelodieException& ex) {
        assert(true);
    }

    Melodie m3{ 3, "da", "da", "sdadas"};
    try {
        v.valideazaMelodie(m);
        assert(false);
    } catch (const MelodieException& ex) {
        assert(true);
    }
}