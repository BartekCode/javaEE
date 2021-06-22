package readstack.domain.category;

import readstack.common.BaseDao;
import readstack.config.DataSourceProvider;

import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryDao  extends BaseDao {



    public List<Category> findAll () {
        final String sql = """
                SELECT
                id, name , description
                FROM
                category
                """;
       // try(Connection connection = dataSource.getConnection();
        try (Connection connection = getConnection();
             Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            List<Category> allCategories = new ArrayList<>();
            while (resultSet.next()) {
                Category category = mapRow(resultSet);
                allCategories.add(category);
            }
            return allCategories;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Category mapRow(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        return new Category(id,name,description);
    }

    public Optional<Category> finById(int categoryId){
        final String sql = """
                SELECT
                id, name, description
                FROM
                category
                WHERE
                id=?
                """;
//      try(Connection connection = dataSource.getConnection();
        try(Connection connection = getConnection();
        PreparedStatement statement = connection.prepareStatement(sql)){
        statement.setInt(1 ,categoryId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()){
            Category category = mapRow(resultSet);
            return Optional.of(category);
        }else {
            return Optional.empty();
        }

    }catch (SQLException e){
          throw new RuntimeException(e);
      }
}
    }
