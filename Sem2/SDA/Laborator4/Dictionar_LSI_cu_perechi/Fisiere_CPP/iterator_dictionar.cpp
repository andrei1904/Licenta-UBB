#include "../Header/iterator_dictionar.h"
#include "../Header/dictionar.h"

using namespace std;

/*
 * Complexitate timp: ϴ(1)
 */
IteratorDictionar::IteratorDictionar(const Dictionar& d) : dict(d){
    pozitie = d.prim;
}

/*
 * Complexitate timp: ϴ(1)
 */
void IteratorDictionar::prim() {
    pozitie = dict.prim;
}

/*
 * Complexitate timp: ϴ(1)
 */
void IteratorDictionar::urmator() {
    pozitie = dict.urm[pozitie];
}

/*
 * Complexitate timp: ϴ(1)
 */
TElem IteratorDictionar::element() const{
    if (!valid()) {
        throw;
    }
    return dict.e[pozitie];
}

/*
 * Complexitate timp: ϴ(1)
 */
bool IteratorDictionar::valid() const {
    return pozitie != -1;
}

