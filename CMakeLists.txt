cmake_minimum_required(VERSION 3.8)

set(CMAKE_MODULE_PATH ${CMAKE_CURRENT_LIST_DIR}/KotlinCMakeModule)

project(conference-app-2018 Kotlin)

konanc_executable(
    NAME conference-app-2018
    TARGET iphone
    SOURCES src/main/kotlin
)

