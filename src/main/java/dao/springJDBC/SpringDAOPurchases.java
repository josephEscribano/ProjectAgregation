package dao.springJDBC;

import dao.DAOPurchases;
import model.Purchase;

import java.util.Date;
import java.util.List;

public class SpringDAOPurchases implements DAOPurchases {
    @Override
    public Purchase get(int id) {
        return null;
    }

    @Override
    public List<Purchase> getPurchasesByItemId(int id) {
        return null;
    }

    @Override
    public List<Purchase> getPurchasesByReviewId(int id) {
        return null;
    }

    @Override
    public List<Purchase> getAll() {
        return null;
    }

    @Override
    public boolean save(Purchase t) {
        return false;
    }

    @Override
    public boolean update(Purchase t) {
        return false;
    }

    @Override
    public List<Purchase> searchCustomerByid(int id) {
        return null;
    }

    @Override
    public boolean delete(Purchase t) {
        return false;
    }

    @Override
    public List<Purchase> findPurchaseByDate(Date date) {
        return null;
    }
}
