package dao.springJDBC;

import dao.DAOItems;
import model.Item;

import java.util.List;

public class SpringDAOItems implements DAOItems {
    @Override
    public Item get(int id) {
        return null;
    }

    @Override
    public List<Item> getAll() {
        return null;
    }

    @Override
    public boolean save(Item t) {
        return false;
    }

    @Override
    public boolean update(Item t) {
        return false;
    }

    @Override
    public boolean deletePurchasesAndItem(Item t) {
        return false;
    }

    @Override
    public boolean deleteItem(Item item) {
        return false;
    }

    @Override
    public Item findItemByID(int id) {
        return null;
    }

    @Override
    public void closePool() {

    }
}
