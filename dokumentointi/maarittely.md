#Määrittelydokumentti

Lyhimmän reitin etsiminen labyrintistä ulos A*-algoritmilla.

**Käytettävät tietorakenteet**
- A* aputietorakenteina
 - minimikeko ja
 - pino
- Verkon tallentamiseen 
 - linkitetty lista


**Syötteet**
- ascii-labyrintti
 - muutetaan vieruslistaesitykseksi verkosta

**Aika- ja tilavaativuustavoitteet**
- *A**
 - aikavaativuus: sama kuin Dijkstran algoritmissa eli O((|E|+|V|)log|V|)
 - tilavaativuus: O(|V|)
- *Linkitetty lista*
 - tilavaativuus: O(|V|+|E|)


https://www.cs.helsinki.fi/u/floreen/tira2015/sivut351-638

http://theory.stanford.edu/~amitp/GameProgramming/AStarComparison.html
