package kg.aldey.model;

public class UserForRegister {

    private String userName;
    private String email;
    private String password;
    private String phone;
    private int storeId;

    public UserForRegister() {
    }

    public UserForRegister(String userName, String email, String password, String phone, int storeId) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.storeId = storeId;
    }

    public UserForRegister(String userName, String password, int storeId) {
        this.userName = userName;
        this.password = password;
        this.storeId = storeId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", storeId=" + storeId +
                '}';
    }
}
