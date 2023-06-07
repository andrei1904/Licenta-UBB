#include <stdio.h>
#include <string.h>

#include "tranzactie.h"
#include "lista.h"
#include "service_cont_bancar.h"
#include "sortare.h"

void teste() {
    test_creeaza_distruge();
    test_lista();
    test_service();
    test_sortare();
}

/*
 * afiseaza tranzactiile ce sunt primite prin lista l
 */
void afiseaza_trancaztii(Lista *l) {
    printf("Tranzactii: \n");
    for (int i = 0; i < dimensiune(l); i++) {
        Tranzactie* tr = get(l, i);
        printf("Numar: %d, Zi: %d, Suma: %.2f, Tip: %s, Descriere: %s\n",
               i, tr->zi, tr->suma, tr->tip, tr->descriere);
    }
    printf("\n");
}

/*
 * citete o tranzactie si o adauga in contul bancar
 */
void adauga_tranzactie(ContBancar *cont) {
    printf("Introduceti ziua: ");
    int zi = 0;
    scanf("%d", &zi);

    printf("Introduceti suma: ");
    double suma = 0;
    scanf("%lf", &suma);

    printf("Introduceti tipul (intrare / iesire): ");
    char tip[10];
    scanf("%s", tip);

    printf("Introduceti descrierea: ");
    char descriere[200];
    scanf("%s", descriere);

    int eroare = srv_adauga_tranzactie(cont, zi, suma, tip, descriere);
    if (eroare) {
        printf("Tranzactie invalida!\n");
    } else {
        printf("Tranzactie adaugata!\n");
    }
    printf("\n");
}


/*
 * citeste numarul unei tranactii si o modifica cu una noua
 */
void modifica_tranactie(ContBancar *cont) {
    int numar_tranzactie = -1;
    printf("Introduceti numarul tranactiei: ");
    scanf("%d", &numar_tranzactie);

    printf("Introduceti ziua: ");
    int zi = 0;
    scanf("%d", &zi);

    printf("Introduceti suma: ");
    double suma = 0;
    scanf("%lf", &suma);

    printf("Introduceti tipul (intrare / iesire): ");
    char tip[10];
    scanf("%s", tip);

    printf("Introduceti descrierea: ");
    char descriere[200];
    scanf("%s", descriere);

    int eroare = srv_modifica_tranzactie(cont, numar_tranzactie, zi, suma, tip, descriere);
    if (eroare == 2) {
        printf("Tranzactia nu exista!\n");
    } else if (eroare == 1) {
        printf("Tranzactia nu este valida!\n");
    } else {
        printf("Tranzactie modificata!\n");
    }
    printf("\n");
}

/*
 * sterge o tranzactie
 */
void sterge_tranzactie(ContBancar *cont) {
    int numar_tranzactie = -1;
    printf("Introduceti numarul tranactiei: ");
    scanf("%d", &numar_tranzactie);

    int eroare = srv_sterge_tranazctie(cont, numar_tranzactie);
    if (eroare == 2) {
        printf("Tranzactia nu exista!\n");
    } else {
        printf("Tranzactia a fost stearsa!\n");
    }
    printf("\n");
}

/*
 * afiseaza toate tranzactiile existente in contul bancar: cont
 */
void afiseaza_toate_tranzactiile(ContBancar *cont) {
    Lista* toate_tr = get_all_tr(cont, NULL, 0);

    if (dimensiune(toate_tr) == 0) {
        printf("Nu exista tranzactii!\n");
    } else {
        afiseaza_trancaztii(toate_tr);
    }
    distruge_lista(toate_tr, (FunctieDistruge) distruge_tranzactie);
}

/*
 * afiseaza tranzactiile care au acelasi tip
 */
void afiseaza_tranzactii_tip(ContBancar *cont) {
    char tip[10];
    printf("Introduceti tipul dorit (intrare / iesire): ");
    scanf("%s", tip);

    Lista* lista_tr = get_all_tr(cont, tip, 1);
    if (dimensiune(lista_tr) == 0) {
        printf("Nu exista tranzactii de acest tip!\n");
    } else {
        afiseaza_trancaztii(lista_tr);
    }
    distruge_lista(lista_tr, (FunctieDistruge) distruge_tranzactie);
}

/*
 * afiseaza tranzactiile care au acelasi tip
 */
void afiseaza_tranzactii_descriere(ContBancar *cont) {
    char descriere[300];
    printf("Introduceti textul: ");
    scanf("%s", descriere);

    Lista* lista_tr = get_all_tr(cont, descriere, 2);
    if (dimensiune(lista_tr) == 0) {
        printf("Nu exista tranzactii care sa contina aceasta descriere!\n");
    } else {
        afiseaza_trancaztii(lista_tr);
    }
    distruge_lista(lista_tr, (FunctieDistruge) distruge_tranzactie);
}

/*
 * afiseaza tranzactii ordonat dupa suma sau zi
 */
void afiseaza_tranzactii_ordonat(ContBancar *cont) {
    char criteriu[5];
    printf("Introduceti criteriul fata de care sa fie ordonate tranzactiile (suma / zi): ");
    scanf("%s", criteriu);

    int ordine = -1;
    printf("Introduceti 0 pentru crescator sau 1 pentru descrescator: ");
    scanf("%d", &ordine);

    Lista* lista_ordonata = sorteaza_tranzactii(cont, criteriu, ordine);
    if (dimensiune(lista_ordonata) == 0) {
        printf("Datele introduse au fost gresite!\n");
    } else {
        afiseaza_trancaztii(lista_ordonata);
    }
    distruge_lista(lista_ordonata, (FunctieDistruge) distruge_tranzactie);
}

/*
 * efectueaza operatia de undo daca se paote
 */
void undo(ContBancar* cont) {
    int eroare = srv_undo(cont);
    if (eroare)
        printf("Nu se poate efectua operatia de undo!\n");
    else
        printf("Sa efectuat operatia de undo!\n");
}

void run() {
    ContBancar cont = creeaza_cont_bancar();
    int ruleaza = 1;
    while (ruleaza) {
        printf("0: Exit \n1: Adauga \n2: Modifica \n3: Sterge \n4: Afiseaza tranzactiile existente \n5: Afiseaza "
               "tranzactiile de un anumit tip \n6: Afiseaza tranzactiile ce contin un text in descriere "
               "\n7: Afiseaza tranzactiile ordonate \n8: Undo \n");
        int cmd = -1;

        printf("Introduceti comanda: ");
        scanf("%d", &cmd);

        switch (cmd) {
            case 0:
                ruleaza = 0;
                break;
            case 1:
                adauga_tranzactie(&cont);
                break;
            case 2:
                modifica_tranactie(&cont);
                break;
            case 3:
                sterge_tranzactie(&cont);
                break;
            case 4:
                afiseaza_toate_tranzactiile(&cont);
                break;
            case 5:
                afiseaza_tranzactii_tip(&cont);
                break;
            case 6:
                afiseaza_tranzactii_descriere(&cont);
                break;
            case 7:
                afiseaza_tranzactii_ordonat(&cont);
                break;
            case 8:
                undo(&cont);
                break;
            default:
                printf("Comanda invalida!\n");
        }
    }
    distruge_cont_bancar(&cont);
}

int main() {
    //teste();
    run();
    return 0;
}