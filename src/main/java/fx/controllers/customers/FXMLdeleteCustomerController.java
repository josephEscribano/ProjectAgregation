/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers.customers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import model.Customer;
import services.CustomersServices;

/**
 * FXML Controller class
 *
 * @author Laura
 */
public class FXMLdeleteCustomerController implements Initializable {
    private Alert alert = new Alert(AlertType.INFORMATION);
    @FXML
    private ListView<Customer> customerBox;

    public void loadCustomersList() {
        CustomersServices cs = new CustomersServices();
        customerBox.getItems().setAll(cs.getAllCustomers());
    }
    
    public void deleteCustomer() {
        CustomersServices cs = new CustomersServices();
        Customer customer = customerBox.getSelectionModel().getSelectedItem();
        if ( customer!= null){
            if (cs.deleteCustomer(customer)){
                customerBox.getItems().remove(customer);
            }else{
                alert.setContentText("could not remove the customer");
                alert.showAndWait();
            }


        }else{
            alert.setContentText("Select a customer");
            alert.showAndWait();
        }

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
