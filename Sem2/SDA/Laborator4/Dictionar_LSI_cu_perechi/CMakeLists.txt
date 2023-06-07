cmake_minimum_required(VERSION 3.15)
project(Dictionar_LSI_cu_perechi)

set(CMAKE_CXX_STANDARD 14)

add_executable(Dictionar_LSI_cu_perechi Fisiere_CPP/main.cpp Fisiere_CPP/dictionar.cpp Header/dictionar.h Fisiere_CPP/iterator_dictionar.cpp Header/iterator_dictionar.h Fisiere_CPP/test_scurt.cpp Header/test_scurt.h Fisiere_CPP/test_extins.cpp Header/test_extins.h)