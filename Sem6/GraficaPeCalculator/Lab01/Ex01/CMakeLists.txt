cmake_minimum_required(VERSION 3.21)
project(Ex01)

set(CMAKE_CXX_STANDARD 14)

set(SOURCE_FILES main.cpp glad.c)
add_executable(Ex01 main.cpp ${SOURCE_FILES})
target_link_libraries(Ex01 -lfreeglut -lglew32 -lopengl32 -lglfw3 -lglu32)
