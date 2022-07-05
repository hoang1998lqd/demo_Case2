package service;

import model.Brand;
import model.Order;
import model.ReadAndWrite;

import java.io.*;
import java.util.ArrayList;

public class Orders_ReadAndWrite implements ReadAndWrite<Order> {

    @Override
    public ArrayList<Order> readFile() {
        File file = new File("D:\\Project\\Modul_2\\CaseStudy_Modul_2\\src\\data\\Order.txt");
        try{
            ObjectInputStream read = new ObjectInputStream(new FileInputStream(file));
            ArrayList<Order> orders = (ArrayList<Order>) read.readObject();
            read.close();
            return orders;
        }catch (Exception e){
            e.getMessage();
        }
        return new ArrayList<>();
    }

    @Override
    public void writeFile(ArrayList<Order> list) throws IOException {
        File file = new File("D:\\Project\\Modul_2\\CaseStudy_Modul_2\\src\\data\\Order.txt");

        try {
            if (!file.exists()){
                file.createNewFile();
            }
            ObjectOutputStream write = new ObjectOutputStream(new FileOutputStream(file));
            write.writeObject(list);
            write.close();
        }
        catch (FileNotFoundException IOException){
            IOException.printStackTrace();
        }
        catch (IOException  e){
            e.getMessage();
        }

    }
}
