package dao;

import model.Customer;

import java.util.ArrayList;
import java.util.List;

public class darftDAOCustomers implements DAOCustomers{
    @Override
    public Customer get(int id) {
        return null;
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> lc = new ArrayList<>();
        lc.add(new Customer(1,"Pedro","612358741","Madrid"));
        lc.add(new Customer(2,"Alberto","643879425","Barcelona"));
        lc.add(new Customer(3,"Javi","613489657","Sevilla"));
        return lc;
    }

    @Override
    public void save(Customer t) {

    }

    @Override
    public void update(Customer t) {

    }

    @Override
    public void delete(Customer t) {

    }
}
