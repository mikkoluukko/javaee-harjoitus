package fi.ptuomaal.ping.service;

import fi.ptuomaal.ping.entity.Kissa;
import fi.ptuomaal.ping.repository.KissaRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.logging.Logger;

// Tämä luokka sisältää ns. apulogiikan controllerin ja repositoryn
// väliseen kommunikointiin.

@Stateless
public class KissaService {

    private final static Logger logger = Logger.getLogger(KissaService.class.getName());

    @Inject
    KissaRepository repository;

    public List<Kissa> getAllKissas() {
        return repository.findAll();
    }

    public List<Kissa> getKissaByName(String name) {
        List<Kissa> results = repository.findByName(name);
        if (results.isEmpty()) {
            results = repository.findAll();
        }
        return results;
    }

    public void generateContent() {
        if ( repository.findAll().isEmpty() ) {
            logger.info("Kanta on tyhjä, luodaan sinne muutama tulos");
            repository.create("Miuku", 2, "Sfinx");
            repository.create("Mauku", 2, "Ragdoll");
            repository.create("Mouku", 5, "Ragdoll");
        } else {
            logger.info("Kannassa on jo tavaraa, ei syytä luoda lisää");
        }
    }

    public Kissa getKissaById(Long id) {
        return repository.findById(id);
    }

    public List<Kissa> getKissaByBreed(String breed) {
        List<Kissa> results = repository.findByBreed(breed);
        return results;
    }

    public Kissa postKissa(String name, int age, String breed) {
        return repository.create(name, age, breed);
    }

    public boolean deleteKissa(Long id) {
        try {
            return repository.delete(repository.findById(id));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
