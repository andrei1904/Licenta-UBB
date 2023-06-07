#include "repository.h"

#include <ostream>
#include <cassert>
#include <algorithm>

using std::ostream;
using std::stringstream;

bool RepoMagazin::exista(const Produs &p) const {
    try {
        cauta_produs(p);
        return true;
    }
    catch (RepoMagazinException &) {
        return false;
    }
}

void RepoMagazin::adauga(const Produs &p) {
    if (exista(p)) {
        throw RepoMagazinException("Exista deja acest produs!\n");
    }
    produse.emplace(p.get_nume(), p);
}

const Produs &RepoMagazin::cauta_produs(const Produs &p) const {
//    auto rez = std::find_if(produse.begin(), produse.end(), [p](const Produs &produs) {
//        return produs.get_nume() == p.get_nume() && produs.get_tip() == p.get_tip() &&
//               produs.get_producator() == p.get_producator();
//    });
    auto rez = produse.find(p.get_nume());
    if (rez != produse.end()) {
        return rez->second;
    }
    throw RepoMagazinException("Nu exista acest produs!\n");
}

//int RepoMagazin::cauta_pozitie(const Produs &p) const {
//    auto rez = std::find_if(produse.begin(), produse.end(), [p](const Produs &produs) {
//        return produs.get_nume() == p.get_nume() && produs.get_tip() == p.get_tip() &&
//               produs.get_producator() == p.get_producator();
//    });
//
//    if (rez != produse.end()) {
//        return std::distance(produse.begin(), rez);
//    }
//    throw RepoMagazinException("Nu exista acest produs!\n");
//}

std::vector<Produs> RepoMagazin::get_all() const {
    std::vector<Produs> rez;
    for (auto const& produs : produse) {
        rez.push_back(produs.second);
    }
    return rez;
}

void RepoMagazin::sterge(const Produs &p) {
//    auto it = std::remove_if(produse.begin(), produse.end(), [p](const Produs &produs) {
//        return produs.get_nume() == p.get_nume() && produs.get_tip() == p.get_tip() &&
//               produs.get_producator() == p.get_producator();
//    });
    auto it = produse.find(p.get_nume());
    if (it != produse.end()) {
        produse.erase(it);
    } else {
        throw RepoMagazinException("Nu exista acest produs!\n");
    }
}

void RepoMagazin::modifica(const Produs &p_vechi, const Produs &p) {
    auto produs = cauta_produs(p_vechi);
    sterge(produs);
    adauga(p);
//    produse.find(produs.get_nume())->second.set_nume(p.get_nume());
//    produse.find(produs.get_nume())->second.set_tip(p.get_tip());
//    produse.find(produs.get_nume())->second.set_pret(p.get_pret());
//    produse.find(produs.get_nume())->second.set_producator(p.get_producator());
//    string nume = p.get_nume();
}



ostream &operator<<(ostream &out, const RepoMagazinException &eroare) {
    out << eroare.mesaj;
    return out;
}

void test_adauga_sterge() {
    RepoMagazin repo;

    Produs p{"da", "da", 10, "da"};
    repo.adauga(p);

    std::vector<Produs> l;

    l = repo.get_all();
    assert(l.size() == 1);

}

void test_repository() {
    test_adauga_sterge();
}
