cmake_minimum_required(VERSION 3.0)
project(t1-cpp-2)

set(CMAKE_CXX_STANDARD 14)
set(CMAKE_BUILD_TYPE Debug)
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -fprofile-arcs -ftest-coverage -Wall -ggdb -lpthread")
set(CMAKE_EXPORT_COMPILE_COMMANDS ON)

include_directories(src)

add_subdirectory(src)
add_subdirectory(test)

set(CMAKE_MODULE_PATH ${PROJECT_SOURCE_DIR}/CMakeModules)
if(CMAKE_COMPILER_IS_GNUCXX)
    include(CodeCoverage)
	set(ENABLE_COVERAGE ON)
	SETUP_TARGET_FOR_COVERAGE(${CMAKE_PROJECT_NAME}-coverage ${CMAKE_PROJECT_NAME}-test coverage)
endif()


