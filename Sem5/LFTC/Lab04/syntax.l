%{
    #include <stdio.h>
    #include <string.h>

    #include "syntax.tab.h"

    int yyerror(char* s);
%}

%option noyywrap

ID [a-zA-Z][a-zA-Z0-9]*
CONST ([+-]?[0-9]+)|([+-]?[0-9]+\.[0-9]+)

%%
"+"|"-"|"*"|"/"|"%" {
    printf("Arithmetic operator: %s\n", yytext);

    strcpy(yylval.name, yytext);

    if (strcmp(yytext, "+") == 0) {
        yylval.type = ADDITION;
    } else if (strcmp(yytext, "-") == 0) {
        yylval.type = SUBTRACTION;
    } else if (strcmp(yytext, "-") == 0) {
        yylval.type = SUBTRACTION;
    } else if (strcmp(yytext, "*") == 0) {
        yylval.type = MULTIPLICATION;
    } else if (strcmp(yytext, "/") == 0) {
        yylval.type = DIVISON;
    } else if (strcmp(yytext, "%") == 0) {
        yylval.type = MODULO;
    }

    return yylval.type;
}

"<"|">"|"=="|"!="|"<="|">=" {
    printf("Relational operator: %s\n", yytext);

    strcpy(yylval.name, yytext);

    if (strcmp(yytext, "<") == 0) {
        yylval.type = LESS_THAN;
    } else if (strcmp(yytext, ">") == 0) {
        yylval.type = GREATER_THAN;
    } else if (strcmp(yytext, "==") == 0) {
        yylval.type = EQUAL;
    } else if (strcmp(yytext, "!=") == 0) {
        yylval.type = NOT_EQUAL;
    } else if (strcmp(yytext, "<=") == 0) {
        yylval.type = LESS_THAN_EQUAL;
    } else if (strcmp(yytext, ">=") == 0) {
        yylval.type = GREATER_THAN_EQUAL;
    }

    return yylval.type;
}

"=" {
    printf("Assignment operator: %s\n", yytext);
    strcpy(yylval.name, yytext);
    yylval.type = ASSIGNMENT;

    return yylval.type;
}

"<<"|">>" {
    printf("Other operator: %s\n", yytext);

    strcpy(yylval.name, yytext);

    if (strcmp(yytext, "<<") == 0) {
        yylval.type = LEFT_SHIFT;
    } else {
        yylval.type = RIGHT_SHIFT;
    }

    return (yylval.type);
}

"{"|"}"|"("|")"|","|";"|" " {
    if (strcmp(yytext, " ") == 0) {
        printf("Sperator: space\n");
    } else {
        printf("Sperator: %s\n", yytext);
    }

    strcpy(yylval.name, yytext);

    if (strcmp(yytext, "{") == 0) {
        yylval.type = LEFT_BRACE;
    } else if (strcmp(yytext, "}") == 0) {
        yylval.type = RIGHT_BRACE;
    } else if (strcmp(yytext, "(") == 0) {
        yylval.type = LEFT_PARENTHESIS;
    } else if (strcmp(yytext, ")") == 0) {
        yylval.type = RIGHT_PARENTHESIS;
    } else if (strcmp(yytext, ",") == 0) {
        yylval.type = COMMA;
    } else if (strcmp(yytext, ";") == 0) {
        yylval.type = SEMICOLON;
    } else if (strcmp(yytext, " ") == 0) {
        yylval.type = SPACE;
    }

    return yylval.type;
}

"daca"|"atunci"|"sfdaca"|"int"|"double"|"struct"|"void"|"while"|"if"|"else"|"cin"|"cout"|"#include"|"<iostream>"|"using"|"namespace"|"std"|"return"|"main" {
    printf("Reserved word: %s\n", yytext);

    strcpy(yylval.name, yytext);

    if (strcmp(yytext, "int") == 0) {
        yylval.type = INT;
    } else if (strcmp(yytext, "double") == 0) {
        yylval.type = DOUBLE;
    } else if (strcmp(yytext, "struct") == 0) {
        yylval.type = STRUCT;
    } else if (strcmp(yytext, "void") == 0) {
        yylval.type = VOID;
    } else if (strcmp(yytext, "while") == 0) {
        yylval.type = WHILE;
    } else if (strcmp(yytext, "if") == 0) {
        yylval.type = IF;
    } else if (strcmp(yytext, "else") == 0) {
        yylval.type = ELSE;
    } else if (strcmp(yytext, "cin") == 0) {
        yylval.type = CIN;
    } else if (strcmp(yytext, "cout") == 0) {
        yylval.type = COUT;
    } else if (strcmp(yytext, "#include") == 0) {
        yylval.type = INCLUDE;
    } else if (strcmp(yytext, "<iostream>") == 0) {
        yylval.type = IOSTREAM;
    } else if (strcmp(yytext, "using") == 0) {
        yylval.type = USING;
    } else if (strcmp(yytext, "namespace") == 0) {
        yylval.type = NAMESPACE;
    } else if (strcmp(yytext, "std") == 0) {
        yylval.type = STD;
    } else if (strcmp(yytext, "return") == 0) {
        yylval.type = RETURN;
    } else if (strcmp(yytext, "main") == 0) {
        yylval.type = MAIN;
    } else if (strcmp(yytext, "daca") == 0) {
        yylval.type = DACA;
    } else if (strcmp(yytext, "atunci") == 0) {
        yylval.type = ATUNCI;
    } else if (strcmp(yytext, "sfdaca") == 0) {
        yylval.type = SFDACA;
    }

    return yylval.type;
}

{CONST} {
    printf("CONST: %s\n", yytext);

    strcpy(yylval.name, yytext);
    yylval.type = CONST;

    return yylval.type;
}

{ID} {
    printf("ID: %s\n", yytext);

    strcpy(yylval.name, yytext);
    yylval.type = ID;

    if (strlen(yytext) > 8) {
        yyerror("ID is to long");
        return 0;
    }

    return yylval.type;
}

. {
    printf("Not specified: %s\n", yytext);

    yyerror("Not specified");
    return 0;
}

[ \t\n]+

%%
