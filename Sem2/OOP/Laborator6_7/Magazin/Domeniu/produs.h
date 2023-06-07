#ifndef MAGAZIN_PRODUS_H
#define MAGAZIN_PRODUS_H

#include <string>
#include <iostream>
#include <utility>

using std::string;
using std::cout;

class Produs {

private:
    string nume;
    string tip;
    int pret = 0;
    string producator;

public:
    Produs(string n, string t, const int p, string prod) : nume{std::move(n)}, tip{std::move(t)}, pret{p},
                                                           producator{std::move(prod)} {
    }

    Produs() = default;

    ~Produs() = default;

    string get_nume() const {
        return nume;
    }

    string get_tip() const {
        return tip;
    }

    int get_pret() const noexcept {
        return pret;
    }

    string get_producator() const {
        return producator;
    }

    void set_nume(const string& val) {
        nume = val;
    }

    void set_tip(const string& val) {
        tip = val;
    }

    void set_pret(int p) {
        pret = p;
    }

    void set_producator(const string& val) {
        producator = val;
    }
};

void test_entitati();

#endif //MAGAZIN_PRODUS_H
