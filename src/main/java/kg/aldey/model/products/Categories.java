package kg.aldey.model.products;

import java.util.List;

public class Categories {

    private String name;
    private int id;
    private List<SubCategories> subCategory;
    private String translit;
    private String description;

    public Categories() {
    }

    public Categories(String translit) {
        this.translit = translit;
        this.id = getId();
    }

    public Categories(String name, int id, String description) {
        this.name = name;
        this.id = id;
        this.description = description;
    }

    public Categories(String name, int id, List<SubCategories> subCategory, String description) {
        this.name = name;
        this.id = id;
        this.subCategory = subCategory;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<SubCategories> getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(List<SubCategories> subCategory) {
        this.subCategory = subCategory;
    }

    public static String transliterate(String text) {
        char[] abcCyr = {' ', '-', 'а', 'б', 'в', 'г', 'д', 'е', 'ё', 'ж', 'з', 'и', 'й', 'к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ', 'ъ', 'ы', 'ь', 'э', 'ю', 'я', 'А', 'Б', 'В', 'Г', 'Д', 'Е', 'Ё', 'Ж', 'З', 'И', 'Й', 'К', 'Л', 'М', 'Н', 'О', 'П', 'Р', 'С', 'Т', 'У', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ', 'Ъ', 'Ы', 'Ь', 'Э', 'Ю', 'Я', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
        String[] abcLat = {"-", "-", "a", "b", "v", "g", "d", "e", "e", "zh", "z", "i", "y", "k", "l", "m", "n", "o", "p", "r", "s", "t", "u", "f", "h", "ts", "ch", "sh", "sch", "", "y", "", "e", "ju", "ja", "A", "B", "V", "G", "D", "E", "E", "Zh", "Z", "I", "Y", "K", "L", "M", "N", "O", "P", "R", "S", "T", "U", "F", "H", "Ts", "Ch", "Sh", "Sch", "", "Y", "", "E", "Ju", "Ja", "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            for (int x = 0; x < abcCyr.length; x++)
                if (text.charAt(i) == abcCyr[x]) {
                    builder.append(abcLat[x].toLowerCase());
                }
        }
        return builder.toString();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTranslit() {
        return translit;
    }

    public void setTranslit(String translit) {
        this.translit = translit;
    }

    @Override
    public String toString() {
        return "Categories{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", subCategory=" + subCategory +
                ", translit='" + translit + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
