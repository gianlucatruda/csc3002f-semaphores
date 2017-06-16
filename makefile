# Makefile for CSC3002F Semaphores assignment
# Gianluca Truda
# Adapted from template for Jammie Java Project
# https://github.com/gianlucatruda/jammie-java

SRCDIR = src
BINDIR = bin

JAVAC = javac
JFLAGS = -g -d $(BINDIR) -cp $(BINDIR)


vpath %.java $(SRCDIR):

vpath %.class $(BINDIR):

# define general build rule for java sources
.SUFFIXES:  .java  .class

.java.class:
	$(JAVAC)  $(JFLAGS)  $<

CLASSES: 	\
			SimTimer.class Voyage.class	\
			Person.class Taxi.class \
			Simulator.class

default: classes

classes: $(CLASSES:.java=.class)

clean:
	@rm -f  $(BINDIR)/*.class
	@rm -f $(BINDIR)/*/*.class
