package kg.aldey.model.forms;

public class RegisterForm {

    private String username;
    private String email;
    private String phoneNumber;
    private String password;
    private String repeatPassword;

    public RegisterForm() {
    }

    public RegisterForm(String username, String email, String phoneNumber, String password, String repeatPassword) {
        this.username = username;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.repeatPassword = repeatPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepeatPassword() {
        return repeatPassword;
    }

    public void setRepeatPassword(String repeatPassword) {
        this.repeatPassword = repeatPassword;
    }

    @Override
    public String toString() {
        return "RegisterForm{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", repeatPassword='" + repeatPassword + '\'' +
                '}';
    }
}
