#!/bin/bash

read -p "Hei, anna hakemisto polkuineen niin kerron montako objektia siellä on: " polku


#ls ilman a flagia ei näytä piilotettuja tiedostoja
#wc -w laskee erilliset sanat eli tiedostot

count=$(ls $polku | wc -l)

echo $count


