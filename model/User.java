package model;

import java.io.Serializable;

public class User  implements Serializable {
    public static int ID_User = 1;
    protected int id;
    protected String fullName;
    protected String phoneNumber;
    protected String address;
    protected Account account;
    protected Bank bank;

    public User() {
    }

    public User( String fullName, String phoneNumber, String address, Account account, Bank bank) {
        this.id = ID_User ++;
        this.fullName = fullName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.account = account;
        this.bank = bank;
    }

    public static int getID_Product() {
        return ID_User;
    }

    public static void setID_User(int id) {
        ID_User = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Bank getBank() {
        return bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", Họ và tên: '" + fullName + '\'' +
                ", Số điện thoại: " + phoneNumber +
                ", Địa chỉ liên hệ: '" + address + '\'' +
                '\n' +
                "Số tài khoản: '" + bank.accountNumber + '\'' +
                ", Số dư toàn khoản: " + bank.getMoney() +
                ", Mã pin: '" + bank.getCode() + '\'' +
                ", Tài khoản: " + account +
                '}';
    }
}
