//
// Created by radua on 6/18/2020.
//

#ifndef EXAMEN_OBSERVER_H
#define EXAMEN_OBSERVER_H

#include <vector>

using std::vector;

class Observer {
public:
    virtual void update() = 0;
};

class Observabil {
private:
    vector<Observer*> observeri;

public:
    void adaugaObserver(Observer* o) {
        observeri.push_back(o);
    }

    void notifica() {
        for (auto o : observeri) {
            o->update();
        }
    }
};

#endif //EXAMEN_OBSERVER_H
