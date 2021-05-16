package netgloo.converter;

import netgloo.controllers.AuthorModel;
import netgloo.controllers.BookModel;
import netgloo.models.Author;
import netgloo.models.Book;

public class Outgoing {

    Incoming incoming;

    public Author toAuthor(AuthorModel model){
        return new Author(model.getFirst_name(), model.getLast_name(), model.getCountry());
    }
    public Author toAuthor(int id, AuthorModel model){
        Author author =  incoming.toAuthor(id);
        author.setFirst_name(model.getFirst_name());
        author.setLast_name(model.getLast_name());
        author.setCountry(model.getCountry());
        return author;
    }
   /* public Book toBook(BookModel model){
        return new Book(model.getName(),model.getCode(),model.getAuthor()
                ,model.getCategory(),model.getPublishDate(),model.getPrice(),model.getUpdateDate());
    }*/

}
