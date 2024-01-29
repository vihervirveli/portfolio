#!/bin/bash

#Create a script that asks a file to remove from the user.
#File is removed if it's a regular file and it's empty, 
#otherwise some kind of error message is printed.
#-f -s
read -p "Which file do you want to remove? Give path along with the filename: " filename

#first condition:
#file exists and is a regular file 
#second condition:
#true if file has content, we want empty file (false)
if [[ -f "${filename}" ]] && [ ! -s "${filename}" ]
then
	rm $filename
	echo "The file ${filename} was removed."
else
	echo "The file was not removed. Make sure the file exists, it is a regular file and that it is empty"
fi
