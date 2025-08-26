package ku.shop;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private final List<OrderItem> items = new ArrayList<>();
    private final LocalDateTime date = LocalDateTime.now();

    public void addItem(String productName, int quantity, ProductCatalog catalog) {
        if (!catalog.hasEnoughStock(productName, quantity)) {
            Product p = catalog.getProduct(productName);
            int have = (p == null) ? 0 : p.getStock();
            throw new OutOfStockException(productName, quantity, have);
        }
        catalog.deductStock(productName, quantity);
        Product p = catalog.getProduct(productName);
        items.add(new OrderItem(p, quantity));
    }

    public double getTotal() {
        double total = 0;
        for (OrderItem item : items) {
            total += item.getSubtotal();
        }
        return total;
    }
}