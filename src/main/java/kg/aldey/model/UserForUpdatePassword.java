package kg.aldey.model;

public class UserForUpdatePassword {

    int userId;
    String oldPassword;
    String newPassword;

    public UserForUpdatePassword(int userId, String oldPassword, String newPassword) {
        this.userId = userId;
        this.oldPassword = oldPassword;
        this.newPassword = newPassword;
    }

    public UserForUpdatePassword() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    @Override
    public String toString() {
        return "UserForUpdatePassword{" +
                "userId=" + userId +
                ", oldPassword='" + oldPassword + '\'' +
                ", newPassword='" + newPassword + '\'' +
                '}';
    }
}
