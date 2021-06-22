package readstack.domain.discovery;

import readstack.common.BaseDao;
import readstack.config.DataSourceProvider;
import readstack.domain.api.DiscoveryBasicInfo;
import readstack.domain.api.DiscoveryService;
import readstack.domain.category.Category;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class DiscoveryDao extends BaseDao {


    //metoda, która zwróci listę wszystkich znalezisk
    public List<Discovery> findAll() {
        //Dzięki Text Blocks (potrójny cudzysłów) zapytania SQL można zapisywać w wygodny i czytelny sposób
        final String sql = """
                SELECT
                    id, title, url, description, date_added, category_id, user_id
                FROM
                    discovery d
                    ORDER BY
                    id
                """;
        //try(Connection connection = dataSource.getConnection();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            List<Discovery> allDiscoveries = new ArrayList<>();
            while (resultSet.next()) {
                Discovery discovery = mapRow(resultSet);
                allDiscoveries.add(discovery);
            }
            return allDiscoveries;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Discovery> findByCategory(int categoryId) {
        final String sql = """
                SELECT
                id, url, title, description, date_added, category_id, user_id
                FROM
                discovery
                WHERE
                category_id= ?
                """;
//      try(Connection connection = dataSource.getConnection();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, categoryId);
            ResultSet resultSet = statement.executeQuery();
            List<Discovery> discoveries = new ArrayList<>();
            while (resultSet.next()) {
                Discovery discovery = mapRow(resultSet);
                discoveries.add(discovery);
            }
            return discoveries;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Discovery> findDiscoveryByUserId(int userId){
        final String sql = """
                SELECT
                id, url, title, description, date_added, category_id, user_id
                FROM
                discovery
                WHERE
                user_id= ?
                """;
//      try(Connection connection = dataSource.getConnection();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            List<Discovery> userDiscoveries = new ArrayList<>();
            while (resultSet.next()) {
                Discovery discovery = mapRow(resultSet);
                userDiscoveries.add(discovery);
            }
            return userDiscoveries;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional <Discovery> findDiscoveryById(int discId){
        final String sql = """
                SELECT
                id, url, title, description, date_added, category_id, user_id
                FROM
                discovery
                WHERE
                id= ?
                """;
//      try(Connection connection = dataSource.getConnection();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, discId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()){
                Discovery discovery = mapRow(resultSet);
                return Optional.of(discovery);
            }else {
                return Optional.empty();
            }

        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }





    //Metoda mapRow() ułatwia zamianę kolejnych rekordów zwróconych z bazy danych na obiekty Javy.
    private static Discovery mapRow(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String title = resultSet.getString("title");
        String url = resultSet.getString("url");
        String description = resultSet.getString("description");
        LocalDateTime date_added = resultSet.getTimestamp("date_added").toLocalDateTime();
        int category_id = resultSet.getInt("category_id");
        int user_id = resultSet.getInt("user_id");
        return new Discovery(id, title, url, description, date_added, category_id, user_id);
    }

    public void save(Discovery discovery) { //zapisanie znaleziska
            final String sql = """
                INSERT INTO
                discovery(title, url, description, date_added, category_id, user_id)
                VALUES
                (?,?,?,?,?,?)
                """;
            try (Connection connection = getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, discovery.getTitle());
                statement.setString(2, discovery.getUrl());
                statement.setString(3, discovery.getDescription());
                statement.setObject(4, discovery.getDateAdded());
                statement.setInt(5, discovery.getCategoryId());
                statement.setInt(6, discovery.getUserId());
                statement.executeUpdate();//upadate
                ResultSet generatedKeys = statement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    discovery.setId(generatedKeys.getInt(1));

                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
    }
}
