#!/bin/bash
#Create a script that prints 3 random 
#numbers on the terminal and also generates 
#a syslog message with a correlating number for each random number.
#Log message priority should be user.info.
#Hints: Special variable $RANDOM
echo "Here are 3 random numbers. You will find their corresponding log messages in /var/log/syslog: "
for x in {1..3}
do
luku=$RANDOM
logger -p user.info "${luku}" && echo "${luku}"
done
