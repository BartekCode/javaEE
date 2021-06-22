package readstack.domain.api;


import java.time.LocalDateTime;
import java.util.List;

//amiast tego wprowadzamy dodatkowy pakiet api, w którym będą się znajdowały klasy usług i DTO.
// Klasy DTO nie muszą mieć w swojej nazwie "Dto", mogą po prostu wskazywać jakie informacje niosą.
// W naszym przypadku na liście znalezisk będą zawarte podstawowe informacje na ich temat, dlatego pierwszą klasę transferu danych nazywam DiscoveryBasicInfo.
//Liczbę głosów powinniśmy zawrzeć w obiektach typu DiscoveryBasicInfo,
// które przekazywane są do szablonów strony głównej oraz kategorii. Dodajemy dodatkowe pole, argument konstruktora i getter.
public class DiscoveryBasicInfo {
    private Integer id;
    private String title;
    private String url;
    private String description;
    private LocalDateTime dateAdded;
    private String author;
    private List<CommentInfo> comments;




    public DiscoveryBasicInfo(Integer id, String title, String url, String description, LocalDateTime dateAdded, String author, List<CommentInfo> comments, int voteCount) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.description = description;
        this.dateAdded = dateAdded;
        this.author = author;
        this.comments = comments;
        this.voteCount = voteCount;
    }

    public List<CommentInfo> getComments() {
        return comments;
    }

    ////Liczbę głosów powinniśmy zawrzeć w obiektach typu DiscoveryBasicInfo,
    //// które przekazywane są do szablonów strony głównej oraz kategorii. Dodajemy dodatkowe pole, argument konstruktora i getter.
    private int voteCount;

//    public DiscoveryBasicInfo(Integer id, String title, String url, String description, LocalDateTime dateAdded, String author, int voteCount) {
//        this.id = id;
//        this.title = title;
//        this.url = url;
//        this.description = description;
//        this.dateAdded = dateAdded;
//        this.author = author;
//        this.voteCount = voteCount;
//    }



    public Integer getId() {
        return id;
    }

    public int getVoteCount() {
        return voteCount;
    }

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

//    Informacja o kategorii na liście znalezisk nie jest nam potrzebna, dlatego nie dołączałem jej do klasy DTO.
}
