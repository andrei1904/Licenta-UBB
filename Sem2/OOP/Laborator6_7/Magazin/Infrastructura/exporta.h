#ifndef MAGAZIN_EXPORTA_H
#define MAGAZIN_EXPORTA_H

#include "../Domeniu/produs.h"

#include <vector>
#include <string>

/*
 * scire in fisierul nume_fisier lista de produse in format HTML
 */
void exporta_html(const std::string& nume_fisier, const std::vector<Produs>& produse);

#endif //MAGAZIN_EXPORTA_H