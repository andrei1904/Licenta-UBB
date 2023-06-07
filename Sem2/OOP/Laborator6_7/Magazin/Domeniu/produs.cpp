#include "produs.h"

#include <cassert>


void test_get() {
    Produs p{"da", "da", 10, "da"};

    assert(p.get_nume() == "da");
    assert(p.get_tip() == "da");
    assert(p.get_pret() == 10);
    assert(p.get_producator() == "da");
}

void test_set() {
    Produs p{"da", "da", 10, "da"};

    assert(p.get_nume() == "da");
    assert(p.get_tip() == "da");
    assert(p.get_pret() == 10);
    assert(p.get_producator() == "da");

    p.set_nume("nu");
    p.set_tip("nu");
    p.set_pret(20);
    p.set_producator("nu");

    assert(p.get_nume() == "nu");
    assert(p.get_tip() == "nu");
    assert(p.get_pret() == 20);
    assert(p.get_producator() == "nu");
}

void test_entitati() {
    test_get();
    test_set();
}