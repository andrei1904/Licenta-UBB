#pragma once
#include "Service.h"
void run();


/*
Adauga o oferta citita de la tastatura in agency 
*/
void adauga_oferta(Agency* agency);

/*
Modifica oferta din agency cu una citita de la tastatura
*/
void modifica_oferta(Agency* agency);

/*
Sterge o oferta din agentie
*/
void sterge_oferta(Agency* agency);

/*
Ordoneaza si afiseaza ofertele dupa pret, destinatie
*/
void ordoneaza_oferte(Agency* agency);

/*
Permite citirea unui tip si afisarea ofertelor cu acel tip 
*/
void filtreaza_oferte_tip(Agency* agency);

/*
Permite citirea unui pret si afisarea ofertelor cu pret mai mare
*/
void filtreaza_oferte_pret(Agency* agency);
/*
Tipareste toate ofertele dintr-o agentie
*/
void tipareste_oferte(Agency* agency);

/*
Realizeaza undo la op de adaugare, stergere, modificare
*/
void undo_UI(Agency* agency);