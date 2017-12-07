cmake_minimum_required(VERSION 3.8)
project(basic)

set(CMAKE_C_STANDARD 99)

set(SOURCE_FILES main.c demo.c)
add_executable(basic ${SOURCE_FILES})