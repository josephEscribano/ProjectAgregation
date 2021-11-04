package dao.springJDBC;

import dao.DAOCustomers;
import dao.DBConPool;
import dao.springJDBC.mappers.CustomerMapper;
import model.Customer;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import utils.Querys;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SpringDAOCustomers implements DAOCustomers {

    @Override
    public Customer get(int id) {
        return null;
    }

    @Override
    public List<Customer> getAll()  {
        JdbcTemplate jtm = new JdbcTemplate(DBConPool.getInstance().getDataSource());

        return jtm.query(Querys.SELECT_CUSTOMERS_QUERY, new CustomerMapper());
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
        JdbcTemplate jtm = new JdbcTemplate(DBConPool.getInstance().getDataSource());
        return jtm.queryForObject(Querys.SELECT_CUSTOMER_BY_ID_QUERY,new Object[]{id},new CustomerMapper());
    }
}
