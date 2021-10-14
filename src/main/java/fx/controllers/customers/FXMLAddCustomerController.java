/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers.customers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Customer;
import services.CustomersServices;

import javax.xml.parsers.ParserConfigurationException;

/**
 * FXML Controller class
 *
 */
public class FXMLAddCustomerController implements Initializable {
    private Alert alert = new Alert(AlertType.ERROR);
    @FXML
    private TextField idBox;
    @FXML
    private TextField nameBox;
    @FXML
    private TextField phoneBox;
    @FXML
    private TextField addressBox;
    @FXML
    private ListView<Customer> customerList;

    public void loadCustomersList()  {
        CustomersServices cs = new CustomersServices();
        customerList.getItems().setAll(cs.getAllCustomers());
     }

    public void addCustomer()  {
        CustomersServices cs = new CustomersServices();
        int id = Integer.parseInt(idBox.getText());
        String name = nameBox.getText();
        String phone = phoneBox.getText();
        String address = addressBox.getText();
        cs.addCustomer(id,name,phone,address);
        loadCustomersList();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadCustomersList();
    }

}
