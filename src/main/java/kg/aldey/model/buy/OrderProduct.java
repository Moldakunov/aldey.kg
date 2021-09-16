package kg.aldey.model.buy;

public class OrderProduct {

    private int productId;
    private int count;

    public OrderProduct() {
    }

    public OrderProduct(int productId, int count) {
        this.productId = productId;
        this.count = count;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "OrderProduct{" +
                "productId=" + productId +
                ", count=" + count +
                '}';
    }
}
