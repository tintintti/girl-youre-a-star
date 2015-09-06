# Käyttöohje

Aseta ohjelman kanssa samaan hakemistoon tekstitiedosto joka sisältää labyrintin kartan, eikä muuta. Kartta tulee antaa numeroina, 9 tarkoittaa seinää jonka läpi ei voi kulkea ja numerot 0-8 määrittelevät miten vaikea kartan ruutuun on siirtyä.

Ohjelma kysyy aluksi tulostetaanko kartta ja reitti reitinhaun jälkeen, karttatiedoston nimen, esim. "map.txt" ja sen jälkeen alku- ja loppupisteen rivin ja sarakkeen (ensimmäinen rivi/sarake = 0).

Sen jälkeen voi valita käyttääkö ohjelma reitin hakemiseen Dijkstran vai A*-algoritmia ja käytetäänkö prioriteettijonototeutuksessa binäärikekoa vai Fibonacci-kekoa.

Ohjelma tulostaa ensin labyrintin kartan niin, että seinät on merkitty #:lla, käyttäjän antamassa kartassa 0-arvolla olleet kohdat tyhjinä ja muissa kohdissa numerolla 2-9 merkittynä liikkumisen haastavuus (käytännössä käyttäjän kartassa ollut numero +1). Alkupiste on merkitty a- ja loppupiste b-kirjaimella. 

Seuraavaksi ohjelma tulostaa kartan niin, että löydetty reitti on merkitty pisteillä, sekä kartan alle reitin pituuden ja reitinhakualgoritmin suoritusajan millisekunteina.

Karttojen tulostuksen saa pois ohjelman käynnistäessä, mikä on kätevää jos haluaa esimerkiksi vertailla miten kauan milläkin algoritmi-prioriteettijonoyhdistelmällä reitinhaku kestää. Tällöin ohjelma tulostaa vain reitin pituuden ja reitinhakuun kuluneen ajan.
