/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.ArrayList;
import java.util.List;

import dao.DAOCustomers;
import dao.DarftDAOCustomers;
import javafx.scene.control.Alert;
import model.Customer;

import javax.xml.parsers.ParserConfigurationException;

/**
 *
 * @author Laura
 */
public class CustomersServices {
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);
    public List<Customer> getAllCustomers() throws ParserConfigurationException {
        DAOCustomers dc = new DarftDAOCustomers();
        return dc.getAll();
    }

    public List<Customer> searchById(int id) throws ParserConfigurationException {
        List<Customer> st =  new ArrayList<>();
        DAOCustomers dc = new DarftDAOCustomers();
        st.add(dc.get(id));
        return st;
    }

    public void deleteCustomer(Customer customer) {

    }

    public void addCustomer(int customerId, String name, String phone, String address) throws ParserConfigurationException {
        Customer custo = new Customer(customerId,name,phone,address);
        DAOCustomers dc = new DarftDAOCustomers();
        if (dc.get(customerId) == null){
            dc.save(custo);
        }else{
            alert.setContentText("El id que has introducido ya existe");
            alert.showAndWait();
        }

    }

}
