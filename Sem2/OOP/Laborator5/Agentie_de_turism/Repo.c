#include "Repo.h"

#include <assert.h>
#include <string.h>
#include <stdlib.h>
/*
Creeaza o lista vida
*/
List* create_list() {
    List* rez = malloc(sizeof(List));
    rez->lg = 0;
    rez->capa = 2;
    rez->elems = malloc(sizeof(TElem) * rez->capa);
    return rez;
}

/*
 Distruge lista
*/
void destroy(List* l, Destroy_Functie dsrtr_fct){
    //dealocate pets
    for (int i = 0; i < l->lg; i++) {
        TElem ele = l->elems[i];
        dsrtr_fct(ele);
    }
    free(l->elems);
    free(l);
}

void destroy_lista_de_oferte(List* l) {

    destroy(l, destroy_ofert);
}

/*
  Returneaza un element din lista de pe o anumita pozitie
  poz - pozitia elementului
*/
TElem get(List* l, int poz) {
    return l->elems[poz];
}

/*
	Modifica elementul de pe o anumita pozitie

*/
TElem set(List* l, int poz, Ofert* o) {
    TElem rez = l->elems[poz];
    l->elems[poz] = o;
    return rez;
}

/*
returneaza numarul de elemente din lista
*/
int size(List* l) {
    return l->lg;
}

/*
  redimensioneaza lista daca este nevoie.
*/
void redim(List* l) {
    if (l->lg < l->capa) {
        return;
    }
    //aloca mai multa memorie
    TElem* new_list = malloc(sizeof(TElem) * (l->capa + 10));
    for (int i = 0; i < l->lg; i++) {
        new_list[i] = l->elems[i];
    }
    free(l->elems);
    l->elems = new_list;
    l->capa += 10;
}
/*
  Adauga un element in lista
  post: elementul a fost adaugat in lista la sfarsit
*/
void add(List* l, TElem el) {
    redim(l);
    l->elems[l->lg] = el;
    l->lg++;
}
/*
Sterge un element din lista
post: elementul este sters
*/
void delete(List* l, int poz) {
    int i;
    destroy_ofert(l->elems[poz]);
    for (i = poz; i < size(l) - 1; i++)
        l->elems[i] = l->elems[i + 1];
    l->lg--;

}
/*
sterge ultimul element dintr o lista si ii returneaza valoarea*/
TElem remove_last(List* l) {
    TElem rez = l->elems[l->lg - 1];
    l->lg -= 1;
    return rez;
}
/*
  Face o copie a listei
  return Vector cu aceleasi elemente ca cele din l
*/
List* copy_list(List* l) {
    List* rez = create_list();
    for (int i = 0; i < size(l); i++) {
        TElem o = get(l, i);
        add(rez, copy_ofert(o));
    }
    return rez;
}

void test_create_list() {
    List* l = create_list();
    assert(size(l) == 0);
    destroy(l, destroy_ofert);
}
void test_iterate_list() {
    List* l = create_list();
    Date d = { 1,1,2001 };
    add(l, create_ofert("mare", "Egipt", d, 3000.5));
    add(l, create_ofert("munte", "Franta", d,1000));
    assert(size(l) == 2);
    Ofert* o = get(l, 0);
    assert(strcmp(o->type, "mare") == 0);
    o = get(l, 1);
    assert(strcmp(o->destination, "Franta") == 0);
    destroy(l, destroy_ofert);
}

void test_copy_list() {
    List* l = create_list();
    Date d = { 1,1,2001 };
    add(l, create_ofert("mare", "Egipt", d, 3000.5));
    add(l, create_ofert("munte", "Franta", d, 1000));
    List* l2 = copy_list(l);
    assert(size(l2) == 2);
    Ofert* o = get(l2, 0);
    assert(strcmp(o->type, "mare") == 0);
    destroy(l, destroy_ofert);
    destroy(l2, destroy_ofert);
}

void test_resize() {
    List* l = create_list();
    Date d = { 1,1,2001 };
    for (int i = 0; i < 10; i++) {
        add(l, create_ofert("mare", "Egipt", d, 3000.5));
    }
    assert(size(l) == 10);
    destroy(l, destroy_ofert);
}

void test_lista_de_liste() {
    List* l1 = create_list();
    Date d = { 2,3,2023 };
    add(l1, create_ofert("mare", "madagascar", d, 2345.6));
    List* l2 = create_list();
    List* undo_list = create_list();
    add(undo_list, l1);
    assert(size(undo_list) == 1);
    add(undo_list, l2);
    assert(size(undo_list) == 2);
    destroy(undo_list, destroy_lista_de_oferte);
}
