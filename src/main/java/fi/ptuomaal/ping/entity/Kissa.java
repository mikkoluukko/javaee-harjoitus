package fi.ptuomaal.ping.entity;

import javax.persistence.*;

// Tämä luokka mallintaa Kissa-entiteetin.

/*
Laitan tähän myös muihin tehtäviin liittyviä kommentteja:

Tehtävä 9: Haastavimmaksi koin kenties epävarmuuden oikeiden käytäntöjen suhteen JakartaEE-maailmassa.
Esimerkiksi en usko että ratkaisuni Pongien POST-endpointiksi on kovinkaan fiksu, mutta en myöskään ehtinyt
selvittämään mikä olisi oikea tapaa välittää Request bodyssä JSON-objekteja. Minulle jäi myös epäselväksi miksi
KissaService:ssä generateContent-metodilla luomieni testiobjektien id:t eivät ole tietokannassa 1...3 vaan
satunnaiset arvot väliltä 1...6. Käytin tehtävään aikaa melkein neljä tuntia ja ilman aikarajaohjetta
olisin jatkanut aiheen opiskelua vielä lisää.

Tehtävä 10: En nyt ole varma, mutta ehkä kaikkien endpointtien vastauksiin olisi hyvä lisätä HTTP Status-koodit.
Repository-tiedostoissa on kaiketi turhaan kaksi erilaista toteutusta nimen perusteella hakemiseen. Oletan että
NamedQuery:ä käyttävä toteutus on tehokkaampi, joten sen toisen voisi varmaankin poistaa. Luulen myös että POST ja
PUT/PATCH endpointtien olisi syytä vastaanottaa ihan oikeita JSON-objekteja, mutta en ehtinyt selvittää miten tämä
kannattaisi toteuttaa.
*/

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