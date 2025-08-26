package ku.shop;

import java.util.HashMap;
import java.util.Map;

public class ProductCatalog {

    private Map<String, Product> products;

    public ProductCatalog() {
        products = new HashMap<>();
    }

    public void addProduct(String name, double price, int stock) {
        products.put(name, new Product(name, price, stock));
    }

    public Product getProduct(String name) {
        return products.get(name);
    }

    public boolean hasEnoughStock(String name, int qty) {
        Product p = products.get(name);
        if (p == null) return false;
        return p.getStock() >= qty;
    }

    public void deductStock(String name, int qty) {
        Product p = products.get(name);
        if (p == null) {
            throw new IllegalArgumentException("No such product: " + name);
        }
        if (p.getStock() < qty) {
            throw new OutOfStockException(name, qty, p.getStock());
        }
        p.setStock(p.getStock() - qty);
    }

}

