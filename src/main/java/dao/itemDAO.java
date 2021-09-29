package dao;

import javafx.scene.control.Alert;
import model.Item;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class itemDAO  implements DAOItems{
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);
    private static List<Item> daoitems = new ArrayList<>();
    File file = new File("textfiles/items");
    @Override
    public Item get(int id) {
        return null;
    }

    @Override
    public List<Item> getAll() {
        List<Item> li = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while (line  != null){
                Item it = new Item(line);
                li.add(it);
                line = br.readLine();
            }
        }catch (Exception e){
            alert.setContentText("error, al leer el fichero");
        }
        alert.showAndWait();
        return li;
    }

    @Override
    public void save(Item t) {
        List<Item> li = this.getAll();
        try(FileWriter writer = new FileWriter(file,true);
            BufferedWriter bw = new BufferedWriter(writer)){


        }catch (IOException e){

        }
    }

    @Override
    public void update(Item t) {

    }

    @Override
    public void delete(Item t) {

    }
}
