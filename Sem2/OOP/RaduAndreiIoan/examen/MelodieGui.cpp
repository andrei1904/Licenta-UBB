//
// Created by radua on 6/18/2020.
//

#include "MelodieGui.h"

void MelodieGui::initInterfata() {
    setWindowTitle("Melodii");
    resize(1500, 700);

    auto *main_ly = new QHBoxLayout;
    main_ly->setMargin(100);
    main_ly->setSpacing(20);
    setLayout(main_ly);

    main_ly->addWidget(tabel);

    // layout partea dreapta
    auto *dr_ly = new QVBoxLayout;

    // layout form
    auto *form_ly = new QFormLayout;
    form_ly->addRow("Titlu: ", text_titlu);
    form_ly->addRow("Artist: ", text_artist);
    form_ly->addRow("Gen: ", text_gen);
    dr_ly->addLayout(form_ly);

    // layout butoane
    auto *btn_ly = new QVBoxLayout;
    btn_ly->addWidget(btn_adauga);
    btn_ly->addWidget(btn_sterge);
    dr_ly->addLayout(btn_ly);

    main_ly->addLayout(dr_ly);

}

void MelodieGui::load() {
    model->setMelodii(ctr.sortAutor());
}

void MelodieGui::connect() {
    // butonul adauga
    QObject::connect(btn_adauga, &QPushButton::clicked, [&]() {
        auto titlu = text_titlu->text().QString::toStdString();
        auto artist = text_artist->text().QString::toStdString();
        auto gen = text_gen->text().QString::toStdString();

        try {
            ctr.adauga(ctr.genereazaId(), titlu, artist, gen);
        } catch (const MelodieException &ex) {
            QMessageBox::critical(this, "Eroare", QString::fromStdString(ex.getMesaj()));
        }
        load();
        numara();
    });

    // butonul sterge
    QObject::connect(btn_sterge, &QPushButton::clicked, [&]() {
        try {
            ctr.sterge(id);
        } catch (const MelodieException &ex) {
            QMessageBox::critical(this, "Eroare", QString::fromStdString(ex.getMesaj()));
        }
        load();
        numara();
    });

    // selecteaza linie in tabel
    QObject::connect(tabel->selectionModel(), &QItemSelectionModel::selectionChanged, [&]() {
        if (tabel->selectionModel()->selectedIndexes().isEmpty()) {
            id = -1;
            return;
        }

        int linie = tabel->selectionModel()->selectedIndexes().at(0).row();
        auto indexId = tabel->model()->index(linie, 0);
        id = tabel->model()->data(indexId, Qt::DisplayRole).toInt();
    });
}

void MelodieGui::paintEvent(QPaintEvent *ev) {
    QPainter p{this};
    QPen pen;
    pen.setWidth(3);

    int latime = 20, inaltime = 20;
    int pozitie = 30, pozitie2 = 30;
    for (int i = 0; i < folk; i++) {
        pen.setColor(Qt::blue);
        p.setPen(pen);

        p.drawEllipse(pozitie, pozitie2, latime, inaltime);
        latime += 5;
        inaltime += 5;
        pozitie -= 1;
    }

    latime = 20, inaltime = 20;
    for (int i = 0; i < rock; i++) {
        pen.setColor(Qt::red);
        p.setPen(pen);

        p.drawEllipse(1470, 30, latime, inaltime);
        latime += 10;
        inaltime += 10;
    }

    latime = 20, inaltime = 20;
    for (int i = 0; i < pop; i++) {
        pen.setColor(Qt::yellow);
        p.setPen(pen);

        p.drawEllipse(30, 670, latime, inaltime);
        latime += 10;
        inaltime += 10;
    }


    latime = 20, inaltime = 20;
    for (int i = 0; i < disco; i++) {
        pen.setColor(Qt::green);
        p.setPen(pen);

        p.drawEllipse(1470, 670, latime, inaltime);
        latime += 10;
        inaltime += 10;
    }
}

void MelodieGui::numara() {
    folk = 0, rock = 0, pop = 0, disco = 0;
    for (const auto& m : ctr.getAll()) {
        if (m.getGen() == "folk") {
            folk++;
        } else if (m.getGen() == "rock") {
            rock++;
        } else if (m.getGen() == "pop" ) {
            pop++;
        } else if (m.getGen() == "disco") {
            disco++;
        }
    }
}

void MelodieGui::update() {
    repaint();
}
