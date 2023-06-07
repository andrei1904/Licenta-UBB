cmake_minimum_required(VERSION 3.21)
project(sfera_fp)

set(CMAKE_CXX_STANDARD 14)

set(SOURCE_FILES main.cpp glad.c)
add_executable(sfera_fp main.cpp ${SOURCE_FILES})

target_link_libraries(sfera_fp -lfreeglut -lglew32 -lopengl32 -lglfw3 -lglu32)
