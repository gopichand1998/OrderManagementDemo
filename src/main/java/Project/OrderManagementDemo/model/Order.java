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



    public Order() {
    private String status;

public String getStatus() {
    return status;
}

public void setStatus(String status) {
    this.status = status;
}
}

    public Order(String orderDescription, Customer customer, List<ShoppingCart> cartItems, LocalDateTime dateAt) {
        this.orderDescription = orderDescription;
        this.customer = customer;
        this.cartItems = cartItems;
        this.dateAt = dateAt;
    private String status;

public String getStatus() {
    return status;
}

public void setStatus(String status) {
    this.status = status;
}
}


    public LocalDateTime getDateAt() {
        return dateAt;
    private String status;

public String getStatus() {
    return status;
}

public void setStatus(String status) {
    this.status = status;
}
}

    public void setDateAt(LocalDateTime dateAt) {
        this.dateAt = dateAt;
    private String status;

public String getStatus() {
    return status;
}

public void setStatus(String status) {
    this.status = status;
}
}

    public int getId() {
        return id;
    private String status;

public String getStatus() {
    return status;
}

public void setStatus(String status) {
    this.status = status;
}
}

    public void setId(int id) {
        this.id = id;
    private String status;

public String getStatus() {
    return status;
}

public void setStatus(String status) {
    this.status = status;
}
}



    public String getOrderDescription() {
        return orderDescription;
    private String status;

public String getStatus() {
    return status;
}

public void setStatus(String status) {
    this.status = status;
}
}

    public void setOrderDescription(String orderDescription) {
        this.orderDescription = orderDescription;
    private String status;

public String getStatus() {
    return status;
}

public void setStatus(String status) {
    this.status = status;
}
}

    public Customer getCustomer() {
        return customer;
    private String status;

public String getStatus() {
    return status;
}

public void setStatus(String status) {
    this.status = status;
}
}

    public void setCustomer(Customer customer) {
        this.customer = customer;
    private String status;

public String getStatus() {
    return status;
}

public void setStatus(String status) {
    this.status = status;
}
}

    public List<ShoppingCart> getCartItems() {
        return cartItems;
    private String status;

public String getStatus() {
    return status;
}

public void setStatus(String status) {
    this.status = status;
}
}

    public void setCartItems(List<ShoppingCart> cartItems) {
        this.cartItems = cartItems;
    private String status;

public String getStatus() {
    return status;
}

public void setStatus(String status) {
    this.status = status;
}
}
private String status;

public String getStatus() {
    return status;
}

public void setStatus(String status) {
    this.status = status;
}
}
