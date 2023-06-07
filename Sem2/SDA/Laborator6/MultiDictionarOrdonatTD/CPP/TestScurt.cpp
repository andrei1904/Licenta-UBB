#include <assert.h>

#include "../Header/MDO.h"
#include "../Header/IteratorMDO.h"

#include <exception>
#include <vector>
#include <iostream>

using std::cout;

using namespace std;

bool relatie1(TCheie cheie1, TCheie cheie2) {
    if (cheie1 <= cheie2) {
        return true;
    }
    else {
        return false;
    }
}

void test_goleste() {
    MDO dict = MDO(relatie1);

    dict.adauga(1, 10);
    dict.adauga(1, 15);
    dict.adauga(2, 20);
    dict.adauga(2, 3);
    dict.adauga(2, 5);

    assert(dict.dim() == 5);

    dict.goleste();
    assert(dict.dim() == 0);

    dict.adauga(1, 10);
    dict.adauga(14, 20);

    assert(dict.dim() == 2);

    dict.goleste();
    assert(dict.dim() == 0);
}

void testAll(){
    MDO dictOrd = MDO(relatie1);
    assert(dictOrd.dim() == 0);
    assert(dictOrd.vid());
    dictOrd.adauga(1,2);
    dictOrd.adauga(1,3);
    assert(dictOrd.dim() == 2);
    assert(!dictOrd.vid());
    vector<TValoare> v= dictOrd.cauta(1);
    assert(v.size()==2);
    v= dictOrd.cauta(3);
    assert(v.empty());
    IteratorMDO it = dictOrd.iterator();
    it.prim();
    while (it.valid()){
        TElem e = it.element();
        it.urmator();
    }
    assert(dictOrd.sterge(1, 2));
    assert(dictOrd.sterge(1, 3));
    assert(!dictOrd.sterge(2, 1));
    assert(dictOrd.vid());

    test_goleste();
}

