cmake_minimum_required(VERSION 3.8)
project(makefile_demo)

set(CMAKE_C_STANDARD 99)

set(SOURCE_FILES main.c)
set(SOURCE_FILES linux-namespace-demo-master)

add_executable(makefile_demo ${SOURCE_FILES})

message("start!\n")
message(${makefile_demo_SOURCE_DIR})
message(${PROJECT_BINARY_DIR})

add_custom_target(demo COMMAND make -C ${makefile_demo_SOURCE_DIR}
        CLION_EXE_DIR=${PROJECT_BINARY_DIR} && cp ${makefile_demo_SOURCE_DIR}/main ./
        )