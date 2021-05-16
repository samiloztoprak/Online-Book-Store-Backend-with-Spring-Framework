package netgloo.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    private String first_name;

    @NotNull
    private String last_name;

    @NotNull
    private String country;

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY)
    private Set<Book> books;

    public Author(){}

    public Author(int id){this.id=id;}

    public Author(String first_name,String last_name,String country){
        this.first_name = first_name;
        this.last_name = last_name;
        this.country = country;
    }
    public Author(int id, String first_name,String last_name,String country){
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.country = country;
    }

    public Author(@NotNull String first_name, @NotNull String last_name, @NotNull String country, Set<Book> books) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.country = country;
        this.books = books;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
