/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import dao.DAOFactory;
import dao.DAOPurchases;
import model.Customer;
import model.Item;
import model.Purchase;

/**
 *
 * @author dam2
 */
public class PurchasesServices {
    private DAOFactory dao;

    public PurchasesServices() {
        dao = new DAOFactory();
    }
    public boolean updatePurchases(Purchase purchase){
        return dao.getDAOPurchases().update(purchase);
    }
    public List<Purchase> getAllPurchases() {
        return dao.getDAOPurchases().getAll();
    }

    public ArrayList<Purchase> searchByDate(String date) {
        ArrayList<Purchase> purch =  null;
        return purch;
    }

    public List<Purchase> getPurchasesByClientId(int id) {
        List<Purchase> purch =  null;

        return purch;
    }
    public List<Purchase> getPurchasesByItemId(int id) {

        return dao.getDAOPurchases().getPurchasesByItemId(id);
    }

    public void deletePurchase(Purchase purchase) {
        dao.getDAOPurchases().delete(purchase);


     }

    public boolean addPurchase(Purchase purchase) {
        return dao.getDAOPurchases().save(purchase);

    }

}
