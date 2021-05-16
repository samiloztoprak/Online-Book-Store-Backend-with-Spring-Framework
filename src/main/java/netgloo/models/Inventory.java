package netgloo.models;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @NotNull
    private int count;

    @OneToOne
    private Book book;

    public Inventory(){}
    public Inventory(int count,Book book){this.count=count;this.book=book;}

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }
    public void setCount(int count) {
        this.count = count;
    }

    public Book getBook() {
        return book;
    }
    public void setBook(Book book) {
        this.book = book;
    }
}
