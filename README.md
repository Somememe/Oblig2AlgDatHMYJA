# Oblig2AlgDatHMYJA

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende studenter:
* Helene Rogne, s344241, s344241@oslomet.no
* Jing Aurora Tvinnereim, s336668, s336668@oslomet.no
* Marianne Storhoug, s336188, s336188@oslomet.no
* Yalda Shams, s344232 , s344232@oslomet.no
* Andrea Bjørge, s344175, s344232@oslomet.no

Notasjoner på koden:
* Testen til oppgave 6 sender en feilmelding ca. annenhver gang med beskjed om at koden er ineffektiv. 


Hvordan oppgavene er løst:

Oppgave 0:
* Main klassen ligger som en egen .java fil i tillegg til de 4 andre java filene

Oppgave 1:
* Først er den øverste konstruktøren kodet med instansiering av alle instansvariablene for å unngå noen feilmeldinger som ble kastet.
* Deretter er metodene antall() og tom() kodet. Antall returnerer bare instansvariabelen antall og tom gjør en sjekk på hele lista. 

Oppgave 2:


Oppgave 3: 
- Har laget metodene som er beskrevet i oppgaveteksten. Synes det var vanskelig å forstå hvordan man skulle løse oppgaven, men fikk hjelp av student assistenter på lab. 

Oppgave 4:
* indeksTil(T verdi) metoden looper gjennom lista med en for løkke og sjekker om verdi eksisterer. 
* Returverdien blir satt ettersom verdi eksisterer eller ikke. 
* -1 betyr at det ikke eksisterer, 0 eller et positivt tall representerer indeksen til verdi i lista. 
* Metoden inneholder(T verdi) returnerer indeksTil(T verdi) != -1, altså true for 0 og positivt tall og false for -1.

Oppgave 5: 
- Begynte først på oppgaven, men innså etter å ha sett Andre sin video om obligen at det var best å løse oppgave 3 først. Forsøkte først å løse denne oppgaven alene, men testene failet, og det gikk ikke. Så fikk hjelp av orakel til å gjøre den ferdig.

Oppgave 6:


Oppgave 7:
* 1.metode: Bruker en while-løkke som kjører så lenge hodet ikke er satt til null. Brukte hjelpevariablene p og q til å oppdatere plassering i listen, samtidig som jeg slettet forrige node.
* 2.metode: Bruker en while-løkke som kjører så lenge hodet ikke er satt til null. Bruker hjelpevariabelen q til å "holde" på neste node. Bruker så fjern(0) for å slette noden som er først i lista. Oppdaterer så p (hodet) til å holde på q.  
* Etter å ha analysert koden så har jeg kommet fram til at metode 1 er mest effektiv fordi det er færre operasjoner metoden må gå igjennom og bruke tid på.

Oppgave 8:
* next()-metoden returnerer midlertidig node-verdi, og flytter samtidig forover til neste node.
* iterator()-metoden oppretter en instans av iteratorklassen og returnerer denne.
* DobbeltLenketListeIterator(int indeks) er en konstruktør som setter variabelen "denne" til noden som sendes inn (dvs. indeks/plassering).
* iterator(int indeks) tester om indeksen er lovlig. Dersom den er det, så sendes den inn i en instans av iteratorklassen, og dette blir så returnert i metoden. 

Oppgave 9:


Oppgave 10: 
* Oppgave ble først beskrevet med kommentarer om hva som skulle skje. Disse kommentarene ligger fremdeles i koden. 
* Deretter ble koden som utfører det kommentarene sier skrevet inn.
* Det er implementert en for-løkke som finner minste element i rekka inne i for-løkka som bytter plass på elementer, siden det ikke var spesifisert noen annen metode for å utføre dette. 
* Tilslutt bytter den ytre for-løkka plass på elementene, slik at de minste elementene kommer først og de største elementene kommer sist. 