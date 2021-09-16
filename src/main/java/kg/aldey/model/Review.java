package kg.aldey.model;

public class Review {

    private String text;
    private int productId;
    private int rating;
    private String userName;
    private String date;

    public Review() {
    }

    public Review(String text, int productId, int rating, String userName) {
        this.text = text;
        this.productId = productId;
        this.rating = rating;
        this.userName = userName;
    }

    public Review(String text, int productId, int rating, String userName, String date) {
        this.text = text;
        this.productId = productId;
        this.rating = rating;
        this.userName = userName;
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Review{" +
                "text='" + text + '\'' +
                ", productid=" + productId +
                ", rating=" + rating +
                ", userName='" + userName + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}
