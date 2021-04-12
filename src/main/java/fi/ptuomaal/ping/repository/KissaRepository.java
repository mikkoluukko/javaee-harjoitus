package fi.ptuomaal.ping.repository;

import fi.ptuomaal.ping.entity.Kissa;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

// Tämä luokka toimii Kissa-entiteettien repositoriona eli toimii tietokannan
// ja API:n välissä.

public class KissaRepository {

    @PersistenceContext(unitName="ping")
    EntityManager entityManager;

    public List<Kissa> findAll() {
        List<Kissa> results = entityManager.createNamedQuery(Kissa.NAMED_QUERY_GET_ALL).getResultList();
        return results;
    }

    public List<Kissa> findByKissaName(String name) {
        List<Kissa> tmp = findAll();
        List<Kissa> results = new ArrayList<>();
        for(Kissa k : tmp) {
            if ( k.getName().equals(name))
                results.add(k);
        }
        return results;
    }

    public List<Kissa> findByName(String name) {
        List<Kissa> results = entityManager.createNamedQuery(Kissa.NAMED_QUERY_FIND_BY_NAME).setParameter("name", name).getResultList();
        return results;
    }

    public Kissa findById(Long id) {
        return (Kissa) entityManager.createNativeQuery("select * from kissa where id=" + id, Kissa.class).getSingleResult();
    }

    public List<Kissa> findByBreed(String breed) {
        List<Kissa> results = entityManager.createNamedQuery(Kissa.NAMED_QUERY_FIND_BY_BREED).setParameter("breed", breed).getResultList();
        return results;
    }

    public Kissa create(String name, int age, String breed) {
        Kissa k = new Kissa();
        k.setName(name);
        k.setAge(age);
        k.setBreed(breed);
        entityManager.persist(k);
        return k;
    }

    public Kissa update(Kissa k) {
        Kissa fresh = entityManager.merge(k);
        return fresh;
    }

    public boolean delete(Kissa k) {
        try {
            entityManager.remove(k);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }



}

