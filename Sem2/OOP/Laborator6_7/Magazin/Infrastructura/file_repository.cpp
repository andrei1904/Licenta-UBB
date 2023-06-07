#include "file_repository.h"
#include "../Domeniu/produs.h"

#include <fstream>
#include <cassert>

void RepoMagazinFisier::incarca_din_fisier() {
    std::ifstream fin(nume_fisier);

    if (!fin.is_open()) { // verif daca fisierul este deschis
        throw RepoMagazinException("Fisierul: " + nume_fisier + " nu a putut sa fie deschis!\n");
    }

    while (!fin.eof()) {
        string nume;
        fin >> nume;

        string tip;
        fin >> tip;

        int pret = 0;
        fin >> pret;

        if (fin.eof()) {
            break;
        }

        string producator;
        fin >> producator;

        Produs produs{ nume, tip, pret, producator};
        RepoMagazin::adauga(produs);
    }
    fin.close();
}

void RepoMagazinFisier::scrie_in_fisier() {
    std::ofstream fout(nume_fisier);

    if (!fout.is_open()) {
        throw RepoMagazinException("Fisierul: " + nume_fisier + " nu a putut sa fie deschis!\n");
    }

    for (auto &produs : get_all()) {
        fout << produs.get_nume();
        fout << std::endl;
        fout << produs.get_tip();
        fout << std::endl;
        fout << produs.get_pret();
        fout << std::endl;
        fout << produs.get_producator();
        fout << std::endl;
    }
    fout.close();
}

void test_adauga() {
    RepoMagazinFisier repo("test.txt");

    Produs p{"da", "da", 10, "da"};
    repo.adauga(p);

    std::vector<Produs> l;

    l = repo.get_all();
    assert(l.size() == 1);

    repo.sterge(p);
    l = repo.get_all();

    assert(l.empty());
}

void test_file_repository() {
    test_adauga();

}
