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
        Customer customer = null;
        try{
            connection = db.getConnection();
            preparedStatement = connection.prepareStatement(Querys.SELECT_CUSTOMERS_QUERY);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();

            customer = new Customer(resultSet.getString(2),resultSet.getString(3),resultSet.getString(4));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return customer;
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> listCustomer = new ArrayList<>();
        try{
            connection = db.getConnection();
            preparedStatement = connection.prepareStatement(Querys.SELECT_CUSTOMERS_QUERY);
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
    public Customer save(Customer customer) {
        try{
            connection = db.getConnection();
            preparedStatement = connection.prepareStatement(Querys.INSERT_CUSTOMER_QUERY,Statement.RETURN_GENERATED_KEYS);


            preparedStatement.setString(1, customer.getName());
            preparedStatement.setString(2, customer.getPhone());
            preparedStatement.setString(3, customer.getAddress());

            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();
            int auto_id = 0;
            if (resultSet.next()){
                auto_id = resultSet.getInt(1);

            }

            customer.setIdCustomer(auto_id);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            db.releaseResources(resultSet);
            db.releaseResources(preparedStatement);
            db.closeConnection(connection);
        }

        return customer;

    }

    @Override
    public Customer update(Customer customer) {
        try{
            connection = db.getConnection();
            preparedStatement = connection.prepareStatement(Querys.UPDATE_CUSTOMER_QUERY);
            preparedStatement.setString(1,customer.getName());
            preparedStatement.setString(2, customer.getPhone());
            preparedStatement.setString(3, customer.getAddress());
            preparedStatement.setInt(4,customer.getIdCustomer());
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally{
            db.releaseResources(resultSet);
            db.releaseResources(preparedStatement);
            db.closeConnection(connection);
        }
        return customer;
    }

    @Override
    public void delete(Customer t) {

    }
}
