#ifndef CONT_BANCAR_LISTA_H
#define CONT_BANCAR_LISTA_H

#include "tranzactie.h"

typedef void* TipElem;
typedef void (*FunctieDistruge)(TipElem);

typedef struct {
    TipElem* elemente;
    int lg; // lungime
    int capacitate;
} Lista;

/*
 * creeaza lista vida
 */
Lista* creeaza_lista();

/*
 * distruge lista si tranzactiile
 */
void distruge_lista_tranzactie(Lista* l);

/*
 * distruge lista
 */
void distruge_lista(Lista* l, FunctieDistruge f_distruge);

/*
 * copieaza o lista
 */
Lista* copiaza_lista(Lista* l);

/*
 * returneaza element din lista de pe pozitia poz
 * poz - pozitia elementului, trebuie sa fie valida
 */
TipElem get(Lista* l, int poz);

/*
 * modifica elementul de pe pozitia poz cu elementul tr
 * returneaza elementul suprascris
 */
TipElem set(Lista*l, int poz, Tranzactie* tr);

/*
 * returneaza numarul de elemente din lista
 */
int dimensiune(Lista* l);

/*
 * adauga un element in lista la final
 */
void adauga(Lista* l, TipElem el);

/*
 * sterge elementul de pe pozitia poz
 */
void sterge(Lista* l, int poz);

/*
 * sterge ultimul element dintr o lista si returneaz valoarea acestuia
 */
TipElem sterge_ultimul(Lista* l);

void test_lista();

#endif //CONT_BANCAR_LISTA_H
