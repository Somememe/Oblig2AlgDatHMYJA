# Oblig2AlgDatHMYJA

Denne oppgaven er en innlevering i Algoritmer og Datastrukturer. 
Oppgaven er levert av følgende studenter:
* Helene Rogne, s344241, s344241@oslomet.no
* Jing Aurora Tvinnereim, s336668, s336668@oslomet.no
* Marianne Storhoug, s336188, s336188@oslomet.no
* Yalda Shams, s344232 , s344232@oslomet.no
* Andrea Bjørge, s344175, s344232@oslomet.no

Notasjoner på koden:
* Testen til oppgave 2, 3 og 6 (endrer seg fra gang til gang hvilken oppgave det er) sender en feilmelding ca. annenhver gang med beskjed om at koden er ineffektiv. 


Hvordan oppgavene er løst:

Oppgave 0:
* Main klassen ligger som en egen .java fil i tillegg til de 4 andre java filene

Oppgave 1:
* Først er den øverste konstruktøren kodet med instansiering av alle instansvariablene for å unngå noen feilmeldinger som ble kastet.
* Deretter er metodene antall() og tom() kodet. Antall returnerer bare instansvariabelen antall og tom gjør en sjekk på hele lista.

* Bruker først en Objects.requirenotNull metode for å sjekke at det ikke er en tom tabell
* Bruker en for-løkke for å opprette konstruktøren til den dobbelt lenkede listen med if-sjekker som sjekker parametertabellen
* Antall økes

Oppgave 2:
-Først ble toString() metoden kodet ved å bruke en Stringbuilder hvor elementene (Strengene) skal ligge. Deretter ble String
omvendtString opprettet som gjør det samme, men verdiene skal komme ut i omvendt rekkefølge. Dette skjer ved å endre 
at hode blir til hale, og at neste blir til forrige.
-Metoden leggInn(T verdi) sjekker først at det ikke eksisterer null verdier. Den skal legge inn en ny node bakerst i lisen, avhengig
av om listen er tom fra før eller ikke. dersom listen er tom skal både hale og hode settes til null, hvis listen ikke er tom skal bare hale 
pekeren endres. 

Oppgave 3: 
- Først ble den private hjelpemetoden Node <T> finnNode()int indeks) som skal returnere en node med indeks lagd. For å finne ut om letingen etter noden skulle starte fra hode eller hale ble det lagd en if-setning.
- Så ble metoden for å hente indeks laget: T hent (int indeks). Denne sjekker først om indeks har gyldig verdi. Så bruker den finnNode metoden og returnerer verdien til noden. 
- Metoden T oppdater (int indeks, T nyverdi) erstatter det som ligger på indeks med nyverdi ved bruk av finnNode metoden. Den returnerer den gamle verdien. 
- Metoden sublist og fraTilKontroll blir så lagd. fraTilKontroll sjekker om indeksene fra og til er gyldige, og intervall. I sublist brukes en for-loop, og det legges inn elementer i den nye lista, som så returneres. 

Oppgave 4:
* indeksTil(T verdi) metoden looper gjennom lista med en for løkke og sjekker om verdi eksisterer. 
* Returverdien blir satt ettersom verdi eksisterer eller ikke. 
* -1 betyr at det ikke eksisterer, 0 eller et positivt tall representerer indeksen til verdi i lista. 
* Metoden inneholder(T verdi) returnerer indeksTil(T verdi) != -1, altså true for 0 og positivt tall og false for -1.

Oppgave 5: 
- Begynte først på denne oppgaven, men innså etterhvert at det var lurt å starte på oppgave 3 først. 
- Lager metoden void leggInn(int indeks, T verdi). Det opprettes if/else if-setninger for å legge inn verdier på riktig indeks i listen. 
- Første if-setning sjekker om antall == 0, og setter den nye noden til første node. Neste sjekker om indeksen er 0, og setter så noden til første node. Neste sjekker om indeks er lik antall, og om den er det setter den noden til siste/hale-node i lista. Siste setter noden mellom to verdier i lista. 

Oppgave 6:
 Fjern (verdi)
 - If-sjekk på starten for å sjekke at det ikke er nullverdier 
 - Oppretter deretter hjelpekere som brukes i en while-løkke
 - Bruker while-løkken til å finne verdien som skal fjernes
 - Peker forbi verdien som er funnet ved hjelp av hjelpepekerne r og q
 - Sjekker også hvis verdien befinner seg på hode og peker videre på hode.neste, og samme med hale
 - Antall reduseres og endringer økes
 - Returner true hvis noden er fjernet 
 
 
 Fjern (indeks)
- Opprettet først en midlertidig T-verdi (returverdi)som jeg skulle plassere noden som skulle fjernes
- fjern (indeks) sjekker først for indeks feil 
- Deretter er det implementert If og else sjekker for de forskjellig tilfeller av hvor noden befinner seg.
- Den plasserer verdien som skal fjernes inn returverdi variabelen og den får riktig verdi etter hvilket tilfellet som gjelder
- Antall og endringer endres 


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
-metoden remove() i iteratorklassen skal fjerne verdier. først vil metoden sjekke om det er tillatt å kalle den, hvis det ikke er det vil den kaste en 
IllegalStateException. dersom endringer og iteratorendringer ikke er like vil det kastes Concurrent-ModificationException. deretter sjekker den om det kun er en verdi i listen som skal fjernes, om det enten er den bakerste eller første 
verdien i listen som skal fjernes og eller om det er en verdi midt i listen som skal fjernes. til slutt øker endringer og iteratorendringer, mens antall synker. 

Oppgave 10: 
* Oppgave ble først beskrevet med kommentarer om hva som skulle skje. Disse kommentarene ligger fremdeles i koden. 
* Deretter ble koden som utfører det kommentarene sier skrevet inn.
* Det er implementert en for-løkke som finner minste element i rekka inne i for-løkka som bytter plass på elementer, siden det ikke var spesifisert noen annen metode for å utføre dette. 
* Tilslutt bytter den ytre for-løkka plass på elementene, slik at de minste elementene kommer først og de største elementene kommer sist. 
