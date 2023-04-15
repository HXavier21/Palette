cmake_minimum_required(VERSION 3.16)

project(monet)

set(CMAKE_CXX_STANDARD 17)
set(CMAKE_CXX_FLAGS "-Werror")
set(CMAKE_EXPORT_COMPILE_COMMANDS ON)

file(GLOB_RECURSE SOURCES "*.cc")

include_directories(${CMAKE_CURRENT_SOURCE_DIR}/cpp)

add_library(${PROJECT_NAME} SHARED ${SOURCES})