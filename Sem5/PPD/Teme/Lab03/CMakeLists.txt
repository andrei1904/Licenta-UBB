cmake_minimum_required(VERSION 3.17)
project(Lab03)

set(CMAKE_CXX_STANDARD 17)

find_package(MPI REQUIRED)
include_directories(SYSTEM ${MPI_INCLUDE_PATH})

#add_executable(Lab03 main_seq.cpp)
#add_executable(Lab03 main_mpi1.cpp)
add_executable(Lab03 main_mpi2.cpp)
#add_executable(Lab03 ok.cpp)
#add_executable(Lab03 prb1.cpp)

target_compile_options(Lab03 PRIVATE ${MPI_CXX_COMPILE_FLAGS})
target_link_libraries(Lab03 ${MPI_CXX_LIBRARIES} ${MPI_CXX_LINK_FLAGS})