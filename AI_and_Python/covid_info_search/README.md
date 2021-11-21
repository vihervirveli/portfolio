# COVID INFO SEARCH - Harjoitustyö Ohjelmoinnin perusteet-kurssille

Harjoitustyön pääohjelmasta kutsutaan ensin ohjelmaa make_df
joka muodostaa DataFramen country_wise_latest.csv-tiedoston
perusteella. DataFrame sisältää eri maiden korona-tiedot.
Sitten pääohjelma kutsuu ask_country_info-funktiota,
joka tarjoaa käyttäjälle neljä eri vaihtoehtoa. 

Joko käyttäjä

1) antaa maan nimen, ja sen perusteella funktio muodostaa
Country-olion (tiedostosta country.py) ja tulostaa olion
tiedot ruudulle. Käyttäjälle näkymättömissä sama tulostus
kirjoitetaan myös tiedostoon.

2) kirjoittaa "deaths" jolloin funktio kutsuu display_deaths-
funktiota, joka lajittelee DataFramen maat järjestykseen
koronakuolemien perusteella, ja visualisoi 5 maata joissa on
eniten kuolemia kuviolla

3) kirjoittaa "my searches" ja funktio kutsuu read_obj_from_file-
funktiota, ja lukee aiemmin tiedostoon kirjoitetut oliot ja
tulostaa ne ruudulle

4) kirjoittaa 'q' ja lopettaa ohjelman, ja saa hyvästelyviestin.
Käyttäjälle näkymättömissä käydään tuhoamassa tiedosto johon oliot
ykköskohdasta kirjoitettiin. Tämä varmistaa, ettei uudelle käyttäjälle
tulosteta vanhan käyttäjän hakuja.

## Harjoitustyössä käytetyt käsitteet:
* poikkeusten käsittely (try/except-rakenne) (ask_country_info, write_obj_to_file, read_obj_from_file)
* pandas-, matplotlib.pyplot- ja os-kirjastot
* tiedostoon kirjoitus ja sieltä luku, sekä tiedoston perusteella DataFramen luonti, tiedoston tuhoaminen
* tietorakenteiden ja kokoelmien käyttö: DataFrame ask_country_info, lista read_obj_from_file (muuttuja "lines" on lista)
* vuorovaikutus loppukäyttäjän kanssa: koko harkka on sitä, että loppukäyttäjä tekee päätöksiä
* ohjausrakenteet: ask_country_info whilella, read_obj_from_file-funktiossa "lines"-lista printataan for-silmukalla
* luokkien käyttö: Country-luokkaa kutsutaan tiedostosta country.py ja käyttäjän etsimät maat
muutetaan olioiksi ja kirjoitetaan tiedostoon olion stringin korvaus-funktion avulla
* koodin kommentointi ja muuttujien nimet mielestäni kuvaavia
* funktiot ja niiden parametrit, funktioiden kutsuminen: paljon
