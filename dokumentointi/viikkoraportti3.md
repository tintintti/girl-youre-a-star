# Viikkoraportti 3

- Aloitin toteuttamaan Dijkstran algoritmia ja muokkaamaan olemassaolevia luokkia niin, että kentän saa painotettua. Nodelle uusi attribuutti costOfMovement, joka kertoo kyseisen solmun painotuksen.

- Muutettu syötteeksi saatava kartta char[][] sijaan int[][], jotta kartan "ruuduille" olisi helpompi antaa arvot. Arvoja on 0-9, joista 9 tarkoittaa seinää jonka läpi ei voi kulkea ja 0-8 eri vaikeuksisia maastoja.

- Päivitetty määrittelydokumenttia

- Tehty testit Dijkstralle ja täydennetty A*:n testejä

- Alettu toteuttaa binäärikekoa prioriteettijonoa varten. Nodelle lisätty atribuutti heapIndex, joka kertoo Noden paikan keossa.

- Tehty testejä binäärikeolle

- Päivitetty melkein kaikkien luokkien kommentointia

- Seuraavaksi jatkan tietorakenteiden toteuttamista, vielä ainakin:
 - fibonacci-keko prioriteettilista
 - pino reitin tallentamista varten
 - linkitetty lista verkon tallentamiseksi vieruslistaan
