package netgloo.controllers;

import netgloo.Application;
import netgloo.models.Author;
import netgloo.models.AuthorDao;
import netgloo.models.Book;
import netgloo.models.BookDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookDao bookDao;
    @Autowired
    private InventoryController inventoryController;
    @Autowired
    private AuthorDao authorDao;
    private static final Logger logger = LogManager.getLogger(Application.class);

    @PostMapping("/book/create")
    public String create(@RequestBody BookModel model){
        try {
            List<Book> bookFinder;
            bookFinder = bookDao.getControl(model);
            Author author = authorDao.getById(model.getAuthor());
            if(bookFinder.isEmpty()){
                bookDao.create(new Book(model.getName(),model.getCode(),author
                        ,model.getCategory(),model.getPublishDate(),model.getPrice(),model.getUpdateDate()));
                inventoryController.create(new Book(model.getName(),model.getCode(),author
                        ,model.getCategory(),model.getPublishDate(),model.getPrice(),model.getUpdateDate()));
                logger.info("book/create created book object: {}", model);
            }
            else{
                inventoryController.countUp(bookFinder);
                logger.info("book/create book count upped object: {}", model);
                return "Book count upped";
            }
        }
        catch (Exception ex){
            logger.error("book/create book not created :{}", ex.getMessage());
            return "Error creating the book:" + ex.toString();
        }
        return "Book successfully created!";
    }

    @GetMapping(value = "/book/get-all")
    public List<Book> getAll(){
        logger.info("book/get-all listed all book.");
        return bookDao.getAll();
    }

    @RequestMapping(value = "/book/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") int id) {
        try {
            bookDao.delete(bookDao.getById(id));
        } catch (Exception ex) {
            logger.error("book/not deleted book id: {}",id);
            return "Error deleting the book: " + ex.toString();
        }
        logger.info("book/delete book id: {}",id);
        return "Book successfully deleted!";
    }

    @GetMapping(value = "/book/get-by-id/{id}")
    public Book getById(@PathVariable int id){
        logger.info("book/get-by-id listed id: {}",id);
        return bookDao.getById(id);
    }

    @GetMapping(value = "/book/get-by-author/{author}")
    public List<Book> getByAuthor(@PathVariable Author author){
        logger.info("book/get-by-author listed author: {}",author);
        return bookDao.getByAuthor(author);
    }

    @GetMapping(value = "/book/get-by-category/{category}")
    public List<Book> getByCategory(@PathVariable String category){
        logger.info("book/get-by-category listed category: {}",category);
        return bookDao.getByCategory(category);
    }

    @GetMapping(value = "/book/get-by-code/{code}")
    public Book getByCode(@PathVariable String code){
        logger.info("book/get-by-code listed code: {}",code);
        return bookDao.getByCode(code);
    }

    @GetMapping(value = "/book/get-by-name/{name}")
    public List<Book> getByName(@PathVariable String name){
        logger.info("book/get-by-name listed name: {}",name);
        return bookDao.getByName(name);
    }

    @GetMapping(value = "/book/get-by-price/{price}")
    public List<Book> getByPrice(@PathVariable int price){
        logger.info("book/get-by-price listed price: {}",price);
        return bookDao.getByPrice(price);
    }

    @GetMapping(value = "/book/get-by-publishdate/{publishDate}")
    public List<Book> getByPublishDate(@PathVariable Date publishDate){
        logger.info("book/get-by-publishdate listed publishdate: {}",publishDate);
        return bookDao.getByPublishDate(publishDate);
    }

    @GetMapping(value = "/book/get-by-updatedate/{updateDate}")
    public List<Book> getByUpdateDate(@PathVariable Date updateDate){
        logger.info("book/get-by-updatedate listed updatedate: {}",updateDate);
        return bookDao.getByUpdateDate(updateDate);
    }

    @RequestMapping(value = "/book/update/{id}",method = RequestMethod.POST)
    @ResponseBody
    public String update(@PathVariable int id, @RequestBody BookModel model){
        try {
            Author author=authorDao.getById(model.getAuthor());
            Book book = new Book(id,model.getName(),model.getCode(),author,model.getCategory(),model.getPublishDate(),model.getPrice(),model.getUpdateDate());
            bookDao.update(book);
        }
        catch (Exception ex){
            logger.error("book/update updated id: {}",id);
            return "Error update the book" + ex.toString();
        }
        logger.info("book/update update id: {}",id);
        return "Book successfully update";
    }
}