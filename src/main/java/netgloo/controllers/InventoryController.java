package netgloo.controllers;

import netgloo.Application;
import netgloo.models.Book;
import netgloo.models.Inventory;
import netgloo.models.InventoryDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InventoryController {

    @Autowired
    private InventoryDao inventoryDao;
    private Inventory inventory;

    private static final Logger logger = LogManager.getLogger(Application.class);

    public String create(Book book){
        try {
            inventory = new Inventory(1, book);
            inventoryDao.create(inventory);
        }catch (Exception ex){
            logger.error("not created inventory. {}",ex.getMessage());
            return "Error adding the book in inventory:"+ex.toString();
        }
        logger.info("successfully created inventory.");
        return "Book successfully add in inventory";
    }
    public String countUp(List<Book> book){
        try {
            inventory = inventoryDao.getByInventory(book);
            inventoryDao.countUp(inventory);
        }catch (Exception ex){
            logger.error("inventory count up not successfully {}", ex.getMessage());
            return "Count not upped:"+ex.toString();
        }
        logger.info("inventory count up successfully");
        return "Count successfully upped";
    }

    @PostMapping(value = "/inventory/increase-by-id/{id}")
    public String countUpId(@PathVariable int id){
        try{
            inventory = inventoryDao.getById(id);
            inventoryDao.countUp(inventory);
        }catch (Exception ex){
            logger.error("inventory count up not successfully {}",ex.getMessage());
            return "Count not upped:"+ex.toString();
        }
        logger.info("Count successfully increase id: {}",id);
        return "Count successfully increase";
    }

    @PostMapping(value = "/inventory/reduce-by-id/{id}")
    public String countDownId(@PathVariable int id){
        try{
            inventory = inventoryDao.getById(id);
            if(inventory.getCount()>0){
                inventoryDao.countDown(inventory);
            }
            else{
                return "This book nothing in inventory";
            }
        }catch (Exception ex){
            logger.error("inventory count down not successfully {}",ex.getMessage());
            return "Count not upped:"+ex.toString();
        }
        logger.info("Count successfully reduce id: {}",id);
        return "Count successfully reduce";
    }

}
