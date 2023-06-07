//
// Created by radua on 6/18/2020.
//

#ifndef EXAMEN_MELODIEGUI_H
#define EXAMEN_MELODIEGUI_H

#include "MelodieController.h"
#include <QMessageBox>
#include <QTableView>
#include <QAbstractTableModel>
#include <utility>
#include <QBoxLayout>
#include <QPushButton>
#include <QFormLayout>
#include <QLineEdit>
#include <QLabel>
#include <QPainter>

class ModelTabel : public QAbstractTableModel {
private:
    vector<Melodie> melodii;

public:
    explicit ModelTabel(vector<Melodie> m) : melodii{std::move( m )} {};

    // numar linii
    int rowCount(const QModelIndex& parent = QModelIndex()) const override {
        return melodii.size();
    }

    // numar coloane
    int columnCount(const QModelIndex& parent = QModelIndex()) const override {
        return 6;
    }

    // initializeaza tabelu
    QVariant data(const QModelIndex& index, int role = Qt::DisplayRole) const override {
        if (role == Qt::DisplayRole) {
            Melodie m = melodii[index.row()];

            if (index.column() == 0) {
                return QString::number(m.getId());
            } else if (index.column() == 1) {
                return QString::fromStdString(m.getTitlu());
            } else if (index.column() == 2) {
                return QString::fromStdString(m.getArtist());
            } else if (index.column() == 3) {
                return QString::fromStdString(m.getGen());
            } else if (index.column() == 4) {
                // acelasi autor
//                int nr = std::count(melodii.begin(), melodii.end(), [&](const Melodie& melodie){
//                    return melodie.getArtist() == m.getArtist();
//                });
                int nr = 0;
                for (const auto& melodie : melodii) {
                    if (melodie.getArtist() == m.getArtist()) {
                        nr++;
                    }
                }
                nr -= 1;
                return nr;
            } else if (index.column() == 5) {
                // acelasi gen
//                int nr = std::count(melodii.begin(), melodii.end(), [&](const Melodie& melodie){
//                    return melodie.getGen() == m.getGen();
//                });
                int nr = 0;
                for (const auto& melodie : melodii) {
                    if (melodie.getGen() == m.getGen()) {
                        nr++;
                    }
                }
                nr -= 1;
                return nr;
            }
        }
        return QVariant{};
    }

    // schimba melodiile
    void setMelodii(const vector<Melodie>& m) {
        this->melodii = m;
        auto susStanga = createIndex(0, 0);
        auto josDreapta = createIndex(rowCount(), columnCount());
        emit dataChanged(susStanga, josDreapta);
    }
};

class MelodieGui : public QWidget, public Observer{
public:
    explicit MelodieGui(MelodieController& ctr) : ctr{ctr} {
        initInterfata();
        ctr.adaugaObserver(this);
        model = new ModelTabel{ ctr.getAll() };
        tabel->setModel(model);
        load();
        connect();
        numara();
        repaint();
    }

private:
    MelodieController ctr;

    QTableView* tabel = new QTableView;
    ModelTabel* model;

    QPushButton* btn_adauga = new QPushButton("&Adauga");
    QPushButton* btn_sterge = new QPushButton("&Sterge");
    QLineEdit* text_titlu = new QLineEdit;
    QLineEdit* text_artist = new QLineEdit;
    QLineEdit* text_gen = new QLineEdit;
    int id = -1;
    int folk = 0;
    int rock = 0;
    int pop = 0;
    int disco = 0;


    // initializeaza interfata grafica
    void initInterfata();

    // reincarca datele in tabel
    void load();

    // conecteaza evenimentele
    void connect();

    // deseneaza 4 cercuri
    void paintEvent(QPaintEvent* ev) override;

    // numara cate melodii sunt din fiecare gen
    void numara();

    // update
    void update() override ;
};


#endif //EXAMEN_MELODIEGUI_H
