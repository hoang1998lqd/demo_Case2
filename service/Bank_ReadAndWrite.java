package service;
import model.Bank;
import model.ReadAndWrite;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;

public class Bank_ReadAndWrite implements ReadAndWrite<Bank> {
    @Override
    public ArrayList<Bank> readFile() {
        File file = new File("D:\\Project\\Modul_2\\CaseStudy_Modul_2\\src\\data\\Bank.txt");
        try{
            ObjectInputStream read = new ObjectInputStream(Files.newInputStream(file.toPath()));
            ArrayList<Bank> banks = (ArrayList<Bank>) read.readObject();
            read.close();

            return banks;
        }catch (Exception e){
            e.getMessage();
        }
        return new ArrayList<>();
    }

    @Override
    public void writeFile(ArrayList<Bank> list) throws IOException {
        File file = new File("D:\\Project\\Modul_2\\CaseStudy_Modul_2\\src\\data\\Bank.txt");
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
