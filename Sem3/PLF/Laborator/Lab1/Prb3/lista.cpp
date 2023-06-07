#include "lista.h"
#include <iostream>

using namespace std;


PNod creare_rec(){
    TElem x;
    cout<<"x=";
    cin>>x;
    if (x==0)
        return NULL;
    else{
        PNod p=new Nod();
        p->e=x;
        p->urm=creare_rec();
        return p;
    }
}

Lista creare(){
    Lista l;
    l._prim=creare_rec();
    return l;
}

void tipar_rec(PNod p){
    if (p!=NULL){
        cout<<p->e<<" ";
        tipar_rec(p->urm);
    }
}

void tipar(Lista l){
    tipar_rec(l._prim);
}

void distruge_rec(PNod p){
    if (p!=NULL){
        distruge_rec(p->urm);
        delete p;
    }
}

void distruge(Lista l) {
    //se elibereaza memoria alocata nodurilor listei
    distruge_rec(l._prim);
}

bool vida(Lista l) {
    if (l._prim == nullptr) {
        return true;
    }
    return false;
}

PNod primul_nod(Lista l) {
    return l._prim;
}

TElem primul_element(Lista l) {
    return l._prim->e;
}

Lista adauga_inceput(Lista l, PNod elem) {
    elem->urm = l._prim;
    l._prim = elem;
    return l;
}

Lista urmatorul_este_prim(Lista l) {
    l._prim = l._prim->urm;
    return l;
}

void schimba_primul_element(Lista l, TElem element) {
    l._prim->e = element;
}

