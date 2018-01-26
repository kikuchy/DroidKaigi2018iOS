cmake_minimum_required(VERSION 3.8)

set(CMAKE_MODULE_PATH ${CMAKE_CURRENT_LIST_DIR}/KotlinCMakeModule)

project(conference-app-2018 Kotlin)

cinterop(
        NAME libs
        TARGET iphone
        DEF_FILE src/main/c_interop/libs.def
)

konanc_executable(
    NAME conference-app-2018
    TARGET iphone
    SOURCES src/main/kotlin
)

