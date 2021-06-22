package readstack.domain.comments;

import readstack.common.BaseDao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CommentDao extends BaseDao {

    public void save(Comment comment) { //zapisanie commentarza
        final String sql = """
                INSERT INTO
                comments(user_id, discovery_id, description, date_added)
                VALUES
                (?,?,?,?)
                """;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, comment.getUserId());
            statement.setInt(2, comment.getDiscoveryId());
            statement.setString(3, comment.getDescription());
            statement.setObject(4, comment.getDateAdded());
            statement.executeUpdate();//upadate

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Comment> findCommentsByDiscoveryId(int discoveryId){
        final String sql = """
                SELECT
                user_id, discovery_id, description, date_added
                FROM
                comments
                WHERE
                discovery_id= ?
                """;
//      try(Connection connection = dataSource.getConnection();
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, discoveryId);
            ResultSet resultSet = statement.executeQuery();
            List<Comment> allComments = new ArrayList<>();
            while (resultSet.next()) {
                Comment comment = mapRow(resultSet);
                allComments.add(comment);
            }
            return allComments;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Comment mapRow(ResultSet resultSet) throws SQLException {
        int userId = resultSet.getInt("user_id");
        int discId = resultSet.getInt("discovery_id");
        String description = resultSet.getString("description");
        LocalDateTime registration_date = resultSet.getObject("date_added", LocalDateTime.class);
        return new Comment(userId,discId,registration_date,description);
    }
}

