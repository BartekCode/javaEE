package readstack.domain.vote;

import readstack.common.BaseDao;
import readstack.domain.discovery.Discovery;
import readstack.domain.user.User;

import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class VoteDao extends BaseDao {

    public void save(Vote vote) {
        final String sql = """
                INSERT INTO
                vote (user_id, discovery_id, type, date_added)
                VALUES
                (?,?,?,?)
                ON DUPLICATE KEY UPDATE
                type = ?
                """;
//ON DUPLICATE KEY. Takie zapytanie sprawi, że jeżeli będziemy zapisywali głos o takim samym kluczu głównym,
// czyli takich samych wartościach discoveryId oraz userId co już istniejący głos, to zostanie on zaktualizowany
// (a bardziej precyzyjnie zostanie zaktualizowana wartość kolumny type danego rekordu). Bez tego musielibyśmy ręcznie sprawdzać,
// czy użytkownik zagłosował już na dane znalezisko, czy nie i w zależności od tego wykonywać zapytanie INSERT lub UPDATE.


        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, vote.getUserId());
            statement.setInt(2, vote.getDiscoveryId());
            statement.setString(3, vote.getType().toString());
            statement.setObject(4, vote.getLocalDateTime());
            statement.setString(5, vote.getType().toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public List<Vote> findByUserId(int userId){
        final String sql ="""
                SELECT
                user_id, discovery_id, type, date_added
                FROM
                vote
                WHERE
                user_id = ?
                """;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            List<Vote> userVotes = new ArrayList<>();
            while (resultSet.next()) {
                Vote vote = mapRow(resultSet);
                userVotes.add(vote);
            }
            return userVotes;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //Zapytanie, które do tego wykorzystamy, to operacja odejmowania na dwóch podzapytaniach
    // (COUNT) zliczających liczbę głosów "na tak" oraz "na nie", oddanych na konkretne znalezisko.
    //W klasie VoteDao dodaję metodę zliczającą głosy na podstawie id znaleziska.
    public int countByDiscoveryId(int discoveryId) {
        final String sql = """
                SELECT
                (SELECT COUNT(discovery_id)FROM vote WHERE discovery_id=? AND type='UP')
                -
                (SELECT COUNT(discovery_id) FROM vote WHERE discovery_id=? AND type='DOWN')
                AS
                vote_count
                """;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, discoveryId);
            statement.setInt(2, discoveryId);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            return resultSet.getInt("vote_count");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private Vote mapRow(ResultSet resultSet) throws SQLException {
        int userId = resultSet.getInt("user_id");
        int discovery_id = resultSet.getInt("discovery_id");
        LocalDateTime date_added =resultSet.getObject("date_added",LocalDateTime.class);
        Vote.Type type = Vote.Type.valueOf(resultSet.getString("type"));
        return new Vote(userId,discovery_id,type,date_added);
    }
}

