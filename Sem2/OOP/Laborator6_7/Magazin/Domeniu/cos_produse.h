#ifndef MAGAZIN_COS_PRODUSE_H
#define MAGAZIN_COS_PRODUSE_H

#include "produs.h"

#include <vector>
#include <algorithm>

/*
 * clasa care implementeaza un cos
 */
class CosProdus {
    std::vector<Produs> in_cos;

public:
    CosProdus() = default;

    /*
     * functie care adauga un produs in cos
     */
    int adauga(const Produs& produs);

    /*
     * functie care goleste cosul
     */
    int goleste();

    /*
     * functie care umple cosul cu produse aleatoare
     */
    int umple(size_t numar_produse, std::vector<Produs> toate);

    /*
     * returneaza o lista cu produsele din cos
     */
    const std::vector<Produs>& get_cos() const;

};

#endif //MAGAZIN_COS_PRODUSE_H
