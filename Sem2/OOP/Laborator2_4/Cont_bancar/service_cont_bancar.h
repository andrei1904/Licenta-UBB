#ifndef CONT_BANCAR_SERVICE_CONT_BANCAR_H
#define CONT_BANCAR_SERVICE_CONT_BANCAR_H

#include "lista.h"

typedef struct {
    Lista* tranzactii;
    Lista* lista_undo;
} ContBancar;

/*
 * creeaza un cont bancar
 */
ContBancar creeaza_cont_bancar();

/*
 * elibereaza memoria ocupata de un cont bancar
 */
void distruge_cont_bancar(ContBancar *cb);

/*
 * adauga o tranzactie unui cont bancar
 */
int srv_adauga_tranzactie(ContBancar *cb, int zi, double suma, char *tip, char *descriere);

/*
 * sterge o tranzactie a unui cont bancar
 */
int srv_sterge_tranazctie(ContBancar *cb, int numar_tranzactie);

/*
 * functie care verifica daca se paote efectua operatia de undo si o realizeaza in caz afirmativ
 */
int srv_undo(ContBancar* cb);

/*
 * modifica o tranzactie a unui cont bancar
 */
int srv_modifica_tranzactie(ContBancar *cb, int numar_tranzactie, int zi_noua, double suma_noua, char *tip_nou,
                            char *descriere_noua);

/*
 * functie care returneaza toate tranzactiile care au stringul text in descriere: mod = 2
 * functie care returneaza toate tranzactiile cu acelasi tip: mod = 1
 * funtcie care retunreaza toate tranzactiile: mod = 0
 */
Lista* get_all_tr(ContBancar* cb, const char* tip, int mod);

Lista* sorteaza_tranzactii(ContBancar* cb, char* cheie, int ordine);

void test_service();

#endif //CONT_BANCAR_SERVICE_CONT_BANCAR_H
