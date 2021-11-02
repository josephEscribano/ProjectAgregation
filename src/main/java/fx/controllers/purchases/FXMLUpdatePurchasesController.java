package fx.controllers.purchases;

import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import model.Item;
import model.Purchase;
import services.ItemsServices;
import services.PurchasesServices;

public class FXMLUpdatePurchasesController {
    public DatePicker dateBox;
    public ListView<Purchase> purchaseList;


    public void showInfo(){
        Purchase purchase = purchaseList.getSelectionModel().getSelectedItem();
        if (purchase != null){
            dateBox.setValue(purchase.getDate());
        }
    }
    public void updatePurchases() {
        PurchasesServices purchasesServices = new PurchasesServices();
        Purchase purchase = purchaseList.getSelectionModel().getSelectedItem();
        if (purchase != null){
            purchase.setDate(dateBox.getValue());
            Purchase purch = purchasesServices.updatePurchases(purchase);
            for (int i  = 0; i < purchaseList.getItems().size();i++){
                if (purchaseList.getItems().get(i) == purch){
                    purchaseList.getItems().set(i,purch);
                }
            }

        }
    }

    public void loadItems() {
        PurchasesServices purchasesServices = new PurchasesServices();
        purchaseList.getItems().setAll(purchasesServices.getAllPurchases());
    }
}
