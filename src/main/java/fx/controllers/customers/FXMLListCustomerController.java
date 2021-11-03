package fx.controllers.customers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import model.Customer;
import services.CustomersServices;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLListCustomerController implements Initializable {

    @FXML
    private ListView<Customer> lvCustomer;

    public void load() {
        CustomersServices cs = new CustomersServices();
        lvCustomer.getItems().setAll(cs.getAllCustomers());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }
}
