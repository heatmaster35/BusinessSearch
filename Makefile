##############################
# Name: Leo Gomez            #
# CruzID: legomez            #
# Class: CMPS-12B            #
# Date: Oct 21, 2014         #
# filename: Makefile         #
# Details:creates jar file   # 
# BusinessSearch on on Shell.#
##############################

JAVASRC = BusinessSearch.java
SOURCES = README makefile ${JAVASRC}
MAINCLASS = BusinessSearch
CLASSES = BusinessSearch.class
JARFILE = BusinessSearch
JARCLASSES = ${CLASSES}

all: ${JARFILE}

${JARFILE}: ${CLASSES}
	echo "Main-class: ${MAINCLASS}"  > Manifest
	jar cvfm ${JARFILE} Manifest ${JARCLASSES}
	rm Manifest
	chmod +x ${JARFILE}

${CLASSES}: ${JAVASRC}
	javac -Xlint ${JAVASRC}

clean:
	rm ${CLASSES}  ${JARFILE}

.PHONY: clean all

clean: BusinessSearch BusinessSearch.class
	rm BusinessSearch.class
	rm BusinessSearch
