package configuration;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class ConfigurationJDBC {
    private static ConfigurationJDBC configurationJDBC = null;

    private Properties properties;

    private ConfigurationJDBC(){
        Path path = Paths.get(ConfigProperties.getInstance().getProperty("mysqlProperties"));
        properties = new Properties();
        try {
            InputStream propertiesStream = Files.newInputStream(path);
            properties.loadFromXML(propertiesStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static ConfigurationJDBC  getInstance(){
        if (configurationJDBC == null){
            configurationJDBC = new ConfigurationJDBC();

        }
        return configurationJDBC;
    }

    public String getProperty(String key) {
       return properties.getProperty(key);
    }
}
