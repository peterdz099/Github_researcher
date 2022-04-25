# Github_researcher

Github_researcher to aplikacja na platformę Android, która ma za zadanie wyszukiwać zarówno repozytoria użytkownika Github jak i języki, w których kod w repozytorium został napisany. Aplikację stworzyłem w języku Java używając Android Studio. Do repozytorium projektu dołączyłem również plik github_researcher.apk, który można bezpośrednio pobrać na swój telefon, zainstalować aplikację oraz bez problemu z niej korzystać. Osobiście uważam, ze zadanie wykonałem na bardzo dobrym poziomie, poświęcając mu całą moją uwagę przez kilka ostatnich dni.

Opis działania aplikacji.

W ekranie głównym użytkownik wpisuje nazwę użytkownika. Jeżeli taki użytkownik nie istnieje na ekranie pojawia się napis "User not found". Zostało to osiągnięte przez pobieranie statusu wątku HttpStatusException. Jeżeli kod statusu to "404" aplikacja nie przechodzi do kolejnego Activity.
W kolejny Activity wyświetlane są repozytoria istniejącego użytkownika. Po kliknięciu w nazwę repozytorium przechodzimy do ekranu szczegółów na temat repozytorium (lista języków oraz w ilu procentach zostały użyte). Jeżeli użytkownik nie posiada repozytoriów na ekranie wyświetli się wiadomość "XYZ doesn’t have any public repositories yet", która również jest pobierana z kodu źródłowego strony. Klikniecie wiadomości skutkuje przejściem do kolejnego ekranu w którym widoczna jest pusta lista języków.

Link do filmu demonstrującego działanie aplikacji na YouTube: https://www.youtube.com/shorts/pgp8uToe-8I.

Zadanie uważam za bardzo ciekawe i rozwijające. W następnych iteracjach można rozwinąć projekt o pobieranie większej ilości szczegółów danego repozytorium (np. listę współtwórców, ilość commitów etc) oraz o jeszcze ciekawszy design.




INSTALACJA
1. Kliknij plik .apk w repozytorium Github_researcher. (https://github.com/peterdz099/Github_researcher)

![Screenshot_2022-04-26-01-24-19-4](https://user-images.githubusercontent.com/103062015/165190596-83ab9a67-3e88-4c47-ade0-41969a0b539e.jpg)


2. Pobierz plik na swoje urządzenie z Androidem. Konieczne może byc zezwolenie na pobieranie plików .apk z nieznanego zródła. 

![Screenshot_2022-04-26-01-24-29-3](https://user-images.githubusercontent.com/103062015/165190823-1d7267d8-20d2-4528-ab05-d8580c530469.jpg)

![Screenshot_2022-04-25-23-17-39-0](https://user-images.githubusercontent.com/103062015/165190246-05ae5303-2199-4563-9f99-81ca68772cdf.jpg)


3. Wejdź w pobrane pliki i zainstaluj aplikację. Konieczne może być zezwolenie na instalację aplikacji z nieznanego źródła.

![Screenshot_2022-04-25-23-17-23-9](https://user-images.githubusercontent.com/103062015/165190297-6a0606ed-2f22-4ac5-81e9-0bcf959c101e.jpg)










