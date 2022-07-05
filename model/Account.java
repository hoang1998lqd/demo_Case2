package model;

import java.io.Serializable;

public class Account implements Serializable {
    public static int ID_Account = 1;
    protected int id;
    protected String account;
    protected String password;

    public Account() {
    }


    public Account(String account, String password) {
        this.id = ID_Account++;
        this.account = account;
        this.password = password;
    }

    public static int getID_Account() {
        return ID_Account;
    }

    public static void setID_Account(int ID) {
        ID_Account = ID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "account{" +
                "id=" + id +
                ", Tài khoản: '" + account + '\'' +
                ", Mật khẩu: '" + password + '\'' +
                '}';
    }
}
