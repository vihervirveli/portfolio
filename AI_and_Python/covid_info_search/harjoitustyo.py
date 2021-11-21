#Author: Maarit Salo P0033

import pandas as pd
import matplotlib.pyplot as plt
import os
from country import Country


def make_df():
    """
    Takes the previously hardcoded csv file
    and turns it into a DataFrame and returns the DataFrame
    """
    df = pd.read_csv("country_wise_latest.csv")
    return df


def write_obj_to_file(strobj):
    """
    Takes an object in string form and writes it to the end of a text file.
    The text file in question has been hardcoded and the user need not bother
    with it.
    """
    tiedosto = 'countryobjs.txt'
    filename = os.path.expanduser('~/') + tiedosto
    try:
        file = open(filename, 'a')
        file.writelines(f"{strobj}\n")
        file.close()
    except Exception as e:
        print(f"Failed to write to file: {tiedosto}")
        

def read_obj_from_file():
    """"
    Reads the country objects that have been written into a file.
    If there is no file or the file is empty, the user is told as much
    """
    tiedosto = 'countryobjs.txt'
    filename = os.path.expanduser('~/') + tiedosto
    try:
        file = open(filename, 'r')
        lines = file.readlines()
        for line in lines:
            print(line)
        file.close()
    except:
        print(f"Failed to read from the file '{tiedosto}'. Did you search for any valid countrynames before trying this option?")


def display_deaths(df):
    """
    Receives a DataFrame, copies it (so we don't mess up the original), 
    and sorts the copy based on covid related deaths and plots the 
    five countries with the biggest mortality rate
    """
    df_kuolemat = df.copy() #let us copy the dataframe so sorting it won't mess up the original
    kuolemat = df_kuolemat[["Country/Region","Deaths"]]
    kuolemat = kuolemat.sort_values(by = "Deaths", ascending = True)
    kuolemattail = kuolemat.tail(5)
    ax = plt.gca()
    plt.title("Top 5 countries by covid deaths")
    kuolemattail.plot(kind='bar',x='Country/Region',y='Deaths',ax=ax)
    plt.xticks(rotation = 15)
    plt.show()


def ask_country_info(df):
    """"
    A Function that will give you information
    based on your input. 
    
    1) if you type a country name, your choice of country will be turned into an object
    and printed onto the console and also written into a text file.

    2) if you type 'deaths', you will be re-directed to display_deaths function,
    which will plot you a nice bar graph of the top 5 countries by covid deaths
    
    3) if you type 'my searches', you will be taken to read_obj_from_file, where
    your searches from option 1 will be printed from file. 
    If you didn't do any searches before trying this option, read_obj_from_file will 
    tell you that
    
    4) If you type 'q', you will quit the program and receive a goodbye message

    If you misspell the country or otherwise give the wrong input, 
    the program will tell you you didn't give a valid countryname or another valid option and
    loop back to the beginning prompt
    """
    user_input = ""
    while user_input != "q":
        try:
            user_input = input("Welcome to Covid Info Search!\n1) Type the country you wish to get covid information about OR\n 2) type 'deaths' to see a visualization of the top 5 countries by covid deaths,\n 3) after spending some time in part 1, you can type 'my searches' to get a list of your searches OR\n 4) You can type 'q' and quit the program: \n")
            #option 2
            if user_input == "deaths":
                display_deaths(df)
                continue
            #option 3
            if user_input == "my searches":
                read_obj_from_file()
                continue
            #option 4
            if user_input == "q":
                print("Thank you for using Covid Info Search. Please come back soon!")
                #poistetaan oliotiedosto jottei uudelle käyttäjälle tulostu vanhan
                #käyttäjän haut
                poistettava = os.path.expanduser('~/') + 'countryobjs.txt'
                os.remove(poistettava)
                break
            #option 1
            #let's see if the first letter is capitalized
            if user_input[0].islower():
                user_input = user_input[0].upper() + user_input[1:]
            countryinfo = df.loc[df["Country/Region"] == user_input]
            #let's see if there's anything in countryinfo
            if countryinfo.empty:
                print("Please check your spelling. You need to give a valid input.")
                continue
            else:
                #if there is something in countryinfo:

                #For clarity's sake, let's take the different informations from
                #specific columns on the DataFrame row "countryinfo" and tie them to variables
                country_name = countryinfo["Country/Region"].values[0]
                country_confirmed = countryinfo["Confirmed"].values[0]
                country_deaths = countryinfo["Deaths"].values[0]
                #here we turn the country into an object (Country-class is in a different file
                # and has been imported into this one)
                country_obj = Country(country_name, country_confirmed, country_deaths)
                print(country_obj)
                #object is written to a file
                write_obj_to_file(str(country_obj))
        except:
            print("Please check your spelling. You need to give a valid input.")


def main():
    """
    Calls for make_df and ask_country_info-functions
    First one makes the DataFrame based on the csv-file in the same folder
    and the second one gives the user options on what they want the program to do
    """
    df = make_df()
    ask_country_info(df)


if __name__ == "__main__":
    main()