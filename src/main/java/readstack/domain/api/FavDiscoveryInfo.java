package readstack.domain.api;

public class FavDiscoveryInfo {
    private int discoveryId;
    private int userId;

    public FavDiscoveryInfo(int discoveryId, int userId) {
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
