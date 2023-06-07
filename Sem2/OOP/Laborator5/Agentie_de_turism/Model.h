#pragma once
//tip (munte,mare, citiy break), destinatie, data plecare, pret

typedef struct {
    int dd;
    int mm;
    int yy;
}Date;

typedef struct {
    char* type;
    char* destination;
    Date date;//DD-MM-YYYY
    float price;
}Ofert;

/*
Creeaza o noua oferta
*/
Ofert* create_ofert(char* type, char* destination, Date date, float price);

char* get_type(Ofert* o);

char* get_destination(Ofert* o);

Date get_date(Ofert* o);

float get_price(Ofert* o);

void set_type(Ofert* o, char* new_type);

void set_destination(Ofert* o, char* new_destination);

void set_date(Ofert* o, Date new_date);

void set_price(Ofert* o, float new_price);

/*
 Elibereaza memoria ocupata de o oferta
*/
void destroy_ofert(Ofert* o);

/*
Creeaza o copie pentru o oferta
*/
Ofert* copy_ofert(Ofert* o);

void test_create_destroy();