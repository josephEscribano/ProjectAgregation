package utils;

public class Querys {
    public static final String SELECT_CUSTOMERS_QUERY = "select * from Customers";
    public static final String SELECT_ITEMS_QUERY = "select * from Items";
    public static final String SELECT_CUSTOMERS_BY_ID_QUERY = "select * from Customers where idCustomer = ?";
    public static final String SELECT_PURCHASES_QUERY = "select idPurchase,date,C.idCustomer,C.name,C.telephone,C.address,I.idItem,I.name,I.company,I.price\n" +
            "from Purchases inner join (Customers C,Items I) on Purchases.idCustomer = C.idCustomer and Purchases.idItem = I.idItem";
    public static final String SELECT_PURCHASES_CUSTOMER_QUERY = "select idPurchase,date,C.idCustomer,C.name,C.telephone,C.address,I.idItem,I.name,I.company,I.price\\n\" +\n" +
            "\"from Purchases inner join (Customers C,Items I) on Purchases.idCustomer = C.idCustomer and Purchases.idItem = I.idItem where C.idCustomer = ? ";
    public static final String INSERT_CUSTOMER_QUERY ="INSERT INTO Customers (name,telephone,address) values(?,?,?)";
    public static final String INSERT_PURCHASE_QUERY = "INSERT INTO Purchases (date,idCustomer,idItem) values(?,?,?)";
    public static final String INSERT_ITEM_QUERY = "INSERT INTO Items (name,company,price) values(?,?,?)";
    public static final String UPDATE_CUSTOMER_QUERY = "update Customers set name = ?, telephone = ?, address = ? where idCustomer= ? ";
    public static final String UPDATE_ITEM_QUERY = "update Items set name = ?, company = ?, price = ? where idItem = ?";
    public static final String UPDATE_PURCHASES_QUERY = "update Purchases set date = ? where idPurchase = ?";
    public static final String DELETE_PURCHASES_QUERY = "DELETE from Customers where idCustomer = ?";
}
