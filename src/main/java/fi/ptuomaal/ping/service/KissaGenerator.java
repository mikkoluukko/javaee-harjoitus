package fi.ptuomaal.ping.service;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

// Tätä luokkaa käytetään KissaServicen generateContent-metodin kutsumiseen

@Startup
@Singleton
public class KissaGenerator {

    @Inject
    KissaService service;

    @PostConstruct
    public void init() {
        service.generateContent();
    }
}