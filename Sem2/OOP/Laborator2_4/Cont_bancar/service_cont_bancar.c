#include "service_cont_bancar.h"
#include "sortare.h"

#include <string.h>
#include <stdlib.h>
#include <assert.h>

/*
 * creeaza un cont bancar
 */
ContBancar creeaza_cont_bancar() {
    ContBancar cont;
    cont.tranzactii = creeaza_lista();
    cont.lista_undo = creeaza_lista();
    return cont;
}

/*
 * elibereaza memoria ocupata de un cont bancar
 */
void distruge_cont_bancar(ContBancar *cont) {
    distruge_lista(cont->tranzactii, (FunctieDistruge) distruge_tranzactie);
    distruge_lista(cont->lista_undo, (FunctieDistruge) distruge_lista_tranzactie);
}

/*
 * valideaza numar tranzactie (daca o tranzactie exista sau nu in contul bancar)
 */
int valideaza_numar_tranzactie(ContBancar *cb, int numar_tranzactie) {
    if (dimensiune(cb->tranzactii) <= numar_tranzactie)
        return 2;
    return 0;
}

/*
 * adauga o tranzactie unui cont bancar
 */
int srv_adauga_tranzactie(ContBancar *cb, int zi, double suma, char *tip, char *descriere) {
    Tranzactie *tr = creeaza_tranzactie(zi, suma, tip, descriere); // se creeaza o tranzactie

    int eroare = valideaza_tranzactie(tr); // se valideaza tranzactia
    if (eroare) {
        distruge_tranzactie(tr);
        return eroare;
    }

    adauga(cb->lista_undo, copiaza_lista(cb->tranzactii));
    adauga(cb->tranzactii, tr);
    return 0; // a fost adaugata cu succes
}

/*
 * modifica o tranzactie a unui cont bancar
 */
int srv_modifica_tranzactie(ContBancar *cb, int numar_tranzactie, int zi_noua, double suma_noua, char *tip_nou,
                            char *descriere_noua) {
    int eroare = valideaza_numar_tranzactie(cb, numar_tranzactie); // verifica daca tranzactia exista
    if (eroare)
        return eroare;

    Tranzactie *tr_noua = creeaza_tranzactie(zi_noua, suma_noua, tip_nou, descriere_noua);

    eroare = valideaza_tranzactie(tr_noua); // se valideaza tranzactia
    if (eroare) {
        distruge_tranzactie(tr_noua);
        return eroare;
    }

    adauga(cb->lista_undo, copiaza_lista(cb->tranzactii));
    distruge_tranzactie(cb->tranzactii->elemente[numar_tranzactie]);
    set(cb->tranzactii, numar_tranzactie, tr_noua); // modifica tranzactia veche cu cea noua
    return 0;
}

/*
 * sterge o tranzactie a unui cont bancar
 */
int srv_sterge_tranazctie(ContBancar *cb, int numar_tranzactie) {
    int eroare = valideaza_numar_tranzactie(cb, numar_tranzactie); // verifica daca tranzactia exista
    if (eroare)
        return eroare;

    adauga(cb->lista_undo, copiaza_lista(cb->tranzactii));
    distruge_tranzactie(cb->tranzactii->elemente[numar_tranzactie]);
    sterge(cb->tranzactii, numar_tranzactie); // sterge tranzactia
    return 0;
}

/*
 * functie care verifica daca se paote efectua operatia de undo si o realizeaza in caz afirmativ
 */
int srv_undo(ContBancar *cb) {
    if (dimensiune(cb->lista_undo) == 0) {
        return 1;
    }
    Lista *l = sterge_ultimul(cb->lista_undo);
    distruge_lista(cb->tranzactii, (FunctieDistruge) distruge_tranzactie);
    cb->tranzactii = l;
    return 0;
}

/*
 * functie care returneaza toate tranzactiile care au stringul text in descriere: mod = 2
 * functie care returneaza toate tranzactiile cu acelasi tip: mod = 1
 * funtcie care retunreaza toate tranzactiile: mod = 0
 */
Lista *get_all_tr(ContBancar *cb, const char *text, int mod) {
    if (mod == 0) {
        return copiaza_lista(cb->tranzactii);
    }

    Lista *l_noua = creeaza_lista();

    // acelasi tip
    if (mod == 1) {
        for (int i = 0; i < dimensiune(cb->tranzactii); i++) {
            Tranzactie *tr = get(cb->tranzactii, i);
            if (strcmp(text, tr->tip) == 0) {
                adauga(l_noua, copiaza_tranzactie(tr));
            }
        }
    }

    // textul se afla in descriere
    if (mod == 2) {
        for (int i = 0; i < dimensiune(cb->tranzactii); i++) {
            Tranzactie *tr = get(cb->tranzactii, i);
            if (strstr(tr->descriere, text)) {
                adauga(l_noua, copiaza_tranzactie(tr));
            }
        }
    }

    return l_noua;
}

/*
 * valideaza parametrii unei sortari
 */
int valideaza_sortare(char *cheie, int ordine) {
    if (strcmp(cheie, "suma") != 0 && strcmp(cheie, "zi") != 0)
        return 1;
    if (ordine != 0 && ordine != 1)
        return 1;
    return 0;
}

Lista *sorteaza_tranzactii(ContBancar *cb, char *cheie, int ordine) {
    if (valideaza_sortare(cheie, ordine)) {
        Lista *l = creeaza_lista();
        return l;
    }

    Lista *l = copiaza_lista(cb->tranzactii);
    if (strcmp(cheie, "suma") == 0) {
        sort_suma(l, ordine);
    } else {
        sort_zi(l, ordine);
    }
    return l;
}

void test_undo() {
    ContBancar cb = creeaza_cont_bancar();
    assert(srv_undo(&cb) == 1);

    assert(srv_adauga_tranzactie(&cb, 20, 10, "intrare", "da") == 0);
    assert(srv_adauga_tranzactie(&cb, 10, 231.11, "iesire", "da") == 0);

    assert(srv_undo(&cb) == 0);
    assert(dimensiune(cb.tranzactii) == 1);

    distruge_cont_bancar(&cb);
}

void test_service() {
    ContBancar cb = creeaza_cont_bancar();
    assert(srv_adauga_tranzactie(&cb, 20, 10, "intrare", "da") == 0);
    assert(srv_adauga_tranzactie(&cb, 10, 231.11, "iesire", "da") == 0);

    assert(srv_modifica_tranzactie(&cb, 1, 10, 10, "iesire", "nu") == 0);
    assert(srv_modifica_tranzactie(&cb, 5, 10, 10, "intrare", "da") == 2);
    assert(srv_modifica_tranzactie(&cb, 1, -10, 10, "iesire", "da") == 1);

    assert(srv_sterge_tranazctie(&cb, 2) == 2);
    assert(srv_sterge_tranazctie(&cb, 0) == 0);
    assert(srv_adauga_tranzactie(&cb, -10, -10, "trare", "da") == 1);

    distruge_cont_bancar(&cb);

    cb = creeaza_cont_bancar();
    assert(srv_adauga_tranzactie(&cb, 20, 10, "intrare", "da") == 0);

    Lista *l = get_all_tr(&cb, NULL, 0);
    assert(dimensiune(l) == dimensiune(cb.tranzactii));
    distruge_lista(l, (FunctieDistruge) distruge_tranzactie);

    l = get_all_tr(&cb, "intrare", 1);
    assert(dimensiune(l) == 1);
    distruge_lista(l, (FunctieDistruge) distruge_tranzactie);

    l = get_all_tr(&cb, "d", 2);
    assert(dimensiune(l) == 1);
    distruge_lista(l, (FunctieDistruge) distruge_tranzactie);

    l = sorteaza_tranzactii(&cb, "suma", 0);
    distruge_lista(l, (FunctieDistruge) distruge_tranzactie);

    l = sorteaza_tranzactii(&cb, "s", 5);
    distruge_lista(l, (FunctieDistruge) distruge_tranzactie);

    l = sorteaza_tranzactii(&cb, "suma", 5);
    distruge_lista(l, (FunctieDistruge) distruge_tranzactie);

    l = sorteaza_tranzactii(&cb, "zi", 1);
    distruge_lista(l, (FunctieDistruge) distruge_tranzactie);

    distruge_cont_bancar(&cb);
    test_undo();
}

