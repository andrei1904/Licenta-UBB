#include <assert.h>

#include "../Header/iterator.h"
#include "../Header/lista_ordonata.h"


bool relatie1(TElement cheie1, TElement cheie2) {
    if (cheie1 <= cheie2) {
        return true;
    }
    else {
        return false;
    }
}

void testAll(){
    LO lo = LO(relatie1);
    assert(lo.dim() == 0);
    assert(lo.vida());
    lo.adauga(1);
    assert(lo.dim() == 1);
    assert(!lo.vida());
    Iterator iterator = lo.iterator();
    assert(iterator.valid());
    iterator.prim();
    assert(iterator.element() == 1);
    iterator.urmator();
    assert(!iterator.valid());
    iterator.prim();
    assert(iterator.valid());
    assert(lo.cauta(1) == 0);
    assert(lo.sterge(0) == 1);
    assert(lo.dim() == 0);
    assert(lo.vida());

    // test anterior
    lo.adauga(1);
    lo.adauga(2);
    iterator.prim();
    assert(iterator.element() == 1);
    iterator.urmator();
    assert(iterator.element() == 2);
    iterator.anterior();
    assert(iterator.element() == 1);
    iterator.anterior();
    assert(!iterator.valid());
}

