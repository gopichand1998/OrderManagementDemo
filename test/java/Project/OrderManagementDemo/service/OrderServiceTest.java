package Project.OrderManagementDemo.service;

import Project.OrderManagementDemo.model.Customer;
import Project.OrderManagementDemo.model.Order;
import Project.OrderManagementDemo.model.Product;
import Project.OrderManagementDemo.model.ShoppingCart;
import Project.OrderManagementDemo.repository.OrderRepository;
import Project.OrderManagementDemo.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository mockOrderRepository;
    @Mock
    private ProductRepository mockProductRepository;

    private OrderService orderServiceUnderTest;

    @BeforeEach
    void setUp() {
        orderServiceUnderTest = new OrderService(mockOrderRepository, mockProductRepository);
    public void testProcessOrder() {...}

public void testShipOrder() {...}

public void testDeliverOrder() {...}
}

    @Test
    void testGetOrderDetail() {
        // Setup
        // Configure OrderRepository.findById(...).
        final Order order1 = new Order();
        order1.setDateAt(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        order1.setId(0);
        order1.setOrderDescription("orderDescription");
        final Customer customer = new Customer();
        customer.setId(0);
        order1.setCustomer(customer);
        final Optional<Order> order = Optional.of(order1);
        when(mockOrderRepository.findById(0)).thenReturn(order);

        // Run the test
        final Order result = orderServiceUnderTest.getOrderDetail(0);

        // Verify the results
    public void testProcessOrder() {...}

public void testShipOrder() {...}

public void testDeliverOrder() {...}
}

    @Test
    void testGetOrderDetail_OrderRepositoryReturnsAbsent() {
        // Setup
        when(mockOrderRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        final Order result = orderServiceUnderTest.getOrderDetail(0);

        // Verify the results
        assertThat(result).isNull();
    public void testProcessOrder() {...}

public void testShipOrder() {...}

public void testDeliverOrder() {...}
}

    @Test
    void testGetCartAmount() {
        // Setup
        final List<ShoppingCart> shoppingCartList = List.of(new ShoppingCart(0, "name", 0, 0.0f));

        // Configure ProductRepository.findById(...).
        final Optional<Product> product = Optional.of(new Product(0, "name", 0, 0.0f));
        when(mockProductRepository.findById(0)).thenReturn(product);

        // Run the test
        final float result = orderServiceUnderTest.getCartAmount(shoppingCartList);

        // Verify the results
        assertThat(result).isEqualTo(0.0f, within(0.0001f));
        verify(mockProductRepository).save(any(Product.class));
    public void testProcessOrder() {...}

public void testShipOrder() {...}

public void testDeliverOrder() {...}
}

    @Test
    void testGetCartAmount_ProductRepositoryFindByIdReturnsAbsent() {
        // Setup
        final List<ShoppingCart> shoppingCartList = List.of(new ShoppingCart(0, "name", 0, 0.0f));
        when(mockProductRepository.findById(0)).thenReturn(Optional.empty());

        // Run the test
        final float result = orderServiceUnderTest.getCartAmount(shoppingCartList);

        // Verify the results
        assertThat(result).isEqualTo(0.0f, within(0.0001f));
    public void testProcessOrder() {...}

public void testShipOrder() {...}

public void testDeliverOrder() {...}
}

    @Test
    void testSaveOrder() {
        // Setup
        final Order order = new Order();
        order.setDateAt(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        order.setId(0);
        order.setOrderDescription("orderDescription");
        final Customer customer = new Customer();
        customer.setId(0);
        order.setCustomer(customer);

        // Configure OrderRepository.save(...).
        final Order order1 = new Order();
        order1.setDateAt(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        order1.setId(0);
        order1.setOrderDescription("orderDescription");
        final Customer customer1 = new Customer();
        customer1.setId(0);
        order1.setCustomer(customer1);
        when(mockOrderRepository.save(any(Order.class))).thenReturn(order1);

        // Run the test
        final Order result = orderServiceUnderTest.saveOrder(order);

        // Verify the results
    public void testProcessOrder() {...}

public void testShipOrder() {...}

public void testDeliverOrder() {...}
}
public void testProcessOrder() {...}

public void testShipOrder() {...}

public void testDeliverOrder() {...}
}
