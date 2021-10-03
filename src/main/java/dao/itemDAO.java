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

        try {
            daoitems.clear();
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line = br.readLine();
            while (line  != null){
                Item it = new Item(line);
                daoitems.add(it);
                line = br.readLine();

            }
        }catch (Exception e){
            alert.setContentText("error, al leer el fichero");
            alert.showAndWait();
        }

        return daoitems;
    }

    @Override
    public boolean getid(int id) {
        boolean confirmacion = false;
        for (Item it:daoitems) {
            if (it.getIdItem() == id){
                confirmacion = false;
            }else{
                confirmacion = true;
            }
        }
        return confirmacion;
    }
    @Override
    public void save(Item t) {
        try(FileWriter writer = new FileWriter(file,true);
            BufferedWriter bw = new BufferedWriter(writer)){
            String content = "\n" +t.toStringTextFile();
            bw.write(content);

        }catch (IOException e){
            alert.setContentText("error");
            alert.showAndWait();
        }

    }

    @Override
    public void update(Item t) {

    }

    @Override
    public void delete(Item t) {
        daoitems.remove(t);
        try(FileWriter writer = new FileWriter(file,false);
            BufferedWriter bw = new BufferedWriter(writer)){

            for (int i = 0; i < daoitems.size(); i++) {
                if (i == daoitems.size() -1){
                    String content = daoitems.get(i).toStringTextFile();
                    bw.write(content);
                }else{
                    String content = daoitems.get(i).toStringTextFile()+"\n";
                    bw.write(content);
                }
            }


        }catch (IOException e){
            alert.setContentText("error");
            alert.showAndWait();
        }
    }


}
