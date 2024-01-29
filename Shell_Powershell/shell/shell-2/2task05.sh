#!/bin/bash
#Create a script that mimics a simple calculator.
#At first, script asks the operation from the user (-, +, *, / ). 
#After the user selects operation, two values are asked and the outcome is printed to the user.
#Script execution stops when the user selects "exit" operation.
#read -p "Choose operation. I

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
      		echo "The deduction of ${a} and ${b} is $((a-b))"
	elif [ "${x}" = "+" ]
	then
		echo "The sum of ${a} and ${b} is $((a+b))"
	elif [ "${x}" = "*" ]
	then
		echo "The product of ${a} and ${b} is $((a*b))"
	else
		jako=$(echo "scale=2; $a / $b" | bc)
		echo "The division of ${a} and ${b} is ${jako}"

	fi	

  fi
  
done
echo "exiting..."
