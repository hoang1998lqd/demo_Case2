package service;

import model.*;


import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

public class Method_Order implements CRUD<Order> , Serializable {
    public Orders_ReadAndWrite readAndWrite = new Orders_ReadAndWrite();
    public ArrayList<Order> orderList;

    public Method_Order() {
        orderList = readAndWrite.readFile();
    }

    public ArrayList<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(ArrayList<Order> orderList) {
        this.orderList = orderList;
    }

    public int getSize(){
        return orderList.size();
    }
    @Override
    public Order getById(int id) {
        for (Order order : orderList){
            if (id == order.getId()){
                return order;
            }
        }
        return null;
    }

    @Override
    public Order add(Order order) {
        orderList.add(order);
        try{
            readAndWrite.writeFile(orderList);
        }catch (IOException e){
            e.printStackTrace();
        }
        return order;
    }

    @Override
    public void update(Order order) {
        for (int i = 0; i < orderList.size(); i++) {
            if (orderList.get(i).getId() == order.getId()){
                orderList.set(i,order);
            }
        }
        try{
            readAndWrite.writeFile(orderList);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public Order deleteById(int id) {
        int index = 0;
        for (int i = 0; i < orderList.size(); i++) {
            if (id == orderList.get(i).getId()){
                index = i;
            }
        }
        orderList.remove(index);
        try {
            readAndWrite.writeFile(orderList);
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void displayById(int id) {
        for (Order order : orderList){
            if (id == order.getId()){
                System.out.println(order);
            }
        }
    }

    @Override
    public void displayAll() {
        for (Order order : orderList){
            System.out.println(order);
        }
    }


}
