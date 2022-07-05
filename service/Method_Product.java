package service;


import model.CRUD;
import model.Product;
import model.ReadAndWrite;

import java.io.IOException;
import java.util.*;


public class Method_Product implements CRUD<Product> {
    public ReadAndWrite<Product> readAndWrite = new Product_ReadAndWrite();
    public ArrayList<Product> productList = readAndWrite.readFile();

    public ArrayList<Product> getProductList() {
        return productList;
    }

    public void setProductList(ArrayList<Product> productList) {
        this.productList = productList;
    }

    public int getSize(){
        return productList.size();
    }

    @Override
    public Product getById(int id) {
        for (Product product : productList){
            if (id == product.getId()){
                return product;
            }
        }
        return null;
    }

    @Override
    public Product add(Product product) {
        productList.add(product);
        try {
            readAndWrite.writeFile(productList);
        }catch (Exception e){
            e.printStackTrace();
        }
        return product;
    }

    @Override
    public void update(Product product) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId() == product.getId()){
                productList.set(i,product);
            }
        }
        try {
            readAndWrite.writeFile(productList);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public Product deleteById(int id) {
        int index = 0;
        for (int i = 0; i < productList.size(); i++) {
            if (id == productList.get(i).getId()){
                index = i;
            }
        }
        productList.remove(index);
        try {
            readAndWrite.writeFile(productList);
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void displayById(int id) {
        for (Product product : productList){
            if (id == product.getId()){
                System.out.println(product);
            }
        }
    }

    @Override
    public void displayAll() {
        for (Product product : productList){
           if (product.getAmount() > 0){
               System.out.println(product);
           }
        }
    }

    Comparator<Product> compareUp = new Comparator<Product>() {
        @Override
        public int compare(Product o1, Product o2) {
             return (int) (o1.getPrice() - o2.getPrice());
        }
    };

    Comparator<Product> compareDown = new Comparator<Product>() {
        @Override
        public int compare(Product o1, Product o2) {
            return (int) (o2.getPrice() - o1.getPrice());
        }
    };




}
