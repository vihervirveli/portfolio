#!/bin/bash
#Create a script that asks values from the user until user types exit. 
#All the values are saved in array. Finally script prints out the whole 
#array (line by line) using the following syntax: index: value.
declare -a array=()
while true
do
	read -p "Give a value. If you type 'exit', I'll show you the values you've typed so far! " uusi
	if [ "${uusi}" = "exit" ]
	then
		break
	fi
	array+=("${uusi}")
done
echo "Here are the values you gave:"
for i in ${!array[@]}
do
	echo "${i}:${array[$i]}"
done
