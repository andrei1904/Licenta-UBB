#include "cos_produse.h"

#include <random>
#include <chrono>
#include <numeric>


int CosProdus::adauga(const Produs &produs) {
    in_cos.push_back(produs);
    return std::accumulate(in_cos.begin(), in_cos.end(), 0, [](int rez, const Produs &produs) {
        return rez + produs.get_pret();
    });
}

int CosProdus::goleste() {
    in_cos.clear();
    return std::accumulate(in_cos.begin(), in_cos.end(), 0, [](int rez, const Produs &produs) {
        return rez + produs.get_pret();
    });
}

int CosProdus::umple(size_t numar_produse, std::vector<Produs> toate) {
    auto seed = std::chrono::steady_clock::now().time_since_epoch().count();
    std::shuffle(toate.begin(), toate.end(), std::default_random_engine(seed));

    std::mt19937 mt{ std::random_device{}() };
    std::uniform_int_distribution<> dist(0, toate.size() - 1);

    while (in_cos.size() < numar_produse && !toate.empty()) {
        in_cos.push_back(toate.at(dist(mt)));
    }

    return std::accumulate(in_cos.begin(), in_cos.end(), 0, [](int rez, const Produs &produs) {
        return rez + produs.get_pret();
    });
}

const std::vector<Produs> &CosProdus::get_cos() const{
    return in_cos;
}


