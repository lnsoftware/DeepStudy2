# CMAKE generated file: DO NOT EDIT!
# Generated by "Unix Makefiles" Generator, CMake Version 3.8

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Remove some rules from gmake that .SUFFIXES does not remove.
SUFFIXES =

.SUFFIXES: .hpux_make_needs_suffix_list


# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

# The shell in which to execute make rules.
SHELL = /bin/sh

# The CMake executable.
CMAKE_COMMAND = /Applications/CLion.app/Contents/bin/cmake/bin/cmake

# The command to remove a file.
RM = /Applications/CLion.app/Contents/bin/cmake/bin/cmake -E remove -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = /Users/hg/Github/DeepStudy2/linux-container/cccc

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = /Users/hg/Github/DeepStudy2/linux-container/cccc/cmake-build-debug

# Utility rule file for demo.

# Include the progress variables for this target.
include CMakeFiles/demo.dir/progress.make

CMakeFiles/demo:
	make -C /Users/hg/Github/DeepStudy2/linux-container/cccc CLION_EXE_DIR=/Users/hg/Github/DeepStudy2/linux-container/cccc/cmake-build-debug && cp /Users/hg/Github/DeepStudy2/linux-container/cccc/main ./

demo: CMakeFiles/demo
demo: CMakeFiles/demo.dir/build.make

.PHONY : demo

# Rule to build all files generated by this target.
CMakeFiles/demo.dir/build: demo

.PHONY : CMakeFiles/demo.dir/build

CMakeFiles/demo.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/demo.dir/cmake_clean.cmake
.PHONY : CMakeFiles/demo.dir/clean

CMakeFiles/demo.dir/depend:
	cd /Users/hg/Github/DeepStudy2/linux-container/cccc/cmake-build-debug && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /Users/hg/Github/DeepStudy2/linux-container/cccc /Users/hg/Github/DeepStudy2/linux-container/cccc /Users/hg/Github/DeepStudy2/linux-container/cccc/cmake-build-debug /Users/hg/Github/DeepStudy2/linux-container/cccc/cmake-build-debug /Users/hg/Github/DeepStudy2/linux-container/cccc/cmake-build-debug/CMakeFiles/demo.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/demo.dir/depend
