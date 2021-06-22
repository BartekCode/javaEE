package readstack.domain.api;


//dto do discovery
public class DiscoverySaveRequest {
    private String title;
    private String url;
    private String description;
    private Integer categoryId;

 //W klasie DTO dodałem pole author typu String, a nie userId, ponieważ na poziomie kontrolera możemy pobrać informację
 // o zalogowanym użytkowniku w postaci obiektu Principal, w którym mamy nazwę zalogowanego użytkownika,
    private String author;

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public String getAuthor() {
        return author;
    }

    public DiscoverySaveRequest(String title, String url, String description, Integer categoryId, String author) {
        this.title = title;
        this.url = url;
        this.description = description;
        this.categoryId = categoryId;
        this.author = author;
    }
}
