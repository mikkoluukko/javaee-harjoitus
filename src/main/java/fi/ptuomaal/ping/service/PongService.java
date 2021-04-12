package fi.ptuomaal.ping.service;

import fi.ptuomaal.ping.entity.Pong;
import fi.ptuomaal.ping.repository.PongRepository;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

// Tämä luokka sisältää ns. apulogiikan controllerin ja repositoryn
// väliseen kommunikointiin.

@Stateless
public class PongService {

    private final static Logger logger = Logger.getLogger(PongService.class.getName());

    @Inject
    @ConfigProperty(name = "message")
    String message;

    @Inject
    @ConfigProperty(name = "message2")
    String message2;

    @Inject
    PongRepository repository;

//    Käytän findByName-metodia findByPongName:n sijaan koska oletan tämän olevan nopeampi
    public List<Pong> getPongs(String name) {
        List<Pong> results = repository.findByName(name);
        if (results.isEmpty()) {
            results = repository.findAll();
        }
        return results;
    }

    public String getPongsAsString(String name) {
        List<Pong> pongs = getPongs(name);
        StringBuilder result = new StringBuilder();
        for (Pong pong : pongs) {
            result.append(pong.getName()).append(", ");
        }
        if (result.length() > 0) {
            result.replace(result.length() - 2, result.length(), "");
        }
        return result.toString();
    }

    public void generateContent() {
        if ( repository.findAll().isEmpty() ) {
            logger.info("Kanta on tyhjä, luodaan sinne muutama tulos");
            repository.create("Ping");
            repository.create("Pong");
            repository.create("Pang");
        } else {
            logger.info("Kannassa on jo tavaraa, ei syytä luoda lisää");
        }
    }

    public String getResponseForTest() {
        return message + "\n" + message2;
    }

    public boolean testDbOperations() {
        try {
            Pong tmp = repository.create("Test");
            Pong tmp2 = repository.update(tmp);
            return repository.delete(tmp2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    public Pong getPong(Long id) {
        return repository.findById(id);
    }

    public Pong postPong(String name) {
        return repository.create(name);
    }

    public boolean deletePong(Long id) {
        try {
            return repository.delete(repository.findById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
