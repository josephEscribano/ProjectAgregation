package dao.jdbcDAO;

import dao.DAOCustomers;
import dao.DBConection;
import model.Customer;
import utils.Querys;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCDAOcustomers implements DAOCustomers {


    private Connection connection;
    private Statement statement;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private DBConection db;

    public JDBCDAOcustomers() {
        this.db = new DBConection();
    }

    @Override
    public Customer get(int id) {
        return null;
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> listCustomer = new ArrayList<>();
        try{
            connection = db.getConnection();
            preparedStatement = connection.prepareStatement(Querys.SELECT_Customers_QUERY);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                Customer customer = new Customer(resultSet.getInt(1),resultSet.getString(2),resultSet.getString(3),resultSet.getString(4));
                listCustomer.add(customer);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {

            db.releaseResources(resultSet);
            db.releaseResources(preparedStatement);
            db.closeConnection(connection);
        }


        return listCustomer;
    }

    @Override
    public void save(Customer customer) {
        try{
            connection = db.getConnection();
            preparedStatement = connection.prepareStatement(Querys.INSERT_CUSTOMER_QUERY);

            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getPhone());
            preparedStatement.setString(3, customer.getAddress());

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
    public void update(Customer customer) {
        try{
            connection = db.getConnection();
            preparedStatement = connection.prepareStatement(Querys.UPDATE_CUSTOMER_NAME_QUERY);
            preparedStatement.setString(1,customer.getName());
            preparedStatement.setInt(2,customer.getIdCustomer());
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public void delete(Customer t) {

    }
}
