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

import dao.DAOCustomers;
import dao.DAOItems;
import dao.DarftDAOCustomers;
import dao.DraftDAOItems;
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
import services.PurchasesServices;

import javax.xml.parsers.ParserConfigurationException;

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

    
    public void load() throws ParserConfigurationException {
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
        DAOItems di = new DraftDAOItems();
        List<Item> li= di.getAll();
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

    public void loadCustomersList() throws ParserConfigurationException {
        DAOCustomers ddi = new DarftDAOCustomers();
        List<Customer> lc = ddi.getAll();
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

        try {
            if (cu != null && it != null && dateBox.getValue() !=null){
                int idcustomer = cu.getIdCustomer();
                int iditem = it.getIdItem();
                LocalDate date = dateBox.getValue();
                ps.addPurchase(idcustomer,iditem,date);

            }else{
                alert.setContentText("Necesitas seleccionar todos los elementos");
                alert.showAndWait();
            }
        }catch (Exception e){
            alert.setContentText("Es necesario que seleeciones todos los campos");
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
