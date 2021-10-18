package main;

import dao.JdbcDAO;

public class MainJDCB {
    public static void main(String[] args) {
        JdbcDAO jdbcDAO = new JdbcDAO();
        System.out.println("List of customers");

        jdbcDAO.showCostumers();
    }
}
