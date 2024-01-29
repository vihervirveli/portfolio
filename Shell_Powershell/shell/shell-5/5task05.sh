#!/bin/bash
#Create a script that reads superhero.json file.
#Script prints hero names to the user who selects which 
#heroes powers or secret identity he/she wants to see.
#Script prints the desired values to the user.
#Script will continue asking what to do until user gives some string to exit to script (exit perhaps?).
#Hints:
#any iterations structure
#jq command
readarray -t memberarr < <(jq -r '.members[] | .name' superhero.json)
memberarr+=("exit")
PS3="Tell me which superhero you want more information about. If you want to quit, select exit: "
select x in "${memberarr[@]}"
do
  if [ "${x}" = "exit" ]
  then
    break
  else
  	if [ "${x}" = "Molecule Man" ]
  	then
		indeksi=0
	elif  [ "${x}" = "Madame Uppercut" ]
	then
		indeksi=1
	else
		indeksi=2
	fi
	while true
      	do
		echo -n "To hear about ${x}'s age, press 1. Their secret identity, press 2. Their powers, press 3. To go back to the main program, press 4: "
		read info
		case $info in

		1)
		age=$(jq '.members['$indeksi'] | .age' superhero.json)
		echo "${x}'s age is ${age}"
    		;;

		2)
		secretid=$(jq '.members['$indeksi'] | .secretIdentity' superhero.json)
		echo "${x}'s Secret Identity is ${secretid}"
    		;;

  		3)
		readarray -t powers < <(jq -r '.members['$indeksi'] | .powers[]' superhero.json)
		printf -v powersstr ' %s, ' "${powers[@]}"
 		echo "${x}'s Powers are: ${powersstr}"
		;;

		4)
		break
		;;

  		*)
    		echo "You chose the wrong thing, let's try again."
		continue
    		;;
		esac
	done
  fi
done
echo "exiting..."


