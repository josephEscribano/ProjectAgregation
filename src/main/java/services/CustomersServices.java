/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.ArrayList;
import java.util.List;

import dao.DAOCustomers;
import dao.DAOFactory;
import javafx.scene.control.Alert;
import model.Customer;

/**
 *
 * @author Laura
 */
public class CustomersServices {
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);
    private DAOFactory dao;

    public CustomersServices(){
        dao = new DAOFactory();
    }
    public List<Customer> getAllCustomers()  {
        return dao.getDAOCustomers().getAll();
    }

    public List<Customer> searchById(int id)  {
        List<Customer> st =  new ArrayList<>();
        st.add(dao.getDAOCustomers().get(id));
        return st;
    }

    public void deleteCustomer(Customer customer) {
        dao.getDAOCustomers().delete(customer);
    }



    public Customer addCustomer(int customerId, String name, String phone, String address)  {
        Customer custo = new Customer(customerId,name,phone,address);

        if (dao.getDAOCustomers().get(customerId) == null){
            dao.getDAOCustomers().save(custo);
        }else{
            alert.setContentText("The id already exist");
            alert.showAndWait();
        }

        return custo;

    }

}
