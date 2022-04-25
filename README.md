# Github_researcher

Github_researcher to aplikacja na platformę Android, która ma za zadanie wyszukiwać zarówno repozytoria użytkownika Github jak i języki, w których kod w repozytorium  został napisany. Aplikację stworzyłem w języku Java używając Android Studio. Do repozytorium projektu dolaczylem rowniez plik github_researcher.apk, który mozna bezposrednio pobrac na swoj telefon, zainstalowac aplikacje oraz bez problemu z niej korzystac. Osobiscie uwazazam, ze zadanie wykonalem na bardzo dobrym poziomie, poswiecajac mu cala moja uwage przez kilka ostatnich dni. 

Opis działania aplikacji.

W ekranie głównym użytkownik wpisuje nazwę użytkownika. Jeżeli taki użytkownik nie istnieje na ekranie pojawia się napis "User not found". Zostało to osiągnięte przez pobieranie statusu wątku HttpStatusException. Jeżeli kod statusu to "404" aplikacja nie przechodzi do kolejnego Activity.

W kolejny Activity wyświetlane są repozytoria istniejącego użytkownika. Po kliknięciu w nazwę repozytorium przechodzimy do ekranu szczegółów na temat repozytorium (lista jeżyków oraz w ilu procentach zostały użyte). Jeżeli użytkownik nie posiada repozytoriów na ekranie wyświetli się wiadomość "XYZ doesn’t have any public repositories yet", która również jest pobierana z kodu źródłowego strony. Klikniecie wiadomości skutkuje przejściem do kolejnego ekranu w którym widoczna jest pusta lista języków.

Link do filmu demonstrującego działanie aplikacji na YouTube: https://www.youtube.com/shorts/pgp8uToe-8I.

Zadanie uważam za bardzo ciekawe i rozwijające. W następnych iteracjach można rozwinąć projekt o pobieranie większej ilości szczegółów danego repozytorium (np. listę współtwórców, ilość commitów etc) oraz o jeszcze ciekawszy design.



INSTALACJA
1. Kliknij plik .apk w repozytorium Github_researcher. (https://github.com/peterdz099/Github_researcher)

![image](https://user-images.githubusercontent.com/103062015/165175641-16b91d6c-c452-49ac-b1b1-8544aef34f1d.png)

2. Pobierz plik na swoje urzadzenie z Androidem. Konieczne moze byc zezwolenie na pobieranie plikow .apk z nieznanego zrodla. 

![image](https://user-images.githubusercontent.com/103062015/165175950-3accac09-e079-450d-9b02-0c8bad208045.png)

![Screenshot_2022-04-25-23-17-23-9812_com android chrome](https://user-images.githubusercontent.com/103062015/165187747-30715972-51a0-4da3-bce9-a64463b8b5c6.jpg)

3. Wejdz w pobrane pliki i zainstaluj aplikacje. Konieczne moze byc zezwolenie na instalacje aplikacji z nieznanego zrodla.

![Screenshot_2022-04-25-23-17-39-0262_com android vending (1)](https://user-images.githubusercontent.com/103062015/165187833-ade6653d-b304-4c69-b4f6-969a89ca8fe8.jpg)









