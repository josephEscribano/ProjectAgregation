package dao.jdbcDAO;

import dao.DAOItems;
import dao.DBConection;
import model.Item;
import utils.Querys;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCDAOItems implements DAOItems {


    private static final String UPDATE_ITEM_QUERY = "update Items set ? = ? ";

    private Connection connection;
    private Statement statement;
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
        try{
            connection = db.getConnection();
            preparedStatement = connection.prepareStatement(Querys.SELECT_items_QUERY);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                Item item = new Item(resultSet.getInt(1)
                        ,resultSet.getString(2)
                        ,resultSet.getString(3)
                        ,resultSet.getDouble(4));
                items.add(item);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            db.releaseResources(resultSet);
            db.releaseResources(preparedStatement);
            db.closeConnection(connection);

        }
        return items;
    }

    @Override
    public void save(Item t) {
        try{
            connection = db.getConnection();
            preparedStatement = connection.prepareStatement(Querys.INSERT_ITEM_QUERY);

            preparedStatement.setString(1,t.getName());
            preparedStatement.setString(2,t.getCompany());
            preparedStatement.setDouble(3,t.getPrice());
            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            db.releaseResources(resultSet);
            db.releaseResources(preparedStatement);
            db.closeConnection(connection);
        }


    }

    @Override
    public void update(Item t) {
    }

    @Override
    public void delete(Item t) {

    }

}
