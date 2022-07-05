package service;

import model.Product;
import model.ReadAndWrite;

import java.io.*;
import java.util.ArrayList;

public class Product_ReadAndWrite implements ReadAndWrite<Product> {


    @Override
    public ArrayList<Product> readFile() {
        File file = new File("D:\\Project\\Modul_2\\CaseStudy_Modul_2\\src\\data\\Products.txt");
        try{
            if (!file.exists()){
                file.createNewFile();
            }
            ObjectInputStream read = new ObjectInputStream(new FileInputStream(file));
            ArrayList<Product> products = (ArrayList<Product>) read.readObject();
            read.close();
            return products;
        }catch (Exception e){
            e.getMessage();
        }
        return new ArrayList<>();
    }

    @Override
    public void writeFile(ArrayList<Product> list) throws IOException {
        File file = new File("D:\\Project\\Modul_2\\CaseStudy_Modul_2\\src\\data\\Products.txt");

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
