package netgloo.models;

import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "book")
public class Book{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotNull
    private String name;

    @NotNull
    private String code;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;

    @NotNull
    private String category;

    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date publishDate;

    @NotNull
    private int price;

    @NotNull
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date updateDate;

    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "books")
    private Set<Cart> cart;

    public Book(){}

    public Book(long id){this.id = id;}

    public Book(long id, @NotNull String name, @NotNull String code, Author author, @NotNull String category, @NotNull Date publishDate, @NotNull int price, @NotNull Date updateDate) {
        this.id = id;
        this.name = name;
        this.code = code;
        this.author = author;
        this.category = category;
        this.publishDate = publishDate;
        this.price = price;
        this.updateDate = updateDate;
    }

    public Book(@NotNull String name, @NotNull String code, Author author, @NotNull String category, @NotNull Date publishDate, @NotNull int price, @NotNull Date updateDate) {
        this.name = name;
        this.code = code;
        this.author = author;
        this.category = category;
        this.publishDate = publishDate;
        this.price = price;
        this.updateDate = updateDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

}