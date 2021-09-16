package kg.aldey.RSS;

public class HTMLRemoverBean {

    public String title;
    public String description;
    public String link;
    public String pubdate;

    @Override
    public String toString() {
        return "HTMLRemoverBean{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", link='" + link + '\'' +
                ", pubdate='" + pubdate + '\'' +
                '}';
    }
}
