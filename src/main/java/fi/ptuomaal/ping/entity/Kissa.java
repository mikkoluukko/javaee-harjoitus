package fi.ptuomaal.ping.entity;

import javax.persistence.*;

// Tämä luokka mallintaa Kissa-entiteetin.

@Entity
@NamedQueries({
        @NamedQuery
                (
                        name=Kissa.NAMED_QUERY_GET_ALL,
                        query="SELECT k FROM Kissa k"
                ),@NamedQuery
        (
                name=Kissa.NAMED_QUERY_FIND_BY_NAME,
                query="SELECT k FROM Kissa k where k.name =:name"
        ),@NamedQuery
        (
                name=Kissa.NAMED_QUERY_FIND_BY_BREED,
                query="SELECT k FROM Kissa k where k.breed =:breed"
        )
})
public class Kissa {
    public static final String NAMED_QUERY_GET_ALL="N3";
    public static final String NAMED_QUERY_FIND_BY_NAME="N4";
    public static final String NAMED_QUERY_FIND_BY_BREED="N5";

    @Id
    @GeneratedValue
    private int id;

    @Column
    private String name;

    @Column
    private int age;

    @Column
    private String breed;

    public Kissa() {   }

    public Kissa (int id, String name, int age, String breed) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.breed = breed;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }
}