package fx.controllers.items;

import dao.DAOItems;
import dao.itemDAO;
import javafx.event.ActionEvent;
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

    public boolean getid(int id){
        ItemsServices it = new ItemsServices();
        return it.getid(id);
    }
    public void saveItem() {
        ItemsServices it = new ItemsServices();
        if (getid(Integer.parseInt(idBox.getText()))){
            int id = Integer.parseInt(idBox.getText());
            String name = nameBox.getText();
            String company = companyBox.getText();
            double price = Double.parseDouble(priceBox.getText());
            it.save(id,name,company,price);
            loadItems();
        }else{
            alert.setContentText("El id ya existe");
            alert.showAndWait();
        }

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
