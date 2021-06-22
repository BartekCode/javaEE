package readstack.domain.api;

public class DiscoveryVote {//DTO
    //Teraz definiujemy klasę DTO o nazwie DiscoveryVote. Podobnie jak np. w przypadku klasy DiscoverySaveRequest
    // definiuję tu pole z nazwą użytkownika, a nie jego id, bo to do nazwy użytkownika mamy dostęp poprzez obiekt Principal.
    private String username;
    private Integer discoveryId;
    private String type;

    public String getUsername() {
        return username;
    }

    public Integer getDiscoveryId() {
        return discoveryId;
    }

    public String getType() {
        return type;
    }

    public DiscoveryVote(String username, Integer discoveryId, String type) {
        this.username = username;
        this.discoveryId = discoveryId;
        this.type = type;
     }
}
