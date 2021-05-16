package netgloo.controllers;

import netgloo.Application;
import netgloo.models.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class UserController {

    private static final Logger logger = LogManager.getLogger(Application.class);

    @Autowired
    private UserDao userDao;

    @Autowired
    private CartDao cartDao;

    @PostMapping("/user/create")
    public String create(@RequestBody UserModel model) {
        try {
            List<User> findUser = userDao.getControl(model);
            if(findUser.isEmpty()){
                User user = new User(model.getFirst_name().toLowerCase(), model.getLast_name().toLowerCase(), model.getRegister_date(), model.getAddress().toLowerCase(),null);
                Cart cart = new Cart(null);
                cart.setUser(user);
                user.setCart(cart);
                userDao.create(user);
                logger.info("user/create User created user: {}",model);
                return "User successfully created!";
            }
            else{
                logger.info("user/create User has user: {}",model);
                return "This user has.";
            }
        } catch (Exception ex) {
            logger.error("user/create User not created error: {}",ex.getMessage());
            return "Error creating the user: " + ex.toString();
        }

    }
    //method = delete araştır
    @RequestMapping(value = "/user/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") int id) {
        try {
            User user = userDao.getById(id);
            userDao.delete(userDao.getById(id));
        } catch (Exception ex) {
            logger.error("user/delete User not deleted error: {}",ex.getMessage());
            return "Error deleting the user: " + ex.toString();
        }
        logger.info("user/delete User deleted by id: {}",id);
        return "User successfully deleted!";
    }

    @GetMapping(value = "/user/get-by-id/{id}")
    public User getById(@PathVariable int id) {
        logger.info("user/get-by-id User listed by id: {}",id);
        return userDao.getById(id);
    }

    @GetMapping(value = "/user/get-by-firstname/{first_name}")
    public List<User> getByFirstName(@PathVariable String first_name) {
        logger.info("user/get-by-firstname User listed by firstname: {}",first_name);
        return userDao.getByFirstName(first_name);
    }

    @RequestMapping(value = "/user/get-by-lastname/{last_name}", method = RequestMethod.GET)
    public List<User> getByLastName(@PathVariable("last_name") String last_name) {
        logger.info("user/get-by-lastname User listed by lastname: {}",last_name);
        return userDao.getByLastName(last_name);
    }

    @RequestMapping(value = "/user/get-by-address/{address}", method = RequestMethod.GET)
    public List<User> getByAddress(@PathVariable("address") String address) {
        logger.info("user/get-by-address User listed by address: {}",address);
        return userDao.getByAddress(address);
    }

    @RequestMapping(value = "/user/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String update(@PathVariable long id, @RequestBody UserModel model) {
        try {
            User user = userDao.getById(id);
            user.setLast_name(model.getLast_name());
            user.setFirst_name(model.getFirst_name());
            user.setRegister_date(model.getRegister_date());
            user.setAddress(model.getAddress());
            userDao.update(user);
        } catch (Exception ex) {
            logger.error("user/update not updated error: {}",ex.getMessage());
            return "Error updating the user: " + ex.toString();
        }
        logger.info("user/update User update by id: {} user: {}",id,model);
        return "User successfully updated!";
    }
}
