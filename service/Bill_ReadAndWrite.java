package service;

import model.Bill;
import model.ReadAndWrite;
import model.User;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;

public class Bill_ReadAndWrite implements ReadAndWrite<Bill> {
    @Override
    public ArrayList<Bill> readFile() {
        File file = new File("D:\\Project\\Modul_2\\CaseStudy_Modul_2\\src\\data\\Bill.txt");
        try{
            ObjectInputStream read = new ObjectInputStream(Files.newInputStream(file.toPath()));
            ArrayList<Bill> bills = (ArrayList<Bill>) read.readObject();
            read.close();
            return bills;
        }catch (Exception e){
            e.getMessage();
        }
        return new ArrayList<>();
    }

    @Override
    public void writeFile(ArrayList<Bill> list) throws IOException {
        File file = new File("D:\\Project\\Modul_2\\CaseStudy_Modul_2\\src\\data\\Bill.txt");
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
