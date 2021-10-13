package dao;




import configuration.ConfigProperties;
import javafx.scene.control.Alert;
import model.Customer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DarftDAOCustomers implements DAOCustomers{
    private Alert alert = new Alert(Alert.AlertType.ERROR);
    @Override
    public Customer get(int id) throws ParserConfigurationException {
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
    public List<Customer> getAll() throws ParserConfigurationException {
        List<Customer> lc = new ArrayList<>();
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(ConfigProperties.getInstance().getProperty("XMLCustomer"));
            int nCustomers = document.getElementsByTagName("customer").getLength();

            for (int i =0 ; i<nCustomers;i++){
                Customer cst = new Customer();
                cst.setIdCustomer(Integer.parseInt(document.getElementsByTagName("id").item(i).getFirstChild().getNodeValue()));
                cst.setName(document.getElementsByTagName("name").item(i).getFirstChild().getNodeValue());
                cst.setPhone(document.getElementsByTagName("phone").item(i).getFirstChild().getNodeValue());
                cst.setAddress(document.getElementsByTagName("address").item(i).getFirstChild().getNodeValue());
                lc.add(cst);
            }

        } catch (IOException | SAXException e) {
            alert.setContentText("Error al leer el fichero");
            alert.showAndWait();
        }

        return lc;
    }

    @Override
    public void save(Customer t) {
        try{
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(ConfigProperties.getInstance().getProperty("XMLCustomer"));
            Element root = document.getDocumentElement();
            Element itemNode = document.createElement("customer");
            Element idNode = document.createElement("id");
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
            alert.setContentText("Error al escribir en el fichero");
            alert.showAndWait();
        }
    }

    @Override
    public void update(Customer t) {

    }

    @Override
    public void delete(Customer t) {

    }
}
