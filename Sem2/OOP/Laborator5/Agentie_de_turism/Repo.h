#pragma once
#include "Model.h"
typedef void* TElem;

typedef void (*Destroy_Functie)(TElem);
typedef struct {
    TElem* elems;
    int lg;
    int capa; //capacitatea maxima
} List;

/*
  Creeaza o lista vida
*/
List* create_list();

/*
  Distruge un vector
*/
void destroy(List* l, Destroy_Functie dsrtr_fct);

void destroy_lista_de_oferte(List* l);


TElem remove_last(List* l);

/*
  Returneaza un element din lista de pe o anumita pozitie
  poz - pozitia elementului
*/
TElem get(List* l, int poz);

/*
    Modifica elementul de pe o anumita pozitie

*/
TElem set(List* l, int poz, Ofert* o);

/*
   Returneaza numarul de oferte din lista
*/
int size(List* l);

/*
  Adauga un element in lista
  post: elementul a fost adaugat in lista la sfarsit
*/
void add(List* l, TElem el);


/*
Sterge un element din lista
post: elementul este sters
*/
void delete(List* l, int poz);


/*
  Face o copie a listei
  return Vector cu aceleasi elemente ca cele din l
*/
List* copy_list(List * l);

void test_create_list();
void test_iterate_list();
void test_copy_list();
void test_resize();
void test_lista_de_liste();