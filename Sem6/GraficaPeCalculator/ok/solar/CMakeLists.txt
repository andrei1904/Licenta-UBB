cmake_minimum_required(VERSION 3.21)
project(solar)

set(CMAKE_CXX_STANDARD 14)

set(SOURCE_FILES main.cpp glad.c)
add_executable(solar main.cpp imageloader.cpp imageloader.h ${SOURCE_FILES} )

target_link_libraries(solar -lfreeglut -lglew32 -lopengl32 -lglfw3 -lglu32)

