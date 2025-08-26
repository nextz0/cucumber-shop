package ku.shop;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.*;

public class BuyStepdefs {

    private ProductCatalog catalog;
    private Order order;
    private Exception thrown;

    @Given("the store is ready to service customers")
    public void the_store_is_ready_to_service_customers() {
        catalog = new ProductCatalog();
        order = new Order();
        thrown = null;
    }

    @Given("a product {string} with price {float} and stock of {int} exists")
    public void a_product_exists(String name, double price, int stock) {
        catalog.addProduct(name, price, stock);
    }

    @When("I buy {string} with quantity {int}")
    public void i_buy_with_quantity(String name, int quantity) {
        order.addItem(name, quantity, catalog);
    }

    @When("I try to buy {string} with quantity {int}")
    public void i_try_to_buy_with_quantity(String name, int quantity) {
        try {
            order.addItem(name, quantity, catalog);
        } catch (Exception e) {
            thrown = e;
        }
    }

    @Then("total should be {float}")
    public void total_should_be(double total) {
        assertEquals(total, order.getTotal());
    }

    @Then("I should see an out of stock error for {string}")
    public void i_should_see_out_of_stock_error_for(String name) {
        assertNotNull(thrown, "Expected an exception but none was thrown");
        assertTrue(thrown instanceof OutOfStockException,
                "Expected OutOfStockException but got: " + thrown.getClass().getSimpleName());
    }

    @Then("stock for {string} should be {int}")
    public void stock_for_should_be(String name, int expected) {
        Product p = catalog.getProduct(name);
        assertNotNull(p, "Product not found: " + name);
        assertEquals(expected, p.getStock());
    }
}