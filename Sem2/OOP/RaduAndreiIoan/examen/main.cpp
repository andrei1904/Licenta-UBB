#include "MelodieGui.h"
#include <QApplication>

void teste() {
    test_melodie();
    test_service();
    test_valid();
}

int main(int argc, char** argv) {
    teste();

    try {
        MelodieRepoFile repo("../melodii.txt");
        MelodieValid valid;
        MelodieController ctr{repo, valid};

        QApplication q(argc, argv);
        MelodieGui m{ctr};
        m.show();
        return QApplication::exec();
    } catch (const MelodieException& ex) {
        QApplication q(argc, argv);
        QMessageBox::critical(nullptr, "Eroare", QString::fromStdString(ex.getMesaj()));
        return QApplication::exec();
    }

    return 0;
}