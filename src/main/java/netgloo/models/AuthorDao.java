package netgloo.models;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class AuthorDao {

    @PersistenceContext
    private EntityManager entityManager;

    public void create(Author author){
        entityManager.persist(author);
    }

    public void delete(Author author){
        if(entityManager.contains(author))
            entityManager.remove(author);
        else
            entityManager.remove(entityManager.merge(author));
    }

    @SuppressWarnings("unchecked")
    public List<Author> getAll(){return entityManager.createQuery("from Author").getResultList();}

    public Author getById(int id){return (Author)entityManager.find(Author.class, id);}

    public List<Author> getByFirstName(String first_name){
        return (List<Author>) entityManager.createQuery("select a from Author a where first_name=:first_name")
                .setParameter("first_name",first_name)
                .getResultList();
    }

    public List<Author> getByLastName(String last_name){
        return (List<Author>) entityManager.createQuery("select a from Author a where last_name =:last_name")
                .setParameter("last_name",last_name).getResultList();
    }

    public List<Author> getByCountry(String country){
        return (List<Author>) entityManager.createQuery("select a from Author a where country=:country")
                .setParameter("country",country).getResultList();
    }

    public void update(Author author){
        entityManager.merge(author);
        return;
    }
}
