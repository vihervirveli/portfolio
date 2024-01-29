#!/bin/bash

#Write a shell script that checks if the file /etc/hosts exists.
#If the file exists, script prints the outcome: /etc/hosts available.
#Also check can you write in the file.
#Script prints the outcome: 
#You have permission to edit the file OR 
#you dont have permission to edit the file

filu="/etc/hosts"

if [ -f $filu ]
then
	echo "${filu} available."
	if [ -w $filu ]
	then
		echo "You have permission to edit the file ${filu}"
	else
		echo "You don't have permission to edit the file ${filu}"
	fi

fi
