#ifndef MAGAZIN_FILE_REPOSITORY_H
#define MAGAZIN_FILE_REPOSITORY_H

#include "repository.h"

#include <utility>

using std::string;

class RepoMagazinFisier: public RepoMagazin {
    string nume_fisier;
    void incarca_din_fisier();
    void scrie_in_fisier();

public:
    explicit RepoMagazinFisier(string nume_fisier): RepoMagazin(), nume_fisier{std::move( nume_fisier )} {
        incarca_din_fisier();
    }

    void adauga(const Produs& produs) override {
        RepoMagazin::adauga(produs);
        scrie_in_fisier();
    }

    void sterge(const Produs& produs) override {
        RepoMagazin::sterge(produs);
        scrie_in_fisier();
    }
};

    void test_file_repository();

#endif //MAGAZIN_FILE_REPOSITORY_H
