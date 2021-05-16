package netgloo.controllers;

import netgloo.Application;
import netgloo.models.Author;
import netgloo.models.AuthorDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AuthorController {

    private static final Logger logger = LogManager.getLogger(Application.class);

    @Autowired
    private AuthorDao authorDao;

    @PostMapping("/author/create")
    public String create(@RequestBody AuthorModel model){
        try {
            authorDao.create(new Author(model.getFirst_name(), model.getLast_name(), model.getCountry()));
        }
        catch (Exception ex){
            logger.error("author/create error : {}",ex.getMessage());
            return "Error creating the author:" + ex.toString();
        }
        logger.info("author/create added object : {}",model);
        return "Author successfully created!";
    }

    @GetMapping(value = "/author/get-all")
    public List<Author> getAll(){return authorDao.getAll();}

    @PostMapping(value = "/author/delete/{id}")
    public String delete(@PathVariable("id") int id){
        try {
            authorDao.delete(authorDao.getById(id));
        } catch (Exception ex) {
            logger.error("author/delete error : {}",ex.getMessage());
            return "Error deleting the author:" + ex.toString();
        }
        logger.info("author/delete delete object id: {}",id);
        return "Author successfully deleted";
    }

    @PostMapping(value = "/author/update/{id}")
    @ResponseBody
    public String update(@PathVariable int id, @RequestBody AuthorModel model){
        try{
            authorDao.update(new Author(id ,model.getFirst_name(), model.getLast_name(), model.getCountry()));
        } catch (Exception ex) {
            logger.error("author/update error : {}",ex.getMessage());
            return "Error update the author" + ex.toString();
        }
        logger.info("author/update update object : {}",model);
        return "Author successfully update";
    }

    @GetMapping(value = "/author/get-by-firstName/{name}")
    public List<Author> getByFirstName(@PathVariable String name){
        logger.info("author listed by first name : {}",name);
        return authorDao.getByFirstName(name);
    }

    @GetMapping(value = "/author/get-by-lastName/{name}")
    public List<Author> getByLastName(@PathVariable String name){
        logger.info("author listed by last name : {}",name);
        return authorDao.getByLastName(name);
    }

    @GetMapping(value = "/author/get-by-country/{country}")
    public List<Author> getByCountry(@PathVariable String country){
        logger.info("author listed by country name : {}",country);
        return authorDao.getByCountry(country);
    }

}
