#!/bin/bash
#Modify Task 3.
#User can give unlimited number of paths and 
#given paths are saved in the array.
#Function takes an array as an argument.
#Hints:
#read commands options might be useful or some kind of loop.
count_objects() {
arr=("$@")
for path in "${arr[@]}"
do
local number_of_objects=$(ls "$path" | wc -l)
echo "Number of objects in the directory ${path}: "
echo "${number_of_objects}"
done

}

echo "Give the paths to the directories that you want to know the content count of, separate paths with a space: "
read -a paths
count_objects "${paths[@]}"
