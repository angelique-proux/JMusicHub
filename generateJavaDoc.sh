#!/bin/sh

javadoc business -sourcepath src/musichub/ -d doc
javadoc main -sourcepath src/musichub/ -d doc
javadoc util -sourcepath src/musichub/ -d doc
#create the javadoc
