package dao;




import configuration.ConfigProperties;
import javafx.scene.control.Alert;
import model.Customer;
import model.Customers;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import javax.xml.bind.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DarftDAOCustomers implements DAOCustomers{
    private Alert alert = new Alert(Alert.AlertType.ERROR);
    @Override
    public Customer get(int id) {
        List<Customer> lc = getAll();
        Customer c = null;
        for (Customer ct:lc) {
            if (ct.getIdCustomer() == id){
                c = ct;
            }
        }
        return c;
    }

    @Override
    public List<Customer> getAll() {
        List<Customer> lc = new ArrayList<>();
        try {
            JAXBContext context = JAXBContext.newInstance(Customers.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Path xmlFiles = Paths.get(ConfigProperties.getInstance().getProperty("XMLCustomer"));
            Customers customerList = (Customers) unmarshaller.unmarshal(Files.newInputStream(xmlFiles));
            lc.addAll(customerList.getCustomer());
        } catch (JAXBException | IOException e) {
            Logger.getLogger("error, when read the xml customers").log(Level.INFO,e.getMessage());
        }

        return lc;
    }

    @Override
    public void save(Customer t) {
        try{
            JAXBContext context = JAXBContext.newInstance(Customers.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Path xmlFiles = Paths.get(ConfigProperties.getInstance().getProperty("XMLCustomer"));
            Customers customerList = (Customers) unmarshaller.unmarshal(Files.newInputStream(xmlFiles));
            customerList.getCustomer().add(t);
            marshaller.marshal(customerList,Files.newOutputStream(xmlFiles));
        } catch (JAXBException | IOException e) {
            Logger.getLogger("error, when save a customer").log(Level.INFO,e.getMessage());
        }
    }
    @Override
    public void update(Customer t) {

    }

    @Override
    public void delete(Customer t) {
        try{
            JAXBContext context = JAXBContext.newInstance(Customers.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            Path xmlFiles = Paths.get(ConfigProperties.getInstance().getProperty("XMLCustomer"));
            Customers customerList = (Customers) unmarshaller.unmarshal(Files.newInputStream(xmlFiles));
            customerList.getCustomer().remove(t);
            marshaller.marshal(customerList,Files.newOutputStream(xmlFiles));
        } catch (JAXBException | IOException e) {
            Logger.getLogger("error, when delete the customer").log(Level.INFO,e.getMessage());
        }
    }

    //This methods are the ones I use for read and write the xml with DOM
    public Customer getDOM(int id)  {
        List<Customer> lc = getAllDOM();
        Customer c = null;
        for (Customer ct:lc) {
            if (ct.getIdCustomer() == id){
                c = ct;
            }

        }
        return c;
    }


    public List<Customer> getAllDOM()  {
        List<Customer> lc = new ArrayList<>();
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(ConfigProperties.getInstance().getProperty("XMLCustomer"));
            int nCustomers = document.getElementsByTagName("customer").getLength();

            for (int i =0 ; i<nCustomers;i++){
                Customer cst = new Customer();
                cst.setIdCustomer(Integer.parseInt(document.getElementsByTagName("ididCustomer").item(i).getFirstChild().getNodeValue()));
                cst.setName(document.getElementsByTagName("name").item(i).getFirstChild().getNodeValue());
                cst.setPhone(document.getElementsByTagName("phone").item(i).getFirstChild().getNodeValue());
                cst.setAddress(document.getElementsByTagName("address").item(i).getFirstChild().getNodeValue());
                lc.add(cst);
            }

        } catch (IOException | SAXException | ParserConfigurationException e) {
            Logger.getLogger("error, when read xml customer").log(Level.INFO,e.getMessage());
        }

        return lc;
    }


    public void saveDOM(Customer t) {
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(ConfigProperties.getInstance().getProperty("XMLCustomer"));
            Element root = document.getDocumentElement();
            Element itemNode = document.createElement("customer");
            Element idNode = document.createElement("idCustomer");
            Element nameNode = document.createElement("name");
            Element phoneNode = document.createElement("phone");
            Element addressNode = document.createElement("address");
            Text nodeIdValue = document.createTextNode(String.valueOf(t.getIdCustomer()));
            Text nodeNameValue = document.createTextNode(t.getName());
            Text nodePhoneValue = document.createTextNode(t.getPhone());
            Text nodeAddressValue = document.createTextNode(t.getAddress());
            idNode.appendChild(nodeIdValue);
            nameNode.appendChild(nodeNameValue);
            phoneNode.appendChild(nodePhoneValue);
            addressNode.appendChild(nodeAddressValue);
            itemNode.appendChild(idNode);
            itemNode.appendChild(nameNode);
            itemNode.appendChild(phoneNode);
            itemNode.appendChild(addressNode);
            root.appendChild(itemNode);

            Source source = new DOMSource(document);
            Result result = new StreamResult(ConfigProperties.getInstance().getProperty("XMLCustomer"));

            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT,"yes");
            transformer.transform(source,result);
        } catch (ParserConfigurationException | IOException | SAXException | TransformerException e) {
            Logger.getLogger("error, when save the customer").log(Level.INFO,e.getMessage());
        }
    }




}
