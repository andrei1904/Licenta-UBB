#ifndef MAGAZIN_UI_MAGAZIN_H
#define MAGAZIN_UI_MAGAZIN_H

#include "../Domeniu/produs.h"
#include "../Bussines/service.h"

using std::vector;

class ConsolaUI {
    ServiceMagazin &srv;

    /*
     * primeste input de la utilizator si adauga produsul
     * arunca exceptie daca produsul nu este valid
     */
    void ui_adauga();

    /*
     * primeste input de la utilizator si sterge produsul
     */
    void ui_sterge();

    /*
     * cauta un produs
     */
    void ui_cauta();

    /*
     * modifica un produs
     * arunca exceptie daca acesta nu exista
     */
    void ui_modifica();

    /*
     * filstreaza produsele dupa nume, pret, producator
     */
    void ui_filtreaza();

    /*
     * sorteaza dupa nume, pret, nume + tip
     */
    void ui_sorteaza();

    /*
     * afiseaza headerul
     */
    static void ui_afiseaza_header();

    /*
     * afiseaza o lista cu produsele
     */
    static void ui_afiseaza(const string &mesaj, const std::vector<Produs> &produse);

    /*
     * citests si validateaza tipul de date al unui intreg
     */
    static int ui_citeste_intreg();

    /*
     * citests si validateaza tipul de date al unui string
     */
    static string ui_citeste_string();

    /*
     * verifica daca un string este format doar din litere
     */
    static bool valideaza_string_litere(const string& x);

    /*
     * primeste input de la utiliator si adauga produsul in cos
     */
    void ui_adauga_cos();

    /*
     * goleste cosul
     */
    void ui_goleste_cos();

    /*
     * afiseaza cosul
     */
    void ui_afiseaza_cos();

    /*
     * populeaza cosul
     */
    void ui_populeaza_cos();

    /*
     * exporta html
     */
    void ui_exporta_html();

    /*
     * undo
     */
    void ui_undo();

public:
    explicit ConsolaUI(ServiceMagazin &srv) : srv{srv} {}

    /*
     * nu permite copierea obiectelor
     */
    ConsolaUI(const ConsolaUI &ot) = delete;

    void run();
};

#endif //MAGAZIN_UI_MAGAZIN_H
