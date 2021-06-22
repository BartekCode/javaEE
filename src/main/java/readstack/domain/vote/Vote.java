package readstack.domain.vote;

import java.lang.reflect.Type;
import java.time.LocalDateTime;

public class Vote {
    private Integer userId;
    private Integer discoveryId;
    private Type type;
    private LocalDateTime localDateTime;

    public Integer getUserId() {
        return userId;
    }

    public Integer getDiscoveryId() {
        return discoveryId;
    }

    public Type getType() {
        return type;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public Vote(Integer userId, Integer discoveryId, Type type, LocalDateTime localDateTime) {
        this.userId = userId;
        this.discoveryId = discoveryId;
        this.type = type;
        this.localDateTime = localDateTime;
    }

    public enum Type{
        UP, DOWN;
    }
}
