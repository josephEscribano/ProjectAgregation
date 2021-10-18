package conections;

import configuration.ConfigurationJDBC;
import dao.JdbcDAO;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConection {

    private String db;
    private String user;
    private String password;


    public Connection getConnection() throws SQLException {

        db = ConfigurationJDBC.getInstance().getProperty("database");
        user = ConfigurationJDBC.getInstance().getProperty("user_name");
        password = ConfigurationJDBC.getInstance().getProperty("password");

        Connection connection = DriverManager.getConnection(db,user,password);
        System.out.println("Connected to DB");
        return connection;
    }

    public void closeConnection(Connection connection){
        try{
            if (connection != null){
                connection.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void releaseResource(PreparedStatement preparedStatement) {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
        } catch (SQLException sqle) {
            Logger.getLogger(JdbcDAO.class.getName()).log(Level.SEVERE, null, sqle);
        }
    }

    public void releaseResource(ResultSet resultSet) {
        try {
            if (resultSet != null) {
                resultSet.close();
            }

        } catch (SQLException sqle) {
            Logger.getLogger(JdbcDAO.class.getName()).log(Level.SEVERE, null, sqle);
        }
    }

    public void releaseResource(Statement statement) {
        try {
            if (statement != null) {
                statement.close();
            }
        } catch (SQLException sqle) {
            Logger.getLogger(JdbcDAO.class.getName()).log(Level.SEVERE, null, sqle);
        }
    }

}
