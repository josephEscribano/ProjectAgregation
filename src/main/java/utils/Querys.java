package utils;

public class Querys {
    public static final String SELECT_Customers_QUERY = "select * from Customers";
    public static final String INSERT_CUSTOMER_QUERY ="INSERT INTO Customers (name,telephone,address) values(?,?,?)";
    public static final String SELECT_items_QUERY = "select * from Items";
    public static final String INSERT_ITEM_QUERY = "INSERT INTO Items (name,company,price) values(?,?,?)";
}
