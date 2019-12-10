#!/usr/bin/env python
# coding: utf-8

# In[1]:


"""
@vihervirveli project for AI and IoT. 
Purpose of the project:
•	Make a CNN that will determine that the pictures used in an age determining program 
    1.	are big enough
    2.	have one (1) face in them
    3.	in addition to a face, the picture also contains face landmarks
"""



from __future__ import absolute_import, division, print_function, unicode_literals
import os
import tensorflow as tf
from tensorflow.keras.models import Sequential
from tensorflow.keras.layers import Dense, Conv2D, Flatten, Dropout, MaxPooling2D
from tensorflow.keras.preprocessing.image import ImageDataGenerator
from tensorflow.keras.optimizers import Adam
from tensorflow.keras.callbacks import EarlyStopping
import os
import numpy as np
import matplotlib.pyplot as plt
AUTOTUNE = tf.data.experimental.AUTOTUNE
import IPython.display as display
from PIL import Image
import pathlib

kasvods_dir = "D://kasvokuvat//kasvods//for_use//"
print("testi")



#the directory where we keep our test and train directories. They both contain directories keep and delete.
data_dir = pathlib.Path(kasvods_dir)
print(data_dir)



#how many pictures do we have for our uses
image_count = len(list(data_dir.glob('*/*/*.jpg'))) #+/*?
image_count



#what our class names are (keep and delete)
CLASS_NAMES = np.array([item.name for item in data_dir.glob('*/*')])
CLASS_NAMES




#here we can see a few example pictures from train deletes
deletes = list(data_dir.glob('train//delete//*'))

for image_path in deletes[:3]:
    display.display(Image.open(str(image_path)))




#let's create a dataset of the file paths
list_ds = tf.data.Dataset.list_files(str(data_dir/'*/*/*'))

for f in list_ds.take(10):
    print(f.numpy())



BATCH_SIZE = 32
IMG_HEIGHT = 150
IMG_WIDTH = 150
STEPS_PER_EPOCH = np.ceil(image_count/BATCH_SIZE)


def get_label(file_path):
    # convert the path to a list of path components
    parts = tf.strings.split(file_path, os.path.sep)
    # The second to last is the class-directory
    return parts[-2] == CLASS_NAMES

def decode_img(img):
    # convert the compressed string to a 3D uint8 tensor
    img = tf.image.decode_jpeg(img, channels=3)
    # Use `convert_image_dtype` to convert to floats in the [0,1] range.
    img = tf.image.convert_image_dtype(img, tf.float32)
    # resize the image to the desired size.
    return tf.image.resize(img, [IMG_WIDTH, IMG_HEIGHT])

def process_path(file_path):
    """
    Returns: a pair of img: image and label:label so that our model can know which is the correct answer when learning
    and testing
    """
    label = get_label(file_path)
    # load the raw data from the file as a string
    img = tf.io.read_file(file_path)
    img = decode_img(img)
    return img, label

labeled_ds = list_ds.map(process_path, num_parallel_calls=AUTOTUNE)
for image, label in labeled_ds.take(2):
    print("Image shape: ", image.numpy().shape)
    print("Label: ", label.numpy())




def show_batch(image_batch, label_batch):
    plt.figure(figsize=(10,10))
    for n in range(25):
        ax = plt.subplot(5,5,n+1)
        plt.imshow(image_batch[n])
        plt.title(CLASS_NAMES[label_batch[n]==1][0].title())
        plt.axis('off')


def prepare_for_training(ds, cache=True, shuffle_buffer_size=1000):
  # This is a small dataset, only load it once, and keep it in memory.
  # use `.cache(filename)` to cache preprocessing work for datasets that don't
  # fit in memory.
    if cache:
        if isinstance(cache, str):
            ds = ds.cache(cache)
        else:
            ds = ds.cache()

    ds = ds.shuffle(buffer_size=shuffle_buffer_size)

    # Repeat forever
    ds = ds.repeat()

    ds = ds.batch(BATCH_SIZE)

    # `prefetch` lets the dataset fetch batches in the background while the model
    # is training.
    ds = ds.prefetch(buffer_size=AUTOTUNE)
    return ds

train_ds = prepare_for_training(labeled_ds)

image_batch, label_batch = next(iter(train_ds))

show_batch(image_batch.numpy(), label_batch.numpy())




PATH = os.path.dirname(kasvods_dir)
train_dir = os.path.join(PATH, 'train')
test_dir = os.path.join(PATH, 'test')
train_keep_dir = os.path.join(train_dir, 'keep')  
train_delete_dir = os.path.join(train_dir, 'delete')  
test_keep_dir = os.path.join(test_dir, 'keep')  
test_delete_dir = os.path.join(test_dir, 'delete') 




num_keeps_tr = len(os.listdir(train_keep_dir))
num_deletes_tr = len(os.listdir(train_delete_dir))

num_keep_test = len(os.listdir(test_keep_dir))
num_delete_test = len(os.listdir(test_delete_dir))

total_train = num_keeps_tr + num_deletes_tr
total_test = num_keep_test + num_delete_test

print('total training keep images:', num_keeps_tr)
print('total training delete images:', num_deletes_tr)

print('total validation keep images:', num_keep_test)
print('total validation delete images:', num_delete_test)
print("--")
print("Total training images:", total_train)
print("Total validation images:", total_test)




batch_size = 50 #55=> 86%, 60 => 88%, 40 => 88%, 50 => 91%, 128 => 85-86%
epochs = 10 #20, 15
IMG_HEIGHT = 350
IMG_WIDTH = 350




train_image_generator = ImageDataGenerator(rescale=1./255,horizontal_flip=True) # Generator for our training data
test_image_generator = ImageDataGenerator(rescale=1./255) # Generator for our validation data




train_data_gen = train_image_generator.flow_from_directory(batch_size=batch_size,
                                                           directory=train_dir,
                                                           shuffle=True,
                                                           target_size=(IMG_HEIGHT, IMG_WIDTH),
                                                           class_mode='binary')

test_data_gen = test_image_generator.flow_from_directory(batch_size=batch_size,
                                                              directory=test_dir,
                                                              target_size=(IMG_HEIGHT, IMG_WIDTH),
                                                              class_mode='binary')




sample_training_images, _ = next(train_data_gen)

# This function will plot images in the form of a grid with 1 row and 5 columns where images are placed in each column.
def plotImages(images_arr):
    fig, axes = plt.subplots(1, 5, figsize=(20,20))
    axes = axes.flatten()
    for img, ax in zip( images_arr, axes):
        ax.imshow(img)
        ax.axis('off')
    plt.tight_layout()
    plt.show()

plotImages(sample_training_images[:5])




#our actual model and a summary of it

model = Sequential([
    Conv2D(16, 3, padding='same', activation='relu', input_shape=(IMG_HEIGHT, IMG_WIDTH ,3)),
    MaxPooling2D(),
    Dropout(0.1),
    Conv2D(32, 3, padding='same', activation='relu'),
    MaxPooling2D(),
    Conv2D(64, 3, padding='same', activation='relu'),
    MaxPooling2D(),
    Dropout(0.1),
    Flatten(),
    Dense(512, activation='relu'),
    Dense(1, activation='sigmoid')
])

adam = Adam(learning_rate=0.001) #0.000001 =>69% default 0.001

model.compile(optimizer=adam,
              loss='binary_crossentropy',
              metrics=['accuracy'])
model.summary()




earlystop_callback = EarlyStopping(monitor='val_accuracy', min_delta=0.0001,
  patience=1)
#and now we run the model to teach it and see how well it does
history = model.fit_generator(
    train_data_gen,
    steps_per_epoch=total_train // batch_size,
    epochs=epochs,
    validation_data = test_data_gen,
    validation_steps=total_test // batch_size
)




"""
After running the model, we can see here the accuracy level and how well we reduced loss. If the lines are close to each other,
our model is pretty good. If the lines are far apart, a few things could be off: if the loss line keeps zigzagging, 
it might mean we have too few testing pictures. If the validation accuracy doesn't meet training accuracy, problem could be 
overfitting => the model is adapting too well to the training data, and doesn't generalize well. In that case we must regularize
our model better, by using dropouts or other methods.
"""
acc = history.history['accuracy']
val_acc = history.history['val_accuracy']

loss = history.history['loss']
val_loss = history.history['val_loss']

epochs_range = range(epochs)

plt.figure(figsize=(8, 8))
plt.subplot(1, 2, 1)
plt.plot(epochs_range, acc, label='Training Accuracy')
plt.plot(epochs_range, val_acc, label='Test Accuracy')
plt.legend(loc='lower right')
plt.title('Training and Testing Accuracy')

plt.subplot(1, 2, 2)
plt.plot(epochs_range, loss, label='Training Loss')
plt.plot(epochs_range, val_loss, label='Testing Loss')
plt.legend(loc='upper right')
plt.title('Training and Testing Loss')
plt.show()




get_ipython().system('pip install -q pyyaml h5py')




model.save("D://kasvokuvat//kasvods//model.h5")




"""
dropoutin kera, jos tarviit sitä vielä, kopioi tästä
model = Sequential([
    Conv2D(16, 3, padding='same', activation='relu', input_shape=(IMG_HEIGHT, IMG_WIDTH ,3)),
    MaxPooling2D(),
    Dropout(0.2),
    Conv2D(32, 3, padding='same', activation='relu'),
    MaxPooling2D(),
    Conv2D(64, 3, padding='same', activation='relu'),
    MaxPooling2D(),
    Dropout(0.2),
    Flatten(),
    Dense(512, activation='relu'),
    Dense(1, activation='sigmoid')
])
"""

