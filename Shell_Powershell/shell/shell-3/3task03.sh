#!/bin/bash
#Modify Task 2.
#Create a script that prints a number of objects 
#in a given directory to the user. The path is asked from the user.
count_objects() {
read -p "Give the path to the directory that you want to know the content count of: " BASEDIR
local number_of_objects=$(ls "$BASEDIR" | wc -l)
echo "Number of objects in the directory ${BASEDIR}: "
echo "${number_of_objects}"
}

count_objects
