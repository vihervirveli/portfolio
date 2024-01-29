#!/bin/bash
#Create a script that asks URL from the user and a
# function that tries to ping it with a 1 package.
#If ping is successfull, return a exit code 0.
#If not, return a exit code 1.
#Based on the exit code that the function returned, print outcome to user.
function pingaa(){
ping "${1}" -c 1 &>/dev/null
if [ ${?} -ne 0 ]
then
	#error
	return 1

else
	#success
	return 0
fi
}

read -p "Give URL and I'll try to ping it: " url
pingaa "$url"
exitti=${?}
if [ $exitti -ne 0 ]
then
	echo "Something went wrong in pinging ${url}"
else
	echo "Ping to ${url} successful, here is the outcome:"
	ping ${url} -c 1

fi
