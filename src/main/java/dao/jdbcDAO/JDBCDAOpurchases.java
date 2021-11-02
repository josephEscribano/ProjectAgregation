package dao.jdbcDAO;

import dao.DAOPurchases;
import model.Purchase;

import java.util.ArrayList;
import java.util.List;

public class JDBCDAOpurchases implements DAOPurchases {
    @Override
    public Purchase get(int id) {
        return null;
    }

    @Override
    public List<Purchase> getPurchasesByItemId(int id) {
        return null;
    }

    @Override
    public List<Purchase> getAll() {
        List<Purchase> lista = new ArrayList<>();
        return lista;
    }

    @Override
    public void save(Purchase t) {

    }

    @Override
    public void update(Purchase t) {

    }

    @Override
    public void delete(Purchase t) {

    }
}
