#ifndef MAGAZIN_UNDO_H
#define MAGAZIN_UNDO_H

#include "../Domeniu/produs.h"
#include "repository.h"

class ActiuneUndo {
public:
    virtual void do_undo() = 0;

    virtual ~ActiuneUndo() = default;
};

class UndoAdauga : public ActiuneUndo {
private:
    Produs produs_adaugat;
    RepoMagazin &repo;

public:
    UndoAdauga(RepoMagazin &repo, const Produs &produs) : repo{repo}, produs_adaugat{produs} {}

    void do_undo() override {
        repo.sterge(produs_adaugat);
    }
};

class UndoSterge : public ActiuneUndo {
private:
    Produs produs_sters;
    RepoMagazin &repo;

public:
    UndoSterge(RepoMagazin &repo, const Produs &produs) : repo{repo}, produs_sters{produs} {}

    void do_undo() override {
        repo.adauga(produs_sters);
    }
};

class UndoModifica : public ActiuneUndo {
private:
    Produs produs_modificat;
    Produs produs_nou;
    RepoMagazin &repo;

public:
    UndoModifica(RepoMagazin &repo, const Produs &produs_modificat, const Produs &produs_nou) :
            repo{repo}, produs_modificat{produs_modificat}, produs_nou{produs_nou} {}

    void do_undo() override {
        repo.modifica(produs_nou, produs_modificat);
    }
};

#endif //MAGAZIN_UNDO_H
