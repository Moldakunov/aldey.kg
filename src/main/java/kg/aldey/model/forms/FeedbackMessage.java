package kg.aldey.model.forms;

public class FeedbackMessage {


    private String name;
    private String number;
    private String message;

    public FeedbackMessage(){}

    public FeedbackMessage(String name, String number, String message) {
        this.name = name;
        this.number = number;
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "FeedbackMessage{" +
                ", name='" + name + '\'' +
                ", number='" + number + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
