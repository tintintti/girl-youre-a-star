#Määrittelydokumentti

Lyhimmän reitin etsiminen labyrintissä kahden pisteen välillä Dijkstran ja A*-algoritmilla. Prioriteettilistan toteuttaminen binäärikeon ja fibonacci-keon avulla sekä näiden vertailu.

**Käytettävät tietorakenteet**
- A* ja Dijkstran aputietorakenteina
 - prioriteettilista toteutettuna
   - binäärikeolla ja
   - fibonacci-keolla
 - pino
- Verkon tallentamiseen 
 - linkitetty lista


**Syötteet**
- labyrintti numeroina: 0-8 esittää maaston vaikeakulkuisuuden tasoa, 9 tarkoittaa seinää jonka läpi ei voi kulkea
 - muutetaan vieruslistaesitykseksi verkosta
- reitin aloitus- ja lopetuspisteet

**Aika- ja tilavaativuustavoitteet**
- *A* ja Dijkstra*
 - aikavaativuus: A*- ja Dijkstran algoritmissa sama eli O((|E|+|V|)log|V|)
   - Fibonacci-kekoa käytettäessä tasoitettu aikavaativuus O(|E|+|V|log|V|)
 - tilavaativuus: O(|V|)
- *Linkitetty lista*
 - tilavaativuus: O(|V|+|E|)


https://www.cs.helsinki.fi/u/floreen/tira2015/sivut351-638

http://theory.stanford.edu/~amitp/GameProgramming/AStarComparison.html
