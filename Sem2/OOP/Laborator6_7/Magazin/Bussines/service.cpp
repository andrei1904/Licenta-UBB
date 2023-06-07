#include "service.h"

#include <cassert>
#include <sstream>
#include <vector>
#include <algorithm>
#include <numeric>

/*
* adauga un nou produs
* arunca exceptie daca nu este valid
*/
void ServiceMagazin::srv_adauga_produs(const string &nume, const string &tip, int pret, const string &producator) {
    Produs p{nume, tip, pret, producator};
    val.valideaza(p);
    repo.adauga(p);

    actiuni_undo.push_back(std::make_unique<UndoAdauga>(repo, p));
}

void ServiceMagazin::srv_sterge_produs(const string &nume) {
    Produs p{nume, "0", 0, "0"};
    val.valideaza(p);
    repo.sterge(p);

    actiuni_undo.push_back(std::make_unique<UndoSterge>(repo, p));
}

void ServiceMagazin::srv_undo() {
    if (actiuni_undo.empty()) {
        throw RepoMagazinException{"Nu se mai poate efectua undo!\n"};
    }
    actiuni_undo.back()->do_undo();
    actiuni_undo.pop_back();

}

const Produs &ServiceMagazin::srv_cauta_produs(const string &nume) {
    Produs p{nume, "0", 0, "0"};
    val.valideaza(p);
    return repo.cauta_produs(p);
}

void ServiceMagazin::srv_modifica_produs(const string &nume_vechi, const string &nume, const string &tip, int pret,
                                         const string &producator) {
    Produs p_vechi{nume_vechi, "0", 0, "0"};
    Produs p{nume, tip, pret, producator};

    val.valideaza(p_vechi);
    val.valideaza(p);

    p_vechi = repo.cauta_produs(p_vechi);
    repo.modifica(p_vechi, p);

    actiuni_undo.push_back(std::make_unique<UndoModifica>(repo, p_vechi, p));
}

std::vector<Produs> ServiceMagazin::srv_filtreaza_pret(int pret_min, int pret_max) const {
    std::vector<Produs> rez;
    auto toate = repo.get_all();

    std::copy_if(toate.begin(), toate.end(), back_inserter(rez), [pret_min, pret_max](const Produs &produs) {
        return produs.get_pret() >= pret_min && produs.get_pret() <= pret_max;
    });

    return rez;
}

std::vector<Produs> ServiceMagazin::srv_filtreaza_nume(const string &nume) const {
    std::vector<Produs> rez;
    auto toate = repo.get_all();

    std::copy_if(toate.begin(), toate.end(), back_inserter(rez), [nume](const Produs &produs) {
        return produs.get_nume() == nume;
    });

    return rez;
}

std::vector<Produs> ServiceMagazin::srv_filtreaza_producator(const string &producator) const {
    std::vector<Produs> rez;
    auto toate = repo.get_all();

    std::copy_if(toate.begin(), toate.end(), back_inserter(rez), [producator](const Produs &produs) {
        return produs.get_producator() == producator;
    });

    return rez;
}

std::vector<Produs> ServiceMagazin::srv_sortare_nume() const {
    auto rez = repo.get_all();

    std::sort(rez.begin(), rez.end(), [](const Produs &produs1, const Produs &produs2) {
        return produs1.get_nume() < produs2.get_nume();
    });

    return rez;
}

std::vector<Produs> ServiceMagazin::srv_sortare_pret() const {
    auto rez = repo.get_all();

    std::sort(rez.begin(), rez.end(), [](const Produs &produs1, const Produs &produs2) {
        return produs1.get_pret() < produs2.get_pret();
    });

    return rez;
}

std::vector<Produs> ServiceMagazin::srv_sortare_nume_tip() const {
    auto rez = repo.get_all();

    std::sort(rez.begin(), rez.end(), [](const Produs &produs1, const Produs &produs2) {
        if (produs1.get_nume() == produs2.get_nume()) {
            return produs1.get_tip() < produs2.get_tip();
        }
        return produs1.get_nume() < produs2.get_nume();
    });

    return rez;
}

int ServiceMagazin::srv_adauga_in_cos(const string &nume) {
    Produs p{nume, "0", 0, "0"};
    val.valideaza(p);
    const Produs &produs = repo.cauta_produs(p);
    
    int rez = cos.adauga(produs);
    notify();
    return rez;
}

int ServiceMagazin::srv_goleste_cos() {
    int rez = cos.goleste();
    notify();
    return rez;
}

const std::vector<Produs>& ServiceMagazin::srv_get_cos() {
    return cos.get_cos();
}

int ServiceMagazin::srv_populeaza_cos(int numar_produse) {
    int rez = cos.umple(numar_produse, repo.get_all());
    notify();
    return rez;
}

void ServiceMagazin::srv_exporta_cos_html(const string& nume_fisier) const {
    exporta_html(nume_fisier, cos.get_cos());
}


void test_srv_adauga() {
    RepoMagazin repo;
    ValidatorProdus val;
    ServiceMagazin srv{repo, val};

    srv.srv_adauga_produs("da", "da", 10, "da");

    try {
        srv.srv_adauga_produs("da", "da", 10, "da"), assert(false);
    }
    catch (const RepoMagazinException &erori) {
        std::stringstream sout;
        sout << erori;
        auto mesaj = sout.str();
        assert(mesaj.find("Exista") >= 0);
    }
}

void test_srv_sterge() {
    RepoMagazin repo;
    ValidatorProdus val;
    ServiceMagazin srv{repo, val};

    srv.srv_adauga_produs("da", "da", 10, "da");
    srv.srv_adauga_produs("nu", "nu", 20, "nu");

    srv.srv_sterge_produs("nu");

    try {
        srv.srv_sterge_produs("nu"), assert(false);
    }
    catch (const RepoMagazinException &erori) {
        std::stringstream sout;
        sout << erori;
        auto mesaj = sout.str();
        assert(mesaj.find("Nu exista") >= 0);
    }
}

void test_srv_cauta() {
    RepoMagazin repo;
    ValidatorProdus val;
    ServiceMagazin srv{repo, val};

    srv.srv_adauga_produs("da", "da", 10, "da");

    Produs p = srv.srv_cauta_produs("da");
    assert(p.get_nume() == "da");
    assert(p.get_pret() == 10);

    try {
        srv.srv_cauta_produs("nu"), assert(false);
    }
    catch (const RepoMagazinException &erori) {

        std::stringstream sout;
        sout << erori;
        auto mesaj = sout.str();
        assert(mesaj.find("Nu exista") >= 0);
    }
}

void test_srv_modifica() {
    RepoMagazin repo;
    ValidatorProdus val;
    ServiceMagazin srv{repo, val};

    srv.srv_adauga_produs("da", "da", 10, "da");
    srv.srv_modifica_produs("da", "nu", "nu", 20, "nu");
    Produs p = srv.srv_cauta_produs("nu");
    assert(p.get_pret() == 20);
}

void test_filtreaza() {
    RepoMagazin repo;
    ValidatorProdus val;
    ServiceMagazin srv{repo, val};

    srv.srv_adauga_produs("da", "da", 10, "da");
    srv.srv_adauga_produs("nu", "nu", 20, "l");
    srv.srv_adauga_produs("l", "nu", 5, "l");

    assert(srv.srv_filtreaza_nume("da").size() == 1);
    assert(srv.srv_filtreaza_nume("nu").size() == 1);
    assert(srv.srv_filtreaza_nume("l").size() == 1);

    assert(srv.srv_filtreaza_producator("da").size() == 1);
    assert(srv.srv_filtreaza_producator("l").size() == 2);

    assert(srv.srv_filtreaza_pret(5, 30).size() == 3);
    assert(srv.srv_filtreaza_pret(6, 19).size() == 1);
}

void test_sorteaza() {
    RepoMagazin repo;
    ValidatorProdus val;
    ServiceMagazin srv{repo, val};

    srv.srv_adauga_produs("da", "da", 10, "da");
    srv.srv_adauga_produs("nu", "nu", 20, "l");
    srv.srv_adauga_produs("m", "nu", 5, "l");

    auto primul_produs = srv.srv_sortare_nume().front();
    assert(primul_produs.get_tip() == "da");

    primul_produs = srv.srv_sortare_pret().front();
    assert(primul_produs.get_pret() == 5);

    primul_produs = srv.srv_sortare_nume_tip().front();
    assert(primul_produs.get_tip() == "da");
}

void test_returneaza_toate() {
    RepoMagazin repo;
    ValidatorProdus val;
    ServiceMagazin srv{repo, val};

    srv.srv_adauga_produs("da", "da", 10, "da");
    srv.srv_adauga_produs("nu", "nu", 20, "l");
    srv.srv_adauga_produs("m", "nu", 5, "l");

    std::vector<Produs> l = srv.get_all();
    assert(l.size() == 3);
}

void test_cos() {
    RepoMagazin repo;
    ValidatorProdus val;
    ServiceMagazin srv{repo, val};

    srv.srv_adauga_produs("da", "da", 10, "da");
    assert(srv.srv_adauga_in_cos("da") == 10);

    assert(srv.srv_goleste_cos() == 0);
    srv.srv_get_cos();

    assert(srv.srv_populeaza_cos(2) == 20);
    srv.srv_exporta_cos_html("test.html");
}

void test_undo() {
    RepoMagazin repo;
    ValidatorProdus val;
    ServiceMagazin srv{repo, val};

    srv.srv_adauga_produs("da", "da", 10, "da");
    srv.srv_undo();

    assert(srv.get_all().empty());

    srv.srv_adauga_produs("da", "da", 10, "da");
    srv.srv_sterge_produs("da");
    srv.srv_undo();

    assert(srv.get_all().size() == 1);

    srv.srv_modifica_produs("da", "nu", "nu", 4, "nu");
    srv.srv_undo();

    assert(srv.get_all().size() == 1);
}

void test_service() {
    test_srv_adauga();
    test_srv_sterge();
    test_srv_cauta();
    test_srv_modifica();
    test_filtreaza();
    test_sorteaza();
    test_returneaza_toate();
    test_cos();
    test_undo();
}


