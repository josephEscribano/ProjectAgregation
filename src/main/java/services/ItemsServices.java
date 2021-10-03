/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.ArrayList;
import java.util.List;

import dao.DAOItems;
import dao.itemDAO;
import model.Item;

/**
 *
 * @author dam2
 */
public class ItemsServices {
    
    public ArrayList<Item> getAllItems() {
        ArrayList<Item> item =  null;
        return item;
    }

    public List<Item> getAll(){
        DAOItems di = new itemDAO();
        return di.getAll();
    }

    public void save(int id, String name, String company, double price){
        DAOItems di = new itemDAO();
        di.save(new Item(id,name,company,price));
    }

    public boolean getid(int id){
        DAOItems di = new itemDAO();
        return di.getid(id);
    }

    public void deleteItem(Item it){
        DAOItems di = new itemDAO();
        di.delete(it);
    }
}
