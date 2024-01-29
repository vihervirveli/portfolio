#!/bin/bash


read -p "Please give a file path to the file you want to copy: " filepath

read -p "Please give the path you want to copy the file to: " newpath

$(cp $filepath $newpath)

echo "Copied the file ${filepath} to ${newpath}"

