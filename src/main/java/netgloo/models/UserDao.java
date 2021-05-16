package netgloo.models;

import netgloo.controllers.UserModel;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
public class UserDao {

  @PersistenceContext
  private EntityManager entityManager;


  public void create(User user) {
    entityManager.persist(user);
    return;
  }

  public void delete(User user) {
    if (entityManager.contains(user))
      entityManager.remove(user);
    else
      entityManager.remove(entityManager.merge(user));
    return;
  }

  @SuppressWarnings("unchecked")
  public List<User> getAll() {
    return (List<User>) entityManager.createQuery("from User").getResultList();
  }

  public List<User> getControl(UserModel userModel){
    return (List<User>)entityManager.createQuery("from User where first_name = :first_name and last_name = :last_name and register_date = :register_date and address = :address")
            .setParameter("first_name",userModel.getFirst_name().toLowerCase())
            .setParameter("last_name",userModel.getLast_name().toLowerCase())
            .setParameter("register_date", userModel.getRegister_date())
            .setParameter("address", userModel.getAddress().toLowerCase())
            .getResultList();
  }

  public User getById(long id) {
    return (User) entityManager.find(User.class, id);
  }

  public List<User> getByFirstName(String first_name) {
    return   (List<User>) entityManager.createQuery("select u from User u Where first_name = :first_name")
            .setParameter("first_name",first_name)
            .getResultList();
  }

  public List<User> getByAddress(String address){
    return (List<User>) entityManager.createQuery("from User Where address like :address")
            .setParameter("address","%"+address+"%")
            .getResultList();
  }

  public  List<User> getByLastName(String last_name){
    return (List<User>) entityManager.createQuery("from User where last_name = :last_name")
            .setParameter("last_name", last_name)
            .getResultList();
  }

  public void update(User user) {
    entityManager.merge(user);
    return;
  }

} // class UserDao
