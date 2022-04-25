# Github_researcher

Github Researcher to aplikacja na platformę Android, która ma za zadanie wyszukiwać zarówno repozytoria użytkownika Github jak i Języki, które zawiera.
Aplikację napisałem w jezyku Java uzywajac Android Studio. Do repozytorium projektu dolaczylem rowniez plik .apk ktory mozna bezposrednio pobrac na swoj telefon, 
zainstalowac aplikacje oraz bez problemu z niej korzystac. 

Osobiscie uwazazam, ze zadanie wykonalem na bardzo dobrym poziomie, poswiecajac mu cala moja uwage. Niestety nie udalo mi sie zrealizowac jednego zalozenia zadania, 
a mianowicie wyswietlania na ekranie liczby bajtow kodu napisanego w danym jezyku w repozytorium. Zwyczajnie braklo mi na to czasu. Liczbe bajtow zastapilem jednak 
procentami (procent uzycia jezyka w repozytorium). Do wykonania zadania uzylem biblioteki Jsoup, dzieki ktorej moglem pobierac dane bezposrednio ze strony github.com.

Opis działania.

1.W ekranie glownym uzytkownik wpisuje nazwe uzytkownika. Jezeli taki uzytkownik nie istnieje na ekranie pojawia sie napis "User not found". Zostalo to osiagniete przez 
pobieranie statusu HTTPSTATUSEXCEPTION. Jezeli kod statusu to 404 aplikacja nie przechodzi do kolejnego Activity.

1.W kolejny activity wyswietlane sa repozytoria istniejacego uzytkownika, ktore mozna klikac. Przenosi nas to ekranu szczegolow na temat repozytorium (lista jezykow 
oraz procenty). Jezeli uzytkowniknie posiada repozytoriow na ekranie wyswietli sie wiadomosc "XYZ doesn’t have any public repositories yet", ktora rowniez jest 
pobierana z kodu zrodlowego strony. Klikniecie wiadomosci skutkuje przejsciem do kolejnego ekranu w ktorym widoczna jest pusta lista jezykow.


Zadanie uwazam za bardzo ciekawe i rozwiajace. W natepnych iteracjach moznaby rozwinac projekt o pobieranie wiekszej ilosci szczegolow danego repozytorium (np. liste 
wspoltworcow, ilosc commitow etc) oraz o jeszcze ciekawszy desing. 


INSTALACJA
1. Kliknij plik .apk w repozytorium Github_researcher. (https://github.com/peterdz099/Github_researcher)
![image](https://user-images.githubusercontent.com/103062015/165175641-16b91d6c-c452-49ac-b1b1-8544aef34f1d.png)

2. Pobierz plik na swoje urzadzenie z Androidem.

![image](https://user-images.githubusercontent.com/103062015/165175950-3accac09-e079-450d-9b02-0c8bad208045.png)

3.



