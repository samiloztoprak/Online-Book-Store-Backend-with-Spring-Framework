package netgloo.models;

import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class InventoryDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Inventory inventory){
        entityManager.persist(inventory);
        return;
    }

    public void countUp(Inventory inventory){
        inventory.setCount(inventory.getCount()+1);
        entityManager.merge(inventory);
        return;
    }

    public void countDown(Inventory inventory){
        inventory.setCount(inventory.getCount()-1);
        entityManager.merge(inventory);
        return;
    }

    public Inventory getByInventory(List<Book> book) {
        return (Inventory) entityManager.createQuery("from Inventory where book_id=:book_id")
                .setParameter("book_id",book.iterator().next())
                .getSingleResult();
    }

    public Inventory getById(int id) {return (Inventory) entityManager.find(Inventory.class, id);}

}
