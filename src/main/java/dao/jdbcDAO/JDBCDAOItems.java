package dao.jdbcDAO;

import dao.DAOItems;
import dao.DBConPool;
import dao.DBConection;
import model.Item;
import utils.Querys;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCDAOItems implements DAOItems {



    private Connection connection;
    private PreparedStatement preparedStatement;
    private PreparedStatement preparedStatement2;
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
    public boolean save(Item item) {
        boolean confirmacion = false;
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
            confirmacion = true;

            item.setIdItem(auto_id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            db.releaseResources(resultSet);
            db.releaseResources(preparedStatement);
            db.closeConnection(connection);
        }

        return confirmacion;

    }

    @Override
    public boolean update(Item item) {
        boolean confimacion = false;
        try{
            connection = db.getConnection();
            preparedStatement = connection.prepareStatement(Querys.UPDATE_ITEM_QUERY);
            preparedStatement.setString(1,item.getName());
            preparedStatement.setString(2,item.getCompany());
            preparedStatement.setDouble(3,item.getPrice());
            preparedStatement.setInt(4,item.getIdItem());

            preparedStatement.executeUpdate();
            confimacion = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            db.releaseResources(resultSet);
            db.releaseResources(preparedStatement);
            db.closeConnection(connection);
        }
        return confimacion;
    }

    @Override
    public boolean deletePurchasesAndItem(Item item) {
        boolean confirmacion = false;
        try{
            connection = db.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(Querys.DELETE_PURCHASES_BY_IDITEM_QUERY);
            preparedStatement.setInt(1,item.getIdItem());
            preparedStatement.executeUpdate();
            preparedStatement2 = connection.prepareStatement(Querys.DELETE_ITEM_QUERY);
            preparedStatement2.setInt(1,item.getIdItem());

            preparedStatement2.executeUpdate();
            connection.commit();
            confirmacion = true;
        } catch (SQLException e) {
            Logger.getLogger(JDBCDAOItems.class.getName());
        }finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            db.releaseResources(resultSet);
            db.releaseResources(preparedStatement);
            db.closeConnection(connection);
        }

        return confirmacion;

    }

    @Override
    public boolean deleteItem(Item item) {
        boolean confirmacion = false;
        try {
            connection = db.getConnection();
            preparedStatement = connection.prepareStatement(Querys.DELETE_ITEM_QUERY);
            preparedStatement.setInt(1,item.getIdItem());
            preparedStatement.executeUpdate();
            confirmacion = true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            db.releaseResources(resultSet);
            db.releaseResources(preparedStatement);
            db.closeConnection(connection);
        }
        return confirmacion;
    }

    @Override
    public Item findItemByID(int id) {
        Item item = null;
        try {
            connection = db.getConnection();
            preparedStatement = connection.prepareStatement(Querys.SELECT_ITEM_BY_ID_QUERY);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()){
                item = new Item(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getDouble(4));
            }


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
    public void closePool()  {
        DBConPool.getInstance().closePool();
    }

}
