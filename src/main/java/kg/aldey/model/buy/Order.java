package kg.aldey.model.buy;

import java.util.List;

public class Order {

    private int userId;
    private String nickName;
    private String phone;
    private String address;
    private List<OrderProduct> ids;

    public Order() {
        this.userId = 0;
    }

    public Order(String nickName, String phone, String address, List<OrderProduct> ids) {
        this.userId = 0;
        this.nickName = nickName;
        this.phone = phone;
        this.address = address;
        this.ids = ids;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<OrderProduct> getIds() {
        return ids;
    }

    public void setIds(List<OrderProduct> ids) {
        this.ids = ids;
    }

    @Override
    public String toString() {
        return "Order{" +
                "userId=" + userId +
                ", nickName='" + nickName + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", ids=" + ids.toString() +
                '}';
    }
}
