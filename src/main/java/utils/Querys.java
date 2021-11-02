package utils;

public class Querys {
    public static final String SELECT_Customers_QUERY = "select * from Customers";
    public static final String INSERT_CUSTOMER_QUERY ="INSERT INTO Customers (name,telephone,address) values(?,?,?)";
    public static final String SELECT_items_QUERY = "select * from Items";
    public static final String INSERT_ITEM_QUERY = "INSERT INTO Items (name,company,price) values(?,?,?)";
    public static final String UPDATE_CUSTOMER_NAME_QUERY = "update Customers set name = ? where idCustomer= ? ";
    public static final String UPDATE_CUSTOMER_TELEPHONE_QUERY = "update Customers set telephone = ? where idCustomer = ?";
    public static final String UPDATE_CUSTOMER_ADDRESS_QUERY = "update Customers set telephone = ? where idCustomer = ?";
    public static final String UPDATE_ITEM_NAME_QUERY = "update Items set name = ? where idItem = ?";
    public static final String UPDATE_ITEM_COMPANY_QUERY = "update Items set company = ? where idItem = ?";
    public static final String UPDATE_ITEM_PRICE_QUERY = "update Items set price = ? where idItem = ?";
    public static final String UDPDATE_PURCHASES_QUERY = "update Purchases set date = ? where idPurchase = ?";
}
