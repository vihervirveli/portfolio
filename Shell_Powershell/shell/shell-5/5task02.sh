#!/bin/bash
#Create a script that reads cars.csv file.
#File that is read must be given at the script startup as a command line argument.
#Script reads information from the file and prints the values to the user.
#Output format:
#--- Line 1 ---
#Manufacturer: X
#Model: X
#Color: X
#Year: X
#--- Line 2 ---
#Manufacturer: Y
#Model: Y
#Color: Y
#Year: Y
cars=()

while read -r line
do
  cars+=(${line})
done < "${1}"

length=${#cars[@]}
for (( i=1; i < ${length}; i++))
do
	currentcarstr=${cars[$i]}
	IFS="," read -ra currentcar <<< ${currentcarstr}
	echo "Manufacturer: ${currentcar[0]} | Model: ${currentcar[1]} | Color: ${currentcar[3]} | Year: ${currentcar[2]}"
done
