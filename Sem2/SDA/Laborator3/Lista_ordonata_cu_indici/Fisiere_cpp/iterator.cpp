#include "../Header/iterator.h"
#include "../Header/lista_ordonata.h"

#include <exception>

/*
 * Complexitate timp: ϴ(1)
 */
Iterator::Iterator(const LO& lo) : lista(lo){
    this->curent = lo.prim;
}

/*
 * Complexitate timp: ϴ(1)
 */
void Iterator::prim() {
    this->curent = lista.prim;
}

/*
 * Complexitate timp: ϴ(1)
 */
void Iterator::urmator(){
    if (!valid()) {
        throw std::exception();
    }
    this->curent = this->curent->urmator();
}

/*
 * Complexitate timp: ϴ(1)
 */
bool Iterator::valid() const{
    return curent != nullptr;
}

/*
 * Complexitate timp: ϴ(1)
 */
TElement Iterator::element() const{
    return curent->element();
}

/*
 * Complexitate timp: ϴ(1)
 */
void Iterator::anterior() {
    if (!valid()) {
        throw std::exception();
    }
    this->curent = this->curent->precedent();
}


