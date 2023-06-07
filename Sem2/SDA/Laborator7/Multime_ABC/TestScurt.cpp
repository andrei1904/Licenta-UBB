#include "TestScurt.h"
#include "Multime.h"
#include <assert.h>
#include "IteratorMultime.h"
#include <iostream>

void testAll() { //apelam fiecare functie sa vedem daca exista
    int vverif[5];
    int iverif;
    TElem e;

    Multime m1;
    assert(m1.adauga(5)==true);
    assert(m1.adauga(1)==true);
    assert(m1.adauga(10)==true);
    IteratorMultime im1 =  m1.iterator();
    im1.prim();
    iverif=0;
    e=im1.element();
    vverif[iverif++] = e;
    im1.urmator();
    while (im1.valid()) {
        assert(rel(e,im1.element()));
        e = im1.element();
        vverif[iverif++] = e;
        im1.urmator();
    }
    assert((vverif[0]==1) &&(vverif[1]==5)&&(vverif[2]==10));

    Multime m;
    //return;
    // test max min
    assert(m.diferentaMaxMin() == -1);

    assert(m.vida());
    assert(m.dim() == 0); //adaug niste elemente
    assert(m.adauga(5));
    assert(m.adauga(1)); // min
    assert(m.adauga(10)); // max
    assert(m.adauga(7));
    assert(!m.adauga(1));
    assert(!m.adauga(10));

    assert(m.diferentaMaxMin() == 9);

    assert(m.adauga(-3)); // min
    assert(m.dim() == 5);
    assert(m.cauta(10));
    assert(!m.cauta(16));
    assert(m.sterge(1));
    assert(!m.sterge(6));
    assert(m.dim() == 4);

    // test max min
    assert(m.diferentaMaxMin() == 13);

    IteratorMultime im = m.iterator();
    iverif=0;
    im.prim();
    e = im.element();
    vverif[iverif++] = e;
    im.urmator();
    while (im.valid()) {
        assert(rel(e,im.element()));
        e = im.element();
        vverif[iverif++] = e;
        im.urmator();
    }
    assert((vverif[3]==10) &&(vverif[2]==7)&&(vverif[1]==5)&&(vverif[0]==-3));
}
