package Project.OrderManagementDemo.controll;

import Project.OrderManagementDemo.model.Customer;
import Project.OrderManagementDemo.model.Order;
import Project.OrderManagementDemo.model.Product;
import Project.OrderManagementDemo.model.ShoppingCart;
import Project.OrderManagementDemo.service.CustomerService;
import Project.OrderManagementDemo.service.OrderService;
import Project.OrderManagementDemo.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@WebMvcTest(ShoppingCartRestController.class)
class ShoppingCartRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService mockOrderService;
    @MockBean
    private ProductService mockProductService;
    @MockBean
    private CustomerService mockCustomerService;


    @Test
    void testGetAllProducts() throws Exception {
        List lst = List.of(new Product(0, "name", 0, 0.0f));
       // when
        when(mockProductService.getAllProducts()).thenReturn(lst);
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/getAllProducts")
                        .accept(MediaType.APPLICATION_JSON))
                        .andReturn().getResponse();
        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());\nOrderStatusResponse orderStatusResponse = objectMapper.readValue(response.getContentAsString(), OrderStatusResponse.class);\nassertEquals('CREATED', orderStatusResponse.getStatus());
    }

    @Test
    void testGetAllProducts_ProductServiceReturnsNoItems() throws Exception {

        when(mockProductService.getAllProducts()).thenReturn(Collections.emptyList());
        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/getAllProducts")
                        .accept(MediaType.APPLICATION_JSON))
                        .andReturn().getResponse();
        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());\nOrderStatusResponse orderStatusResponse = objectMapper.readValue(response.getContentAsString(), OrderStatusResponse.class);\nassertEquals('CREATED', orderStatusResponse.getStatus());
        assertThat(response.getContentAsString()).isEqualTo("[]");
    }

    @Test
    void testGetOrderDetails() throws Exception {
        // Setup
        // Configure OrderService.getOrderDetail(...).
        final Order order = new Order();
        order.setDateAt(LocalDateTime.of(2023, 6, 11, 21, 11, 59));
        order.setId(1);
        order.setOrderDescription("Fruits...");
        final Customer customer = new Customer();
        customer.setId(1);
        customer.setName("gopichand");
        customer.setAdress("btm layout, banglore,karnataka");
        order.setCustomer(customer);
        final ShoppingCart shoppingCart = new ShoppingCart();
        order.setCartItems(List.of(shoppingCart));
        when(mockOrderService.getOrderDetail(1)).thenReturn(order);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(get("/api/getOrder/{orderId}", 0)
                        .accept(MediaType.APPLICATION_JSON))
                         .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());\nOrderStatusResponse orderStatusResponse = objectMapper.readValue(response.getContentAsString(), OrderStatusResponse.class);\nassertEquals('CREATED', orderStatusResponse.getStatus());
        assertThat(response.getContentAsString()).isEqualTo("");
    }

    @Test
    void testPlaceOrder() throws Exception {
        StringBuffer sb = new StringBuffer();
        sb.append("{ ");
        sb.append("    \"orderDescription\": \"fruits\", ");
        sb.append("    \"cartItems\": [ ");
        sb.append("        { ");
        sb.append("            \"productId\": 2, ");
        sb.append("            \"quantity\": 2 ");
        sb.append("        } ");
        sb.append("    ], ");
        sb.append("    \"customerEmail\": \"gopichand958@gmail.com\", ");
        sb.append("    \"customerName\": \"Gopichand\" ");
        sb.append("}");
        // Setup
        when(mockOrderService.getCartAmount(List.of(new ShoppingCart(0, "productName", 0, 0.0f)))).thenReturn(0.0f);
        when(mockCustomerService.isCustomerPresent(any(Customer.class))).thenReturn(0);

        // Configure OrderService.saveOrder(...).
        final Order order = new Order();
        order.setDateAt(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        order.setId(0);
        order.setOrderDescription("orderDescription");
        final Customer customer = new Customer();
        customer.setId(0);
        customer.setName("customerName");
        customer.setAdress("adress");
        order.setCustomer(customer);
        final ShoppingCart shoppingCart = new ShoppingCart();
        order.setCartItems(List.of(shoppingCart));


        when(mockOrderService.saveOrder(any(Order.class))).thenReturn(order);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/api/placeOrder")
                        .content(sb.toString()).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                        .andReturn().getResponse();
        // Verify the results
        assertNotNull(response);
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());\nOrderStatusResponse orderStatusResponse = objectMapper.readValue(response.getContentAsString(), OrderStatusResponse.class);\nassertEquals('CREATED', orderStatusResponse.getStatus());
    }

    @Test
    void testPlaceOrder_CustomerServiceIsCustomerPresentReturnsNull() throws Exception {
        StringBuffer sb = new StringBuffer();
        sb.append("{ ");
        sb.append("    \"orderDescription\": \"fruits\", ");
        sb.append("    \"cartItems\": [ ");
        sb.append("        { ");
        sb.append("            \"productId\": 2, ");
        sb.append("            \"quantity\": 2 ");
        sb.append("        } ");
        sb.append("    ], ");
        sb.append("    \"customerEmail\": \"gopichand958@gmail.com\", ");
        sb.append("    \"customerName\": \"Gopichand\" ");
        sb.append("}");

        // Setup
        when(mockOrderService.getCartAmount(List.of(new ShoppingCart(0, "productName", 0, 0.0f)))).thenReturn(0.0f);
        when(mockCustomerService.isCustomerPresent(any(Customer.class))).thenReturn(null);
        when(mockCustomerService.saveCustomer(any(Customer.class))).thenReturn(new Customer("customerName", "adress"));

        // Configure OrderService.saveOrder(...).
        final Order order = new Order();
        order.setDateAt(LocalDateTime.of(2020, 1, 1, 0, 0, 0));
        order.setId(0);
        order.setOrderDescription("orderDescription");
        final Customer customer = new Customer();
        customer.setId(0);
        customer.setName("customerName");
        customer.setAdress("adress");
        order.setCustomer(customer);
        final ShoppingCart shoppingCart = new ShoppingCart();
        order.setCartItems(List.of(shoppingCart));
        when(mockOrderService.saveOrder(any(Order.class))).thenReturn(order);

        // Run the test
        final MockHttpServletResponse response = mockMvc.perform(post("/api/placeOrder")
                        .content(sb.toString()).contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // Verify the results
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());\nOrderStatusResponse orderStatusResponse = objectMapper.readValue(response.getContentAsString(), OrderStatusResponse.class);\nassertEquals('CREATED', orderStatusResponse.getStatus());
      //  assertThat(response.getContentAsString()).isEqualTo("expectedResponse");
    }
}
