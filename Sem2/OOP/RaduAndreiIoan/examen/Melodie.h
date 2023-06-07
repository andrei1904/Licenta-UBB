//
// Created by radua on 6/18/2020.
//

#ifndef EXAMEN_MELODIE_H
#define EXAMEN_MELODIE_H

#include <string>
#include <utility>
#include <cassert>

using std::string;

// reprezina o melodie ce contine id(int), titlu(string), artist(string), gen(string)
class Melodie {
    int id;
    string titlu;
    string artist;
    string gen;

public:
    // constructor
    Melodie(int id, string titlu, string artist, string gen) : id{id}, titlu{std::move(titlu)},
        artist{std::move(artist)}, gen{std::move(gen)} {};

    int getId() const;

    void setId(int id);

    const string &getTitlu() const;

    void setTitlu(const string &titlu);

    const string &getArtist() const;

    void setArtist(const string &artist);

    const string &getGen() const;

    void setGen(const string &gen);
};

// capteaza mesajele de eroare aruncate in timpul derularii programului
class MelodieException : std::exception {
private:
    string mesaj;
public:
    // constructor
    explicit MelodieException(string msg) : mesaj{std::move( msg )} {};

    // returneaza mesajul de eroare
    string getMesaj() const {
        return mesaj;
    }
};

void test_melodie();


#endif //EXAMEN_MELODIE_H
