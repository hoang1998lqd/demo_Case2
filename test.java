import model.*;
import service.Method_Brand;
import service.Method_Product;
import service.Store_Manage;
import java.text.SimpleDateFormat;
import java.util.*;


public class test {
    Method_Brand method_brand = new Method_Brand();
    Method_Product method_product = new Method_Product();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Store_Manage store_manage = new Store_Manage();
        test test = new test();
        do {
            System.out.println("1.Hello");
            System.out.println("2.Bye");
            System.out.println("Mời nhập lựa chọn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    System.out.println("Hello");
                    break;
                case 2:
                    System.out.println("Bye");
                    break;
            }
        }while (true);
//        Brand brand = new Brand("Dell");
//        Account account = new Account("hoang1998","hoang123");
//        Account account1 = new Account("thao130694","hoang123");
//        Account account2 = new Account("abcxyz","hoang123");
//        ArrayList<Account> accounts = new ArrayList<>();
//        store_manage.method_account.accountList.add(account);
//        store_manage.method_account.accountList.add(account1);
//        store_manage.method_account.accountList.add(account2);
//
//        User user = new User("Vũ Hoàng","0344550559","Nam Định",account);
//        User user1 = new User("Phạm Thao","0344550559","Nam Định",account1);
//        User user2 = new User("Thiều Ánh","0344550559","Nam Định",account2);
//
//        ArrayList<User> users = new ArrayList<>();
//        users.add(user);
//        users.add(user1);
//        users.add(user2);
//
//        Product product = new Product("Laptop Dell Gamming G15 5510", 20000000,30,"Red",brand);
//        order order = new order(1,product,40000000,"Vũ Huy Hoàng","0344550559","Nam Định");
//        order order1 = new order(3,product,23000000,"Phạm Ngọc Thao","0344550559","Nam Định");
//        order order2 = new order(2,product,20000000,"Thiều Thị Ngọc Ánh","0344550559","Nam Định");
//
//
//        ArrayList<order> list = new ArrayList<>();
//        list.add(order);
//        list.add(order1);
//        list.add(order2);
////       for (model.order order3 : list){
////           System.out.println(order3.string());
////       }
////
////       Store store  =new Store(users,list);
////
////        System.out.println(store.turnover());
////        System.out.println(store);
//        Date date = new Date();
//        String date1 = "15-12-1998";
//        test test = new test();
//        date = test.chuyenStringDate(date1);
////        System.out.println(date);
//        String num = "123";
//        int a = Integer.parseInt(num) + 15;
//        System.out.println(a);



      }
    public Date tranStringDate(String str)
    {
        Date ns=null;
        SimpleDateFormat sdf=new SimpleDateFormat("dd-MM-yyyy");
        try
        {
            ns=sdf.parse(str);
        }
        catch(Exception e)
        {
            System.out.println("Loi dinh dang ngay thang.!");
        }
        return ns;
    }

    public void pay(Scanner scanner){
        Bank bank = new Bank("123456",200,"1456");
        int a = bank.getMoney();
        System.out.println("Nhập số vào đây");
        int b =Integer.parseInt(scanner.nextLine());
        if (a >= 300){
            if (b == 300){
                System.out.println("Ok");
            }else {
                System.out.println("Thất bại");
                pay(scanner);
            }
        }else {
            System.out.println("Thất bại con mẹ nó rồi");
            System.out.println("-------------------");
            System.out.println("Nạp thêm");
            int c = Integer.parseInt(scanner.nextLine());
            int e = a + c;
            bank.setMoney(e);
            pay(scanner);
        }

    }





    }
