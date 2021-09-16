package kg.aldey.model.products;

public class SubCategories {

    private String name;
    private int id;
    private String translit;
    private int parentId;
    private String description;

    public SubCategories() {
    }

    public SubCategories(String translit) {
        this.translit = translit;
        this.id = getId();
    }

    public SubCategories(String name, int id, int parentId, String description) {
        this.name = name;
        this.id = id;
        this.parentId = parentId;
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

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getTranslit() {
        return translit;
    }

    public void setTranslit(String translit) {
        this.translit = translit;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "SubCategories{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", translit='" + translit + '\'' +
                ", parentId=" + parentId +
                ", description='" + description + '\'' +
                '}';
    }
}
