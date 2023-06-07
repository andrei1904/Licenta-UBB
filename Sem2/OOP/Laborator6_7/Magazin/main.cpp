//#include "Prezentare/ui_magazin.h"
//#include "Infrastructura/file_repository.h"
//
//void teste() {
//    test_entitati();
//    test_validator();
//    test_service();
//    test_repository();
//    test_file_repository();
//}
//
//int main() {
//    teste();
//
//    try {
//        RepoMagazinFisier repo("../produse.txt");
//        ValidatorProdus val;
//        ServiceMagazin srv{ repo, val };
//
//        ConsolaUI ui{ srv };
//        ui.run();
//    } catch (const RepoMagazinException &ex) {
//        cout << "Eroare fisier: " <<ex << "\n";
//    }
//
//    return 0;
//}

#include <QtWidgets/QApplication>

#include "GUImagazin.h"
#include "Infrastructura/file_repository.h"
#include "Infrastructura/repository.h"
#include "Domeniu/validator.h"
#include "Domeniu/produs.h"
#include "Bussines/service.h"


void teste() {
    //test_file_repository();
    test_repository();
    test_validator();
    test_entitati();
    test_service();
}

int main(int argc, char *argv[]) {
    teste();

    RepoMagazinFisier repo("../produse.txt");
    ValidatorProdus val;
    ServiceMagazin srv{ repo, val };

    QApplication a(argc, argv);
    GUImagazin mag{ srv };
    mag.show();

    return a.exec();

}
