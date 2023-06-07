#include "validator.h"

#include <cassert>
#include <sstream>

void ValidatorProdus::valideaza(const Produs &p) {
    vector<string> mesaj;

    if (p.get_nume().empty())
        mesaj.emplace_back("Nume invalid!\n");
    if (p.get_tip().empty())
        mesaj.emplace_back("Tip invalid!\n");
    if (p.get_pret() < 0)
        mesaj.emplace_back("Pret invalid!\n");
    if (p.get_producator().empty())
        mesaj.emplace_back("Producator invalid!\n");
    if (!mesaj.empty())
        throw ValidatorException(mesaj);}

ostream &operator<<(ostream &out, const ValidatorException &erori) {
    for (const auto &mesaj : erori.mesaj) {
        out << mesaj << " ";
    }
    return out;
}

void test_validator() {
    ValidatorProdus v;
    Produs p{"", "", -10, ""};

    try {
        v.valideaza(p);
    }
    catch (const ValidatorException &erori) {
        std::stringstream sout;
        sout << erori;
        auto mesaj = sout.str();
        assert(mesaj.find("invalid") >= 0);
    }
}
