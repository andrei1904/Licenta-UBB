//
// Created by radua on 6/18/2020.
//

#include "MelodieRepo.h"

vector<Melodie> MelodieRepo::getAll() const {
    vector<Melodie> rez;
    for (const auto& m : melodii) {
        rez.emplace_back(m);
    }

    return rez;
}

bool MelodieRepo::exista(const Melodie &melodie) {
    for (const auto& m : melodii) {
        if (m.getId() == melodie.getId()) {
            return true;
        }
    }
    return false;
}

void MelodieRepo::adauga(const Melodie &melodie) {
    if (exista(melodie)) {
        throw MelodieException("Melodia exista deja");
    }
    melodii.emplace_back(melodie);
}

void MelodieRepo::sterge(int id) {
    for (auto it = melodii.begin(); it != melodii.end(); ++it) {
        if (it->getId() == id) {
            melodii.erase(it);
        }
    }
}

void MelodieRepoFile::incarcaDinFisier() {
    std::ifstream fin(numeFisier);

    if (!fin.is_open()) {
        throw MelodieException("Eroare deschidere fisier");
    }

    while (!fin.eof()) {
        string id;
        getline(fin, id, ',');

        string titlu;
        getline(fin, titlu, ',');

        string artist;
        getline(fin, artist, ',');

        string gen;
        getline(fin, gen);

        Melodie m{stoi(id), titlu, artist, gen};
        MelodieRepo::adauga(m);
    }
    fin.close();
}

void MelodieRepoFile::scrieInFisier() {
    std::ofstream fout(numeFisier);

    if (!fout.is_open()) {
        throw MelodieException("Eroare deschidere fisier!");
    }

    bool primu = true;
    for (const auto& m : getAll()) {
        if (primu) {
            primu = false;
        } else {
            fout << std::endl;
        }
        fout << m.getId() << ',';
        fout << m.getTitlu() << ',';
        fout << m.getArtist() << ',';
        fout << m.getGen();
    }
}

void MelodieRepoFile::adauga(const Melodie &melodie) {
    MelodieRepo::adauga(melodie);
    scrieInFisier();
}

void MelodieRepoFile::sterge(int id) {
    MelodieRepo::sterge(id);
    scrieInFisier();
}



