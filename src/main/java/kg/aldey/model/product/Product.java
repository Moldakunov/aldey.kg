package kg.aldey.model.product;

public class Product {
    private int id;
    private int localCatLevel1;
    private int localCatLevel2;
    private int brandId;
    private String seoUrl;
    private String nameShort;
    private String name;
    private double price;
    private double discountPrice;
    private String discountPercent;
    private int status;
    private int qty;
    private int globalCategoryId;
    private int sellQty;
    // @DateTimeFormat(pattern = "dd.mm.yyyy")
    private String datePost;
    // @DateTimeFormat(pattern = "dd.mm.yyyy")
    private String dateNew;
    private String storeName;
    private int storeId;
    private String bullet1;
    private String bullet2;
    private String bullet3;
    private String bullet4;
    private String bullet5;
    private String imageUrl;
    private String shortDescription;
    private String descriptionLong;
    private String keyWords;
    private byte rating;

    public Product() {
    }

    public Product(int id) {
        this.id = id;
    }

    public Product(int id, int localCatLevel1, int localCatLevel2, int brandId, String seoUrl, String nameShort, String name, double price, double discountPrice, String discountPercent, int status, int qty, int globalCategoryId, int sellQty, String datePost, String dateNew, String storeName, int storeId, String bullet1, String bullet2, String bullet3, String bullet4, String bullet5, String imageUrl, String shortDescription, String descriptionLong, String keyWords, byte rating) {
        this.id = id;
        this.localCatLevel1 = localCatLevel1;
        this.localCatLevel2 = localCatLevel2;
        this.brandId = brandId;
        this.seoUrl = seoUrl;
        this.nameShort = nameShort;
        this.name = name;
        this.price = price;
        this.discountPrice = discountPrice;
        this.discountPercent = discountPercent;
        this.status = status;
        this.qty = qty;
        this.globalCategoryId = globalCategoryId;
        this.sellQty = sellQty;
        this.datePost = datePost;
        this.dateNew = dateNew;
        this.storeName = storeName;
        this.storeId = storeId;
        this.bullet1 = bullet1;
        this.bullet2 = bullet2;
        this.bullet3 = bullet3;
        this.bullet4 = bullet4;
        this.bullet5 = bullet5;
        this.imageUrl = imageUrl;
        this.shortDescription = shortDescription;
        this.descriptionLong = descriptionLong;
        this.keyWords = keyWords;
        this.rating = rating;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLocalCatLevel1() {
        return localCatLevel1;
    }

    public void setLocalCatLevel1(int localCatLevel1) {
        this.localCatLevel1 = localCatLevel1;
    }

    public int getLocalCatLevel2() {
        return localCatLevel2;
    }

    public void setLocalCatLevel2(int localCatLevel2) {
        this.localCatLevel2 = localCatLevel2;
    }

    public int getBrandId() {
        return brandId;
    }

    public void setBrandId(int brandId) {
        this.brandId = brandId;
    }

    public String getSeoUrl() {
        return seoUrl;
    }

    public void setSeoUrl(String seoUrl) {
        this.seoUrl = seoUrl;
    }

    public String getNameShort() {
        return nameShort;
    }

    public void setNameShort(String nameShort) {
        this.nameShort = nameShort;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscountPrice() {
        return discountPrice;
    }

    public void setDiscountPrice(double discountPrice) {
        this.discountPrice = discountPrice;
    }

    public String getDiscountPercent() {
        return discountPercent;
    }

    public void setDiscountPercent(String discountPercent) {
        this.discountPercent = discountPercent;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getGlobalCategoryId() {
        return globalCategoryId;
    }

    public void setGlobalCategoryId(int globalCategoryId) {
        this.globalCategoryId = globalCategoryId;
    }

    public int getSellQty() {
        return sellQty;
    }

    public void setSellQty(int sellQty) {
        this.sellQty = sellQty;
    }

    public String getDatePost() {
        return datePost;
    }

    public void setDatePost(String datePost) {
        this.datePost = datePost;
    }

    public String getDateNew() {
        return dateNew;
    }

    public void setDateNew(String dateNew) {
        this.dateNew = dateNew;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getBullet1() {
        return bullet1;
    }

    public void setBullet1(String bullet1) {
        this.bullet1 = bullet1;
    }

    public String getBullet2() {
        return bullet2;
    }

    public void setBullet2(String bullet2) {
        this.bullet2 = bullet2;
    }

    public String getBullet3() {
        return bullet3;
    }

    public void setBullet3(String bullet3) {
        this.bullet3 = bullet3;
    }

    public String getBullet4() {
        return bullet4;
    }

    public void setBullet4(String bullet4) {
        this.bullet4 = bullet4;
    }

    public String getBullet5() {
        return bullet5;
    }

    public void setBullet5(String bullet5) {
        this.bullet5 = bullet5;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getDescriptionLong() {
        return descriptionLong;
    }

    public void setDescriptionLong(String descriptionLong) {
        this.descriptionLong = descriptionLong;
    }

    public String getKeyWords() {
        return keyWords;
    }

    public void setKeyWords(String keyWords) {
        this.keyWords = keyWords;
    }

    public byte getRating() {
        return rating;
    }

    public void setRating(byte rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", localCatLevel1=" + localCatLevel1 +
                ", localCatLevel2=" + localCatLevel2 +
                ", brandId=" + brandId +
                ", seoUrl='" + seoUrl + '\'' +
                ", nameShort='" + nameShort + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", discountPrice=" + discountPrice +
                ", discountPercent='" + discountPercent + '\'' +
                ", status=" + status +
                ", qty=" + qty +
                ", globalCategoryId=" + globalCategoryId +
                ", sellQty=" + sellQty +
                ", datePost='" + datePost + '\'' +
                ", dateNew='" + dateNew + '\'' +
                ", storeName='" + storeName + '\'' +
                ", storeId=" + storeId +
                ", bullet1='" + bullet1 + '\'' +
                ", bullet2='" + bullet2 + '\'' +
                ", bullet3='" + bullet3 + '\'' +
                ", bullet4='" + bullet4 + '\'' +
                ", bullet5='" + bullet5 + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", descriptionLong='" + descriptionLong + '\'' +
                ", keyWords='" + keyWords + '\'' +
                ", rating=" + rating +
                '}';
    }
}
