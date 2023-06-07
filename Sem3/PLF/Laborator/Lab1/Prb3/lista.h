#ifndef LISTA_H
#define LISTA_H


//tip de data generic (pentru moment este intreg)
typedef int TElem;

//referire a structurii Nod;
struct Nod;

//se defineste tipul PNod ca fiind adresa unui Nod
typedef Nod *PNod;

typedef struct Nod{
    TElem e;
    PNod urm;
};

typedef struct{
//prim este adresa primului Nod din lista
    PNod _prim;
} Lista;

//operatii pe lista - INTERFATA

//crearea unei liste din valori citite pana la 0
Lista creare();
//tiparirea elementelor unei liste
void tipar(Lista l);
// destructorul listei
void distruge(Lista l);

// verifica daca lista este vida
bool vida(Lista l);

// returneaza primul element din lista
TElem primul_element(Lista l);

PNod primul_nod(Lista l);

// adauga la inceputul listei
Lista adauga_inceput(Lista l, PNod elem);

//
Lista urmatorul_este_prim(Lista l);

//
void schimba_primul_element(Lista l, int element);

#endif
