package fx.controllers.purchases;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import model.Purchase;

public class FXMLUpdatePurchasesController {
    public ListView<Purchase> purchaseList;
    public ComboBox<Purchase> itemBox;
    public DatePicker dateBox;
    public ComboBox<Purchase> customerBox;

    public void updatePurchases(ActionEvent actionEvent) {
    }

    public void loadItems() {
    }
}
