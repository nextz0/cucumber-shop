package ku.shop;

public class OutOfStockException extends RuntimeException {
    public OutOfStockException(String name, int req, int have) {
        super("Not enough stock for " + name + " (requested " + req + ", available " + have + ")");
    }
}