package model;

import java.io.Serializable;

public class Order implements Serializable {
    public static int ID_Order = 1;
   protected int id;  // ID tự tăng
   protected long count;  // Số lượng mua hàng
   protected Product product; // Thông tin sản phẩm
   protected long totalPrice;
   protected String name;
   protected String phoneNumber;
   protected String address;
   protected Account account;

    public Order(Account account) {
        this.account = account;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Order() {
    }

    public Order(long count, Product product, long totalPrice, Account account) {
        this.id = ID_Order ++;
        this.count = count;
        this.product = product;
        this.totalPrice = totalPrice;
        this.account = account;
    }

    public static int getID_Order() {
        return ID_Order;
    }

    public static void setID_Order(int ID_Order) {
        Order.ID_Order = ID_Order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }



    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public long getTotalPrice() {
        return totalPrice = count * product.getPrice();
    }

    public void setTotalPrice(long totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public String toString() {
        return "Đơn hàng: {" +
                " Tên sản phẩm: " + product.getName_product() +
                ", Thương hiệu: " + product.getBrand().getNameBrand() +
                ", Màu sắc: " + product.getColor() +
                ", Giá sản phẩm: " + product.getPrice() +
                ", Số lượng mua: " + count +
                ", Thành tiền: " + totalPrice + " VNĐ" +
                '}';
    }
    public String string(){
        return  "Đơn hàng{" +
                ", Tên người dùng: " + name +
                ", Số điện thoại: " + phoneNumber +
                ", Địa chỉ nhận hàng: " + address +
                "\n" +
                "Tên sản phẩm: " + product.getName_product() +
                ", Thương hiệu: " + product.getBrand().getNameBrand() +
                ", Màu sắc: " + product.getColor() +
                ", Giá sản phẩm: " + product.getPrice() +
                ", Số lượng mua: " + count +
                ", Thành tiền: " + totalPrice + " VNĐ" +
                '}';
    }

}
