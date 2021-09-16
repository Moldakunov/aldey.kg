package kg.aldey.model;

public class UserForSignin {

    private String userName;
    private String password;
    private int storeId;

    public UserForSignin(String userName, String password, int storeId) {
        this.userName = userName;
        this.password = password;
        this.storeId = storeId;
    }

    public UserForSignin() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    @Override
    public String toString() {
        return "UserForSignin{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", storeId=" + storeId +
                '}';
    }
}
