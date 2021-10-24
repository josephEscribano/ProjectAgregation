package configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class JDBCproperties {
    private String userName;
    private String password;
    private String urlDB;

    private String databaseMysql;
    private Properties prop;

    private static final String PROPERTIES_FILE = "config/store-properties.xml";

    public JDBCproperties(){
        super();
        this.setProperties(PROPERTIES_FILE);
    }

    private void setProperties(String filename) {

        prop = new Properties();

        try {
            prop.loadFromXML(Files.newInputStream(Paths.get(filename)));
        } catch (IOException e) {
            Logger.getLogger(JDBCproperties.class.getName()).log(Level.SEVERE, null, e);
        }
        this.databaseMysql = this.prop.getProperty("databaseMysql");
        this.userName = this.prop.getProperty("user_name");
        this.password = this.prop.getProperty("password");
        this.urlDB = this.prop.getProperty("database");
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getUrlDB() {
        return urlDB;
    }
}
