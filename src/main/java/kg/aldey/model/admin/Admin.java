package kg.aldey.model.admin;

import javax.validation.constraints.NotBlank;

public class Admin {

    @NotBlank(message = "Name can't be empty")
    private String name;
    @NotBlank(message = "Password can't be empty")
    private String password;

    public Admin() {
        this.name = "admin";
        this.password = "yBj56mGHfe5hnf4";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
