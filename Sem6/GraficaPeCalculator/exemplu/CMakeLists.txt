cmake_minimum_required(VERSION 3.21)
project(exemplu)

set(CMAKE_CXX_STANDARD 14)

set(SOURCE_FILES main.cpp glad.c mesh.cpp mesh.h model.cpp model.h obiect.cpp obiect.h scena.cpp scena.h stb_image.h)
add_executable(exemplu main.cpp ${SOURCE_FILES})

target_link_libraries(exemplu -lfreeglut -lglew32 -lopengl32 -lglfw3 -lglu32 -lassimp)
