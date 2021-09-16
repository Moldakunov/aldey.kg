package kg.aldey.model;

public class ProductInOrder {
    private int id;
    private String productName;
    private double price;
    private String serialNumber;
    private String barCode;
    private int orderId;
    private String waranty;
    private int ProductId;
    private String date;
    private double discount;
    private int storeId;
    private int qty;
    private int color;
    private String size;
    private String weight;
    private String imageUrl;
    private String seoUrl;

    public ProductInOrder() {
    }

    public ProductInOrder(int id, String productName, double price, String serialNumber, String barCode, int orderId, String waranty, int productId, String date, double discount, int storeId, int qty, int color, String size, String weight, String imageUrl, String seoUrl) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.serialNumber = serialNumber;
        this.barCode = barCode;
        this.orderId = orderId;
        this.waranty = waranty;
        ProductId = productId;
        this.date = date;
        this.discount = discount;
        this.storeId = storeId;
        this.qty = qty;
        this.color = color;
        this.size = size;
        this.weight = weight;
        this.imageUrl = imageUrl;
        this.seoUrl = seoUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getWaranty() {
        return waranty;
    }

    public void setWaranty(String waranty) {
        this.waranty = waranty;
    }

    public int getProductId() {
        return ProductId;
    }

    public void setProductId(int productId) {
        ProductId = productId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getSeoUrl() {
        return seoUrl;
    }

    public void setSeoUrl(String seoUrl) {
        this.seoUrl = seoUrl;
    }

    @Override
    public String toString() {
        return "ProductInOrder{" +
                "id=" + id +
                ", productName='" + productName + '\'' +
                ", price=" + price +
                ", serialNumber='" + serialNumber + '\'' +
                ", barCode='" + barCode + '\'' +
                ", orderId=" + orderId +
                ", waranty='" + waranty + '\'' +
                ", ProductId=" + ProductId +
                ", date='" + date + '\'' +
                ", discount=" + discount +
                ", storeId=" + storeId +
                ", qty=" + qty +
                ", color=" + color +
                ", size='" + size + '\'' +
                ", weight='" + weight + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", seoUrl='" + seoUrl + '\'' +
                '}';
    }
}
