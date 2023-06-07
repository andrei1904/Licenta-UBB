%{
    #include <stdio.h>
    #include <stdlib.h>

    int yylex(void);
    int yyerror(char* s);

    extern FILE* yyin;
%}

%start program

%token ID
%token CONST
%token INCLUDE
%token IOSTREAM
%token USING
%token NAMESPACE
%token STD
%token EQUAL
%token GREATER_THAN
%token LESS_THAN
%token GREATER_THAN_EQUAL
%token LESS_THAN_EQUAL
%token NOT_EQUAL
%token LEFT_PARENTHESIS
%token RIGHT_PARENTHESIS
%token LEFT_BRACE
%token RIGHT_BRACE
%token SEMICOLON
%token COMMA
%token MAIN
%token RETURN
%token VOID
%token INT
%token DOUBLE
%token STRUCT
%token ADDITION
%token SUBTRACTION
%token MULTIPLICATION
%token DIVISON
%token MODULO
%token IF
%token ELSE
%token WHILE
%token LEFT_SHIFT
%token RIGHT_SHIFT
%token ASSIGNMENT
%token COUT
%token CIN
%token DACA
%token ATUNCI
%token SFDACA
%token SPACE

%union {
    int type;
    char name[50];
}


%%
program:        definition function main {
                        printf("Program has no errors!");
                        };

definition:     INCLUDE SPACE IOSTREAM USING SPACE NAMESPACE SPACE STD SEMICOLON;

main:           INT SPACE MAIN LEFT_PARENTHESIS RIGHT_PARENTHESIS SPACE LEFT_BRACE instruction_list RETURN SPACE CONST SEMICOLON RIGHT_BRACE;

function:       type SPACE ID LEFT_PARENTHESIS parameter_list RIGHT_PARENTHESIS SPACE LEFT_BRACE instruction_list RIGHT_BRACE;
        //        | type SPACE ID LEFT_PARENTHESIS parameter_list RIGHT_PARENTHESIS SPACE LEFT_BRACE variable_declaration instruction_list RETURN SPACE type SEMICOLON RIGHT_BRACE;

type:           type_no_void
                | VOID;

type_no_void:   INT
                | DOUBLE
                | struct_type;

struct_type:    STRUCT ID LEFT_BRACE variable_declaration RIGHT_BRACE SEMICOLON;

parameter_list:     type_no_void SPACE variable COMMA parameter_list
                    | type_no_void SPACE variable;

variable_declaration:   type_no_void SPACE variable_list SEMICOLON
                        | type_no_void SPACE assign;

variable_list:          variable COMMA variable_list
                        | variable;

variable:               ID;

instruction_list:   instruction
                    | instruction instruction_list;

instruction:        assign
                    | variable_declaration
                    | conditional_instruction
                    | loop_instruction
                    | read
                    | write
                    | function_call;

assign:             ID SPACE ASSIGNMENT SPACE expression SEMICOLON;

expression:         ID
                    | CONST
                    | expression SPACE arithmetic_operator SPACE expression;

arithmetic_operator:    ADDITION
                        | SUBTRACTION
                        | MULTIPLICATION
                        | DIVISON
                        | MODULO;

conditional_instruction:    DACA SPACE LEFT_PARENTHESIS conditional_expression RIGHT_PARENTHESIS SPACE ATUNCI instruction_list SFDACA
                            | IF SPACE LEFT_PARENTHESIS conditional_expression RIGHT_PARENTHESIS SPACE LEFT_BRACE instruction_list RIGHT_BRACE
                            | IF SPACE LEFT_PARENTHESIS conditional_expression RIGHT_PARENTHESIS SPACE LEFT_BRACE instruction_list RIGHT_BRACE SPACE ELSE SPACE LEFT_BRACE instruction_list RIGHT_BRACE;

conditional_expression:     expression SPACE relational_operator SPACE expression;

relational_operator:    LESS_THAN
                        | GREATER_THAN
                        | EQUAL
                        | NOT_EQUAL
                        | LESS_THAN_EQUAL
                        | GREATER_THAN_EQUAL;

loop_instruction:       WHILE SPACE LEFT_PARENTHESIS conditional_expression RIGHT_PARENTHESIS SPACE LEFT_BRACE instruction_list RIGHT_BRACE;

read:                   CIN SPACE RIGHT_SHIFT SPACE variable SEMICOLON;

write:                  COUT SPACE LEFT_SHIFT SPACE expression SEMICOLON;

function_call:          ID LEFT_PARENTHESIS variable_list RIGHT_PARENTHESIS SEMICOLON
                        | ID LEFT_PARENTHESIS RIGHT_PARENTHESIS;
%%

int yyerror(char* s) {
    extern char* yytext;

    printf("%s for: %s\n", s, yytext);
    return 0;
}

int main() {

    FILE *fp;
    char filename[50];
    printf("Enter the filename: \n");
    scanf("%s", filename);
    fp = fopen(filename, "r");
    yyin = fp;

    yyparse();

    return 0;
}
