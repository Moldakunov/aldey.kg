package kg.aldey.model;

public class UserWithAllInfo {

    private String status;
    private String userName;
    private String email;
    private String address;
    private String nickName;
    private String phone;
    private String id;
    private String countFavorite;

    public UserWithAllInfo() {
    }

    public UserWithAllInfo(String status, String userName, String email, String address, String nickName, String phone, String id, String countFavorite) {
        this.status = status;
        this.userName = userName;
        this.email = email;
        this.address = address;
        this.nickName = nickName;
        this.phone = phone;
        this.id = id;
        this.countFavorite = countFavorite;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountFavorite() {
        return countFavorite;
    }

    public void setCountFavorite(String countFavorite) {
        this.countFavorite = countFavorite;
    }

    @Override
    public String toString() {
        return "UserWithAllInfo{" +
                "status='" + status + '\'' +
                ", userName='" + userName + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", nickName='" + nickName + '\'' +
                ", phone='" + phone + '\'' +
                ", id='" + id + '\'' +
                ", countFavorite='" + countFavorite + '\'' +
                '}';
    }
}
