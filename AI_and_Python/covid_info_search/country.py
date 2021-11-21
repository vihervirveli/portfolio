#Author: Maarit Salo P0033

""""
Country-olio joka muodostetaan tiettyjen maa tietojen avulla.
"""
class Country:
    #properties
    name = ""
    confirmed = 0
    deaths = 0
    #methods
    def __str__(self):
        return f"Country name: {self.name}, Number of confirmed cases: {self.confirmed} and Number of deaths: {self.deaths}"
    def __init__(self, name, confirmed, deaths):
        self.name = name
        self.confirmed = confirmed
        self.deaths = deaths