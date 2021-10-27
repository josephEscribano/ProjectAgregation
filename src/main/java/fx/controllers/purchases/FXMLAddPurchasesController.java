/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fx.controllers.purchases;


import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.util.StringConverter;
import model.Customer;
import model.Item;
import model.Purchase;
import services.CustomersServices;
import services.ItemsServices;
import services.PurchasesServices;

/**
 * FXML Controller class
 *
 * @author Laura
 */
public class FXMLAddPurchasesController implements Initializable {
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);

    @FXML
    private ComboBox<Customer> customerBox;
    @FXML
    private ComboBox<Item> itemBox;
    @FXML
    private ListView<Purchase> purchaseList;
    @FXML
    private DatePicker dateBox;

    
    public void load()  {
        loadCustomersList();
        loadPurchasesList();
        loadItemsList();
    }
    
    public void loadPurchasesList() {
        PurchasesServices ps = new PurchasesServices();
        purchaseList.getItems().clear();
        purchaseList.getItems().addAll(ps.getAllPurchases());


    }

    public void loadItemsList() {
        ItemsServices itemsServices = new ItemsServices();
        List<Item> li= itemsServices.getAll();
        itemBox.getItems().clear();
        for (Item it:li) {
            itemBox.getItems().add(it);
        }
        itemBox.setConverter(new StringConverter<Item>() {
            @Override
            public String toString(Item item) {
                return item == null ? null : item.getName();
            }

            @Override
            public Item fromString(String s) {
                return null;
            }
        });

    }

    public void loadCustomersList()  {
        CustomersServices customersServices = new CustomersServices();
        List<Customer> lc = customersServices.getAllCustomers();
        customerBox.getItems().clear();
        for (Customer cu: lc) {
            customerBox.getItems().add(cu);
        }
        customerBox.setConverter(new StringConverter<Customer>() {
            @Override
            public String toString(Customer customer) {
                return customer == null ? null : customer.getName();
            }

            @Override
            public Customer fromString(String s) {
                return null;
            }
        });
    }

    public void addPurchase() {
        PurchasesServices ps = new PurchasesServices();
        Customer cu = customerBox.getSelectionModel().getSelectedItem();
        Item it = itemBox.getSelectionModel().getSelectedItem();
        if (cu != null && it != null && dateBox.getValue() !=null){
            LocalDate date = dateBox.getValue();
            ps.addPurchase(cu,it,date);

        }else{
            alert.setContentText("you need fill all fields");
            alert.showAndWait();
        }
        loadPurchasesList();



    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    }
}
