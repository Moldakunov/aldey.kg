package kg.aldey.model.products;

import javax.validation.constraints.NotBlank;

public class SearchText {

    @NotBlank(message = "Search text can't be empty")
    private String text;

    @Override
    public String toString() {
        return "SearchText{" +
                "searchText='" + text + '\'' +
                '}';
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
