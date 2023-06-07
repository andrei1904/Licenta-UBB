cmake_minimum_required(VERSION 3.21)
project(Ex03)

set(CMAKE_CXX_STANDARD 14)

set(SOURCE_FILES main.cpp glad.c)
add_executable(Ex03 main.cpp ${SOURCE_FILES})
target_link_libraries(Ex03 -lfreeglut -lglew32 -lopengl32 -lglfw3 -lglu32)
