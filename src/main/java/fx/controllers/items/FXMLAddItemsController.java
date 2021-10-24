package fx.controllers.items;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Item;
import services.ItemsServices;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLAddItemsController implements Initializable {

    private Alert alert = new Alert(Alert.AlertType.INFORMATION);
    @FXML
    private ListView<Item> LVlist;
    @FXML
    private TextField idBox;
    @FXML
    private TextField nameBox;
    @FXML
    private TextField companyBox;
    @FXML
    private TextField priceBox;

    public void saveItem() {
        ItemsServices it = new ItemsServices();
        int id = Integer.parseInt(idBox.getText());
        String name = nameBox.getText();
        String company = companyBox.getText();
        double price = Double.parseDouble(priceBox.getText());
        if (!it.save(id,name,company,price)){
            alert.setContentText("The id item doesn't exist");
            alert.showAndWait();
        }
        loadItems();


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void loadItems() {
        ItemsServices is = new ItemsServices();
        LVlist.getItems().clear();
        LVlist.getItems().addAll(is.getAll());
    }
}
