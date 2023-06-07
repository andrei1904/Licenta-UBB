cmake_minimum_required(VERSION 3.13)
project(Multime_cu_vector_dinamic)

set(CMAKE_CXX_STANDARD 14)

add_executable(Multime_cu_vector_dinamic app.cpp Multime.cpp Header/Multime.h IteratorMultime.cpp Header/IteratorMultime.h TestScurt.cpp Header/TestScurt.h TestExtins.cpp Header/TestExtins.h)