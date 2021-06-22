package readstack.domain.api;

import java.time.LocalDateTime;

public class CommentInfo {
    private String author;
    private String description;
    private Integer discoveryId;
    private LocalDateTime dateAdded;

    public CommentInfo(String author, String description, Integer discoveryId, LocalDateTime dateAdded) {
        this.author = author;
        this.description = description;
        this.discoveryId = discoveryId;
        this.dateAdded = dateAdded;
    }

    public Integer getDiscoveryId() {
        return discoveryId;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }
}
