#include <cmath>

#include "Header/IteratorMultime.h"
#include "Header/Multime.h"


IteratorMultime::IteratorMultime(const Multime &m) : multime(m) {
    pozitie = 0;
}

/*
 * Complexitate timp: ϴ(1)
 */
void IteratorMultime::prim() {
    pozitie = 0;
}

/*
 * Complexitate timp: ϴ(1)
 */
void IteratorMultime::urmator() {
    if (!valid())
        throw (std::exception());
    pozitie += 1;
}

/*
 * Complexitate timp: ϴ(1);
 */
TElem IteratorMultime::element() const {
    if (!valid())
        throw (std::exception());
    return multime.vector[pozitie];
}

/*
 * Complexitate timp: ϴ(1)
 */
bool IteratorMultime::valid() const {
    return pozitie >= 0 && pozitie < multime.dimensiune;
}
