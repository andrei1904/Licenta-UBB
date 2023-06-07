cmake_minimum_required(VERSION 3.15)
project(MultiDictionarOrdonatTD)

set(CMAKE_CXX_STANDARD 14)

add_executable(MultiDictionarOrdonatTD main.cpp CPP/IteratorMDO.cpp Header/IteratorMDO.h CPP/MDO.cpp Header/MDO.h CPP/TestExtins.cpp Header/TestExtins.h CPP/TestScurt.cpp Header/TestScurt.h)