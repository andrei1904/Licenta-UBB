cmake_minimum_required(VERSION 3.15)
project(Magazin)

set(CMAKE_CXX_STANDARD 14)
set(CMAKE_CXX_FLAGS "--coverage")
set(CMAKE_PREFIX_PATH  "C:\\Qt\\5.14.2\\mingw73_64\\")

find_package(Qt5Core REQUIRED )
find_package(Qt5Widgets REQUIRED )
add_executable(Magazin main.cpp Prezentare/ui_magazin.cpp Prezentare/ui_magazin.h Bussines/service.cpp Bussines/service.h Domeniu/produs.h Infrastructura/repository.cpp Infrastructura/repository.h Domeniu/validator.cpp Domeniu/validator.h Domeniu/lista.h Domeniu/produs.cpp Domeniu/cos_produse.h Domeniu/cos_produse.cpp Infrastructura/file_repository.cpp Infrastructura/file_repository.h Infrastructura/exporta.cpp Infrastructura/exporta.h Infrastructura/undo.h Prezentare/grafic.cpp GUImagazin.cpp GUImagazin.h observer.h)

target_link_libraries(Magazin Qt5::Core Qt5::Widgets)