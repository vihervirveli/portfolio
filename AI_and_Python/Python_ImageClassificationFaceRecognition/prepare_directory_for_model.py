#!/usr/bin/env python
# coding: utf-8
"""
Author: Vihervirveli/N5830
@vihervirveli project for AI and IoT. 
Purpose of the file:
•	Prepare a directory for a neural network. Moves away pictures that are too small (not to delete, but its own trash directory), resizes images, finds pictures with only 1 face in them
and checks that one face for face landmarks. Based on that, the pictures are moved to keep or delete directories.    
"""


import pandas as pd
import face_recognition
from PIL import ImageDraw
import numpy as np
from PIL import Image
import os
import shutil
import matplotlib.pyplot as plt

directory = "D://kasvokuvat//imdb_crop//imdb_crop//13//"
kasvods_dir = "D://kasvokuvat//kasvods//"
temp_keep_dir = os.path.join(kasvods_dir, "temp_keep//")
keep_dir = os.path.join(kasvods_dir, "keep//")
delete_dir = os.path.join(kasvods_dir, "delete//")
trash_dir = os.path.join(kasvods_dir, "trash//")





def throw_away_small_pictures(directorypath, minwidth, minheight, disposal_directory):
    """
    Author: Vihervirveli/N5830
    Receives a directory and dimensions, moves the pictures in the folder whose dimensions are smaller than
    desired_width and desired_height to a disposal directory
    Args: directorypath: path to a directory where the pictures reside
          desired_width: minimum width for a picture
          desired_height: minimum height for a picture
          disposal_directory: where to move the small pictures
    """    
    picture_list = os.listdir(directorypath)
    horizontal = []
    for pic in picture_list:
        img = Image.open(os.path.join(directorypath,pic))
        width,height = img.size
        horizontal.append(width)
        img.close()
        if width < minwidth or height < minheight:
            shutil.move(os.path.join(directorypath,pic), trash_dir)
    return horizontal, picture_list




def illustrate_picture_sizes(horl, piclist):
    """
    Author: Vihervirveli/N5830
    Receives a list of horizontal sizes of pictures, makes a DataFrame of it, sorts it and plots to illustrate what kind of
    sizes the ds has the most
    """
    horizontal_size_df = pd.DataFrame(horl, columns=["Horizontal size"])
    horizontal_size_df["Picture filenames"] = piclist
    horizontal_size_df.sort_values(by=["Horizontal size"], inplace=True)
    horizontal_size_df.plot(kind='bar',x='Picture filenames',y='Horizontal size',color='red')
    plt.show()
    



def resize_dir(directorypath, desired_width, desired_height):
    """
    Author: Vihervirveli/N5830
    Receives a directory and dimensions, resizes all the pictures in the folder to desired_width and desired_height
    Args: directorypath: path to a directory where the pictures reside
          desired_width: which width to resize the pictures
          desired_height: which height to resize the pictures
    """
    picture_list = os.listdir(directorypath)
    for pic in picture_list:
        img = Image.open(os.path.join(directorypath,pic))
        img = img.resize((desired_width, desired_height), Image.ANTIALIAS)
        img.convert("RGB")
        img.save(os.path.join(directorypath,pic))




def batch_locations_from_pics(directorypath, batchsize):
    """
    Author: Vihervirveli/N5830
    Args: directory path to where the pics are, batchsize: how many pics will go to the batch_face_locations at a time
    Returns: df: DataFrame with the relevant information about the pictures
    """
    #list of pictures filenames
    pictures = os.listdir(directorypath)
        
    #creating a dataframe to keep all of this clear
    df = pd.DataFrame({"Picture filenames": pictures})
    #separate file paths into batches
    how_many_pics = len(pictures)
    batch_designator = []
    whichbatch = 0
    division = int(how_many_pics / batchsize) 
    remain = (how_many_pics % batchsize > 0) #either 1 if True or 0 if False
    how_many_batches = division + remain #if how_many_pics is even, 0 and if odds 1 
    
    #now that we have batches, we'll load those batches and feed them to batch_face_locations
    face_locales = []
    for i in range(how_many_batches):
        #creating a list of pic paths for uploading
        if (i*batchsize) < how_many_pics:
            current_batch_for_upload = df[(i*batchsize):((i+1)*batchsize)]
        else:
            current_batch_for_upload = df[i:]
        pic_paths = current_batch_for_upload["Picture filenames"].tolist()
        our_batch_numpy = []
        #uploading the pictures, then transforming them into numpy arrays
        for pic_path in pic_paths:
            img = face_recognition.load_image_file(os.path.join(directorypath, pic_path))
            data = np.array(img)
            our_batch_numpy.append(data)
        #feeding the batch into the function
        kasa_kuvia = face_recognition.batch_face_locations(our_batch_numpy,number_of_times_to_upsample=0, batch_size = batchsize)
        face_locales.extend(kasa_kuvia)
        
    
    df["Keep or delete"] = [len(i) == 1 for i in face_locales]
    return df




def move_files_per_label(df,label, homedir, destination_dir):
    """
    Author: Vihervirveli/N5830
    Moves files in the DataFrame based on their label to a new directory
    Args: df: DataFrame with the picture filenames
          label: keep or delete => True or False 
          homedir: where the pictures are
          destination_dir: where to move the pictures
    """
    label_df = df.loc[df["Keep or delete"] == label]
    labelit_files = label_df["Picture filenames"].tolist()
    for filename in labelit_files:
        path_to_pic = os.path.join(homedir, filename)
        shutil.move(path_to_pic, destination_dir)


def face_landmarks_check(temp_directory):
    """
    Author: Vihervirveli/N5830
    This function is run on the temporary keep directory, to see if the pictures inside contain face landmarks. If they don't,
    they become deletes.
    Returns: a DataFrame of the files, with labels keep or delete on them
    """
    kuvat = os.listdir(temp_directory)
    keepOrDelete = []
    df = pd.DataFrame(kuvat, columns=["Picture filenames"]) 
    for p in kuvat:
        pic = face_recognition.load_image_file(os.path.join(temp_directory, p))
        data = np.array(pic)
        landmarks = face_recognition.face_landmarks(data, model= "small")
        landmarksOrNo = len(landmarks)
        if  landmarksOrNo == 0:
            keepOrDelete.append(False)
        else:
            keepOrDelete.append(True)

    df["Keep or delete"] = keepOrDelete
    return df


def main():
    listicles = throw_away_small_pictures(directory, 200,200, trash_dir)
    horizontals = listicles[0]
    piclist = listicles[1]
    illustrate_picture_sizes(horizontals, piclist)

    resize_dir(directory, 350,350)

    #prepare to wait an eternity
    df = batch_locations_from_pics(directory, 80)

    move_files_per_label(df, True,temp_keep_dir)
    move_files_per_label(df, False, delete_dir)

    landmark_df = face_landmarks_check(temp_keep_dir)
    move_files_per_label(landmark_df, True,temp_keep_dir, keep_dir)
    move_files_per_label(landmark_df, False,temp_keep_dir, delete_dir)



if __name__ == '__main__':
    main()








