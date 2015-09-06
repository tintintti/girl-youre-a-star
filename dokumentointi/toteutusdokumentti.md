# Toteutusdokumentti

**Ohjelman yleisrakenne**

  Ohjelma koostuu kahdesta algoritmista, A*:sta ja Dijkstrasta, sekä niiden toteuttamista varten tarvittavista tietorakenteista. Luokat Astar ja Dijkstra sisältävät reitinhaun ja molemmat toteuttavat rajapinnan RouteFinder.

  Prioriteettilistana voi vaihtoehtoisesti käyttää joko binäärikekototeutusta BinaryHeap tai Fibonacci-kekototeutusta FibonacciHeap. Molemmat toteuttavat rajapinnan MinHeap. 

  Verkon tallentamiseen on toteutettu linkitetty lista LinkedList ja sen elementtien tallentamiseen LinkedListNode. Jotta olisi mahdollista esim. tulostaa sokkelon kartta helposti, on luokka Graph, joka sisältää kartan kaksiuloitteisena taulukkona, verkon solmut kaksiuloitteisena taulukkona sekä verkon solmut vieruslistana käyttäen linkitettyä listaa ja taulukkoa. Verkon solmun tiedot tallennetaan Node-luokkaan.

  Löydetyn reitin tulostamisen helpottamiseksi toteutettu pino (Stack).

  Yksinkertainen tekstikäyttöliittymä löytyy luokasta TextUI ja labyrintin kartan sisältävän tekstitiedoston lukemiseen on luokka MapFileReader.


**Aika- ja tilavaativuudet**

  Dijkstran ja A*-algoritmin aikavaativuudet ovat molemmilla O((|E|+|V|)log|V|), kun prioriteettijono on toteutettu binäärikeolla. Kun prioriteettijonona käytetään Fibonacci-kekoa, tulee aikavaativuudeksi O(|E|+|V|log|V|). Fibonacci-kekoa käytettäessä kuitenkin reitinhaku oli käytännössä hitaampaa sen kokoisilla kartoilla kun tässä projektissa. 

  Tilavaativuudeltaan molemmat algoritmit ovat O(|E|).

**Vertailu**

  Algoritmeja ja prioriteettikekototeutuksia testattiin useilla erilaisilla kartoilla. Tulokset löytybät taulukoituna tiedostosta vertailu.ods.

  Pienillä kartoilla Dijkstran algoritmi toimi nopeammin kuin A* testitapauksissa. Suurimmilla läppärin pyörittämillä kartoilla A* toimi nopeammin Fibonacci-kekoa käytettäessä, mutta binäärikekoa käytettäessä Dijkstra toimi jostain syystä nopeammin. Suurin kartta, jota pystyin testata omalla koneellani oli kooltaan 21589x290.

**Puutteet ja parannusehdotukset**
  


**Lähteet**
- T. H. Cormen, C. E. Leiserson, R. L. Rivest, C. Stein: Introduction to Algorithms, 3rd ed., MIT Press, 2009.
- https://nlfiedler.github.io/2008/05/31/analysis-of-java-implementations-of-fibonacci-heap.html
- http://www.growingwiththeweb.com/2014/06/fibonacci-heap.html
- http://www.redblobgames.com/pathfinding/a-star/introduction.html
