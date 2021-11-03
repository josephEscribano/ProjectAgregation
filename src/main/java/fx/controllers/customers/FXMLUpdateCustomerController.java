package fx.controllers.customers;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Customer;
import services.CustomersServices;

import javax.management.ValueExp;
import java.util.stream.Collectors;

public class FXMLUpdateCustomerController {
    private Alert alert = new Alert(Alert.AlertType.ERROR);
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
            if (customersServices.updateCustomers(customer)){
                for (int i  = 0; i < customerListUpdate.getItems().size();i++){
                    if (customerListUpdate.getItems().get(i) == customer){
                        customerListUpdate.getItems().set(i,customer);
                    }
                }
            }else{
                alert.setContentText("could not updated the customer ");
                alert.showAndWait();
            }


        }

    }

    public void loadItems() {
        CustomersServices customersServices = new CustomersServices();
        customerListUpdate.getItems().setAll(customersServices.getAllCustomers());
    }
}
