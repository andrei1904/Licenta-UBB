#include "exporta.h"
#include "repository.h"

#include <fstream>

void exporta_html(const std::string& nume_fisier, const std::vector<Produs>& produse) {
    std::ofstream fout(nume_fisier, std::ios::trunc);

    if (!fout.is_open()) {
        throw RepoMagazinException("Fisierul: " + nume_fisier + " nu a putut sa fie deschis!\n");
    }

    fout << "<html><body>" << std::endl;
    fout << R"(<table border="1" style="width:100 % ">)" << std::endl;
    for (const auto& produs : produse) {
        fout << "<tr>" << std::endl;
        fout << "<td>" << produs.get_nume() << "</td>" << std::endl;
        fout << "<td>" << produs.get_tip() << "</td>" << std::endl;
        fout << "<td>" << produs.get_pret() << "</td>" << std::endl;
        fout << "<td>" << produs.get_producator() << "</td>" << std::endl;
        fout << "</tr>" << std::endl;
    }
    fout << "</table>" << std::endl;
    fout << "</body></html>" << std::endl;
    fout.flush();
    fout.close();
}