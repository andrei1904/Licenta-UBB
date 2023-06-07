%{

#include <fstream>
#include <iostream>
#include <string.h>
#include <map>
#include <vector>

using namespace std;

map<string, int> atomCode;
map<string, int> identificatorTS;
map<string, int> constantTS;
vector<pair<int, int>> FIP;

int constantCount = 0;
int identificatorCount = 0;

void readAtomCode(string filename, map<string, int> &atomCode) {
    ifstream fin(filename);

    string atom;
    int code;
    while (fin >> atom && fin >> code) {
        atomCode[atom] = code;
    }

    fin.close();
}

void writeTsInFile(string filename, map<string, int> data) {
    ofstream fout(filename);

    for (auto& elem : data) {
        fout << elem.first << " " << elem.second << "\n";
    }

    fout.close();
}

void writeFipInFile(string filename, vector<pair<int, int>> data) {
    ofstream fout(filename);

    for (auto& elem : data) {
        fout << elem.first << " " << elem.second << "\n";
    }

    fout.close();
}

%}

%option noyywrap

ID [a-zA-Z][a-zA-Z0-9]*
CONST ([+-]?[0-9]+)|([+-]?[0-9]+\.[0-9]+)

%%
"+"|"-"|"*"|"/"|"%" {
    printf("Arithmetic operator: %s\n", yytext);
    FIP.push_back({atomCode[yytext], -1});
}

"<"|">"|"=="|"!="|"<="|">=" {
    printf("Relational operator: %s\n", yytext);
    FIP.push_back({atomCode[yytext], -1});
}

"=" {
    printf("Assignment operator: %s\n", yytext);
    FIP.push_back({atomCode[yytext], -1});
}

"<<"|">>" {
    printf("Other operator: %s\n", yytext);
    FIP.push_back({atomCode[yytext], -1});
}

"{"|"}"|"("|")"|","|";"|" " {
    if (strcmp(yytext, " ") == 0) {
        printf("Separator: space\n");
        FIP.push_back({atomCode["space"], -1});

    } else {
        printf("Separator: %s\n", yytext);
        FIP.push_back({atomCode[yytext], -1});
    }
}

"daca"|"atunci"|"sfdaca"|"int"|"double"|"struct"|"void"|"while"|"if"|"else"|"cin"|"cout"|"#include"|"<iostream>"|"using"|"namespace"|"std"|"return"|"main" {
    printf("Reserved word: %s\n", yytext);
    FIP.push_back({atomCode[yytext], -1});
}

{CONST} {
    printf("CONST: %s\n", yytext);

    // check if the key isn't already in the map
    if (constantTS.find(yytext) == constantTS.end()) {
        constantTS[yytext] = constantCount++;
    }

    FIP.push_back({atomCode["CONST"], constantTS[yytext]});
}

{ID} {
    if (strlen(yytext) > 8) {
        printf("\nIDENTIFICATOR %s is longer than 8 characters!\n", yytext);
        return 0;
    }
    
    printf("IDENTIFICATOR: %s\n", yytext);

    // check if the key isn't already in the map
    if (identificatorTS.find(yytext) == identificatorTS.end()) {
        identificatorTS[yytext] = identificatorCount++;
    }

    FIP.push_back({atomCode["ID"], identificatorTS[yytext]});
}

. {
    printf("Not specified: %s\n", yytext);
    return 0;
}

[ \t\n]+

%%

int main() {

    FILE *fp;
    char filename[50];
    printf("Enter the filename: \n");
    scanf("%s", filename);
    fp = fopen(filename, "r");
    yyin = fp;

    readAtomCode("C:\\Fac\\Sem5\\LFTC\\Lab03\\data\\atomCode.txt", atomCode);

    yylex();

    writeTsInFile("C:\\Fac\\Sem5\\LFTC\\Lab03\\data\\idTS.txt", identificatorTS);
    writeTsInFile("C:\\Fac\\Sem5\\LFTC\\Lab03\\data\\constTS.txt", constantTS);

    writeFipInFile("C:\\Fac\\Sem5\\LFTC\\Lab03\\data\\FIP.txt", FIP);

    return 0;
}
