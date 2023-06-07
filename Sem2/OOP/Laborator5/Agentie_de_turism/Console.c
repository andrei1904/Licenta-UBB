#include "Console.h"
#include <stdio.h>
#include <string.h>
/*
Adauga o oferta citita de la tastatura in agency
*/
void adauga_oferta(Agency* agency) {
    printf("Type:");
    char type[15];
    scanf("%s", type);
    printf("Destination: ");
    char destination[50];
    scanf("%s", destination);
    printf("Date:\nDay:");
    Date d;
    scanf("%d", &d.dd);
    printf("Month: ");
    scanf("%d", &(d.mm));
    printf("Year: ");
    scanf("%d", &(d.yy));
    printf("Price: ");
    float price;
    scanf("%f", &price);
    int add_code = add_ofert(agency, type, destination, d, price);
    switch (add_code)
    {
        case 0:
            printf("Added!\n");
            break;
        case 1:
            printf("Invalid type!\n");
            break;
        case 2:
            printf("Invalid date!\n");
            break;
        case 3:
            printf("Invalid destination!\n");
            break;
        case 4:
            printf("Invalid price!\n");
            break;
        default:
            break;
    }
}

/*
Sterge o oferta din agentie
*/
void sterge_oferta(Agency* agency) {
    printf("Position of offert: ");
    int poz;
    scanf("%d", &poz);
    int delete_code = delete_ofert(agency, poz);
    switch (delete_code) {
        case 0:
            printf("Deleted!\n");
            break;
        case 1:
            printf("Invalid position!\n");
            break;
        default:
            break;
    }
}


/*
Modifica oferta din agency cu una citita de la tastatura
*/
void modifica_oferta(Agency* agency) {
    printf("Position of offert: ");
    int poz;
    scanf("%d", &poz);
    printf("Type:");
    char type[15];
    scanf("%s", type);
    printf("Destination: ");
    char destination[50];
    scanf("%s", destination);
    printf("Date:\nDay:");
    Date d;
    scanf("%d", &d.dd);
    printf("Month: ");
    scanf("%d", &(d.mm));
    printf("Year: ");
    scanf("%d", &(d.yy));
    printf("Price: ");
    float price;
    scanf("%f", &price);
    int modify_code = modify_ofert(agency, poz, type, destination, d, price);
    switch (modify_code)
    {
        case 0:
            printf("Modified!\n");
            break;
        case 1:
            printf("Invalid type!\n");
            break;
        case 2:
            printf("Invalid date!\n");
            break;
        case 3:
            printf("Invalid destination!\n");
            break;
        case 4:
            printf("Invalid price!\n");
            break;
        case 5:
            printf("Invalid position!\n");
            break;
        default:
            break;
    }
}
/*
ordoneaza ofertele dupa pret, destinatie
*/
void ordoneaza_oferte(Agency* agency) {
    List* ordered = order_oferts(agency);
    TElem o;
    int i;
    for (i = 0; i < size(ordered); i++)
    {
        o = get(ordered, i);
        printf("%s, %s, %d-%d-%d, %f\n", get_type(o), get_destination(o), get_date(o).dd, get_date(o).mm, get_date(o).yy, get_price(o));
    }
    destroy(ordered, (Destroy_Functie) destroy_ofert);
}

/*
Permite citirea unui tip si afisarea ofertelor cu acel tip
*/
void filtreaza_oferte_tip(Agency* agency) {
    char type[15];
    printf("Type: ");
    scanf("%s", type);
    //validate type
    if (strcmp(type, "munte") != 0 &&
        strcmp(type, "mare") != 0 &&
        strcmp(type, "city break") != 0)
        printf("Invalid type!\n");
    List* filtered = filter_oferts_type(agency, type);
    if (size(filtered) == 0)
        printf("There are no offerts with that type!\n");
    else
    {
        TElem o;
        int i;
        for (i = 0; i < size(filtered); i++)
        {
            o = get(filtered, i);
            printf("%s, %s, %d-%d-%d, %f\n", get_type(o), get_destination(o), get_date(o).dd, get_date(o).mm, get_date(o).yy, get_price(o));
        }
    }
    destroy(filtered, (Destroy_Functie) destroy_ofert);
}

/*
Permite citirea unui pret si afisarea ofertelor cu pret mai mare
*/
void filtreaza_oferte_pret(Agency* agency) {
    float price;
    printf("Price: ");
    scanf("%f", &price);
    //validate price
    if (price <=0)
        printf("Invalid price!\n");
    List* filtered = filter_oferts_price(agency, price);
    if (size(filtered) == 0)
        printf("There are no offerts with higher price!\n");
    else
    {
        TElem o;
        int i;
        for (i = 0; i < size(filtered); i++)
        {
            o = get(filtered, i);
            printf("%s, %s, %d-%d-%d, %f\n", get_type(o), get_destination(o), get_date(o).dd, get_date(o).mm, get_date(o).yy, get_price(o));
        }
    }
    destroy(filtered, (Destroy_Functie) destroy_ofert);
}

/*
Tipareste ofertele dintr o agentie
*/
void tipareste_oferte(Agency* agency) {
    List* oferte = get_all(agency);
    TElem o;
    int i = 0;
    for (i = 0; i < size(oferte); i++)
    {
        o = get(oferte, i);
        printf("%d: %s, %s, %d-%d-%d, %f\n", i, get_type(o), get_destination(o), get_date(o).dd, get_date(o).mm, get_date(o).yy, get_price(o));
    }
    destroy(oferte, (Destroy_Functie) destroy_ofert);
}
/*
Realizeaza operatia de undo, daca este posibil
*/
void undo_UI(Agency* agency) {

    int err = srv_undo(agency);
    if (err)
        printf("Nu se poate executa operatia de undo!\n");
    else
        printf("Undo realizat!\n");
}
void run(){
    Agency agency = create_agency();
    int ruleaza = 1;
    while (ruleaza) {
        printf("1 Add offert\n2 Modify offert\n3 Delete offert\n4 Order by price, destination\n5 Filter by type\n6 Filter by price\n7 Print all offerts\n8 Undo\n0 Exit\nCommand:");
        int cmd = 0;
        scanf("%d", &cmd);
        switch (cmd) {
            case 0:
                ruleaza = 0;
                break;
            case 1:
                adauga_oferta(&agency);
                break;
            case 2:
                modifica_oferta(&agency);
                break;
            case 3:
                sterge_oferta(&agency);
                break;
            case 4:
                ordoneaza_oferte(&agency);
                break;
            case 5:
                filtreaza_oferte_tip(&agency);
                break;
            case 6:
                filtreaza_oferte_pret(&agency);
                break;
            case 7:
                tipareste_oferte(&agency);
                break;
            case 8:
                undo_UI(&agency);
                break;
            default:
                printf("Comanda invalida!\n");
        }
    }
    destroy_agency(&agency);
}


