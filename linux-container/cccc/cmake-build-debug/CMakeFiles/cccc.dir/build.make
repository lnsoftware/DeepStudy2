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
include CMakeFiles/cccc.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/cccc.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/cccc.dir/flags.make

CMakeFiles/cccc.dir/main.c.o: CMakeFiles/cccc.dir/flags.make
CMakeFiles/cccc.dir/main.c.o: ../main.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=/Users/hg/Github/DeepStudy2/linux-container/cccc/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building C object CMakeFiles/cccc.dir/main.c.o"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles/cccc.dir/main.c.o   -c /Users/hg/Github/DeepStudy2/linux-container/cccc/main.c

CMakeFiles/cccc.dir/main.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/cccc.dir/main.c.i"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E /Users/hg/Github/DeepStudy2/linux-container/cccc/main.c > CMakeFiles/cccc.dir/main.c.i

CMakeFiles/cccc.dir/main.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/cccc.dir/main.c.s"
	/Applications/Xcode.app/Contents/Developer/Toolchains/XcodeDefault.xctoolchain/usr/bin/cc $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S /Users/hg/Github/DeepStudy2/linux-container/cccc/main.c -o CMakeFiles/cccc.dir/main.c.s

CMakeFiles/cccc.dir/main.c.o.requires:

.PHONY : CMakeFiles/cccc.dir/main.c.o.requires

CMakeFiles/cccc.dir/main.c.o.provides: CMakeFiles/cccc.dir/main.c.o.requires
	$(MAKE) -f CMakeFiles/cccc.dir/build.make CMakeFiles/cccc.dir/main.c.o.provides.build
.PHONY : CMakeFiles/cccc.dir/main.c.o.provides

CMakeFiles/cccc.dir/main.c.o.provides.build: CMakeFiles/cccc.dir/main.c.o


# Object files for target cccc
cccc_OBJECTS = \
"CMakeFiles/cccc.dir/main.c.o"

# External object files for target cccc
cccc_EXTERNAL_OBJECTS =

cccc: CMakeFiles/cccc.dir/main.c.o
cccc: CMakeFiles/cccc.dir/build.make
cccc: CMakeFiles/cccc.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=/Users/hg/Github/DeepStudy2/linux-container/cccc/cmake-build-debug/CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking C executable cccc"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles/cccc.dir/link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/cccc.dir/build: cccc

.PHONY : CMakeFiles/cccc.dir/build

CMakeFiles/cccc.dir/requires: CMakeFiles/cccc.dir/main.c.o.requires

.PHONY : CMakeFiles/cccc.dir/requires

CMakeFiles/cccc.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles/cccc.dir/cmake_clean.cmake
.PHONY : CMakeFiles/cccc.dir/clean

CMakeFiles/cccc.dir/depend:
	cd /Users/hg/Github/DeepStudy2/linux-container/cccc/cmake-build-debug && $(CMAKE_COMMAND) -E cmake_depends "Unix Makefiles" /Users/hg/Github/DeepStudy2/linux-container/cccc /Users/hg/Github/DeepStudy2/linux-container/cccc /Users/hg/Github/DeepStudy2/linux-container/cccc/cmake-build-debug /Users/hg/Github/DeepStudy2/linux-container/cccc/cmake-build-debug /Users/hg/Github/DeepStudy2/linux-container/cccc/cmake-build-debug/CMakeFiles/cccc.dir/DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/cccc.dir/depend
