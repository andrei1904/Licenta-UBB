%{
    #include <stdio.h>
    #include <string.h>

    #include "bison.tab.h"

    int yyerror(char* s);
%}

%option noyywrap

ID [a-zA-Z][a-zA-Z0-9]*
CONST ([+-]?[0-9]+)|([+-]?[0-9]+\.[0-9]+)

%%
"+"|"-"|"*"|"/"|"%" {
    printf("Arithmetic operator: %s\n", yytext);

    strcpy(yylval.id, yytext);

    if (strcmp(yytext, "+") == 0) {
        return ADDITION;
    } else if (strcmp(yytext, "-") == 0) {
        return SUBTRACTION;
    } else if (strcmp(yytext, "-") == 0) {
        return SUBTRACTION;
    } else if (strcmp(yytext, "*") == 0) {
        return MULTIPLICATION;
    } else if (strcmp(yytext, "/") == 0) {
        return DIVISON;
    } else if (strcmp(yytext, "%") == 0) {
        return MODULO;
    }
}

"=" {
    printf("Assignment operator: %s\n", yytext);
    strcpy(yylval.id, yytext);

    return ASSIGNMENT;
}

"<<"|">>" {
    printf("Other operator: %s\n", yytext);

    strcpy(yylval.id, yytext);

    if (strcmp(yytext, "<<") == 0) {
        return LEFT_SHIFT;
    } else {
        return RIGHT_SHIFT;
    }
}

"{"|"}"|"("|")"|","|";"|" " {
    if (strcmp(yytext, " ") == 0) {
        printf("Sperator: space\n");
    } else {
        printf("Sperator: %s\n", yytext);
    }

    strcpy(yylval.id, yytext);

    if (strcmp(yytext, "{") == 0) {
        return LEFT_BRACE;
    } else if (strcmp(yytext, "}") == 0) {
        return RIGHT_BRACE;
    } else if (strcmp(yytext, "(") == 0) {
        return LEFT_PARENTHESIS;
    } else if (strcmp(yytext, ")") == 0) {
        return RIGHT_PARENTHESIS;
    } else if (strcmp(yytext, ",") == 0) {
        return COMMA;
    } else if (strcmp(yytext, ";") == 0) {
        return SEMICOLON;
    } else if (strcmp(yytext, " ") == 0) {
        return SPACE;
    }
}

"int"|"cin"|"cout"|"#include"|"<iostream>"|"using"|"namespace"|"std"|"return"|"main" {
    printf("Reserved word: %s\n", yytext);

    strcpy(yylval.id, yytext);

    if (strcmp(yytext, "int") == 0) {
        return INT;
    } else if (strcmp(yytext, "cin") == 0) {
        return CIN;
    } else if (strcmp(yytext, "cout") == 0) {
        return COUT;
    } else if (strcmp(yytext, "#include") == 0) {
        return INCLUDE;
    } else if (strcmp(yytext, "<iostream>") == 0) {
        return IOSTREAM;
    } else if (strcmp(yytext, "using") == 0) {
        return USING;
    } else if (strcmp(yytext, "namespace") == 0) {
        return NAMESPACE;
    } else if (strcmp(yytext, "std") == 0) {
        return STD;
    } else if (strcmp(yytext, "return") == 0) {
        return RETURN;
    } else if (strcmp(yytext, "main") == 0) {
        return MAIN;
    }
}

{CONST} {
    printf("CONST: %s\n", yytext);
    strcpy(yylval.id, yytext);

    return CONST;
}

{ID} {
    printf("ID: %s\n", yytext);
    strcpy(yylval.id, yytext);

    if (strlen(yytext) > 8) {
        yyerror("ID is to long");
        return 0;
    }

    return ID;
}

. {
    printf("Not specified: %s\n", yytext);

    yyerror("Not specified");
    return 0;
}

[ \t\n]+

%%
