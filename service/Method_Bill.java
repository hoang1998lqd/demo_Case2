package service;

import model.Bill;
import model.Bill;
import model.CRUD;
import model.ReadAndWrite;

import java.io.IOException;
import java.util.ArrayList;

public class Method_Bill implements CRUD<Bill> {
    public ReadAndWrite<Bill> readAndWrite = new Bill_ReadAndWrite();


    public ArrayList<Bill> BillList = readAndWrite.readFile();


    public ArrayList<Bill> getBillList() {
        return BillList;
    }

    public void setBillList(ArrayList<Bill> BillList) {
        this.BillList = BillList;
    }
    public int getSize(){
        return BillList.size();
    }

    @Override
    public Bill getById(int id) {
        for (Bill Bill : BillList){
            if (id == Bill.getId()){
                return Bill;
            }
        }
        return null;
    }

    @Override
    public Bill add(Bill Bill) {
        BillList.add(Bill);
        try {
            readAndWrite.writeFile(BillList);
        }catch (IOException e){
            e.printStackTrace();
        }
        return Bill;
    }

    @Override
    public void update(Bill Bill) {
        for (int i = 0; i < BillList.size(); i++) {
            if (BillList.get(i).getId() == Bill.getId()){
                BillList.set(i,Bill);
            }
        }
        try {
            readAndWrite.writeFile(BillList);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public Bill deleteById(int id) {
        int index = 0;
        for (int i = 0; i < BillList.size(); i++) {
            if (id == BillList.get(i).getId()){
                index = i;
            }
        }
        BillList.remove(index);
        try {
            readAndWrite.writeFile(BillList);
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void displayById(int id) {
        for (Bill Bill : BillList){
            if (id == Bill.getId()){
                System.out.println(Bill);
            }
        }
    }

    @Override
    public void displayAll() {
        for (Bill Bill : BillList){
            System.out.println(Bill);
        }
    }


}
