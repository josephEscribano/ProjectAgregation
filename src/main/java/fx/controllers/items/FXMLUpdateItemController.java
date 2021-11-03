package fx.controllers.items;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Customer;
import model.Item;
import services.CustomersServices;
import services.ItemsServices;

public class FXMLUpdateItemController {
    private Alert alert = new Alert(Alert.AlertType.ERROR);
    public TextField nameBox;
    public TextField companyBox;
    public TextField priceBox;
    public ListView<Item> lvListItems;

    public void showInfo(){
        Item item = lvListItems.getSelectionModel().getSelectedItem();
        if (item != null){
            nameBox.setText(item.getName());
            companyBox.setText(item.getCompany());
            priceBox.setText(String.valueOf(item.getPrice()));
        }
    }
    public void updateItem() {
        ItemsServices itemsServices = new ItemsServices();
        Item item = lvListItems.getSelectionModel().getSelectedItem();
        if (item != null){
            item.setName(nameBox.getText());
            item.setCompany(companyBox.getText());
            item.setPrice(Double.parseDouble(priceBox.getText()));

            if (itemsServices.updateItem(item)){
                for (int i  = 0; i < lvListItems.getItems().size();i++){
                    if (lvListItems.getItems().get(i) == item){
                        lvListItems.getItems().set(i,item);
                    }
                }
            }else{
                alert.setContentText("could not updated the item");
                alert.showAndWait();
            }


        }
    }

    public void loadItems() {
        ItemsServices itemsServices = new ItemsServices();
        lvListItems.getItems().setAll(itemsServices.getAll());
    }
}
