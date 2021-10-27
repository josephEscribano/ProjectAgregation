/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.util.List;

import dao.DAOFactory;
import model.Item;

/**
 *
 * @author dam2
 */
public class ItemsServices {

    private DAOFactory dao;

    public ItemsServices(){
        dao = new DAOFactory();
    }
    public List<Item> getAll(){
        return dao.getDAOItems().getAll();
    }
    public Item get(int id){
        return dao.getDAOItems().get(id);
    }

    public boolean save(int id, String name, String company, double price){
        boolean confirmacion = false;
        if (get(id) == null){
            dao.getDAOItems().save(new Item(id,name,company,price));
            confirmacion = true;
        }

        return confirmacion;
    }


    public void deleteItem(Item it){
        dao.getDAOItems().delete(it);
    }
}
