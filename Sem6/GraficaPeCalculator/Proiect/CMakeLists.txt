cmake_minimum_required(VERSION 3.21)
project(Proiect)

set(CMAKE_CXX_STANDARD 14)

set(SOURCE_FILES main.cpp glad.c)
add_executable(Proiect main.cpp ${SOURCE_FILES})

target_link_libraries(Proiect -lfreeglut -lglew32 -lopengl32 -lglfw3 -lglu32)
