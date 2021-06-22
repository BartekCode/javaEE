package readstack.domain.favDiscovery;

public class FavDiscovery {
    private int discoveryId;
    private int userId;

    public FavDiscovery(int discoveryId, int userId) {
        this.discoveryId = discoveryId;
        this.userId = userId;
    }

    public int getDiscoveryId() {
        return discoveryId;
    }

    public int getUserId() {
        return userId;
    }
}
