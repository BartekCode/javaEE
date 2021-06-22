package readstack.domain.user;

import jakarta.servlet.http.HttpSession;
import readstack.common.BaseDao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;

public class UserDao extends BaseDao {


//W warstwie DAO potrzebujemy na razie tylko jednej metody, która zapisze obiekt w bazie danych. Do pola id dodałem setter,
// ponieważ będziemy chcieli ustawić to pole po zapisaniu obiektu w bazie danych.
    public void save(User user) {
        saveUser(user);
        saveUserRole(user);
    }

    private void saveUser(User user) {
        final String sql = """
                INSERT INTO
                user(username, email, password, registration_date)
                VALUES
                (?,?,?,?)
                """;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getUsername()); //1 kolumna i pobieramy username z user
            statement.setString(2, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setObject(4, user.getRegistrationDate());
            statement.executeUpdate(); // updatujemy baze
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1)); //1 kolumna
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void saveUserRole(User user) {
        final String sql = """
                INSERT INTO
                user_role(username)
                VALUES
                (?)
                """;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getUsername());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<User> findByUsername(String username) {
        final String sql = """
                SELECT
                id,username,email,password,registration_date
                FROM
                user
                WHERE
                username=?
               
                """;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(mapRow(resultSet));

            }else
                return Optional.empty();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    //W klasie UserDao potrzebna będzie nam metoda do wyszukiwania użytkownika na podstawie id.
    public Optional<User> findById(int id) {
        final String sql = """
                SELECT
                id,username,email,password,registration_date
                FROM
                user
                WHERE
                id=?
                """;
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(mapRow(resultSet)); //zwaracamy zmapowanego usera
            } else {
                return Optional.empty();//albo pusty optional
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private User mapRow(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String username = resultSet.getString("username");
        String email = resultSet.getString("email");
        String password = resultSet.getString("password");
        LocalDateTime registration_date = resultSet.getObject("registration_date", LocalDateTime.class);
        return new User(id,username,email,password,registration_date);
    }
}
