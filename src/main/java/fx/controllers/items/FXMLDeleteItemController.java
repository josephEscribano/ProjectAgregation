package fx.controllers.items;


import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import model.Item;
import services.ItemsServices;

import java.net.URL;
import java.util.ResourceBundle;

public class FXMLDeleteItemController implements Initializable {
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);
    public ListView<Item> LVlistItems;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void deleteItem() {
        Item it = LVlistItems.getSelectionModel().getSelectedItem();
        ItemsServices is = new ItemsServices();
        if (it != null){
            LVlistItems.getItems().remove(it);
            is.deleteItem(it);
        }else{
            alert.setContentText("Selecciona un Item");
            alert.showAndWait();
        }
    }

    public void loadItems() {
        ItemsServices is = new ItemsServices();
        LVlistItems.getItems().clear();
        LVlistItems.getItems().addAll(is.getAll());
    }
}
