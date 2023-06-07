#pragma once

#include <vector>

using std::vector;

class Observer {
public:
	virtual void update() = 0;
};

class Observable {
private:
	vector<Observer*> observers;

public:
	void adauga_observer(Observer* obs) {
		observers.push_back(obs);
	}

	void sterge_observer(Observer* obs) {
		observers.erase(std::remove(observers.begin(), observers.end(), obs), observers.end());
	}

	void notify() {
		for (auto obs : observers) {
			obs->update();
		}
	}
};