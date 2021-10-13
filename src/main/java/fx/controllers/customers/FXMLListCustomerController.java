package fx.controllers.customers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.ListView;
import model.Customer;
import services.CustomersServices;

import javax.xml.parsers.ParserConfigurationException;
import java.net.URL;
import java.util.ResourceBundle;

public class FXMLListCustomerController implements Initializable {
    private Alert alert = new Alert(Alert.AlertType.ERROR);
    @FXML
    private ListView<Customer> lvCustomer;

    public void load() throws ParserConfigurationException {
        CustomersServices cs = new CustomersServices();
        lvCustomer.getItems().setAll(cs.getAllCustomers());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            load();
        } catch (ParserConfigurationException e) {

            alert.setContentText("Error al cargar los elementos de la lista");
            alert.showAndWait();
        }
    }
}
