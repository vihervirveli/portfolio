#!/bin/bash
#Create a script that installs the program using apt,
#based on the user input. (cowsay etc.)
#If the installation is successfull, tell that to the user and exit normally.
#If not, inform user and exit with a code 111.
#Hints: sudo permissions and apt options
read -p  "Tell me which program you want to install! " prog

sudo apt-get install -y "${prog}" &>/dev/null
exitti=${?}
if [ $exitti -ne 0 ]
then
	echo "Something went wrong in installing ${prog}."
	exit 111
else
	echo "Successfully installed ${prog}"
	exit 0

fi




