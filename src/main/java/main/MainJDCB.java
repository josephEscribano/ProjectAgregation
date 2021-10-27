package main;

import dao.jdbcDAO.JDBCDAOItems;

public class MainJDCB {
    public static void main(String[] args) {
        JDBCDAOItems jdbcDAO = new JDBCDAOItems();
        System.out.println("List of customers");

        jdbcDAO.showCostumers();
    }
}
