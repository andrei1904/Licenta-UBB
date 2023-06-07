cmake_minimum_required(VERSION 3.17)
project(Lab01_C__)

set(CMAKE_CXX_STANDARD 14)

set(CMAKE_EXE_LINKER_FLAGS "${CMAKE_EXE_LINKER_FLAGS} -Wl,--stack,10000000")
add_executable(Lab01_C__ main_dinamic.cpp)