#ifndef MAGAZIN_SERVICE_H
#define MAGAZIN_SERVICE_H

#include <string>
#include <functional>
#include <memory>

#include "../Domeniu/produs.h"
#include "../Domeniu/validator.h"
#include "../Infrastructura/file_repository.h"
#include "../Domeniu/lista.h"
#include "../Domeniu/cos_produse.h"
#include "../Infrastructura/exporta.h"
#include "../Infrastructura/undo.h"
#include "../../../Lab10-11/MagazinGUI/MagazinGUI/observer.h"


using std::function;


class ServiceMagazin: public Observable {
    RepoMagazin& repo;
    ValidatorProdus& val;
    CosProdus cos;

    std::vector <std::unique_ptr <ActiuneUndo>> actiuni_undo;

public:
    ServiceMagazin(RepoMagazin& repo, ValidatorProdus& val) : repo{ repo }, val{ val } {}

    /*
     * nu permite copierea
     */
    ServiceMagazin(const ServiceMagazin& ot) = delete;

    /*
     * nu permite assignment
     */
    void operator=(const RepoMagazin& ot) = delete;

    /*
     * adauga un nou produs
     * arunca exceptie daca nu este valid
     */
    void srv_adauga_produs(const string& nume, const string& tip, int pret, const string& producator);

    /*
     * sterge un produs
     * arunca exceptie daca produsul nu exista
     */
    void srv_sterge_produs(const string& nume);

    /*
     * undo
     */
    void srv_undo();

    /*
     * cauta un produs si il returneaza
     * arunca exceptie daca produsul nu exista
     */
    const Produs& srv_cauta_produs(const string& nume);

    /*
     * modifica un produs
     * arunca exceptie daca produsul nu exista
     */
    void srv_modifica_produs(const string& nume_vechi, const string& nume, const string& tip, int pret,
        const string& producator);


    /*
     * returneaza toate animalele adaugate
     */
    std::vector<Produs> get_all() noexcept {
        return repo.get_all();
    }

    /*
     * returneaza produsele cu pret intre cele doua preturi
     */
    std::vector<Produs> srv_filtreaza_pret(int pret_min, int pret_max) const;

    /*
     * returneaza produsele cu aceeasi denumire
     */
    std::vector<Produs> srv_filtreaza_nume(const string& nume) const;

    /*
     * returneaza produsele cu aceeasi denumire
     */
    std::vector<Produs> srv_filtreaza_producator(const string& producator) const;

    /*
     * sorteaza produsele dupa nume
     */
    std::vector<Produs> srv_sortare_nume() const;

    /*
     * sorteaza produsele dupa pret
     */
    std::vector<Produs> srv_sortare_pret() const;

    /*
     * sorteaza produsele dupa nume si tip
     */
    std::vector<Produs> srv_sortare_nume_tip() const;

    /*
     * adauga un produs in cos
     */
    int srv_adauga_in_cos(const string& nume);

    /*
     * goleste cosul
     */
    int srv_goleste_cos();

    /*
     * afiseaza cosul
     */
    const std::vector<Produs>& srv_get_cos();

    /*
     * populeaza cosul
     */
    int srv_populeaza_cos(int numar_produse);

    /*
     * exporta fisier html
     */
    void srv_exporta_cos_html(const string& nume_fisier) const;
};

void test_service();

#endif //MAGAZIN_SERVICE_H
