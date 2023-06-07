cmake_minimum_required(VERSION 3.13)
project(Cont_bancar C)

set(CMAKE_C_FLAGS "--coverage")

add_executable(Cont_bancar main.c lista.c lista.h service_cont_bancar.c service_cont_bancar.h tranzactie.c tranzactie.h sortare.c sortare.h)