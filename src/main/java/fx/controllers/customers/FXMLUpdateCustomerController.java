package fx.controllers.customers;

import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Customer;

public class FXMLUpdateCustomerController {
    
    public TextField nameBox;
    public ListView<Customer> customerListUpdate;
    public TextField phoneBox;
    public TextField addressBox;

    public void updateCustomer() {
        Customer customer = customerListUpdate.getSelectionModel().getSelectedItem();
        if (customer != null){
            nameBox.setText(customer.getName());
            phoneBox.setText(customer.getPhone());
            addressBox.setText(customer.getAddress());
        }

    }

    public void loadItems() {
    }
}
