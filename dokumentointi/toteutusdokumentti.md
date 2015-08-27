# Toteutusdokumentti

**Ohjelman yleisrakenne**

  Ohjelma koostuu kahdesta algoritmista, A*:sta ja Dijkstrasta, sekä niiden toteuttamista varten tarvittavista tietorakenteista.

  Prioriteettilistana voi vaihtoehtoisesti käyttää joko binäärikekototeutusta BinaryHeap tai Fibonacci-kekototeutusta FibonacciHeap. Molemmat toteuttavat rajapinnan MinHeap. 

  Verkon tallentamiseen on toteutettu linkitetty lista LinkedList ja sen elementtien tallentamiseen LinkedListNode. Jotta olisi mahdollista esim. tulostaa sokkelon kartta helposti, on luokka Graph, joka sisältää kartan kaksiuloitteisena taulukkona, verkon solmut kaksiuloitteisena taulukkona sekä verkon solmut vieruslistana käyttäen linkitettyä listaa ja taulukkoa. Verkon solmun tiedot tallennetaan Node-luokkaan.

  Löydetyn reitin tulostamisen helpottamiseksi toteutettu pino (Stack).
