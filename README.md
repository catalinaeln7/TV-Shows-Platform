Serverul din pachetul action contine metoda de entry point prin care se citeste
inputul si se incepe rularea programului. Serverul este singleton si prin el
se face si scrierea in fisier.

Am creat o baza de date (Database) singleton care sa retina inputul in liste de
actori, filme, seriale, utilizatori.

Actiunile sunt realizate o data cu citirea lor din fisier prin ActionHandler
care verifica tipul de actiune si apeleaza metodele specifice din Command,
Query si Recommendation.

Am creat pachetele actor, show si user care contin entitatile pe care le-am
modelat si alte clase in care se gasesc metode specifice cerintelor pentru
prelucrarea datelor.

Clasa Command este unica pentru rezolvarea actiunii command.

Pentu actiunea query am creat clasele de tipul Sort (ActorSort, ShowSort,
UserSort) care sorteaza in functie de criterii, filtre, tip de sortare.

Pentru actiunile query si recommendation am creat clasele de tipul Search
(ShowSearch, ActorSearch) care contin metode ce intorc liste de obiecte sau
obiecte cu anumite proprietati cerute.

In clasa UserMessage se construieste mesajul ce va aparea pe baza actiunii
alese de utilizator.
