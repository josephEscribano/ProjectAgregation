package dao.jdbcDAO;

import dao.DAOItems;
import dao.DBConection;
import model.Item;
import utils.Querys;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCDAOItems implements DAOItems {



    private Connection connection;
    private Statement statement;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    private DBConection db;

    public JDBCDAOItems(){
        db = new DBConection();
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
            preparedStatement = connection.prepareStatement(Querys.SELECT_ITEMS_QUERY);
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
    public Item save(Item item) {
        try{
            connection = db.getConnection();
            preparedStatement = connection.prepareStatement(Querys.INSERT_ITEM_QUERY,Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1,item.getName());
            preparedStatement.setString(2,item.getCompany());
            preparedStatement.setDouble(3,item.getPrice());
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            int auto_id = 0;
            if (resultSet.next()){
                auto_id = resultSet.getInt(1);

            }

            item.setIdItem(auto_id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            db.releaseResources(resultSet);
            db.releaseResources(preparedStatement);
            db.closeConnection(connection);
        }

        return item;

    }

    @Override
    public Item update(Item item) {
        try{
            connection = db.getConnection();
            preparedStatement = connection.prepareStatement(Querys.UPDATE_ITEM_QUERY);
            preparedStatement.setString(1,item.getName());
            preparedStatement.setString(2,item.getCompany());
            preparedStatement.setDouble(3,item.getPrice());
            preparedStatement.setInt(4,item.getIdItem());

            preparedStatement.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            db.releaseResources(resultSet);
            db.releaseResources(preparedStatement);
            db.closeConnection(connection);
        }
        return item;
    }

    @Override
    public void delete(Item t) {

    }

}
