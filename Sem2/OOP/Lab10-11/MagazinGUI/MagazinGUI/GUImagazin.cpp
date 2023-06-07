#include "GUImagazin.h"

void GUImagazin::init_gui()
{
    /*
    QHBoxLayout* main_layout = new QHBoxLayout;
    main_layout->setSpacing(20);
    main_layout->setMargin(30);
    setLayout(main_layout);*/
    resize(700, 400);
    setWindowTitle("Magazin");

    // tab produse   
    QWidget* produse = new QWidget;

    QGridLayout* grid_layout = new QGridLayout();
    produse->setLayout(grid_layout);

    grid_layout->addWidget(lista, 0, 0, 1, 1);

    auto up_right_layout = new QVBoxLayout;

    auto form_layout = new QFormLayout;
    form_layout->addRow("Nume: ", text_nume);
    form_layout->addRow("Tip: ", text_tip);
    text_pret->setMaximum(999999999);
    form_layout->addRow("Pret: ", text_pret);
    form_layout->addRow("Producator: ", text_producator);
    up_right_layout->addLayout(form_layout);

    auto button_layout = new QHBoxLayout;
    button_layout->addWidget(btn_adauga);
    button_layout->addWidget(btn_sterge);
    button_layout->addWidget(btn_modifica);
    up_right_layout->addLayout(button_layout);

    auto button_layout2 = new QHBoxLayout;
    button_layout2->addWidget(btn_sorteaza);
    button_layout2->addWidget(btn_undo);
    button_layout2->addWidget(btn_filtreaza);
    up_right_layout->addLayout(button_layout2);

    auto button_layout_exit = new QVBoxLayout;
    auto button_layout_in_dr = new QHBoxLayout;
    button_layout_in_dr->addWidget(btn_afiseaza);
    button_layout_in_dr->addWidget(btn_adauga_cos_produs);
    button_layout_exit->addLayout(button_layout_in_dr);

    auto button_layout_in_dr_cos = new QHBoxLayout;
    button_layout_in_dr_cos->addWidget(btn_afiseaza_cos_crud);
    button_layout_in_dr_cos->addWidget(btn_afiseaza_cos_readonly);
    button_layout_exit->addLayout(button_layout_in_dr_cos);

    button_layout_exit->addWidget(btn_exit);
    up_right_layout->addLayout(button_layout_exit);

    grid_layout->addLayout(up_right_layout, 0, 1, 1, 1);
    
    lista_butoane->addButton(btn_exit);
    
    grid_layout->addLayout(layout_btn, 0, 2, 1, 1);

    addTab(produse, "Produse");

    /*
    // tab cos
    QWidget* cos = new QWidget;
    
    auto cos_layout = new QVBoxLayout;
    cos->setLayout(cos_layout);

    // tabel
    QStringList labels;
    labels << tr("Nume") << tr("Tip") << tr("Pret") << tr("Producator");
    tabel->setHorizontalHeaderLabels(labels);
    tabel->horizontalHeader()->setSectionResizeMode(0, QHeaderView::Stretch);
    tabel->verticalHeader()->hide();
    tabel->setShowGrid(true);
    cos_layout->addWidget(tabel);

    auto cos_layout_pret = new QHBoxLayout;
    label_pret->setText("Pretul este: 0");
    cos_layout_pret->addWidget(label_pret);
    cos_layout->addLayout(cos_layout_pret);

    auto cos_layout_butoane = new QHBoxLayout;
    cos_layout_butoane->addWidget(btn_adauga_cos);
    cos_layout_butoane->addWidget(btn_goleste_cos);
    cos_layout_butoane->addWidget(btn_populeaza_cos);
    cos_layout_butoane->addWidget(btn_export);
    cos_layout_butoane->addWidget(btn_exit_cos);
    cos_layout->addLayout(cos_layout_butoane);

    addTab(cos, "Cos");
    */
}

void GUImagazin::load_data(vector<Produs> toate_produsele) {
    lista->clear();

    for (const auto& produs : toate_produsele) {
        QListWidgetItem* item = new QListWidgetItem(QString::fromStdString(produs.get_nume()), lista);
        item->setData(Qt::UserRole, QString::fromStdString(produs.get_tip()));
        item->setData(Qt::UserRole + 1, QString::fromStdString(std::to_string(produs.get_pret())));
        item->setData(Qt::UserRole + 2, QString::fromStdString(produs.get_producator()));
        item->setBackground(QBrush{ Qt::lightGray, Qt::SolidPattern });
    }
}

void GUImagazin::clear_layout(QLayout* layout) {
    QLayoutItem* item;
    while ((item = layout->takeAt(0))) {
        if (item->layout()) {
            clear_layout(item->layout());
            delete item->layout();
        }
        if (item->widget()) {
            delete item->widget();
        }
        delete item;
    }
}

void GUImagazin::load_data_butoane(vector<Produs> toate_produsele) {
    clear_layout(layout_btn);

    for (const auto& produs : toate_produsele) {
        auto buton = new QPushButton{ QString::fromStdString(produs.get_nume()) };
        layout_btn->addWidget(buton);

        QObject::connect(buton, &QPushButton::clicked, [this, produs, buton]() {
            srv.srv_sterge_produs(produs.get_nume());

            load_data(srv.get_all());
            delete buton;
        });
    }
}

void GUImagazin::load_cos(vector<Produs> produse_cos) {
    tabel->model()->removeRows(0, tabel->rowCount());

    for (const auto& produs : produse_cos) {
        int row = tabel->rowCount();
        tabel->insertRow(row);
        QTableWidgetItem* item_n = new QTableWidgetItem;
        QTableWidgetItem* item_t = new QTableWidgetItem;
        QTableWidgetItem* item_p = new QTableWidgetItem;
        QTableWidgetItem* item_prod = new QTableWidgetItem;

        item_n->setText(QString::fromStdString(produs.get_nume()));
        item_t->setText(QString::fromStdString(produs.get_tip()));
        item_p->setText(QString::fromStdString(std::to_string(produs.get_pret())));
        item_prod->setText(QString::fromStdString(produs.get_producator()));

        tabel->setItem(row, 0, item_n);
        tabel->setItem(row, 1, item_t);
        tabel->setItem(row, 2, item_p);
        tabel->setItem(row, 3, item_prod);
    }
}

void GUImagazin::init_connect() {
    // exit
    QObject::connect(btn_exit, &QPushButton::clicked, [&]() {
        close();
    });

    // adauga
    QObject::connect(btn_adauga, &QPushButton::clicked, [&]() {
        auto nume = text_nume->text();
        auto tip = text_tip->text();
        auto pret = text_pret->text();
        auto producator = text_producator->text();

        if (nume.isEmpty() || tip.isEmpty() || pret.isEmpty() || producator.isEmpty()) {
            QMessageBox::information(this, "Info", "Date invalide");
        }
        else {
            try {
                srv.srv_adauga_produs(nume.QString::toStdString(), tip.QString::toStdString(), stoi(pret.QString::toStdString()), producator.QString::toStdString());
                load_data_butoane(srv.get_all());
            }
            catch (const RepoMagazinException& ex) {
                QMessageBox::information(this, "Info", "Acest produs exista deja");
            }

            load_data(srv.get_all());
        }
     });
    

    // sterge
    QObject::connect(btn_sterge, &QPushButton::clicked, [&]() {
        auto nume = text_nume->text();

        srv.srv_sterge_produs(nume.QString::toStdString());

        load_data(srv.get_all());
        load_data_butoane(srv.get_all());
     });

    // modifica
    QObject::connect(btn_modifica, &QPushButton::clicked, [&]() {
        ModificaGUI* window_modifica = new ModificaGUI(srv, lista, layout_btn);
        window_modifica->show();
     });


    // afisare detalii
    QObject::connect(lista, &QListWidget::itemSelectionChanged, [&]() {
        if (lista->selectedItems().isEmpty()) {
            text_nume->setText("");
            text_tip->setText("");
            text_pret->setValue(0);
            text_producator->setText("");
            return;
        }

        QListWidgetItem* selectat = lista->selectedItems().at(0);
        text_nume->setText(selectat->text());
        text_tip->setText(selectat->data(Qt::UserRole).toString());
        text_pret->setValue(selectat->data(Qt::UserRole + 1).toInt());
        text_producator->setText(selectat->data(Qt::UserRole + 2).toString());

    });

    // sorteaza
    QObject::connect(btn_afiseaza, &QPushButton::clicked, [&]() {
        load_data(srv.get_all());
        load_data_butoane(srv.get_all());
    });

    // sorteaza
    QObject::connect(btn_sorteaza, &QPushButton::clicked, [&]() {
        SortareGUI* window_sortare = new SortareGUI(srv, lista, layout_btn);
        window_sortare->show();
    });

    // filtreaza
    QObject::connect(btn_filtreaza, &QPushButton::clicked, [&]() {
        FiltrareGUI* window_filtrare = new FiltrareGUI(srv, lista);
        window_filtrare->show();
    });

    // undo 
    QObject::connect(btn_undo, &QPushButton::clicked, [&]() {
        try {
            srv.srv_undo();
            load_data(srv.get_all());
            load_data_butoane(srv.get_all());
        
        }
        catch (RepoMagazinException ex) {
            QMessageBox::information(this, "Info", "Nu se mai poate efectua undo!");
        }
    });

    // adauga in cos 2
    QObject::connect(btn_adauga_cos_produs, &QPushButton::clicked, [&]() {
        auto nume = text_nume->text();
        int pret = srv.srv_adauga_in_cos(nume.QString::toStdString());
        string mesaj = "Pretul este: " + std::to_string(pret);
        label_pret->setText(QString::fromStdString(mesaj));

        load_cos(srv.srv_get_cos());
        });

    QObject::connect(btn_afiseaza_cos_crud, &QPushButton::clicked, [this]() {
        auto window_cos = new CosCRUD{ srv };
        window_cos->show();
    });

    QObject::connect(btn_afiseaza_cos_readonly, &QPushButton::clicked, [this]() {
        auto window_cos = new CosReadOnly{ srv };
        window_cos->show();
        });
}


void SortareGUI::init_gui()
{
    setWindowTitle("Sorteaza");

    QHBoxLayout* main_layout = new QHBoxLayout{};
    main_layout->setSpacing(20);
    main_layout->setMargin(30);
    setLayout(main_layout);

    auto button_layout = new QVBoxLayout;
    button_layout->addWidget(btn_sort_nume);
    button_layout->addWidget(btn_sort_pret);
    button_layout->addWidget(btn_sort_nume_tip);
    button_layout->addWidget(btn_anuleaza);
    main_layout->addLayout(button_layout);
}

void SortareGUI::load_data(vector<Produs> toate_produsele)
{
    lista->clear();

    for (const auto& produs : toate_produsele) {
        QListWidgetItem* item = new QListWidgetItem(QString::fromStdString(produs.get_nume()), lista);
        item->setData(Qt::UserRole, QString::fromStdString(produs.get_tip()));
        item->setData(Qt::UserRole + 1, QString::fromStdString(std::to_string(produs.get_pret())));
        item->setData(Qt::UserRole + 2, QString::fromStdString(produs.get_producator()));
    }
}

void SortareGUI::clear_layout(QLayout* layout) {
    QLayoutItem* item;
    while ((item = layout->takeAt(0))) {
        if (item->layout()) {
            clear_layout(item->layout());
            delete item->layout();
        }
        if (item->widget()) {
            delete item->widget();
        }
        delete item;
    }
}

void SortareGUI::load_data_butoane(vector<Produs> toate_produsele) {
    clear_layout(layout_btn);

    for (const auto& produs : toate_produsele) {
        auto buton = new QPushButton{ QString::fromStdString(produs.get_nume()) };
        layout_btn->addWidget(buton);

        QObject::connect(buton, &QPushButton::clicked, [this, produs, buton]() {
            srv.srv_sterge_produs(produs.get_nume());

            load_data(srv.get_all());
            delete buton;
            });
    }
}

void SortareGUI::init_connect()
{
    // sorteaza dupa nume
    QObject::connect(btn_sort_nume, &QPushButton::clicked, [&]() {
        load_data(srv.srv_sortare_nume());
        load_data_butoane(srv.srv_sortare_nume());
        close();
    });

    // sorteaza dupa pret
    QObject::connect(btn_sort_pret, &QPushButton::clicked, [&]() {
        load_data(srv.srv_sortare_pret());
        load_data_butoane(srv.srv_sortare_pret());
        close();
     });

    // sorteaza dupa nume tip
    QObject::connect(btn_sort_nume_tip, &QPushButton::clicked, [&]() {
        load_data(srv.srv_sortare_nume_tip());
        load_data_butoane(srv.srv_sortare_nume_tip());
        close();
    });

    // anuleaza
    QObject::connect(btn_anuleaza, &QPushButton::clicked, [&]() {
        close();
     });
}

void FiltrareGUI::init_gui()
{
    setWindowTitle("Filtreaza");

    QVBoxLayout* main_layout = new QVBoxLayout;
    main_layout->setSpacing(20);
    main_layout->setMargin(30);
    setLayout(main_layout);

    auto form_layout_nume = new QFormLayout;
    form_layout_nume->addRow("Nume", text_nume);
    main_layout->addLayout(form_layout_nume);
    main_layout->addWidget(btn_filtr_nume);


    auto form_pret = new QFormLayout;
    text_pret_minim->setMaximum(999999999);
    text_pret_maxim->setMaximum(999999999);
    form_pret->addRow("Pret minim", text_pret_minim);
    form_pret->addRow("Pret maxim", text_pret_maxim);
    main_layout->addLayout(form_pret);
    main_layout->addWidget(btn_filtr_pret);

    auto form_producator = new QFormLayout;
    form_producator->addRow("Producator", text_producator);
    main_layout->addLayout(form_producator);
    main_layout->addWidget(btn_filtr_producator);


    main_layout->addWidget(btn_anuleaza);
}

void FiltrareGUI::load_data(vector<Produs> toate_produsele)
{
    lista->clear();

    for (const auto& produs : toate_produsele) {
        QListWidgetItem* item = new QListWidgetItem(QString::fromStdString(produs.get_nume()), lista);
        item->setData(Qt::UserRole, QString::fromStdString(produs.get_tip()));
        item->setData(Qt::UserRole + 1, QString::fromStdString(std::to_string(produs.get_pret())));
        item->setData(Qt::UserRole + 2, QString::fromStdString(produs.get_producator()));
    }
}

void FiltrareGUI::init_connect()
{
    // filtreaza dupa nume
    QObject::connect(btn_filtr_nume, &QPushButton::clicked, [&]() {
        auto nume = text_nume->text();

        if (nume.isEmpty()) {
            QMessageBox::information(this, "Info", "Date invalide");
        }
        else {
            load_data(srv.srv_filtreaza_nume(nume.QString::toStdString()));
        }
        close();
    });

    // filtreaza dupa pret
    QObject::connect(btn_filtr_pret, &QPushButton::clicked, [&]() {
        auto pret_minim = text_pret_minim->text();
        auto pret_maxim = text_pret_maxim->text();
        auto producator = text_producator->text();

        if (pret_minim.isEmpty() || pret_maxim.isEmpty()) {
            QMessageBox::information(this, "Info", "Date invalide");
        }
        else {
            load_data(srv.srv_filtreaza_pret(stoi(pret_minim.QString::toStdString()), stoi(pret_maxim.QString::toStdString())));
        }
        close();
    });

    // filtreaza dupa nume tip
    QObject::connect(btn_filtr_producator, &QPushButton::clicked, [&]() {
        auto producator = text_producator->text();

        if (producator.isEmpty()) {
            QMessageBox::information(this, "Info", "Date invalide");
        }
        else {

            load_data(srv.srv_filtreaza_producator(producator.QString::toStdString()));
        }
        close();
    });

    // anuleaza
    QObject::connect(btn_anuleaza, &QPushButton::clicked, [&]() {
        close();
    });
}

void ModificaGUI::init_gui() {
    setWindowTitle("Modifica");

    QVBoxLayout* main_layout = new QVBoxLayout{};
    main_layout->setSpacing(20);
    main_layout->setMargin(30);
    setLayout(main_layout);


    auto form_layout = new QFormLayout;
    form_layout->addRow("Nume: ", text_nume);
    form_layout->addRow("Tip: ", text_tip);
    text_pret->setMaximum(999999999);
    form_layout->addRow("Pret: ", text_pret);
    form_layout->addRow("Producator: ", text_producator);
    main_layout->addLayout(form_layout);

    main_layout->addWidget(btn_modifica);
    main_layout->addWidget(btn_anuleaza);
}

void ModificaGUI::clear_layout(QLayout* layout) {
    QLayoutItem* item;
    while ((item = layout->takeAt(0))) {
        if (item->layout()) {
            clear_layout(item->layout());
            delete item->layout();
        }
        if (item->widget()) {
            delete item->widget();
        }
        delete item;
    }
}

void ModificaGUI::load_data_butoane(vector<Produs> toate_produsele) {
    clear_layout(layout_btn);

    for (const auto& produs : toate_produsele) {
        auto buton = new QPushButton{ QString::fromStdString(produs.get_nume()) };
        layout_btn->addWidget(buton);

        QObject::connect(buton, &QPushButton::clicked, [this, produs, buton]() {
            srv.srv_sterge_produs(produs.get_nume());

            load_data(srv.get_all());
            delete buton;
            });
    }
}

void ModificaGUI::load_data(vector<Produs> toate_produsele)
{
    lista->clear();

    for (const auto& produs : toate_produsele) {
        QListWidgetItem* item = new QListWidgetItem(QString::fromStdString(produs.get_nume()), lista);
        item->setData(Qt::UserRole, QString::fromStdString(produs.get_tip()));
        item->setData(Qt::UserRole + 1, QString::fromStdString(std::to_string(produs.get_pret())));
        item->setData(Qt::UserRole + 2, QString::fromStdString(produs.get_producator()));
    }
}

void ModificaGUI::init_connect() {
    QObject::connect(btn_modifica, &QPushButton::clicked, [&]() {
        if (lista->selectedItems().isEmpty()) {
            QMessageBox::information(this, "Info", "Trebuie selectat un produs");
            this->close();
            return;
        }
        QListWidgetItem* selectat = lista->selectedItems().at(0);
        auto nume_vechi = selectat->text();
        auto nume = text_nume->text();
        auto tip = text_tip->text();
        auto pret = text_pret->text();
        auto producator = text_producator->text();

        if (nume_vechi.isEmpty() || nume.isEmpty() || tip.isEmpty() || pret.isEmpty() || producator.isEmpty()) {
            QMessageBox::information(this, "Info", "Date invalide");
        }
        else {
            try {
                srv.srv_modifica_produs(nume_vechi.QString::toStdString(), nume.QString::toStdString(), tip.QString::toStdString(), stoi(pret.QString::toStdString()), producator.QString::toStdString());
            }
            catch (const RepoMagazinException& ex) {
                QMessageBox::information(this, "Info", "Acest produs exista deja");
            }

            load_data(srv.get_all());
            load_data_butoane(srv.get_all());
        }
        close();
    });

    // anuleaza
    QObject::connect(btn_anuleaza, &QPushButton::clicked, [&]() {
        close();
        });
}

void PopuleazaCosGUI::init_gui() {
    setWindowTitle("Populeaza");

    QVBoxLayout* main_layout = new QVBoxLayout{};
    main_layout->setSpacing(20);
    main_layout->setMargin(30);
    setLayout(main_layout);

    QLabel* text = new QLabel("Introduceti numarul de produse: ");
    main_layout->addWidget(text);
    main_layout->addWidget(text_populeaza);
    main_layout->addWidget(btn_populeaza);
    main_layout->addWidget(btn_anuleaza);
}

void PopuleazaCosGUI::init_connect() {
    // anuleaza
    QObject::connect(btn_anuleaza, &QPushButton::clicked, [&]() {
        close();
    });

    // populeaza
    QObject::connect(btn_populeaza, &QPushButton::clicked, [&]() {
        auto nr = text_populeaza->text();
        int pret = srv.srv_populeaza_cos(stoi(nr.QString::toStdString()));

        string mesaj = "Pretul este: " + std::to_string(pret);
        label_pret->setText(QString::fromStdString(mesaj));
        
        load_cos(srv.srv_get_cos());
        
        close();
    });
}

void PopuleazaCosGUI::load_cos(vector<Produs> produse_cos) {
    tabel->model()->removeRows(0, tabel->rowCount());

    for (const auto& produs : produse_cos) {
        int row = tabel->rowCount();
        tabel->insertRow(row);
        QTableWidgetItem* item_n = new QTableWidgetItem;
        QTableWidgetItem* item_t = new QTableWidgetItem;
        QTableWidgetItem* item_p = new QTableWidgetItem;
        QTableWidgetItem* item_prod = new QTableWidgetItem;

        item_n->setText(QString::fromStdString(produs.get_nume()));
        item_t->setText(QString::fromStdString(produs.get_tip()));
        item_p->setText(QString::fromStdString(std::to_string(produs.get_pret())));
        item_prod->setText(QString::fromStdString(produs.get_producator()));

        tabel->setItem(row, 0, item_n);
        tabel->setItem(row, 1, item_t);
        tabel->setItem(row, 2, item_p);
        tabel->setItem(row, 3, item_prod);
    }
}

void AdaugaCosGUI::init_gui() {
    setWindowTitle("Populeaza");

    QVBoxLayout* main_layout = new QVBoxLayout{};
    main_layout->setSpacing(20);
    main_layout->setMargin(30);
    setLayout(main_layout);

    QLabel* text = new QLabel("Introduceti numele produsului: ");
    main_layout->addWidget(text);
    main_layout->addWidget(text_nume);
    main_layout->addWidget(btn_adauga);
    main_layout->addWidget(btn_anuleaza);
}

void AdaugaCosGUI::init_connect() {
    // anuleaza
    QObject::connect(btn_anuleaza, &QPushButton::clicked, [&]() {
        close();
        });

    // adauga
    QObject::connect(btn_adauga, &QPushButton::clicked, [&]() {
        try {
            auto nume = text_nume->text();
            int pret = srv.srv_adauga_in_cos(nume.QString::toStdString());
            string mesaj = "Pretul este: " + std::to_string(pret);
            label_pret->setText(QString::fromStdString(mesaj));

            load_cos(srv.srv_get_cos());

            close();
        }
        catch (const RepoMagazinException& ex) {
            QMessageBox::information(this, "Info", "Acest produs nu exista");
        }
        
    });
}

void AdaugaCosGUI::load_cos(vector<Produs> produse_cos) {
    tabel->model()->removeRows(0, tabel->rowCount());

    for (const auto& produs : produse_cos) {
        int row = tabel->rowCount();
        tabel->insertRow(row);
        QTableWidgetItem* item_n = new QTableWidgetItem;
        QTableWidgetItem* item_t = new QTableWidgetItem;
        QTableWidgetItem* item_p = new QTableWidgetItem;
        QTableWidgetItem* item_prod = new QTableWidgetItem;

        item_n->setText(QString::fromStdString(produs.get_nume()));
        item_t->setText(QString::fromStdString(produs.get_tip()));
        item_p->setText(QString::fromStdString(std::to_string(produs.get_pret())));
        item_prod->setText(QString::fromStdString(produs.get_producator()));

        tabel->setItem(row, 0, item_n);
        tabel->setItem(row, 1, item_t);
        tabel->setItem(row, 2, item_p);
        tabel->setItem(row, 3, item_prod);
    }
}

void CosCRUD::init_gui()
{
    setWindowTitle("CosCRUD");
    auto cos_layout = new QVBoxLayout;
    setLayout(cos_layout);

    // tabel
    QStringList labels;
    labels << tr("Nume") << tr("Tip") << tr("Pret") << tr("Producator");
    tabel->setHorizontalHeaderLabels(labels);
    tabel->horizontalHeader()->setSectionResizeMode(0, QHeaderView::Stretch);
    tabel->verticalHeader()->hide();
    tabel->setShowGrid(true);
    cos_layout->addWidget(tabel);

    auto cos_layout_pret = new QHBoxLayout;
    //label_pret->setText("Pretul este: 0");
    //cos_layout_pret->addWidget(label_pret);
    cos_layout->addLayout(cos_layout_pret);

    auto cos_layout_butoane = new QHBoxLayout;
    cos_layout_butoane->addWidget(btn_adauga_cos);
    cos_layout_butoane->addWidget(btn_goleste_cos);
    cos_layout_butoane->addWidget(btn_populeaza_cos);
    cos_layout_butoane->addWidget(btn_export);
    cos_layout_butoane->addWidget(btn_exit_cos);
    cos_layout->addLayout(cos_layout_butoane);
}

void CosCRUD::init_connect() {
    srv.adauga_observer(this);
    // populeaza cos
    QObject::connect(btn_populeaza_cos, &QPushButton::clicked, [&]() {
        PopuleazaCosGUI* populeaza = new PopuleazaCosGUI(srv, tabel, label_pret);
        populeaza->show();
        });

    // goleste cos
    QObject::connect(btn_goleste_cos, &QPushButton::clicked, [&]() {
        label_pret->setText("Pretul este: 0");
        srv.srv_goleste_cos();
        //load_cos(srv.srv_get_cos());
        });

    // exit cos
    QObject::connect(btn_exit_cos, &QPushButton::clicked, [&]() {
        close();
        });

    // adauga in cos
    QObject::connect(btn_adauga_cos, &QPushButton::clicked, [&]() {
        AdaugaCosGUI* adauga = new AdaugaCosGUI(srv, tabel, label_pret);
        adauga->show();
        });

    // export
    QObject::connect(btn_export, &QPushButton::clicked, [&]() {
        srv.srv_exporta_cos_html("exp.html");
        close();
        });
}

void CosCRUD::load_cos(vector<Produs> produse_cos) {
    tabel->model()->removeRows(0, tabel->rowCount());

    for (const auto& produs : produse_cos) {
        int row = tabel->rowCount();
        tabel->insertRow(row);
        QTableWidgetItem* item_n = new QTableWidgetItem;
        QTableWidgetItem* item_t = new QTableWidgetItem;
        QTableWidgetItem* item_p = new QTableWidgetItem;
        QTableWidgetItem* item_prod = new QTableWidgetItem;

        item_n->setText(QString::fromStdString(produs.get_nume()));
        item_t->setText(QString::fromStdString(produs.get_tip()));
        item_p->setText(QString::fromStdString(std::to_string(produs.get_pret())));
        item_prod->setText(QString::fromStdString(produs.get_producator()));

        tabel->setItem(row, 0, item_n);
        tabel->setItem(row, 1, item_t);
        tabel->setItem(row, 2, item_p);
        tabel->setItem(row, 3, item_prod);
    }
}
