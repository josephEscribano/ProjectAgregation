package fx.controllers.customers;

import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Customer;
import services.CustomersServices;

import java.util.stream.Collectors;

public class FXMLUpdateCustomerController {
    
    public TextField nameBox;
    public ListView<Customer> customerListUpdate;
    public TextField phoneBox;
    public TextField addressBox;

    public void showInfo(){
        Customer customer = customerListUpdate.getSelectionModel().getSelectedItem();
        if (customer != null){
            nameBox.setText(customer.getName());
            phoneBox.setText(customer.getPhone());
            addressBox.setText(customer.getAddress());
        }
    }
    public void updateCustomer() {
        CustomersServices customersServices = new CustomersServices();
        Customer customer = customerListUpdate.getSelectionModel().getSelectedItem();
        if (customer != null){
            customer.setName(nameBox.getText());
            customer.setPhone(phoneBox.getText());
            customer.setAddress(addressBox.getText());
            Customer custo = customersServices.updateCustomers(customer);
            for (int i  = 0; i < customerListUpdate.getItems().size();i++){
                if (customerListUpdate.getItems().get(i) == custo){
                    customerListUpdate.getItems().set(i,custo);
                }
            }

        }

    }

    public void loadItems() {
        CustomersServices customersServices = new CustomersServices();
        customerListUpdate.getItems().setAll(customersServices.getAllCustomers());
    }
}
