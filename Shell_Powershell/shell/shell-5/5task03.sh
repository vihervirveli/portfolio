#!/bin/bash
#Modify the task 2.
#Script should have function that adds a new entry to the cars.csv 
#file based on the user input.
#Every value must be asked from the user separately and value/values 
#added should be given to the function as an argument.
#Hints:
#Remember delimiters.
#One string might be easier than multiple --> how you can combine multiple strings (concatenate)?
read_filecontent(){
cars=()

while read -r line
do
  cars+=(${line})
done < $filu

length=${#cars[@]}
for (( i=1; i < ${length}; i++))
do
	currentcarstr=${cars[$i]}
	IFS="," read -ra currentcar <<< ${currentcarstr}
	echo "Manufacturer: ${currentcar[0]} | Model: ${currentcar[1]} | Color: ${currentcar[3]} | Year: ${currentcar[2]}"
done

}



add_new_entry(){
read -p "Give me the name of your car's manufacturer: " manuf
read -p "Give me the model of your car: " mdl
read -p "Give me the year your car was made: " yr
read -p "Give me the color of your car: " clr
new_entry="${manuf},${mdl},${yr},${clr}"
echo $new_entry >> $filu

}

filu=${1}
add_new_entry
read_filecontent
