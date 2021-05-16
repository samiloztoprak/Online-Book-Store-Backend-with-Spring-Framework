package netgloo.converter;

import netgloo.controllers.BookModel;
import netgloo.models.Author;
import netgloo.models.AuthorDao;
import netgloo.models.Book;
import netgloo.models.BookDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class Incoming {
    @Autowired
    AuthorDao authorDao;
    @Autowired
    BookDao bookDao;

    public Author toAuthor(int id){
        return authorDao.getById(id);
    }

    public List<Book> toBookList(BookModel model){
        return bookDao.getControl(model);
    }
    /**public Book toBook(BookModel model){
        return new Book(model.getName(),model.getCode(),model.getAuthor()
                ,model.getCategory(),model.getPublishDate(),model.getPrice(),model.getUpdateDate());
    }*/
    public Book toBookid(int id){
        return bookDao.getById(id);
    }
}
