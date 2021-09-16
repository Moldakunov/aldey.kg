package kg.aldey.model;

public class UserForForgotPassword {

    String MailsList;
    int storeId;

    public UserForForgotPassword(String MailsList, int storeId) {
        this.MailsList = MailsList;
        this.storeId = storeId;
    }

    public String getMailsList() {
        return MailsList;
    }

    public void setMailsList(String MailsList) {
        this.MailsList = MailsList;
    }

    public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }

    @Override
    public String toString() {
        return "UserForForgotPassword{" +
                "MailsList='" + MailsList + '\'' +
                ", storeId=" + storeId +
                '}';
    }
}
