#include "IteratorMultime.h"
#include "Multime.h"

/*
 * Complexitate timp: O(h), h = inaltime arborelui
 */
IteratorMultime::IteratorMultime(const Multime &m) : mult(m) {
    if (mult.radacina != nullptr) {
        poz_curenta = Multime::minim(mult.radacina);
    } else {
        poz_curenta = nullptr;
    }
}

/*
 * Complexitate timp: ϴ(1)
 */
TElem IteratorMultime::element() const {
    if (!valid()) {
        throw;
    }
    return poz_curenta->get_element();
}

/*
 * Complexitate timp: ϴ(1)
 */
bool IteratorMultime::valid() const {
    return poz_curenta != nullptr;
}

/*
 * Complexitate timp: O(h), h = inaltime arborelui
 */
void IteratorMultime::urmator() {
    if (!valid()) {
        throw;
    }
    poz_curenta = Multime::succesor(poz_curenta);
}

/*
 * Complexitate timp: O(h), h = inaltime arborelui
 */
void IteratorMultime::prim() {
    if (mult.radacina != nullptr) {
        poz_curenta = Multime::minim(mult.radacina);
    } else {
        poz_curenta = nullptr;
    }
}

