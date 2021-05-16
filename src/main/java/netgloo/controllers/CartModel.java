package netgloo.controllers;

import netgloo.models.Book;
import netgloo.models.User;
import java.util.Set;

public class CartModel {

    private int id;

    private User user;

    private Set<Book> books;

    public CartModel() {
    }

    public CartModel(int id) {
        this.id = id;
    }

    public CartModel(int id, Set<Book> books) {
        this.id = id;
        this.books = books;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CartModel(User user) {
        this.user = user;
    }

    public CartModel(int id, User user, Set<Book> books) {
        this.id = id;
        this.user = user;
        this.books = books;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }
}
