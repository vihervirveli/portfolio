#!/bin/bash


read -p "Anna tiedostonimi: " filename

paate=$(date +%F)

uusifilu="${filename}${paate}.txt"

kotikansio=$HOME

fullpath="${kotikansio}/${uusifilu}"

touch $fullpath

echo "File ${uusifilu} has been created in your home directory"
