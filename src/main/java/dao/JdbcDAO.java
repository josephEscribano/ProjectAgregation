package dao;

import conections.DBConection;
import java.sql.*;

public class JdbcDAO {

    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private DBConection db;

    public void showCostumers(){
        statement = null;
        resultSet = null;
        db = new DBConection();

        try{
            connection = db.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from Customers");

            while (resultSet.next()){
                int idCustomer = resultSet.getInt("idCustomer");
                String name = resultSet.getString("name");
                String phone = resultSet.getString("telephone");
                String address = resultSet.getString("address");
                System.out.println(idCustomer + ", " + name + ", " + phone + ", " + address);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            db.releaseResource(resultSet);
            db.releaseResource(statement);
            db.closeConnection(connection);

        }
    }

}
