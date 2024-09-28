# Konfigurerbart system for <br> modenhetsanalyser av IT-sikkerhet <br>
## Saphêneia versjon 1.0.0
                                                                                                        
____________________________________________________________________________________________________________<br>
### Quick Start Guide: <br>
#### Systemkrav  <br>
- Docker desktop v 4.29.0 eller høyere
- Minstekrav for å kjøre docker: https://docs.docker.com/desktop/install/windows-install/.
- Ytterligere 3 GB for hele installasjonen.
Docker kan lastes ned fra denne lenken: <br>
https://www.docker.com/products/docker-desktop/
<br>
____________________________________________________________________________________________________________<br>

#### For å installere programvaren: Lokal versjon med bruk av Docker / VM hos skyleverandør <br> 
1. Installer Docker desktop
2. Start Docker desktop
3. Last ned hele mappen dunamis ifra dette repositoriet og lagre på C:
4. Tast 🪟(Windowstast) + R og tast "cmd" + ok / Eller bruk søkefeltet og søk etter "Command Promt" eller "Kommandolinje"
5. skriv "cd c:/dunamis/custom_image/dunamis_spring"
6. skriv "docker load -i c:/dunamis/custom_image/dunamis_spring/dunamis_spring.tar" (ligger også en .txt fil i mappen med kommandoen)
7. skriv "docker image ls" <br>

Du skal nå kunne se følgende:
![image](https://github.com/BO24-G25/bacheloroppgave/assets/89580955/18808279-d3e5-4d7a-a687-b3971561a32a)

8. skriv "cd c:/dunamis/custom_image/dunamis_react"
9. skriv "docker load -i c:/dunamis/custom_image/dunamis_react/dunamis_react.tar" (ligger også en .txt fil i mappen med kommandoen)
10. skriv "docker image ls" <br>

Du skal nå kunne se følgende:
![image](https://github.com/BO24-G25/bacheloroppgave/assets/89580955/aa812190-6caf-4291-9260-435f5dd52136)

11. skriv "cd c:/dunamis"
12. skriv "docker compose up -d" <br>

Du skal nå kunne se følgende:
![image](https://github.com/BO24-G25/bacheloroppgave/assets/89580955/2436536c-3a87-478b-b088-e5e413956d7a)

13. Etter kommandoen har kjørt ferdig så vil du kunne nå systemet umiddelbart, men ved første gangs installasjon kan det
    ta opptil 4 minutter før databasen har blitt ferdig installert. Databasen inneholder en eksempel bedrift.
    Denne vil være synlig når databasen er ferdig installert.
15. Sjekk i Docker desktop under container om det ser slik ut:
![image](https://github.com/BO24-G25/bacheloroppgave/assets/89580955/3d74a567-a4fb-4b49-9e64-77e64d5ff5f0)

16. Åpne en webleser og skriv inn adressen "http://localhost:3000/" <br>

Du skal nå noe som ligner på dette:
![image](https://github.com/BO24-G25/bacheloroppgave/assets/89580955/86a29511-53e8-466b-b718-3b7d5b0a7301)

<br>
____________________________________________________________________________________________________________<br>

#### For å installere programvaren: Separate docker containere hos en skyplattform <br> 
NB! Systemet er ikke testet eller utviklet til å kunne direkte hostes på en skyplattform i separate 
Docker containere. Systemet er designet til å bli innstallert via Docker på en og samme plattform/enhet/VM/PC.

1. Last opp filen dunamis_react.tar til en container tjeneste hos en skyplattform (dette er et custom docker image)
   (filen genereres ved bruk av GitHub workflow 'Build Docker Custom Image dunamis.react (Node)' eller
   ligger i mappen /dunamis/custom_image/dunamis_react )
2. Start containeren
3. Last opp filen dunamis_spring.tar til en container tjeneste hos en skyplattform (dette er et custom docker image)
   (filen genereres ved bruk av GitHub workflow 'Build Docker Custom Image dunamis.spring (Java)' eller
   ligger i mappen /dunamis/custom_image/dunamis_spring )
4. Star containeren
5. Bruk første del av Docker compose scriptet til å lage MySQL containeren.
![image](https://github.com/BO24-G25/bacheloroppgave/assets/89580955/1ec4e717-1e85-4aa6-8321-9f21b7f3105c)
6. Lag sikker kommunikasjon mellom container tjenestene på den valgte skyplattformen.
7. dunamis_react trenger å kommunisere med dunamis_spring på port 8080, dunamis_spring trenger å kommunisere dunamis_mysql
  på port 3306. Dette bør gjøres etter beste praksis og videre instruksers på hvordan dette gjøres sikkert er utenfor scopen til denne oppgaven.

<br>
____________________________________________________________________________________________________________<br>

#### For å starte programvaren: Lokal versjon med bruk av Docker / VM hos skyleverandør <br> 
1. Start Docker desktop (dersom ikke Docker desktop starter av seg selv når Windows starter)
2. Containeren skal starte av seg selv, i korrekt rekkefølge. Hvis ikke gå i Docker desktop under containers
   og trykk play på container mappen dunamis.
   ![image](https://github.com/BO24-G25/bacheloroppgave/assets/89580955/72f60325-eae1-4ae4-97b9-a33e91153b61)
3. Åpne en webleser og skriv inn adressen "http://localhost:3000/" <br>

<br>
____________________________________________________________________________________________________________<br>

#### Lage nye docker images ved videre utvikling av programvaren: I GitHub <br> 
1. Last opp repositoriet levert med denne bacheloroppgaven til en GitHub konto.
2. Hver gang kode blir merget til main branchen så vil det genereres nye tar-filer for react og spring delen av programvaren.
   Alternativt start hver av workflow manuelt ved å gå til menyen Actions og klikke og starte dem to workflowene manuelt <br>
   
   ![image](https://github.com/BO24-G25/bacheloroppgave/assets/89580955/44647020-38f0-4983-ad1f-dc7f07140acb)
   
4. Last ned tar-filene når workflowen er ferdig (grønn). <br>

   ![image](https://github.com/BO24-G25/bacheloroppgave/assets/89580955/4d87b714-3b14-4c5a-8648-d772f207458f)

5. Lagre tar-filene under dem respektive mappene under c:\dunamis\custom_image\dunamis_******
6. Følg instruksene for å installere programvaren.

<br>
____________________________________________________________________________________________________________<br>

#### Lage nye docker images ved videre utvikling av programvaren: I en IDE/Terminal <br> 
1. Bruk repositoriet levert med denne bacheloroppgaven.
2. Installer Docker på maskinen du driver utviklingen på.
3. Start Docker.
4. Etter du har utført endringene, åpne root mappen på prosjektet i en terminal.
5. Skriv "docker build -t dunamis_spring:latest ." (med punktum på slutten) for å lage dunamis_spring Docker imaget
6. Skriv "docker image ls" for å se at imaget er lagt inn i Docker på maskinen. <br>

   Du skal nå noe som ligner på dette: <br>
   
   ![image](https://github.com/BO24-G25/bacheloroppgave/assets/89580955/d0819b06-b29b-4943-918c-34fb497538f4)


7. Naviger til mappen frontend åpne den med en terminal.
8. Skriv "docker build -t dunamis_react:latest ." (med punktum på slutten) for å lage dunamis_react Docker imaget
9. Skriv "docker image ls" for å se at imaget er lagt inn i Docker på maskinen. <br>

   Du skal nå noe som ligner på dette: <br>
   
   ![image](https://github.com/BO24-G25/bacheloroppgave/assets/89580955/5fd4f63f-8474-417e-bf4b-5320fbb4e1c6)

11. Fortsett på punkt 11. på instruksene for å installere programvaren.
<br>
____________________________________________________________________________________________________________<br>
