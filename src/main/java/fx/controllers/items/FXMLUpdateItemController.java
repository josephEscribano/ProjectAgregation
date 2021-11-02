package fx.controllers.items;

import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import model.Customer;
import model.Item;
import services.CustomersServices;
import services.ItemsServices;

public class FXMLUpdateItemController {
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
            Item it = itemsServices.updateItem(item);
            for (int i  = 0; i < lvListItems.getItems().size();i++){
                if (lvListItems.getItems().get(i) == it){
                    lvListItems.getItems().set(i,it);
                }
            }

        }
    }

    public void loadItems() {
        ItemsServices itemsServices = new ItemsServices();
        lvListItems.getItems().setAll(itemsServices.getAll());
    }
}
