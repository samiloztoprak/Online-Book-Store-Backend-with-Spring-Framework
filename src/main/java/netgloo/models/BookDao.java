package netgloo.models;

import netgloo.controllers.BookModel;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.*;

@Repository
@Transactional
public class BookDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Book book){
        entityManager.persist(book);
        return;
    }

    public void delete(Book book){
        if(entityManager.contains(book)){
            entityManager.remove(book);
        }
        else {
            entityManager.remove(entityManager.merge(book));
        }
        return;
    }

    @SuppressWarnings("unchecked")
    public List<Book> getAll(){return entityManager.createQuery("select u from Book u order by price").getResultList();}

    public Book getById(long id) {return (Book)entityManager.find(Book.class, id);}

    public List<Book> getByAuthor(Author author){
        return   (List<Book>) entityManager.createQuery("select u from Book u Where author = :author")
                .setParameter("author",author)
                .getResultList();
    }

    public List<Book> getByCategory(String category){
        return   (List<Book>) entityManager.createQuery("select u from Book u Where category = :category")
                .setParameter("category",category)
                .getResultList();
    }

    public Book getByCode(String code){
        return   (Book) entityManager.createQuery("select u from Book u Where code = :code")
                .setParameter("code",code)
                .getSingleResult();
    }

    public List<Book> getByName(String name){
        return   (List<Book>) entityManager.createQuery("select u from Book u Where name = :name")
                .setParameter("name",name)
                .getResultList();
    }

    public List<Book> getByPrice(int price){
        return   (List<Book>) entityManager.createQuery("select u from Book u Where price = :price")
                .setParameter("price", price)
                .getResultList();
    }

    public List<Book> getByPublishDate(Date pDate){
        return   (List<Book>) entityManager.createQuery("select u from Book u Where publish_date = :pDate")
                .setParameter("pDate",pDate)
                .getResultList();
    }

    public List<Book> getByUpdateDate(Date uDate){
        return   (List<Book>) entityManager.createQuery("select u from Book u Where update_date = :uDate")
                .setParameter("uDate",uDate)
                .getResultList();
    }

    public List<Book> getControl(BookModel bookModel){
        return (List<Book>) entityManager.createQuery("from Book where name=:name and code=:code and category=:category")
                .setParameter("name",bookModel.getName())
                .setParameter("code",bookModel.getCode())
                .setParameter("category",bookModel.getCategory())
                .getResultList();
    }

   /** public List<Book> getByAll(){
        return (List<Book>) entityManager.createQuery("select u from Book u order by price").getResultList();
    }*/

    public void update(Book book){
        entityManager.merge(book);
        return;
    }

}
