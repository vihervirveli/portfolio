#!/bin/bash


path1="/home/user/${1}"
path2="/home/user/${2}"
path3="/home/user/${3}"

$(rm $path1 $path2 $path3)

echo "I deleted the following files: ${1}, ${2} and ${2}"
