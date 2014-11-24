#    make	      # Defaults to the first target: default
#    make default    # Same as make compile.
#    make compile    # Compiles Java code in this directory.
#    make style      # Runs style checks (only on instructional machines)
#    make check      # Compile if needed and test using HW1Test.
#    make clean      # Remove extraneous or rebuildable files

# Define $(SRCS) to be a list of Java files.
SRCS = Leetcode.java Test.java Leetcode2.java Leetcode3.java

# The targets following .PHONY aren't actually files; they serve as commands.
.PHONY: compile default check clean style

# By default, compile all sources
default: compile

# To compile all source files, just bring the file YearCheck.class up to date.
compile: Test.class

# Test the compiled program, after first making sure it is up-to-date.
check: Test.class
	java Test

# Test Prime.java
prime: Test.class
	java Prime PrimeTest.txt

# Test Factor.java
factor: Test.class
	java Factor FactorTest.txt

# Remove extraneous or reconstructable files.
clean:
	rm -f *.class *~

# To force pull from git and overwrite all the files with current version.
forcePull:
	git fetch --all
	git reset --hard origin/master

Test.class: $(SRCS) 
	javac -g $(SRCS)
