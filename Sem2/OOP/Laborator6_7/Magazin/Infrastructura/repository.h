#ifndef MAGAZIN_REPOSITORY_H
#define MAGAZIN_REPOSITORY_H

#include "../Domeniu/produs.h"
#include "../Domeniu/lista.h"

#include <utility>
#include <string>
#include <ostream>
#include <vector>
#include <map>

using std::string;
using std::ostream;

class Repo {
public:
    virtual ~Repo() = default;

    virtual bool exista(const Produs &p) const = 0;

    virtual void adauga(const Produs &p) = 0;

    virtual void sterge(const Produs &p) = 0;

    virtual void modifica(const Produs &p_vechi, const Produs &p_nou) = 0;

    virtual const Produs& cauta_produs(const Produs& p) const = 0;

    virtual std::vector<Produs> get_all() const = 0;
};

class RepoMagazin: public Repo{
    std::map<string, Produs> produse;

    /*
     * verifica daca exista deja produsul p in repo
     */
    bool exista(const Produs &p) const override;

public:
    RepoMagazin() = default;

    /*
     * nu este permisa copierea de obiecte
     */
    RepoMagazin(const RepoMagazin &ot) = delete;

    /*
     * adauga produs in repo
     * arunca exceptie daca produsul exista deja
     */
    void adauga(const Produs &p) override ;

    /*
     * sterge un produs din repo
     * arunca exceptie daca produsul nu exista
     */
    void sterge(const Produs &p) override ;

    /*
     * modfica produsl vechi cu cel nou
     */
    void modifica(const Produs &p_vechi, const Produs &p) override ;

    /*
     * cauta produsul
     * arunca exceptie daca nu exista produsul
     */
    const Produs& cauta_produs(const Produs& p) const override ;

    /*
     * returneaza pozitia pe care se afla produsul
     * arunca exceptie daca nu exista produsul
     */
//    int cauta_pozitie(const Produs& p) const;

    /*
     * returneaza toate produsele adaugate
     */
    std::vector<Produs> get_all() const override ;
};

class RepoMagazinException : std::exception {
    string mesaj;
public:
    explicit RepoMagazinException(string mesaj) : mesaj{std::move(mesaj)} {}

    friend ostream &operator<<(ostream &out, const RepoMagazinException &eroare);
};

ostream &operator<<(ostream &out, const RepoMagazinException &eroare);

void test_repository();

#endif //MAGAZIN_REPOSITORY_H
