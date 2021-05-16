package netgloo.controllers;

import netgloo.Application;
import netgloo.models.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.Set;

@RestController
public class CartController {

    @Autowired
    private CartDao cartDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private BookDao bookDao;

    private static final Logger logger = LogManager.getLogger(Application.class);

    @PostMapping("cart/create")
    private String create(@RequestBody Cart cart){
        try {
            cartDao.create(cart);
        }catch (Exception e){
            logger.error("cart/create not created object: {}, error: {}",cart,e.getMessage());
            return "Error: " + e.toString();
        }
        logger.info("cart/create create cart object: {}",cart);
        return "Successfully";
    }

    @PostMapping("cart/addBook/{userId}/{bookId}")
    private String addBook(@PathVariable("userId") int userId, @PathVariable("bookId") int bookId){
        try {
            Cart cart = userDao.getById(userId).getCart();
            Book book = bookDao.getById(bookId);
            cartDao.addBook(cart, book);
        }catch (Exception ex){
            logger.error("cart/addBook not added book in cart user id: {}, book id:{} error: {}",userId,bookId,ex.getMessage());
            return "Error: " + ex.toString();
        }
        logger.info("cart/addBook added book in cart user id: {}, book id:{}",userId,bookId);
        return "Successfully";
    }

    @PostMapping("cart/removeBook/{userId}/{bookId}")
    private String removeBook(@PathVariable("userId") int userId, @PathVariable("bookId") int bookId){
        try {
            Cart cart = userDao.getById(userId).getCart();
            Book book = bookDao.getById(bookId);
            cartDao.removeBook(cart, book);
        }catch (Exception ex){
            logger.error("cart/removeBook not removed book cart user id: {}, book id:{}, error: {}",userId,bookId,ex.getMessage());
            return "Error: " + ex.toString();
        }
        logger.info("cart/addBook remove book cart user id: {}, book id:{}",userId,bookId);
        return "Successfully";
    }

    @PostMapping("cart/list/{userId}")
    private Set<Book> bookList(@PathVariable("userId") int userId){
        logger.info("cart/list listed user id: {}",userId);
        return cartDao.bookList(userDao.getById(userId));
    }

    @PostMapping("cart/totalPrice/{userId}")
    private int totalPrice(@PathVariable int userId){
        logger.info("cart/totalPrice listed user id: {}",userId);
        return cartDao.totalPrice(userDao.getById(userId));
    }
}