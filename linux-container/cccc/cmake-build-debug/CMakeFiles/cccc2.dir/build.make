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

# Include any dependencies generated for this target.
include CMakeFiles/cccc2.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/cccc2.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/cccc2.dir/flags.make

CMakeFiles/cccc2.dir/main3.c.o: CMakeFiles/cccc2.dir/flags.make
CMakeFiles/cccc2.dir/main3.c.o: ../main3.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/Users/hg/Github/DeepStudy2/linux-container/cccc/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building C object CMakeFiles/cccc2.dir/main3.c.o"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles/cccc2.dir/main3.c.o   -c /Users/hg/Github/DeepStudy2/linux-container/cccc/main3.c

CMakeFiles/cccc2.dir/main3.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/cccc2.dir/main3.c.i"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E /Users/hg/Github/DeepStudy2/linux-container/cccc/main3.c > CMakeFiles/cccc2.dir/main3.c.i

CMakeFiles/cccc2.dir/main3.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/cccc2.dir/main3.c.s"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S /Users/hg/Github/DeepStudy2/linux-container/cccc/main3.c -o CMakeFiles/cccc2.dir/main3.c.s

CMakeFiles/cccc2.dir/main3.c.o.requires:

.PHONY : CMakeFiles/cccc2.dir/main3.c.o.requires

CMakeFiles/cccc2.dir/main3.c.o.provides: CMakeFiles/cccc2.dir/main3.c.o.requires
	$(MAKE) -f CMakeFiles/cccc2.dir/build.make CMakeFiles/cccc2.dir/main3.c.o.provides.build
.PHONY : CMakeFiles/cccc2.dir/main3.c.o.provides

CMakeFiles/cccc2.dir/main3.c.o.provides.build: CMakeFiles/cccc2.dir/main3.c.o


# Object files for target cccc2
cccc2_OBJECTS = \
"CMakeFiles/cccc2.dir/main3.c.o"

# External object files for target cccc2
cccc2_EXTERNAL_OBJECTS =

cccc2: CMakeFiles/cccc2.dir/main3.c.o
cccc2: CMakeFiles/cccc2.dir/build.make
cccc2: CMakeFiles/cccc2.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/Users/hg/Github/DeepStudy2/linux-container/cccc/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking C executable cccc2"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/cccc2.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/cccc2.dir/build: cccc2

.PHONY : CMakeFiles/cccc2.dir/build

CMakeFiles/cccc2.dir/requires: CMakeFiles/cccc2.dir/main3.c.o.requires

.PHONY : CMakeFiles/cccc2.dir/requires

CMakeFiles/cccc2.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/cccc2.dir/cmake_clean.cmake
.PHONY : CMakeFiles/cccc2.dir/clean

CMakeFiles/cccc2.dir/depend:
	cd /Users/hg/Github/DeepStudy2/linux-container/cccc/cmake-build-debug && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /Users/hg/Github/DeepStudy2/linux-container/cccc /Users/hg/Github/DeepStudy2/linux-container/cccc /Users/hg/Github/DeepStudy2/linux-container/cccc/cmake-build-debug /Users/hg/Github/DeepStudy2/linux-container/cccc/cmake-build-debug /Users/hg/Github/DeepStudy2/linux-container/cccc/cmake-build-debug/CMakeFiles/cccc2.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/cccc2.dir/depend
