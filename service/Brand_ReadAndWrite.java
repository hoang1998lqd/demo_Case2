package service;

import model.Brand;
import model.ReadAndWrite;

import java.io.*;
import java.util.ArrayList;

public class Brand_ReadAndWrite implements ReadAndWrite<Brand> {
    @Override
    public ArrayList<Brand> readFile() {
        File file = new File("D:\\Project\\Modul_2\\CaseStudy_Modul_2\\src\\data\\Brand.txt");
        try{
            ObjectInputStream read = new ObjectInputStream(new FileInputStream(file));
            ArrayList<Brand> brands = (ArrayList<Brand>) read.readObject();
            read.close();
            return brands;
        }catch (Exception e){
            e.getMessage();
        }
        return new ArrayList<>();
    }

    @Override
    public void writeFile(ArrayList<Brand> list) throws IOException {
        File file = new File("D:\\Project\\Modul_2\\CaseStudy_Modul_2\\src\\data\\Brand.txt");

        try {
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
