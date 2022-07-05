package model;

import java.io.Serializable;

public class Staff extends User implements Serializable {
    public static int ID_Staff = 1;
    protected  int id;
    protected int salary;
    protected String position;



    public Staff(String fullName, String phoneNumber, String address, Bank bank, Account account, int salary, String position) {
        super(fullName, phoneNumber, address, account, bank);
        this.id = ID_Staff ++;
        this.salary = salary;
        this.position = position;
    }

    public static int getID_Staff() {
        return ID_Staff;
    }

    public static void setID_Staff(int ID_Staff) {
        Staff.ID_Staff = ID_Staff;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Nhân viên: {" +
                "id=" + id +
                ", Họ và tên: '" + fullName + '\'' +
                ", Số điện thoai: '" + phoneNumber + '\'' +
                ", Địa chỉ: '" + address + '\'' +
                '\n' +
                " Chức vụ: '" + position + '\'' +
                ", Tiền lương: " + salary +
                ", Tài khonar" + account +
                '}';
    }
}
