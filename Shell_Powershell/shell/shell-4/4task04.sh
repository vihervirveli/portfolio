#!/bin/bash
#Create a script that has a function called network_information 
#that shows the following information:
#ip address: 
#hostname: 
#Log the messages in the syslog with a custom tag "network-information".
#Hints: Utilize arrays and ip or hostname commands with options
#hostnamea kandee käyttää, koska ip komento itkettää
#utilise arrays turha koska vlestä tulee vain yksi ip-osoite

network_information(){
ipari=$(hostname -i)
hostname=$(hostname -A)
iparimsg="Your IP address is ${ipari}"
hostnamemsg="Your hostname is ${hostname}"
echo "${iparimsg}"
echo "${hostnamemsg}"
logger -t "network-information" "${iparimsg}"
logger -t "network-information" "${hostnamemsg}"
}


network_information
