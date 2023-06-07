cmake_minimum_required(VERSION 3.15)
project(examen)

set(CMAKE_CXX_STANDARD 14)
set(CMAKE_CXX_FLAGS --coverage)

set(CMAKE_PREFIX_PATH  "C:\\Qt\\5.14.2\\mingw73_64\\")

find_package(Qt5Core REQUIRED )
find_package(Qt5Widgets REQUIRED )

add_executable(examen main.cpp Melodie.cpp Melodie.h MelodieValid.cpp MelodieValid.h MelodieController.cpp MelodieController.h MelodieRepo.cpp MelodieRepo.h MelodieGui.cpp MelodieGui.h Observer.h)

target_link_libraries(examen Qt5::Core Qt5::Widgets)