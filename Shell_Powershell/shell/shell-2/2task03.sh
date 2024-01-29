#!/bin/bash
#Create a script that returns a type of objects given (file or directory or something else).
#User can give unlimited number of paths using the commandline arguments.
#Every object type is printed on the own line.


#echo $args
for ARG in "$@"
do
	if [ -d "${ARG}" ]
	then
		echo "${ARG} is a directory"
	elif [ -f "${ARG}" ]
	then
		echo "${ARG} is a file"
	else
		echo "${ARG} is something other than a file or a directory"
	fi
done


