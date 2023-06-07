cmake_minimum_required(VERSION 3.21)
project(Ex01_calota_pp)

set(CMAKE_CXX_STANDARD 14)

set(SOURCE_FILES main.cpp glad.c)
add_executable(Ex01_calota_pp main.cpp ${SOURCE_FILES})

target_link_libraries(Ex01_calota_pp -lfreeglut -lglew32 -lopengl32 -lglfw3 -lglu32)
