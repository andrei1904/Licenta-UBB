cmake_minimum_required(VERSION 3.15)
project(Agentie_de_turism C)

set(CMAKE_C_STANDARD 11)
set(CMAKE_C_FLAGS "--coverage")

add_executable(Agentie_de_turism Main.c Console.c Console.h Repo.c Repo.h Service.c Service.h Test.c Test.h
        Validation.c Validation.h Model.h Model.c)