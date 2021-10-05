/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers.purchases;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ListView;
import services.PurchasesServices;
import model.Purchase;

/**
 * FXML Controller class
 *
 * @author dam2
 */
public class FXMLDeleteController implements Initializable {
    private Alert alert = new Alert(AlertType.INFORMATION);
    @FXML
    private ListView<Purchase> purchaseBox;


    public void loadPurchases() {
        PurchasesServices ps = new PurchasesServices();
        purchaseBox.getItems().clear();
        purchaseBox.getItems().addAll(ps.getAllPurchases());
     }
    
    public void deletePurchase(){

        Purchase p = purchaseBox.getSelectionModel().getSelectedItem();
        PurchasesServices ps = new PurchasesServices();
        if (p != null){
            purchaseBox.getItems().remove(p);
            ps.deletePurchase(p);
        }else{
            alert.setContentText("Tienes que seleccionar un elemento");
            alert.showAndWait();
        }


    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadPurchases();
    }

}
