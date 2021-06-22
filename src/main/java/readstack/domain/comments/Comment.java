package readstack.domain.comments;

import java.time.LocalDateTime;

public class Comment {
    private Integer userId;
    private Integer discoveryId;
    private LocalDateTime dateAdded;
    private String description;

    public Comment(Integer userId, Integer discoveryId, LocalDateTime dateAdded, String description) {
        this.userId = userId;
        this.discoveryId = discoveryId;
        this.dateAdded = dateAdded;
        this.description = description;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getDiscoveryId() {
        return discoveryId;
    }

    public LocalDateTime getDateAdded() {
        return dateAdded;
    }

    public String getDescription() {
        return description;
    }
}
