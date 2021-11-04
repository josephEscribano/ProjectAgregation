package dao.springJDBC;

import dao.DAOCustomers;
import model.Customer;

import java.util.List;

public class SpringDAOCustomers implements DAOCustomers {
    @Override
    public Customer get(int id) {
        return null;
    }

    @Override
    public List<Customer> getAll() {
        return null;
    }

    @Override
    public boolean save(Customer t) {
        return false;
    }

    @Override
    public boolean update(Customer t) {
        return false;
    }

    @Override
    public boolean delete(Customer t) {
        return false;
    }

    @Override
    public Customer findCustomerByID(int id) {
        return null;
    }
}
