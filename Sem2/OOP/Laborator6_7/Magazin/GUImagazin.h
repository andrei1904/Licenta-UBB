#pragma once

#include <QtWidgets/QMainWindow>
#include <QtWidgets/QLabel>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QBoxLayout>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QFormLayout>
#include <QtWidgets/QListWidget>
#include <QtWidgets/QMessageBox>
#include <QtWidgets/qmainwindow.h>
#include <QtWidgets/QStyle>
#include <QtWidgets/QDesktopWidget>
#include <QtWidgets/QSpinBox>
#include <QtWidgets/QDialog>
#include <QtWidgets/QTabWidget>
#include <QtWidgets/QTableWidget>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QButtonGroup>
#include <QtWidgets/qlayoutitem.h>
#include <qpainter.h>

#include <vector>
#include <string>
#include <random>

#include "Bussines/service.h"

using std::vector;
using std::string;

class GUImagazin : public QTabWidget {

public:
    GUImagazin(ServiceMagazin& srv) : srv{ srv } {
        init_gui();
        load_data(srv.get_all());
        init_connect();
        load_data_butoane(srv.get_all());
    }

private:
    ServiceMagazin& srv;
    QListWidget* lista = new QListWidget;
    QTableWidget* tabel = new QTableWidget(0, 4);
    QButtonGroup* lista_butoane = new QButtonGroup;
    QVBoxLayout* layout_btn = new QVBoxLayout;

    QPushButton* btn_exit = new QPushButton{ "&Exit" };
    QPushButton* btn_adauga = new QPushButton{ "&Adauga" };
    QPushButton* btn_sterge = new QPushButton{ "&Sterge" };
    QPushButton* btn_modifica = new QPushButton{ "&Modifica" };
    QPushButton* btn_sorteaza = new QPushButton{ "&Sorteaza" };
    QPushButton* btn_filtreaza = new QPushButton{ "&Filtreaza" };
    QPushButton* btn_afiseaza = new QPushButton{ "&Afiseaza produsele" };
    QPushButton* btn_undo = new QPushButton{ "&Undo" };
    QPushButton* btn_adauga_cos_produs = new QPushButton{ "&Adauga in cos" };
    QPushButton* btn_afiseaza_cos_crud = new QPushButton{ "&CosCRUD" };
    QPushButton* btn_afiseaza_cos_readonly = new QPushButton{ "&CosReadOnly" };
    QLabel* label_pret = new QLabel;


    QLineEdit* text_nume = new QLineEdit;
    QLineEdit* text_tip = new QLineEdit;
    QDoubleSpinBox* text_pret = new QDoubleSpinBox;
    QLineEdit* text_producator = new QLineEdit;



    void init_gui();

    void load_data(vector<Produs> toate_produsele);

    void load_data_butoane(vector<Produs> toate_produsele);

    void load_cos(vector<Produs> produse_cos);

    void init_connect();

    void clear_layout(QLayout* layout);
};


class SortareGUI : public QDialog {
public:
    SortareGUI(ServiceMagazin& srv, QListWidget* lista, QVBoxLayout* layout_btn) : srv{ srv }, lista{ lista }, layout_btn{ layout_btn } {
        init_gui();
        init_connect();
    }

private:
    ServiceMagazin& srv;
    QVBoxLayout* layout_btn = new QVBoxLayout;
    QListWidget* lista = new QListWidget;
    QPushButton* btn_sort_nume = new QPushButton{ "&Sorteaza dupa nume" };
    QPushButton* btn_sort_pret = new QPushButton{ "&Sorteaza dupa pret" };
    QPushButton* btn_sort_nume_tip = new QPushButton{ "&Sorteaza dupa nume si tip" };
    QPushButton* btn_anuleaza = new QPushButton{ "&Anuleaza" };

    void init_gui();

    void load_data(vector<Produs> toate_produsele);

    void init_connect();

    void load_data_butoane(vector<Produs> toate_produsele);

    void clear_layout(QLayout* layout);
};

class FiltrareGUI : public QDialog {
public:
    FiltrareGUI(ServiceMagazin& srv, QListWidget* lista) : srv{ srv }, lista{ lista } {
        init_gui();
        init_connect();
    }

private:
    ServiceMagazin& srv;
    QListWidget* lista = new QListWidget;
    QPushButton* btn_filtr_nume = new QPushButton{ "&Filtreaza dupa nume" };
    QPushButton* btn_filtr_pret = new QPushButton{ "&Filtreaza dupa pret" };
    QPushButton* btn_filtr_producator = new QPushButton{ "&Filtreaza dupa producator" };
    QPushButton* btn_anuleaza = new QPushButton{ "&Anuleaza" };
    QLineEdit* text_nume = new QLineEdit;
    QLineEdit* text_tip = new QLineEdit;
    QDoubleSpinBox* text_pret_minim = new QDoubleSpinBox;
    QDoubleSpinBox* text_pret_maxim = new QDoubleSpinBox;
    QLineEdit* text_producator = new QLineEdit;


    void init_gui();

    void load_data(vector<Produs> toate_produsele);

    void init_connect();
};


class ModificaGUI : public QDialog {
public:
    ModificaGUI(ServiceMagazin& srv, QListWidget* lista, QVBoxLayout* layout_btn) : srv{ srv }, lista{ lista }, layout_btn{ layout_btn } {
        init_gui();
        init_connect();
    }

private:
    QVBoxLayout* layout_btn = new QVBoxLayout;
    ServiceMagazin& srv;
    QListWidget* lista = new QListWidget;
    QPushButton* btn_modifica = new QPushButton{ "&Modifica" };
    QPushButton* btn_anuleaza = new QPushButton{ "&Anuleaza" };
    QLineEdit* text_nume_vechi = new QLineEdit;
    QLineEdit* text_nume = new QLineEdit;
    QLineEdit* text_tip = new QLineEdit;
    QLineEdit* text_producator = new QLineEdit;
    QDoubleSpinBox* text_pret = new QDoubleSpinBox;


    void init_gui();

    void load_data(vector<Produs> toate_produsele);

    void init_connect();

    void load_data_butoane(vector<Produs> toate_produsele);

    void clear_layout(QLayout* layout);
};


class PopuleazaCosGUI : public QDialog {
public:
    PopuleazaCosGUI(ServiceMagazin& srv, QTableWidget* tabel, QLabel* label_pret) : srv{ srv },
                                                                                    tabel{ tabel }, label_pret{ label_pret } {
        init_gui();
        init_connect();
    }

private:
    QTableWidget* tabel;
    ServiceMagazin& srv;
    QLabel* label_pret;
    QLineEdit* text_populeaza = new QLineEdit;
    QPushButton* btn_populeaza = new QPushButton{ "&Populeaza" };
    QPushButton* btn_anuleaza = new QPushButton{ "&Anuleaza" };

    void init_gui();

    void init_connect();

    void load_cos(vector<Produs> produse_cos);
};


class AdaugaCosGUI : public QDialog {
public:
    AdaugaCosGUI(ServiceMagazin& srv, QTableWidget* tabel, QLabel* label_pret) : srv{ srv },
                                                                                 tabel{ tabel }, label_pret{ label_pret } {
        init_gui();
        init_connect();
    }

private:
    QTableWidget* tabel;
    ServiceMagazin& srv;
    QLabel* label_pret;
    QLineEdit* text_nume = new QLineEdit;
    QPushButton* btn_adauga = new QPushButton{ "&Adauga" };
    QPushButton* btn_anuleaza = new QPushButton{ "&Anuleaza" };

    void init_gui();

    void init_connect();

    void load_cos(vector<Produs> produse_cos);
};

class CosCRUD : public QDialog, public Observer {
public:

    CosCRUD(ServiceMagazin& srv) : srv{ srv } {
        init_gui();
        init_connect();
        load_cos(srv.srv_get_cos());
    };

    void update() override {
        load_cos(srv.srv_get_cos());
    }

    ~CosCRUD() {
        srv.sterge_observer(this);
    }

private:

    QTableWidget* tabel = new QTableWidget(0, 4);
    ServiceMagazin& srv;
    QPushButton* btn_exit_cos = new QPushButton{ "&Exit" };
    QPushButton* btn_adauga_cos = new QPushButton{ "&Adauga in cos" };
    QPushButton* btn_goleste_cos = new QPushButton{ "&Goleste cosul " };
    QPushButton* btn_populeaza_cos = new QPushButton{ "&Populeaza cosul" };
    QLabel* label_pret = new QLabel;
    QTableWidgetItem* item_nume = new QTableWidgetItem("Nume");
    QTableWidgetItem* item_tip = new QTableWidgetItem("Tip");
    QTableWidgetItem* item_pret = new QTableWidgetItem("Pret");
    QTableWidgetItem* item_producator = new QTableWidgetItem("Producator");
    QPushButton* btn_export = new QPushButton{ "&Exporta" };

    void init_gui();

    void init_connect();

    void load_cos(vector<Produs> produse_cos);

};

class CosReadOnly : public QDialog, public Observer {

private:
    ServiceMagazin& srv;


public:
    CosReadOnly(ServiceMagazin& srv) : srv{ srv } {
        srv.adauga_observer(this);
        setWindowTitle("CodReadOnly");
        resize(400, 400);
    }

    ~CosReadOnly() {
        srv.sterge_observer(this);
    }

    void update() override {
        repaint();
    }

    void paintEvent(QPaintEvent* ev) override {
        QPainter p{ this };
        QPen pen;
        pen.setWidth(5);
        p.setBrush(Qt::BDiagPattern);

        const int minim = 40;
        const int maxim = 360;
        int x = 10;
        for (const auto& produs : srv.srv_get_cos()) {
            int poz_x = (rand() % (maxim - minim + 1)) + minim;
            int poz_y = (rand() % (maxim - minim + 1)) + minim;

            if (x % 3 == 0) {
                pen.setColor(Qt::blue);
                p.setPen(pen);

                p.drawRect(poz_x, poz_y, 40, 40);
                x++;
            }
            else if (x % 3 == 1) {
                pen.setColor(Qt::red);
                p.setPen(pen);

                p.drawEllipse(poz_x, poz_y, 40, 40);
                x++;
            }
            else {
                pen.setColor(Qt::green);
                p.setPen(pen);

                p.drawRoundRect(poz_x, poz_y, 40, 40);
                x++;
            }
        }
    }
};