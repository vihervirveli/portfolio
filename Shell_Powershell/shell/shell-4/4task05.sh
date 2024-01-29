#!/bin/bash
#Create a script that has 3 functions: 
#mk_folder, download_image and change_perm.
#mk_folder function asks a new folder path from the 
#user and creates the folder.
#download_image function downloads the following 
#image to the previously created directory and 
#renames it as a linux-tux.png: image
#change_perm function sets the folder and image 
#permissions in a way that only owner has rights to them (rwx).
#Every possible variable substitution and command execution 
#must be shown in the terminal during the execution.
#katso video siitä mitä se sanoi exitkoodeista
set -xv
mk_folder(){
read -p "Give a new folder path and I'll make you a folder. Example input: /home/user/newfolder :" fldr
dirlocation=$fldr
sudo mkdir $dirlocation

}
download_image(){
sudo wget https://student.labranet.jamk.fi/~pekkju/script/tux.png -P $dirlocation -O "${dirlocation}/linux-tux.png"

}
change_perm(){
sudo chmod -R 700 $dirlocation
}


mk_folder
download_image
change_perm
