/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Customer;
import model.Purchase;

import java.util.List;

/**
 *
 */
public interface DAOPurchases {

    Purchase get(int id);

    public List<Purchase> getPurchasesByItemId(int id);
     
    List<Purchase> getAll();
     
    boolean save(Purchase t);
     
    boolean update(Purchase t);

    List<Purchase> searchCustomerByid(int id );
    void delete(Purchase t);
}
