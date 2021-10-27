package dao;

import dao.jdbcDAO.JDBCDAOItems;
import dao.jdbcDAO.JDBCDAOcustomers;
import dao.jdbcDAO.JDBCDAOpurchases;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOFactory {
    private static DAOFactory daoFactoryItem;

    private Properties daoProps;


    private String PROPERTIES_FILES = "config\\dao-properties.xml";

    public DAOFactory(){
        try {
            setDAOTypes(PROPERTIES_FILES);
        }catch (Exception e){
            Logger.getLogger(DAOFactory.class.getName()).log(Level.SEVERE,null,e);
        }
    }

    private void setDAOTypes(String confiFiles) throws IOException {
        daoProps = new Properties();
        daoProps.loadFromXML(Files.newInputStream(Paths.get(confiFiles)));
    }

    public DAOItems getDAOItems(){
        String sourceItems;
        DAOItems dao = null;
        sourceItems = daoProps.getProperty("daoItemsJDBC");
        if (sourceItems.equals("JDBCDAOItems")){
            dao = new JDBCDAOItems();
        }

        return dao;
    }

    public DAOCustomers getDAOCustomers(){
        String sourceCustomers;
        DAOCustomers dao = null;
        sourceCustomers = daoProps.getProperty("daoCustomersJDBC");
        if (sourceCustomers.equals("JDBCDAOcustomers")){
            dao = new JDBCDAOcustomers();
        }

        return dao;

    }

    public DAOPurchases getDAOPurchases(){
        String sourcesPurchses;
        DAOPurchases dao = null;
        sourcesPurchses = daoProps.getProperty("daoCustomersJDBC");
        if (sourcesPurchses.equals("JDBCDAOcustomers")){
            dao = new JDBCDAOpurchases();
        }

        return dao;
    }


}
