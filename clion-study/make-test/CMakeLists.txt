cmake_minimum_required(VERSION 3.8)
project(make_test)

set(CMAKE_C_STANDARD 99)

set(SOURCE_FILES main.c)
add_executable(make_test ${SOURCE_FILES})