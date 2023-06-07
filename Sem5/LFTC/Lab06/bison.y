%{
    #include <stdio.h>
    #include <stdlib.h>
    #include <string.h>
    #include <ctype.h>

    int yylex(void);
    int yyerror(char* s);

    extern FILE* yyin;

    char dataSegment[5000];
    char codeSegment[5000];

    int temp;

    void addToDataSegment(char* x);
    void addToCodeSegment(char* x);
    void generateASM();
    void newTemp(char* x);
%}

%start program

%token INCLUDE
%token IOSTREAM
%token USING
%token NAMESPACE
%token STD
%token LEFT_PARENTHESIS
%token RIGHT_PARENTHESIS
%token LEFT_BRACE
%token RIGHT_BRACE
%token SEMICOLON
%token COMMA
%token MAIN
%token RETURN
%token INT
%token ADDITION
%token SUBTRACTION
%token MULTIPLICATION
%token DIVISON
%token MODULO
%token LEFT_SHIFT
%token RIGHT_SHIFT
%token ASSIGNMENT
%token COUT
%token CIN
%token SPACE

%union {
    char id[100];
}

%token <id> ID
%token <id> CONST

%type <id> variable
%type <id> expression


%%
program:    definition main
{
    printf("Program has no errors!");
};

definition:    INCLUDE SPACE IOSTREAM USING SPACE NAMESPACE SPACE STD SEMICOLON;

main:    INT SPACE MAIN LEFT_PARENTHESIS RIGHT_PARENTHESIS SPACE LEFT_BRACE instruction_list RETURN SPACE CONST SEMICOLON RIGHT_BRACE;

instruction_list:    instruction
                     | instruction instruction_list;

instruction:    variable_declaration
                | read
                | write
                | assignment;

variable_declaration:   INT SPACE ID SEMICOLON
{
    char* variableName = (char*)malloc(sizeof(char) * 100);
    sprintf(variableName, "%s dd 0\n", $3);
    addToDataSegment(variableName);
    free(variableName);
};

variable:    ID
{
    strcpy($$, $1);
}
    | CONST
{
    strcpy($$, $1);
};

read:    CIN SPACE RIGHT_SHIFT SPACE ID SEMICOLON
{
    char* code = (char*) malloc(sizeof(char) * 100);

    sprintf(code, "push dword %s\npush dword format\ncall [scanf]\nadd esp, 4*2\n", $5);
	addToCodeSegment(code);

	free(code);
};

write:    COUT SPACE LEFT_SHIFT SPACE ID SEMICOLON
{
    char* code = (char*) malloc(sizeof(char) * 100);

	sprintf(code, "push dword [%s]\npush dword format\ncall [printf]\nadd esp, 4*2\n", $5);
	addToCodeSegment(code);

	free(code);
}

assignment:    ID SPACE ASSIGNMENT SPACE expression SEMICOLON
{
    char* code = (char*) malloc(sizeof(char) * 100);

	sprintf(code, "mov eax, [%s]\n", $5);
    addToCodeSegment(code);

    sprintf(code, "mov [%s], eax\n", $1);
	addToCodeSegment(code);

	free(code);
}
|   ID SPACE ASSIGNMENT SPACE ID SEMICOLON
{
    char* code = (char*) malloc(sizeof(char) * 100);

	sprintf(code, "mov eax, [%s]\n", $5);
    addToCodeSegment(code);

    sprintf(code, "mov [%s], eax\n", $1);
	addToCodeSegment(code);

	free(code);
}
|   ID SPACE ASSIGNMENT SPACE CONST SEMICOLON
{
    char* code = (char*) malloc(sizeof(char) * 100);

    sprintf(code, "mov eax, %s\n", $5);
    addToCodeSegment(code);

    sprintf(code, "mov [%s], eax\n", $1);
    addToCodeSegment(code);

    free(code);
};

expression:    variable
    | variable SPACE ADDITION SPACE variable
{
    char* variableName = (char*)malloc(sizeof(char) * 100);

    newTemp(variableName);
    strcpy($$, variableName);

    char* code = (char*)malloc(sizeof(char) * 100);

    sprintf(code, isdigit($1[0]) ? "mov eax, %s\n" : "mov eax, [%s]\n", $1);
    addToCodeSegment(code);

    sprintf(code, isdigit($5[0]) ? "add eax, dword %s\n" : "add eax, [%s]\n", $5);
    addToCodeSegment(code);

    sprintf(code, "mov [%s], eax\n", variableName);
    addToCodeSegment(code);

    free(variableName);
    free(code);
}
    | variable SPACE SUBTRACTION SPACE variable
{
    char* variableName = (char*)malloc(sizeof(char) * 100);

    newTemp(variableName);
    strcpy($$, variableName);

    char* code = (char*)malloc(sizeof(char) * 100);

    sprintf(code, isdigit($1[0]) ? "mov eax, %s\n" : "mov eax, [%s]\n", $1);
    addToCodeSegment(code);

    sprintf(code, isdigit($5[0]) ? "sub eax, dword %s\n" : "sub eax, [%s]\n", $5);
    addToCodeSegment(code);

    sprintf(code, "mov [%s], eax\n", variableName);
    addToCodeSegment(code);

    free(variableName);
    free(code);
}
    | variable SPACE MULTIPLICATION SPACE variable
{
    char* variableName = (char*)malloc(sizeof(char) * 100);

    newTemp(variableName);
    strcpy($$, variableName);

    char* code = (char*)malloc(sizeof(char) * 100);

    sprintf(code, isdigit($1[0]) ? "mov eax, %s\n" : "mov eax, [%s]\n", $1);
    addToCodeSegment(code);

    sprintf(code, isdigit($5[0]) ? "mov ebx, %s\nmul ebx\n" : "mul dword[%s]\n", $5);
    addToCodeSegment(code);

    sprintf(code, "mov [%s], eax\n", variableName);
    addToCodeSegment(code);

    free(variableName);
    free(code);
}
    | variable SPACE DIVISON SPACE variable
{
    char* variableName = (char*)malloc(sizeof(char) * 100);

    newTemp(variableName);
    strcpy($$, variableName);

    char* code = (char*)malloc(sizeof(char) * 100);

    sprintf(code, isdigit($1[0]) ? "mov eax, %s\n" : "mov eax, [%s]\n", $1);
    addToCodeSegment(code);

    strcpy(code, "mov edx, 0\n");
    addToCodeSegment(code);

    sprintf(code, isdigit($5[0]) ? "mov ebx, %s\ndiv ebx\n" : "div dword[%s]\n", $5);
    addToCodeSegment(code);

    sprintf(code, "mov [%s], eax\n", variableName);
    addToCodeSegment(code);

    free(variableName);
    free(code);
};


%%

int yyerror(char* s) {
    extern char* yytext;

    printf("%s for: %s\n", s, yytext);
    return 0;
}

void addToDataSegment(char* x) {
    strcat(dataSegment, x);
}

void addToCodeSegment(char* x) {
    strcat(codeSegment, x);
}

void newTemp(char* x) {
    sprintf(x, "temp%d dd 1\n", temp);
	addToDataSegment(x);
	sprintf(x, "temp%d", temp);
	temp++;
}

void generateASM() {
    FILE* file = fopen("program.asm", "w");

    char* header = (char*)malloc(sizeof(char) * 3000);
    char* dSegment = (char*)malloc(sizeof(char) * 3000);
    char* cSegment = (char*)malloc(sizeof(char) * 3000);

    strcpy(header, "bits 32\n");
    strcat(header, "global start\nextern exit, scanf, printf\nimport exit msvcrt.dll\nimport scanf msvcrt.dll\nimport printf msvcrt.dll\n");
    fwrite(header, strlen(header), sizeof(char), file);

    strcpy(dSegment, "segment data use32 class=data\nformat db '%d', 0\n");
    strcat(dSegment, dataSegment);
    strcat(dSegment, "\n");
    fwrite(dSegment, strlen(dSegment), sizeof(char), file);

    strcpy(cSegment, "segment code use32 class=code\nstart:\n");
	strcat(cSegment, codeSegment);
	strcat(cSegment, "push dword 0\ncall [exit]\n");
	fwrite(cSegment, strlen(cSegment), sizeof(char), file);
}

int main() {

    FILE* file;
    char filename[50];
    printf("Enter the filename: \n");
    scanf("%s", filename);
    file = fopen(filename, "r");
    yyin = file;

    yyparse();

    generateASM();

    return 0;
}
