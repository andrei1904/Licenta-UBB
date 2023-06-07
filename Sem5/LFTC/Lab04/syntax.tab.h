
/* A Bison parser, made by GNU Bison 2.4.1.  */

/* Skeleton interface for Bison's Yacc-like parsers in C
   
      Copyright (C) 1984, 1989, 1990, 2000, 2001, 2002, 2003, 2004, 2005, 2006
   Free Software Foundation, Inc.
   
   This program is free software: you can redistribute it and/or modify
   it under the terms of the GNU General Public License as published by
   the Free Software Foundation, either version 3 of the License, or
   (at your option) any later version.
   
   This program is distributed in the hope that it will be useful,
   but WITHOUT ANY WARRANTY; without even the implied warranty of
   MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
   GNU General Public License for more details.
   
   You should have received a copy of the GNU General Public License
   along with this program.  If not, see <http://www.gnu.org/licenses/>.  */

/* As a special exception, you may create a larger work that contains
   part or all of the Bison parser skeleton and distribute that work
   under terms of your choice, so long as that work isn't itself a
   parser generator using the skeleton or a modified version thereof
   as a parser skeleton.  Alternatively, if you modify or redistribute
   the parser skeleton itself, you may (at your option) remove this
   special exception, which will cause the skeleton and the resulting
   Bison output files to be licensed under the GNU General Public
   License without this special exception.
   
   This special exception was added by the Free Software Foundation in
   version 2.2 of Bison.  */


/* Tokens.  */
#ifndef YYTOKENTYPE
# define YYTOKENTYPE
   /* Put the tokens into the symbol table, so that GDB and other debuggers
      know about them.  */
   enum yytokentype {
     ID = 258,
     CONST = 259,
     INCLUDE = 260,
     IOSTREAM = 261,
     USING = 262,
     NAMESPACE = 263,
     STD = 264,
     EQUAL = 265,
     GREATER_THAN = 266,
     LESS_THAN = 267,
     GREATER_THAN_EQUAL = 268,
     LESS_THAN_EQUAL = 269,
     NOT_EQUAL = 270,
     LEFT_PARENTHESIS = 271,
     RIGHT_PARENTHESIS = 272,
     LEFT_BRACE = 273,
     RIGHT_BRACE = 274,
     SEMICOLON = 275,
     COMMA = 276,
     MAIN = 277,
     RETURN = 278,
     VOID = 279,
     INT = 280,
     DOUBLE = 281,
     STRUCT = 282,
     ADDITION = 283,
     SUBTRACTION = 284,
     MULTIPLICATION = 285,
     DIVISON = 286,
     MODULO = 287,
     IF = 288,
     ELSE = 289,
     WHILE = 290,
     LEFT_SHIFT = 291,
     RIGHT_SHIFT = 292,
     ASSIGNMENT = 293,
     COUT = 294,
     CIN = 295,
     DACA = 296,
     ATUNCI = 297,
     SFDACA = 298,
     SPACE = 299
   };
#endif



#if ! defined YYSTYPE && ! defined YYSTYPE_IS_DECLARED
typedef union YYSTYPE
{

/* Line 1676 of yacc.c  */
#line 57 ".\\syntax.y"

    int type;
    char name[50];



/* Line 1676 of yacc.c  */
#line 103 "syntax.tab.h"
} YYSTYPE;
# define YYSTYPE_IS_TRIVIAL 1
# define yystype YYSTYPE /* obsolescent; will be withdrawn */
# define YYSTYPE_IS_DECLARED 1
#endif

extern YYSTYPE yylval;


