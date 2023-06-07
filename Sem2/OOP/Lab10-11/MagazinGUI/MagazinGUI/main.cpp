#include <QtWidgets/QApplication>

#include "GUImagazin.h"
#include "../../../Laborator6_7/Magazin/Infrastructura/file_repository.h"
#include "../../../Laborator6_7/Magazin/Infrastructura/repository.h"
#include "../../../Laborator6_7/Magazin/Domeniu/validator.h"
#include "../../../Laborator6_7/Magazin/Domeniu/produs.h"
#include "../../../Laborator6_7/Magazin/Bussines/service.h"


void teste() {
	//test_file_repository();
	test_repository();
	test_validator();
	test_entitati();
	test_service();
}

int main(int argc, char *argv[]) {
	teste();

	RepoMagazinFisier repo("produse.txt");
	ValidatorProdus val;
	ServiceMagazin srv{ repo, val };

	QApplication a(argc, argv);
	GUImagazin mag{ srv };
	mag.show();

	return a.exec();
	
}
