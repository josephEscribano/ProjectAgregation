package dao.jdbcDAO;

import dao.DAOItems;
import dao.DBConection;
import model.Item;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCDAOItems implements DAOItems {

    private static final String SELECT_items_QUERY = "select iditem,name,company,price from Items";
    private Connection connection;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    private DBConection db;

    public JDBCDAOItems(){
        db = new DBConection();
    }
    public void showCostumers(){

    }

    @Override
    public Item get(int id) {
        return null;
    }

    @Override
    public List<Item> getAll() {
        List<Item> items = new ArrayList<>();
        preparedStatement = null;
        resultSet = null;

        try{
            connection = db.getConnection();
            preparedStatement = connection.prepareStatement(SELECT_items_QUERY);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Item item = new Item(resultSet.getInt(1)
                        ,resultSet.getString(2)
                        ,resultSet.getString(3)
                        ,resultSet.getInt(4));
                items.add(item);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            db.releaseResources(preparedStatement,resultSet);
            db.closeConnection(connection);

        }
        return items;
    }

    @Override
    public void save(Item t) {

    }

    @Override
    public void update(Item t) {

    }

    @Override
    public void delete(Item t) {

    }
}
