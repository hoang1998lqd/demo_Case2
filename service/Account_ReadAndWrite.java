package service;

import model.ReadAndWrite;
import model.Account;

import java.io.*;
import java.util.ArrayList;

public class Account_ReadAndWrite implements ReadAndWrite<Account> {
    @Override
    public ArrayList<Account> readFile() {
        File file = new File("D:\\Project\\Modul_2\\CaseStudy_Modul_2\\src\\data\\Account.txt");
        try{

            ObjectInputStream read = new ObjectInputStream(new FileInputStream(file));
            ArrayList<Account> Accounts = (ArrayList<Account>) read.readObject();
            read.close();

            return Accounts;
        }catch (Exception e){
            e.getMessage();
        }
        return new ArrayList<>();
    }

    @Override
    public void writeFile(ArrayList<Account> list) throws IOException {
        File file = new File("D:\\Project\\Modul_2\\CaseStudy_Modul_2\\src\\data\\Account.txt");

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
