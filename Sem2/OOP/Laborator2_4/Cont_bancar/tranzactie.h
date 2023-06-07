//
// Created by radua on 3/4/2020.
//

#ifndef CONT_BANCAR_TRANZACTIE_H
#define CONT_BANCAR_TRANZACTIE_H

/*
 * tip de date care stocheaza o tranzactie
 *
 */
typedef struct {
    int zi;
    double suma;
    char* tip;
    char* descriere;
} Tranzactie;

/*
 * creeaza tranzactie
 */
Tranzactie* creeaza_tranzactie(int zi, double suma, char* tip, char* descriere);

/*
 * elibereaza memorie ce este ocupata de o tranzactie
 */
void distruge_tranzactie(Tranzactie* tr);

/*
 * creeaza o copie a unei tranzactii
 */
Tranzactie* copiaza_tranzactie(Tranzactie* tr);

/*
 * valideaza o tranzactie
 */
int valideaza_tranzactie(Tranzactie* tr);


void test_creeaza_distruge();

#endif //CONT_BANCAR_TRANZACTIE_H
