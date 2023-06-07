cmake_minimum_required(VERSION 3.17)
project(Lab03)

if(NOT CMAKE_BUILD_TYPE)
    set(CMAKE_BUILD_TYPE Release)
endif()

set(CMAKE_CXX_STANDARD 17)

set(CMAKE_CXX_FLAGS_RELEASE "-O2")

add_executable(Lab03 main.cpp)