package readstack.domain.api;

public class DiscoveryComment {
    private String author;
    private String description;
    private Integer discoveryId;

    public DiscoveryComment(String author, String description, Integer discoveryId) {
        this.author = author;
        this.description = description;
        this.discoveryId = discoveryId;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public Integer getDiscoveryId() {
        return discoveryId;
    }
}
