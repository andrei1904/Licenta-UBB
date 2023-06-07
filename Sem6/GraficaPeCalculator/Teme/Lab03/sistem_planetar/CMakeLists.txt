cmake_minimum_required(VERSION 3.21)
project(sistem_planetar)

set(CMAKE_CXX_STANDARD 14)

set(SOURCE_FILES main.cpp glad.c stb_image.h)
add_executable(sistem_planetar main.cpp ${SOURCE_FILES})

target_link_libraries(sistem_planetar -lfreeglut -lglew32 -lopengl32 -lglfw3 -lglu32)

