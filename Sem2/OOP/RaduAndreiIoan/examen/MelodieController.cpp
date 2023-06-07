//
// Created by radua on 6/18/2020.
//

#include "MelodieController.h"

vector<Melodie> MelodieController::getAll() const {
    return repo.getAll();
}

void MelodieController::adauga(int id, const string& titlu, const string& artist, const string& gen) {
    Melodie m{id, titlu, artist, gen};
    MelodieValid::valideazaMelodie(m);
    repo.adauga(m);
    notifica();
}

vector<Melodie> MelodieController::sortAutor() const {
    vector<Melodie> rez = repo.getAll();

    std::sort(rez.begin(), rez.end(), [](const Melodie& m1, const Melodie& m2){
        return m1.getArtist() < m2.getArtist();
    });

    return rez;
}

int MelodieController::genereazaId() {
    int id = rand();
    while (exista(id)) {
        id = rand();
    }
    return id;
}

bool MelodieController::exista(int id) {
    vector<Melodie> rez = repo.getAll();

    for (const auto& m : rez) {
        if (m.getId() == id) {
            return true;
        }
    }
    return false;
}

void MelodieController::sterge(int id) {
    if (!exista(id)) {
        throw MelodieException("Nu exista idul!");
    }
    repo.sterge(id);
    notifica();
}

void test_service() {
    MelodieRepoFile repo("../test.txt");
    MelodieValid valid;
    MelodieController ctr{repo, valid};

    assert(ctr.getAll().size() == 4);

    assert(ctr.sortAutor().front().getId() == 4);

    assert(ctr.exista(4));

    int nr = ctr.genereazaId();
    for (auto x : ctr.getAll()) {
        if (nr == x.getId()) {
            assert(false);
        }
    }
//    ctr.sterge(2);
//    assert(!ctr.exista(2));
//    assert(ctr.getAll().size() == 3);
//
//    ctr.adauga(2, "bb", "b", "rock");
//    assert(ctr.getAll().size() == 4);
}