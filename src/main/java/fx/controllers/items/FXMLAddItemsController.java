package fx.controllers.items;

import dao.DAOItems;
import dao.itemDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Item;
import services.ItemsServices;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLAddItemsController implements Initializable {
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
        it.save(id,name,company,price);
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
