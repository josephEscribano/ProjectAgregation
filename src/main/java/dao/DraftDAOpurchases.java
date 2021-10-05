package dao;


import javafx.scene.control.Alert;
import model.Purchase;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import static java.nio.file.StandardOpenOption.APPEND;
import static java.nio.file.StandardOpenOption.WRITE;

public class DraftDAOpurchases implements DAOPurchases{
    private Alert alert = new Alert(Alert.AlertType.INFORMATION);
    private Path file = Paths.get("textfiles/purchases");
    @Override
    public Purchase get(int id) {
        return null;
    }

    @Override
    public List<Purchase> getAll() {
        List<Purchase> lp = new ArrayList<>();
        try {
            BufferedReader reader = Files.newBufferedReader(file);
            String line ;
            while ((line = reader.readLine())  != null){
                Purchase p = new Purchase(line);
                lp.add(p);
            }
            reader.close();

        }catch (IOException e){
            alert.setContentText("Ha ocurrido un error");
            alert.showAndWait();
        }
        return lp;
    }

    @Override
    public void save(Purchase t) {
        OpenOption[] options = new OpenOption[2];
        options[0] = APPEND;
        options[1] = WRITE;
        try(BufferedWriter writer = Files.newBufferedWriter(file,options)) {
            writer.write(t.toStringTexto(),0,t.toStringTexto().length());
            writer.newLine();
        }catch (IOException e){
            alert.setContentText("Ha ocurrido un error");
            alert.showAndWait();
        }
    }

    @Override
    public void update(Purchase t) {

    }


    @Override
    public void delete(Purchase t) {

        OpenOption[] options = new OpenOption[2];
        options[0] = APPEND;
        options[1] = WRITE;
        List<Purchase> lp = getAll();
        lp.remove(t);
        try(BufferedWriter writer = Files.newBufferedWriter(file,options);
            BufferedWriter bw = new BufferedWriter(writer)) {
            for(int i = 0; i <= lp.size();i++){
                if (i < lp.size() -1){
                    writer.newLine();
                    writer.write(lp.get(i).toStringTexto(),0,lp.get(i).toStringTexto().length());
                }else{
                    writer.write(lp.get(i).toStringTexto(),0,lp.get(i).toStringTexto().length());
                }
            }




        }catch (IOException e){
            alert.setContentText("Ha ocurrido un error");
            alert.showAndWait();
        }

    }


}
