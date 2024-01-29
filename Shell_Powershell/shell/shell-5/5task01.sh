#!/bin/bash
#Create a script that asks values from the user until user 
#decides to end the script.
#Given values are added to the end of file called animal.txt
#Finally script prints every value from the file.
filu="animal.txt"
#writing into a file
while true
do
	read -p "Give me a value and I'll write it into a text file called ${filu}. If you type exit, the program will terminate and I'll show you the values from the file: " new
	if [ "${new}" != exit  ]
	then
		echo ${new} >> ${filu} #&>/dev/null
	else
		break
	fi
done
#reading from a file
while read line
do
	echo ${line}
done < ${filu}
