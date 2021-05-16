package netgloo.models;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PreRemove;
import javax.transaction.Transactional;
import java.util.Set;

@Repository
@Transactional
public class CartDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Cart cart){
        entityManager.persist(cart);
    }

    public void delete(Cart cart){
        if(entityManager.contains(cart)){
            entityManager.remove(cart);
        }
        else {
            entityManager.remove(entityManager.merge(cart));
        }
    }

    public void addBook(Cart cart,Book book){
        cart.getBooks().add(book);
        entityManager.merge(cart);
    }

    public void removeBook(Cart cart, Book book){
        for (Book book1 : cart.getBooks()) {
            if(book.getId()==book1.getId())
                cart.getBooks().remove(book1);
        }
        entityManager.merge(cart);
    }

    public Set<Book> bookList(User user){
        return user.getCart().getBooks();
    }

    public int totalPrice(User user){
        int totalPrice = 0;
        for (Book book: user.getCart().getBooks()
             ) {
            totalPrice += book.getPrice();

        }
        return totalPrice;
    }
}
