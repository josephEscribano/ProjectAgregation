package dao;


import configuration.JDBCproperties;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class DBConPool {
    private static DBConPool dbConnectionPool = null;
    private BasicDataSource pool = null;

    private DBConPool(){
        super();
        pool = this.getPool();
    }

    public static DBConPool getInstance(){
        if (dbConnectionPool == null){
            dbConnectionPool = new DBConPool();
        }
        return dbConnectionPool;
    }

    private BasicDataSource getPool() {
        JDBCproperties properties = new JDBCproperties();
        BasicDataSource basicDataSource = new BasicDataSource();

        basicDataSource.setUsername(properties.getUserName());
        basicDataSource.setPassword(properties.getPassword());
        basicDataSource.setUrl(properties.getUrlDB());


        return basicDataSource;
    }

    public Connection getConnection() throws SQLException {
        return pool.getConnection();
    }
}
