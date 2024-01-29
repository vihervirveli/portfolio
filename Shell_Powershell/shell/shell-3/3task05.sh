#!/bin/bash
#Modify Exercise set 2, Task 5 (Create a script that 
#mimics a simple calculator).
#Change the logic that all operations are calculated by using functions.
subtract(){
let "vastaus = ${1} - ${2}"
vahennys=${vastaus}
echo "The deduction of ${1} and ${2} is ${vastaus}"
}

add(){
let "vastaus = ${1} + ${2}"
summa=${vastaus}
echo "The sum of ${1} and ${2} is ${vastaus}"
}

multiply(){
let "vastaus = ${1} * ${2}"
tulo=${vastaus}
echo "The product of ${1} and ${2} is ${tulo}"
}

division(){
jako=$(echo "scale=2; $a / $b" | bc)
echo "The division of ${1} and ${2} is ${jako}"


}


OPTIONS=("-" "+" "*" "/" "exit")

PS3="Select the operation you want: "
#this is optional, PS3 system variable is used to print out the wanted string when asking the number from the user, the default value is #?
select x in "${OPTIONS[@]}"
do
  if [ "${x}" = "exit" ]
  then
    break
  else
  read -p "Give the first number: " a
  read -p "Give the second number: " b
  	if [ "${x}" = "-" ]
  	then
      		subtract "${a}" "${b}"
	elif [ "${x}" = "+" ]
	then
		add "${a}" "${b}"
	elif [ "${x}" = "*" ]
	then
		multiply "${a}" "${b}"
	else
		division "${a}" "${b}"

	fi	

  fi
  
done
echo "exiting..."
