# Testausdokumentti

Testauksessa on käytetty JUnit-testejä, sekä jossain määrin manuaalista testausta.

JUnit-testit löytyvät test-kansion alta nimillä (alkuperäisen luokan nimi)Test.

Manuaalista testausta käytettiin lähinnä Fibonacci-keon kanssa sekä karttatiedostoista kartan haun yhteydessä ja suuria karttoja käytettäessä.

**Fibonacci-keko**

Suuria syötteitä on testattu manuaalisesti esim. Fibonacci-kekoa testatessa. Fibonacci-kekoon lisättiin 2000 solmua, niitä poistettiin ja niiden arvoa vähennettiin vuorotellen, jotta pystyttiin testaamaan, että puiden järjestäminen toimii halutulla tavalla. Fibonacci-keossa oli melko pitkään ongelmaa sen kanssa, kun suuria määriä alkoita poistettiin niin, että välillä vähennettettiin alkion arvoa. Tämä viittasi virheeseen FibonacciHeapin delMin- tai FNoden cut- ja cascadingCut-metodeissa, mikä korjattiin edellä mainitun kaltaisen manuaalisen testauksen avulla.

**Karttatiedostojen haku ja suurien karttojen testaus**

Kartat joita testattiin, ovat GirlYoureAStar-kansiossa nimillä map.txt, map2.txt ja map3.txt. Niillä testattiin algoritmeja katsomalla silmämääräisesti ettei reitti tehnyt ylimääräisiä mutkia ja että kaikki algoritmi-prioriteettijonoyhdistelmät antoivat saman tuloksen.
