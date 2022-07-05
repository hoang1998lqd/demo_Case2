package service;

import model.ReadAndWrite;
import model.User;

import java.io.*;
import java.util.ArrayList;

public class Users_ReadAndWrite implements ReadAndWrite<User> {
    @Override
    public ArrayList<User> readFile() {
        File file = new File("D:\\Project\\Modul_2\\CaseStudy_Modul_2\\src\\data\\Users.txt");
        try{
            if (!file.exists()){
                file.createNewFile();
            }
            ObjectInputStream read = new ObjectInputStream(new FileInputStream(file));
            ArrayList<User> users = (ArrayList<User>) read.readObject();
            read.close();
            return users;
        }catch (Exception e){
            e.getMessage();
        }
        return new ArrayList<>();
    }

    @Override
    public void writeFile(ArrayList<User> list) throws IOException {
        File file = new File("D:\\Project\\Modul_2\\CaseStudy_Modul_2\\src\\data\\Users.txt");
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
