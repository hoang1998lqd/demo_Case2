package service;

import model.Bank;
import model.CRUD;
import model.ReadAndWrite;
import model.User;

import java.io.IOException;
import java.util.ArrayList;

public class Method_User implements CRUD<User> {
    public ReadAndWrite<User> readAndWrite = new Users_ReadAndWrite();
    public ArrayList<User> UserList  = readAndWrite.readFile();
    public ReadAndWrite<Bank> readAndWriteBank = new Bank_ReadAndWrite();
    public ArrayList<Bank> bankList  = readAndWriteBank.readFile();

    public ArrayList<User> getUserList() {
        return UserList;
    }

    public void setUserList(ArrayList<User> userList) {
        UserList = userList;
    }


    public int getSize(){
        return UserList.size();
    }

    @Override
    public User getById(int id) {
        for (User User : UserList){
            if (id == User.getId()){
                return User;
            }
        }
        return null;
    }

    @Override
    public User add(User User) {
        UserList.add(User);
        try{
            readAndWrite.writeFile(UserList);
        }catch (IOException e){
            e.printStackTrace();
        }
        return User;
    }

    @Override
    public void update(User User) {
        for (int i = 0; i < UserList.size(); i++) {
            if (UserList.get(i).getId() == User.getId()){
                UserList.set(i,User);
            }
        }
        try{
            readAndWrite.writeFile(UserList);
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public User deleteById(int id) {

        int index = 0;
        for (int i = 0; i < UserList.size(); i++) {
            if (id == UserList.get(i).getId()){
                index = i;
            }
        }
        UserList.remove(index);
        try{
            readAndWrite.writeFile(UserList);
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void displayById(int id) {
        for (User User : UserList){
            if (id == User.getId()){
                System.out.println(User);
            }
        }
    }



    @Override
    public void displayAll() {
        for (User User : UserList){
            System.out.println(User);
        }
    }
    public boolean checkPhoneInList(String phone){
        for (User user : UserList){
            if (phone.equals(user.getPhoneNumber())){
                return false;
            }
        }
        return true;
    }

    
}
