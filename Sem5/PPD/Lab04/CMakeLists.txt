cmake_minimum_required(VERSION 3.17)
project(Lab04)

set(CMAKE_CXX_STANDARD 14)

find_package(MPI REQUIRED)
include_directories(SYSTEM ${MPI_INCLUDE_PATH})

add_executable(Lab04 main2.cpp)

target_compile_options(Lab04 PRIVATE ${MPI_CXX_COMPILE_FLAGS})
target_link_libraries(Lab04 ${MPI_CXX_LIBRARIES} ${MPI_CXX_LINK_FLAGS})