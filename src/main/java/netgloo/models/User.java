package netgloo.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "userT")
public class User {
  
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;
  
  @NotNull
  private String first_name;
  
  @NotNull
  private String last_name;

  @NotNull
  @DateTimeFormat(pattern = "dd-MM-yyyy")
  private Date register_date;

  @NotNull
  private String address;

  @OneToOne(cascade = CascadeType.ALL, mappedBy = "user")
  @JsonIgnore
  private Cart cart;

  public User(){}

  public User(long id) { 
    this.id = id;
  }

  public User(String first_name, String last_name, Date register_date, String address,Cart cart) {
    this.cart = cart;
    this.first_name = first_name;
    this.last_name = last_name;
    this.address = address;
    this.register_date = register_date;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getFirst_name() {
    return first_name;
  }

  public void setFirst_name(String first_name) {
    this.first_name = first_name;
  }

  public String getLast_name() {
    return last_name;
  }

  public void setLast_name(String last_name) {
    this.last_name = last_name;
  }

  public Date getRegister_date() {
    return register_date;
  }

  public void setRegister_date(Date register_date) {
    this.register_date = register_date;
  }

  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public Cart getCart() {
    return cart;
  }

  public void setCart(Cart cart) {
    this.cart = cart;
  }
}