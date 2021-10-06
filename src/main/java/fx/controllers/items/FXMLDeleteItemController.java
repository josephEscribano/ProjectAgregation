package fx.controllers.items;


import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import model.Item;
import model.Purchase;
import services.ItemsServices;
import services.PurchasesServices;

import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class FXMLDeleteItemController implements Initializable {
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);
    private Alert confir = new Alert(Alert.AlertType.CONFIRMATION);
    public ListView<Item> lvlistItems;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void deleteItem() {
        Item it = lvlistItems.getSelectionModel().getSelectedItem();
        PurchasesServices ps = new PurchasesServices();
        confir.setTitle("Remove the purchase");
        confir.setContentText("¿Do you want remove the purchase too?");
        Optional<ButtonType> action = confir.showAndWait();
        if (action.get() == ButtonType.OK){
            List<Purchase> lp = ps.getPurchasesByItemId(it.getIdItem());
            for (Purchase p: lp) {
                ps.deletePurchase(p);
            }
        }
        ItemsServices is = new ItemsServices();
        if (it != null){
            lvlistItems.getItems().remove(it);
            is.deleteItem(it);
        }else{
            alert.setContentText("Select a Item");
            alert.showAndWait();
        }
    }

    public void loadItems() {
        ItemsServices is = new ItemsServices();
        lvlistItems.getItems().clear();
        lvlistItems.getItems().addAll(is.getAll());
    }
}
