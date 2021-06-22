package readstack.domain.api;

import readstack.domain.comments.Comment;
import readstack.domain.discovery.DiscoveryDao;
import readstack.domain.favDiscovery.FavDiscoveriesDao;
import readstack.domain.favDiscovery.FavDiscovery;
import readstack.domain.user.UserDao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FavDiscoveryService {
    private final FavDiscoveriesDao favDiscoveriesDao = new FavDiscoveriesDao();
    private final DiscoveryService discoveryService = new DiscoveryService();
    private final DiscoveryDao discoveryDao = new DiscoveryDao();
    private final FavDiscMapper favDiscMapper = new FavDiscMapper();


    public void addFavDisc(FavDiscoveryInfo favDiscoveryInfo) throws SQLException {
        FavDiscovery favDiscovery = FavDiscMapper.mapRow(favDiscoveryInfo);
        favDiscoveriesDao.saveFav(favDiscovery);
    }

    //metoda zwracająca ulubione znaleziska
    public List<DiscoveryBasicInfo> findFavUserDisc(int userId) {
        List<FavDiscoveryInfo> favDiscoveryByUserId = favDiscoveriesDao.findFavDiscoveryByUserId(userId)
                .stream().map(favDiscMapper::map)
                .collect(Collectors.toList());

        List<DiscoveryBasicInfo> specUserFavDiscoveries = new ArrayList<>();
        for (FavDiscoveryInfo discId : favDiscoveryByUserId) {
            int discoveryId = discId.getDiscoveryId();
            DiscoveryBasicInfo byDiscByDiscId = discoveryService.findByDiscByDiscId(discoveryId)
                    .orElseThrow();
            specUserFavDiscoveries.add(byDiscByDiscId);
        }
        return specUserFavDiscoveries;
    }

    private static class FavDiscMapper {
        private static final UserDao userDao = new UserDao();

        private static FavDiscovery mapRow(FavDiscoveryInfo resultSet) throws SQLException {
            int discovery_id = resultSet.getDiscoveryId();
            int user_id = resultSet.getUserId();
            return new FavDiscovery(discovery_id, user_id);
        }

        FavDiscoveryInfo map(FavDiscovery favDiscovery){
            return new FavDiscoveryInfo(favDiscovery.getDiscoveryId(), favDiscovery.getUserId());
        }

    }
}