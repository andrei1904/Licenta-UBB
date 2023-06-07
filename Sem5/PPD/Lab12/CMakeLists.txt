cmake_minimum_required(VERSION 3.9)
project(Lab12)

set(CMAKE_CXX_STANDARD 17)

find_package(OpenMP)

add_executable(Lab12 main.cpp)

if(OpenMP_CXX_FOUND)
    target_link_libraries(Lab12 PUBLIC OpenMP::OpenMP_CXX)
endif()