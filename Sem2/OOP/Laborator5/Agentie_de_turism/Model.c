#include <stdio.h>
#include "Model.h"
#include <string.h>
#include <assert.h>
#include <stdlib.h>
/*
Creeaza o noua oferta
*/
Ofert* create_ofert(char* type, char* destination,Date date, float price) {
    Ofert* new_ofert = malloc(sizeof(Ofert));
    int len = strlen(type) + 1;
    new_ofert->type = malloc(sizeof(char) * len);
    strcpy(new_ofert->type, type);
    len = strlen(destination) + 1;
    new_ofert->destination = malloc(sizeof(char) * len);
    strcpy(new_ofert->destination, destination);
    new_ofert->date = date;
    new_ofert->price = price;
    return new_ofert;
}
/*
returneaza tipul ofertei o
*/
char* get_type(Ofert* o) {

    return o->type;
}
/*
returneaza destinatia ofertei o
*/
char* get_destination(Ofert* o) {

    return o->destination;
}
/*
returneaza data ofertei o
*/
Date get_date(Ofert* o) {

    return o->date;
}
/*
returneaza destinatia ofertei o
*/
float get_price(Ofert* o) {

    return o->price;
}
/*
sets the type to new type
*/
void set_type(Ofert* o, char* new_type) {
    strcpy(o->type , new_type);
}
/*
sets the destination to new_destination
*/
void set_destination(Ofert* o, char* new_destination) {
    strcpy(o->destination, new_destination);

}
/*
sets the date to new_date
*/
void set_date(Ofert* o, Date new_date) {
    o->date = new_date;
}
/*
sets the price to new_price
*/
void set_price(Ofert* o, float new_price) {
    o->price = new_price;
}
/*
Elibereaza memoria ocupata de o oferta
*/
void destroy_ofert(Ofert* o) {
    free(o->type);
    free(o->destination);
    free(o);
}
/*
  Creaeaza o copie pentru o oferta
*/
Ofert* copy_ofert(Ofert* o)
{
    return create_ofert(o->type, o->destination, o->date, o->price);
}
void test_create_destroy() {
    Date d = { 2, 4, 2005 };
    Ofert* o = create_ofert("munte", "MunteNegro",d,243.5);
    assert(strcmp(get_type(o), "munte") == 0);
    assert(strcmp(get_destination(o), "MunteNegro") == 0);
    assert(get_date(o).dd == 2);
    assert(get_date(o).mm == 4);
    assert(get_date(o).yy == 2005);
    assert(get_price(o) == 243.5);
    set_type(o, "mare");
    set_destination(o, "Constanta");
    set_price(o, 1234);
    assert(strcmp(get_type(o), "mare") == 0);
    assert(strcmp(get_destination(o), "Constanta") == 0);
    assert(get_price(o) == 1234);
    destroy_ofert(o);
}