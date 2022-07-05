package service;

import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import model.Account;
import model.Bank;
import model.CRUD;

import java.io.IOException;
import java.util.ArrayList;

public class Method_Bank implements CRUD<Bank> {
    public Bank_ReadAndWrite readAndWrite = new Bank_ReadAndWrite();
    public ArrayList<Bank> bankList;
    public Method_Bank() {
        bankList = readAndWrite.readFile();
    }

    public ArrayList<Bank> getBankList() {
        return bankList;
    }

    public void setBankList(ArrayList<Bank> bankList) {
        this.bankList = bankList;
    }

    @Override
    public Bank getById(int id) {
        for (Bank bank : bankList){
           if(bank.getId() == id){
               return bank;
           }
        }
        return null;
    }

    @Override
    public Bank add(Bank bank) {
        bankList.add(bank);
        try {
            readAndWrite.writeFile(bankList);
        }catch (IOException e){
            e.printStackTrace();
        }
        return bank;
    }

    @Override
    public void update(Bank bank) {
        for (int i = 0; i < bankList.size(); i++) {
            if (bankList.get(i).getId() == bank.getId()){
                bankList.set(i,bank);
            }
        }
        try {
            readAndWrite.writeFile(bankList);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public Bank deleteById(int id) {
        int index = 0;
        for (int i = 0; i < bankList.size(); i++) {
            if (id == bankList.get(i).getId()){
                index = i;
            }
        }
        bankList.remove(index);
        try {
            readAndWrite.writeFile(bankList);
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void displayById(int id) {
        for (Bank bank : bankList){
            if (id == bank.getId()){
                System.out.println(bank);
            }
        }

    }

    @Override
    public void displayAll() {
        for (Bank bank : bankList){
            System.out.println(bank);
        }
    }
    public boolean checkAccountNumber(String account){
        for (Bank bank : bankList){
            if (bank.getAccountNumber().equals(account)){
                return false;
            }
        }
        return true;
    }

    public boolean checkPin(String code){
        for (Bank bank : bankList){
            if (bank.getCode().equals(code)){
                return true;
            }
        }
        return false;
    }

}
