/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.ArrayList;
import java.util.List;

import dao.DAOItems;
import dao.DraftDAOItems;
import javafx.scene.control.Alert;
import model.Item;

/**
 *
 * @author dam2
 */
public class ItemsServices {
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);

    public List<Item> getAll(){
        DAOItems di = new DraftDAOItems();
        return di.getAll();
    }
    public Item get(int id){
        DAOItems di = new DraftDAOItems();
        return di.get(id);
    }

    public void save(int id, String name, String company, double price){
        DAOItems di = new DraftDAOItems();
        if (get(id) == null){
            di.save(new Item(id,name,company,price));
        }else{
            alert.setContentText("The id already exist");
            alert.showAndWait();
        }
    }


    public void deleteItem(Item it){
        DAOItems di = new DraftDAOItems();
        di.delete(it);
    }
}
