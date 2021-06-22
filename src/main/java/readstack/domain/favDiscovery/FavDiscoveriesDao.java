package readstack.domain.favDiscovery;

import readstack.common.BaseDao;
import readstack.domain.discovery.Discovery;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class FavDiscoveriesDao extends BaseDao {
//moze zrobic tablice tylko z FAV ktora bedzie miala FAV id userID discID

    public void saveFav(FavDiscovery favDiscovery) { //zapisanie znaleziska
        final String sql = """
                INSERT INTO
                favdiscovery(discovery_id, user_id)
                VALUES
                (?,?)
                """;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, favDiscovery.getDiscoveryId());
            statement.setInt(2, favDiscovery.getUserId());
            statement.executeUpdate();//upadate
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<FavDiscovery> findFavDiscoveryByUserId(int userId) {
        final String sql = """
                SELECT
                discovery_id, user_id
                FROM
                favdiscovery
                WHERE
                user_id= ?
                """;
        //      try(Connection connection = dataSource.getConnection();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            List<FavDiscovery> userFavDiscoveries = new ArrayList<>();
            while (resultSet.next()) {
                FavDiscovery discovery = mapRow(resultSet);
                userFavDiscoveries.add(discovery);
            }
            return userFavDiscoveries;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static FavDiscovery mapRow(ResultSet resultSet) throws SQLException {
        int discovery_id = resultSet.getInt("discovery_id");
        int user_id = resultSet.getInt("user_id");
        return new FavDiscovery(discovery_id, user_id);
    }


}
