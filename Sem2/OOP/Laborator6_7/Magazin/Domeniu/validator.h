#ifndef MAGAZIN_VALIDATOR_H
#define MAGAZIN_VALIDATOR_H

#include <string>
#include <utility>
#include <vector>

#include "produs.h"

using std::string;
using std::vector;
using std::ostream;

class ValidatorException: std::exception {
    vector<string> mesaj;

public:
    explicit ValidatorException(vector<string>  erori) :mesaj{std::move( erori )} {}

    friend ostream& operator<<(ostream& out, const ValidatorException& erori);
};

ostream& operator<<(ostream& out, const ValidatorException& erori);

class ValidatorProdus {
public:
    void valideaza(const Produs& p);
};

void test_validator();

#endif //MAGAZIN_VALIDATOR_H