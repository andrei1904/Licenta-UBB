#include "Service.h"
#include <assert.h>
#include <string.h>
#include <stdio.h>
/*
creates an empty agency
*/
Agency create_agency() {
    Agency agency;
    agency.oferts = create_list();
    agency.undo_list = create_list();
    return agency;
}
/*
destroy an agency
*/
void destroy_agency(Agency* agency) {
    destroy(agency->oferts, (Destroy_Functie) destroy_ofert);
    destroy(agency->undo_list, (Destroy_Functie) destroy_lista_de_oferte);
}
/*
add an ofert to an agency
*/
int add_ofert(Agency* agency, char* type, char* destination, Date date, float price) {
    Ofert* o = create_ofert(type, destination, date, price);
    int err = validate_ofert(o);
    if (err  == 0)
    {
        add(agency->undo_list, copy_list(agency->oferts));
        add(agency->oferts, o);
        return 0;
    }
    destroy_ofert(o);
    return err;
}
/*
Returneaza lista de oferte dintr-o agentie
*/
List* get_all(Agency* agency) {
    return copy_list(agency->oferts);
}
/*
verifica daca se poate realiza operatia de undo si o realizeaza in caz afirmativ
*/
int srv_undo(Agency* agency) {

    if (size(agency->undo_list) == 0)
        return 1;
    List* l = remove_last(agency->undo_list);
    destroy(agency->oferts, (Destroy_Functie) destroy_ofert);
    agency->oferts = l;
    return 0;
}


/*
Valideaza pozitia, construieste Oferta si o valideaza, iar apoi face modificarea in agentie
return: 0- modificare cu succes, coduri de eroare daca nu.
*/
int modify_ofert(Agency* agency, int poz, char* type, char* destination, Date d, float price) {
    if (poz >= size(agency->oferts)||poz<0)
        return 5;
    Ofert* o = create_ofert(type, destination, d, price);
    int err = validate_ofert(o);
    if (err == 0)
    {
        add(agency->undo_list, copy_list(agency->oferts));
        Ofert* del = set(agency->oferts, poz, o);
        destroy_ofert(del);
        return 0;
    }
    destroy_ofert(o);
    return err;
}

/*
Valideaza pozitia si sterge oferta de pe acea pozitie din agetie
return: 0- stergere cu succes, cod de eroare daca nu
*/
int delete_ofert(Agency* agency, int poz) {
    if (poz >= size(agency->oferts) || poz < 0)
        return 1;
    add(agency->undo_list, copy_list(agency->oferts));
    delete(agency->oferts, poz);
    return 0;
}

int cmp_destination(Ofert* o1, Ofert* o2) {
    return strcmp(o1->destination, o2->destination);
}

int cmp_price(Ofert* o1, Ofert* o2) {
    if (o1->price == o2->price)
        return 0;
    if (o1->price > o2->price)
        return 1;
    return -1;
}
void sort(List* l, CmpFunction cmp) {
    int i, j;
    for (i = 0; i < size(l); i++) {
        for (j = i + 1; j < size(l); j++) {
            TElem o1 = get(l, i);
            TElem o2 = get(l, j);
            if (cmp(o1, o2) > 0) {
                set(l, i, o2);
                set(l, j, o1);
            }
        }
    }
}


/*
returneaza lista de oferte ordonata dupa pret, destinatie
*/
List* order_oferts(Agency* agency) {
    List* rez = copy_list(agency->oferts);
    sort(rez, cmp_price);
    sort(rez, cmp_destination);
    return rez;
}

/*
Returneaza o lista cu ofertele care au tipul type din agency
*/
List* filter_oferts_type(Agency* agency, char* type) {
    List* rez = create_list();
    int i;
    TElem o;
    for (i = 0; i < size(agency->oferts); i++)
    {
        o = get(agency->oferts, i);
        if (strcmp(get_type(o), type) == 0)
            add(rez, copy_ofert(o));
    }
    return rez;
}
/*
Returneaza o lista cu ofertele care au tprtul mai mare din agency
*/
List* filter_oferts_price(Agency* agency, float price) {
    List* rez = create_list();
    int i;
    TElem o;
    for (i = 0; i < size(agency->oferts); i++)
    {
        o = get(agency->oferts, i);
        if (get_price(o)>price)
            add(rez, copy_ofert(o));
    }
    return rez;
}

void test_add_ofert() {
    Agency agency = create_agency();
    Date d = { 10,10,2003 }, d1 = {3,4,2005};
    Ofert* o;
    assert(add_ofert(&agency, "mare", "Grecia", d, 120)==0);
    assert(add_ofert(&agency, "munte", "Alpi", d1,10000)==0);
    assert(add_ofert(&agency, "munte", "Alpi", d1, -3) == 4);
    List* test_ofert = get_all(&agency);
    assert(size(test_ofert) == 2);
    destroy(test_ofert, (Destroy_Functie) destroy_ofert);
    destroy_agency(&agency);
}

void test_modify_ofert() {
    Agency agency = create_agency();
    Date d = { 10,10,2003 }, d1 = { 3,4,2005 };
    add_ofert(&agency, "mare", "Grecia", d, 120);
    assert(modify_ofert(&agency, 4, "munte", "Alpi", d1, 10000) == 5);
    assert(modify_ofert(&agency, 0, "munte", "Alpi", d1, -3) == 4);
    modify_ofert(&agency, 0, "munte", "Alpi", d1, 10000);
    List* test_ofert = get_all(&agency);
    Ofert* o=get(test_ofert, 0);
    assert(strcmp(get_type(o), "munte")==0);
    assert(get_price(o) == 10000);
    destroy(test_ofert, (Destroy_Functie) destroy_ofert);
    destroy_agency(&agency);
}

void test_delete_ofert() {
    Agency agency = create_agency();
    Date d = { 10,10,2003 }, d1 = { 3,4,2005 };
    add_ofert(&agency, "mare", "Grecia", d, 120);
    add_ofert(&agency, "munte", "Alpi", d1, 10000);
    List* test_ofert = get_all(&agency);
    assert(size(test_ofert) == 2);
    destroy(test_ofert, (Destroy_Functie) destroy_ofert);
    delete_ofert(&agency, 0);
    test_ofert = get_all(&agency);
    assert(size(test_ofert) == 1);
    assert(delete_ofert(&agency, 5) == 1);
    destroy(test_ofert, (Destroy_Functie) destroy_ofert);
    destroy_agency(&agency);

}

void test_order_ofert() {
    Agency agency = create_agency();
    Date d = { 10,10,2003 }, d1 = { 3,4,2005 };
    add_ofert(&agency, "mare", "Grecia", d, 120);
    add_ofert(&agency, "munte", "Alpi", d1, 10000);
    List* ordered = order_oferts(&agency);
    TElem o = get(ordered, 0);
    assert(strcmp(get_type(o), "munte") == 0);
    destroy(ordered, (Destroy_Functie) destroy_ofert);
    destroy_agency(&agency);
}

void test_filter_oferts() {
    Agency agency = create_agency();
    Date d = { 10,10,2003 }, d1 = { 3,4,2005 };
    add_ofert(&agency, "mare", "Grecia", d, 120);
    add_ofert(&agency, "munte", "Alpi", d1, 10000);
    add_ofert(&agency, "mare", "Romania", d, 1200);
    List* filtered = filter_oferts_type(&agency, "mare");
    assert(size(filtered) == 2);
    TElem o;
    o = get(filtered, 0);
    assert(strcmp(get_destination(o), "Grecia") == 0);
    destroy(filtered, (Destroy_Functie) destroy_ofert);
    List* filtered1 = filter_oferts_price(&agency, 1000.5);
    assert(size(filtered1) == 2);
    destroy(filtered1, (Destroy_Functie) destroy_ofert);
    destroy_agency(&agency);
}



void test_undo() {
    Agency agency = create_agency();
    Date d = { 10,10,2003 }, d1 = { 3,4,2005 };
    add_ofert(&agency, "mare", "Grecia", d, 120);
    add_ofert(&agency, "munte", "Alpi", d1, 10000);
    srv_undo(&agency);
    List* l = get_all(&agency);
    assert(size(l) == 1);
    destroy(l, (Destroy_Functie) destroy_ofert);
    srv_undo(&agency);
    l = get_all(&agency);
    assert(size(l) == 0);
    destroy(l, (Destroy_Functie) destroy_ofert);

    assert(srv_undo(&agency) != 0);
    assert(srv_undo(&agency) != 0);
    assert(srv_undo(&agency) != 0);

    destroy_agency(&agency);
}