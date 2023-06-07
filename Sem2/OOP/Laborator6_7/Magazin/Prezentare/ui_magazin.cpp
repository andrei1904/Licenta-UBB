#include "ui_magazin.h"

#include <iostream>
#include <string>
#include <limits>

using std::cout;
using std::cin;
using std::streamsize;
using std::numeric_limits;

void ConsolaUI::ui_afiseaza_header() {
    cout.width(10);
    cout << "Nume";
    cout.width(20);
    cout << "Tip";
    cout.width(20);
    cout << "Pret";
    cout.width(20);
    cout << "Producator";
    cout << std::endl;
}

void ConsolaUI::ui_afiseaza(const string &mesaj, const std::vector<Produs> &produse) {
    cout << "\n" << mesaj << "(" << produse.size() << "):\n";
    ui_afiseaza_header();
    for (const auto &produs : produse) {
        cout.width(10);
        cout << produs.get_nume();
        cout.width(20);
        cout << produs.get_tip();
        cout.width(20);
        cout << produs.get_pret();
        cout.width(20);
        cout << produs.get_producator();
        cout << std::endl;
    }
    cout << std::endl;
}

int ConsolaUI::ui_citeste_intreg() {
    int x = 0;
    while (!(cin >> x)) {
        cout << "Tip de date incorect!\nIntroduceti o noua valoare: ";
        cin.clear();
        cin.ignore(numeric_limits<streamsize>::max(), '\n');
    }
    return x;
}

bool ConsolaUI::valideaza_string_litere(const string &x) {
    for (char i : x) {
        if (!isalpha(i))
            return false;
    }
    return true;
}

string ConsolaUI::ui_citeste_string() {
    string x;
    cin >> x;
    while (!valideaza_string_litere(x)) {
        cout << "Tip de date incorect!\nIntroduceti o noua valoare: ";
        cin.clear();
        cin.ignore(numeric_limits<streamsize>::max(), '\n');
        cin >> x;
    }
    return x;
}

void ConsolaUI::ui_adauga() {
    string nume, tip, producator;
    int pret = 0;

    cout << "Introduceti numele: ";
    nume = ui_citeste_string();

    cout << "Introduceti tip: ";
    tip = ui_citeste_string();

    cout << "Introduceti pret: ";
    pret = ui_citeste_intreg();

    cout << "Introduceti producator: ";
    producator = ui_citeste_string();

    srv.srv_adauga_produs(nume, tip, pret, producator);
    cout << "Produsul a fost adaugat\n\n";
}

void ConsolaUI::ui_sterge() {
    string nume, tip, producator;

    cout << "Introduceti numele: ";
    nume = ui_citeste_string();

    srv.srv_sterge_produs(nume);
    cout << "Produsul a fost sters\n\n";
}

void ConsolaUI::ui_cauta() {
    string nume, tip, producator;

    cout << "Introduceti numele: ";
    nume = ui_citeste_string();

    Produs produs = srv.srv_cauta_produs(nume);
    cout << "Produsul este:\n";
    cout << "Nume: " << produs.get_nume() << "  Tip: " << produs.get_tip() << "  Pret: " << produs.get_pret() <<
         "  Producator: " << produs.get_producator() << "\n\n";
}

void ConsolaUI::ui_modifica() {
    string nume_vechi, tip_vechi, producator_vechi;
    string nume, tip, producator;
    int pret;

    cout << "Introduceti numele vechi: ";
    nume_vechi = ui_citeste_string();

    cout << "Introduceti numele nou: ";
    nume = ui_citeste_string();

    cout << "Introduceti tipul nou: ";
    tip = ui_citeste_string();

    cout << "Introduceti pretul nou: ";
    pret = ui_citeste_intreg();

    cout << "Introduceti producatorul nou: ";
    producator = ui_citeste_string();

    srv.srv_modifica_produs(nume_vechi, nume, tip, pret, producator);
    cout << "Produsul vechi a fost modifica in Nume: " << nume << "  Tip: " << tip << "  Pret: " << pret <<
         "  Producator: " << producator << "\n\n";
}

void ConsolaUI::ui_filtreaza() {
    string cheie;
    cout << "Introduceti cheia dupa care se filtreaza (pret, nume, producator): ";
    cheie = ui_citeste_string();

    if (cheie == "pret") {
        int pret_min = 0, pret_max = 0;

        cout << "Introduceti pretul minim: ";
        pret_min = ui_citeste_intreg();

        cout << "Introduceti pretul maxim: ";
        pret_max = ui_citeste_intreg();

        ui_afiseaza("Produse", srv.srv_filtreaza_pret(pret_min, pret_max));
    } else if (cheie == "nume"){
        string nume;

        cout << "Introduceti numele: ";
        nume = ui_citeste_string();

        ui_afiseaza("Produse", srv.srv_filtreaza_nume(nume));
    } else if (cheie == "producator") {
        string producator;

        cout << "Introduceti producatorul: ";
        producator = ui_citeste_string();

        ui_afiseaza("Produse", srv.srv_filtreaza_producator(producator));
    }
}

void ConsolaUI::ui_sorteaza() {
    string cheie;
    cout << "Introduceti cheia dupa care sa fie sortata lista(nume, pret, numetip): ";
    cheie = ui_citeste_string();

    if (cheie == "nume") {
        ui_afiseaza("Produse", srv.srv_sortare_nume());
    } else if (cheie == "pret") {
        ui_afiseaza("Produse", srv.srv_sortare_pret());
    } else if (cheie == "numetip") {
        ui_afiseaza("Produse", srv.srv_sortare_nume_tip());
    }
}

void ConsolaUI::ui_adauga_cos() {
    string nume;

    cout << "Introduceti numele: ";
    nume = ui_citeste_string();

    int pret = srv.srv_adauga_in_cos(nume);
    cout << "Produsul a fost adaugat in cos!\n";
    cout << "Pretul total al produselor din cos este: " << pret << "\n\n";
}

void ConsolaUI::ui_goleste_cos() {
    int pret = srv.srv_goleste_cos();
    cout << "Cosul a fost golit!\n";
    cout << "Pretul total al produselor din cos este: " << pret << "\n\n";
}

void ConsolaUI::ui_afiseaza_cos() {
    ui_afiseaza("Cos", srv.srv_get_cos());
}

void ConsolaUI::ui_populeaza_cos() {
    cout << "Introduceti numrul de produse: ";
    int numar_produse = ui_citeste_intreg();

    int pret = srv.srv_populeaza_cos(numar_produse);
    cout << "Pretul total al produselor din cos este: " << pret << "\n\n";
}

void ConsolaUI::ui_exporta_html() {
    cout << "Introduceti numele fisierului: ";
    string nume_fisier;
    cin >> nume_fisier;

    srv.srv_exporta_cos_html(nume_fisier);
}

void ConsolaUI::ui_undo() {
    srv.srv_undo();
    cout << "Undo efectuat!\n\n";
}

void ConsolaUI::run() {
    bool ruleaza = true;
    while (ruleaza) {
        cout << "Meniu: \n";
        cout << "0: Exit \n1: Adauga  |  2: Sterge  |  3: Modifica  |  4: Afiseaza \n";
        cout << "5: Cauta  |  6: Filtrare  |  7: Sortare\n";
        cout << "8: Adauga in cos  |  9: Goleste cosul  |  10: Afiseaza cosul  |  11: Populeaza cos  |  12: Exporta\n";
        cout << "13: Undo\n";

        int cmd = 0;
        cout << "Intoruceti comanda: ";
        cmd = ui_citeste_intreg();

        try {
            switch (cmd) {
                case 1:
                    ui_adauga();
                    break;
                case 2:
                    ui_sterge();
                    break;
                case 3:
                    ui_modifica();
                    break;
                case 4:
                    ui_afiseaza("Produse", srv.get_all());
                    break;
                case 5:
                    ui_cauta();
                    break;
                case 6:
                    ui_filtreaza();
                    break;
                case 7:
                    ui_sorteaza();
                    break;
                case 8:
                    ui_adauga_cos();
                    break;
                case 9:
                    ui_goleste_cos();
                    break;
                case 10:
                    ui_afiseaza_cos();
                    break;
                case 11:
                    ui_populeaza_cos();
                    break;
                case 12:
                    ui_exporta_html();
                    break;
                case 13:
                    ui_undo();
                    break;
                case 0:
                    ruleaza = false;
                    break;
                default:
                    cout << "Comanda invalida!\n";
            }
        }
        catch (const RepoMagazinException &ex) {
            cout << ex << "\n";
        }
        catch (const ValidatorException &ex) {
            cout << ex << "\n";
        }
    }
}

