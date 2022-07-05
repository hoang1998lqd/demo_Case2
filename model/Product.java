package model;

import java.io.Serializable;
import java.util.Comparator;

public class Product implements Serializable {
    public static int ID_Product = 1;
    protected int id;
    protected String name_product;
    protected long price;
    protected int amount;
    protected String color;
    protected Brand brand;

    public Product() {
    }

    public Product(String name_product, long price, int amount, String color, Brand brand) {
        this.id = ID_Product++;
        this.name_product = name_product;
        this.price = price;
        this.amount = amount;
        this.color = color;
        this.brand = brand;
    }

    @Override
    public String toString() {
        return "Product{" +
                "ID=" + id +
                ", Tên sản phẩm: '" + name_product + '\'' +
                ", Giá tiền: " + price +  " " + "VNĐ" +
                ", Số lượng: " + amount +
                ", Màu sắc: '" + color + '\'' +
                ", Thương hiệu: " + brand.getNameBrand() +
                '}';
    }

    public static int getID_Product() {
        return ID_Product;
    }

    public static void setID_Product(int ID_Product) {
        Product.ID_Product = ID_Product;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_product() {
        return name_product;
    }

    public void setName_product(String name_product) {
        this.name_product = name_product;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}

