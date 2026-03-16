package Project.OrderManagementDemo.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "myorder")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String orderDescription;

    @Column(name="date_reserved")
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="dd-MM-yyyy-HH:mm:ss")
    @NotNull
    private LocalDateTime dateAt;


    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "cust_id" ,referencedColumnName = "id")
    private Customer customer;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, targetEntity = ShoppingCart.class)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private List<ShoppingCart> cartItems;



public Order(String id) {
    if (id == null || id.isEmpty()) {
        throw new IllegalArgumentException("Order ID cannot be null or empty");
    }
    this.id = id;
}

    public Order(String orderDescription, Customer customer, List<ShoppingCart> cartItems, LocalDateTime dateAt) {
        this.orderDescription = orderDescription;
        this.customer = customer;
        this.cartItems = cartItems;
        this.dateAt = dateAt;
    }


    public LocalDateTime getDateAt() {
        return dateAt;
    }

    public void setDateAt(LocalDateTime dateAt) {
        this.dateAt = dateAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getOrderDescription() {
        return orderDescription;
    }

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<ShoppingCart> getCartItems() {
        return cartItems;
    }

    public void setCartItems(List<ShoppingCart> cartItems) {
        this.cartItems = cartItems;
    }
}
