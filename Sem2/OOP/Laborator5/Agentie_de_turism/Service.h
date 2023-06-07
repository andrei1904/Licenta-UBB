#pragma once
#include "Repo.h"
#include "Validation.h"
#include "Model.h"

typedef struct {
    List* oferts;
    List* undo_list;
}Agency;


typedef int(*CmpFunction)(Ofert* o1, Ofert* o2);


/*
Functie care sorteaza o lista dupa functia cmp
*/
void sort(List* l, CmpFunction cmp);


/*
Creeaza o agentie goala
*/
Agency create_agency();
/*
Distruge o agentie
*/
void destroy_agency(Agency* agency);

/*
Construieste, valideaza si adauga o oferta la sfarsitul agentiei
return: 0 - daca s a adaugat cu succes, coduri de eroare daca nu.
*/
int add_ofert(Agency* agency, char* type, char* destination, Date date, float price);


/*
Valideaza pozitia, construieste Oferta si o valideaza, iar apoi face modificarea in agentie
return: 0- modificare cu succes, coduri de eroare daca nu.
*/
int modify_ofert(Agency* agency, int poz, char* type, char* destination, Date d, float price);


/*
Valideaza pozitia si sterge oferta de pe acea pozitie din agetie
return: 0- stergere cu succes, cod de eroare daca nu
*/
int delete_ofert(Agency* agency, int poz);

/*
returneaza lista de oferte ordonata dupa pret, destinatie
*/
List* order_oferts(Agency* agency);

/*
realizeaza op de undo
*/
int srv_undo(Agency* agency);

/*
Returneaza o lista cu ofertele care au tipul type din agency
*/
List* filter_oferts_type(Agency* agency, char* type);

/*
Returneaza o lista cu ofertele care au tprtul mai mare din agency
*/
List* filter_oferts_price(Agency* agency, float price);

/*
Returneaza lista de oferte din agentie
*/
List* get_all(Agency* agency);


void test_add_ofert();
void test_modify_ofert();
void test_delete_ofert();
void test_order_ofert();
void test_filter_oferts();
void test_undo();