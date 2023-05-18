# Alerting_System_Kotlin

## Indítás elött:

    Szükséges PostgreSQL adatbáziskezelő. Azon belül egy „reportKotlin” nevezetű adatbázis.

![](/pics/report_prop_db.png)

    Táblák létrehozása és feltöltése első indításkor megtörténik a „schema.sql” és a „data.sql” által. Ez csak a „repost-service”-re vonatkozik.

## Indítás

### 1.Módszer:

    IntelliJ IDEA Ultimate-ben a Service fülnél indítható vagy a Compound-nál az „Alert_System”-re jobb klick és „Run”, vagy a Spring Boot alatt ugyan így egyesével elindítva.

![](/pics/intellij_services.png)

    Ha bal alsó sarokban nem jelenik meg a Service, akkor View > Tool Windows > Services.

### 2.Módszer

    IntelliJ IDEA Community-ben a jobb oldalon kis elefántra kattintva ez jelenik meg: 

![](/pics/little_elephant.png)

    Itt a Console ikonra kattintva(balról 4.) megjelenik a következő konzol:

![](/pics/gradle_console.png)

    Ide a következő 4 parancsot kell beírni:

    1.  Report Service Indítása:
        gradle report-service:bootRun
    2. Police Alert Service Indítása:
        gradle alert-service:bootRun --args='--spring.profiles.active=police' 
    3. Fire Department Alert Service Indítása:
        gradle alert-service:bootRun --args='--spring.profiles.active=fire'
    4. Hospital Alert Service Indítása:
        gradle alert-service:bootRun --args='--spring.profiles.active=hospital'

    A parancsok beírása után a program fut.

## Program használata:

    Indítás után itt érhetőek el a Swagger-ek:

[Report Swagger](http://localhost:8080/swagger-ui/index.html)

[Police Swagger](http://localhost:8081/swagger-ui/index.html)

[Fire Department Swagger](http://localhost:8082/swagger-ui/index.html)

[Hospital Service](http://localhost:8083/swagger-ui/index.html)

    A utolsó 3 megegyezőek hiszen ugyan az a microservice az alapja, viszont más adatbázison dolgoznak.

### Adatbázis eléréshez ajánlom:

    Report esetén : PGAdmin, vagy IntelliJ Ultimate beépített

    Alert esetén : http://localhost:80{adott service}/h2-console/, vagy IntelliJ Ultimate beépített
	
[Pl.: H2 database console](http://localhost:8082/h2-console)

	h2 bejelentkezési adatok:

![](/pics/h2_console.png)


### Report Swagger:

![](/pics/report_swag1.png)

    Ezt a WebClient használja, ezt hívják meg az alert service-k.

![](/pics/report_swag2.png)

    Ezzel tudunk az alert service-nek üzenetet küldeni. Az „eventTypeId” helyére kell beírni az EventType típus id-t.

![](/pics/report_swag3.png)

    Letudjuk kérni a létező event típusokat

![](/pics/report_swag4.png)

    Itt látható a típus Id-ja és hogy mely servicek-nek fog küldeni jelzést.

![](/pics/report_swag5.png)

    Letudjuk kérni az elküldött event-eket.

![](/pics/report_swag6.png)

    Itt látható, ha az event új akkor a státusza NEW.

    Ha valamelyik alert-re válaszolnak, tehát a messageId nem lesz egyenlő null-al, akkor a státusz PARTIAL lesz.

    Ha mindegyikre válaszolnak, akkor pedig CLOSED

![](/pics/report_swag7.png)

    Létező jelzések lekérése.


### Alert Swagger

![](/pics/alert_swag1.png)

    Ezt a WebClient használja, ezt hívja meg a report service.

![](/pics/alert_swag2.png)

    Le lehet kérni a már létező message-et.

![](/pics/alert_swag3.png)

    Itt az id a messageId, ami a későbbiekben fog kelleni.

![](/pics/alert_swag4.png)

    Ezzel lehet választ küldeni a report service-nek.
