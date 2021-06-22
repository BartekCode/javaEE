package readstack.common;

import readstack.config.DataSourceProvider;

import javax.naming.NamingException;
import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.SQLException;

public class BaseDao {
    private final DataSource dataSource;

    public BaseDao(){
        try {
            this.dataSource = DataSourceProvider.getDataSource();
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection(){
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}//Na początek klasy DAO. Ich część wspólna dotyczy obiektu DataSource i pobierania z niego nowych połączeń.
// Możemy to łatwo wyeliminować, definiując klasę nadrzędną BaseDao, po której będziemy dziedziczyli.
