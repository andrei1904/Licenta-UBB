cmake_minimum_required(VERSION 3.15)
project(Lista_ordonata_cu_indici)

set(CMAKE_CXX_STANDARD 14)

add_executable(Lista_ordonata_cu_indici app.cpp Fisiere_cpp/iterator.cpp Header/iterator.h Fisiere_cpp/lista_ordonata.cpp Header/lista_ordonata.h Fisiere_cpp/TestExtins.cpp Header/TestExtins.h Fisiere_cpp/TestScurt.cpp Header/TestScurt.h)