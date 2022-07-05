package service;

import model.*;

import java.io.Serializable;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Store_Manage implements Serializable {
    public final Method_Account  method_account = new Method_Account();
    public final Method_Brand method_brand = new Method_Brand();
    public final Method_Product method_product = new Method_Product();
    public final Method_User method_user = new Method_User();
    public final Method_Order method_oder = new Method_Order();
    public final Method_Bank method_bank = new Method_Bank();
    public final Method_Bill method_bill = new Method_Bill();
    public final Orders_ReadAndWrite readAndWrite = new Orders_ReadAndWrite();


    protected final Scanner scanner = new Scanner(System.in);


    //----------------------Account-----------------------------
    // Check Login
    public boolean checkAccount(ArrayList<Account> list, String account1, String password){
        for (Account account : list){
            if (account.getAccount().equals(account1) && account.getPassword().equals(password)){
                return true;
            }
        }
        return false;
    }

    public boolean checkAdmin(String account1, String password1){
        return account1.equals("admin") && password1.equals("admin");
    }


    //Check Account theo yêu cầu.
    public static boolean checkAccountByChar(String account){
        String regex = "^[a-zA-Z\\d]+[a-zA-Z\\d]\\w{6,30}";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(account);
        return matcher.find();

    }

    public Account addAccount(){
        Account account = creatAccount();
        System.out.println("Tạo mới tài khoản thành công !!!");
        System.out.println("Nhập thông tin người dùng: ");
        addUser(account);
        return method_account.add(account);
    }

    public Account creatAccount(){
        System.out.println("-------------------------");
        String name;
        do {
            System.out.println("Nhập tên tài khoản: ");
            name = scanner.nextLine();
        }while (method_account.checkAccount(name) && !checkAccountByChar(name));
        String pass;
        do {
            System.out.println("Nhập mật khẩu: ");
            pass = scanner.nextLine();
        }while (!checkAccountByChar(pass));
        if (method_account.accountList.size() > 0){
            Account.ID_Account = method_account.accountList.get(method_account.accountList.size()-1).getId() + 1;

        }
        return new Account(name,pass);
    }

    public void editPassword(){
        System.out.println("-------------------------");
        System.out.println("Nhập ID cần thay đổi mật khẩu: ");
        int id = Integer.parseInt(scanner.nextLine());
        Account account = method_account.getById(id);
        String password;
        do {
            System.out.println("Nhập mật khẩu mới: ");
            password = scanner.nextLine();
        }while (!checkAccountByChar(password));
        account.setPassword(password);
        method_account.update(account);
    }

    public void deleteById(){
        System.out.println("-------------------------");
        System.out.println("Nhập ID tài khoản cần xóa: ");
        int id = Integer.parseInt(scanner.nextLine());
        Account account = method_account.deleteById(id);
        if (account != null){
            System.out.println("Xóa tài khoản thành công !!!");
        }else {
            System.out.println("Không tìm thấy ID theo yêu cầu !!!");
        }
    }

    public void displayByIdAccount(){
        System.out.println("-------------------------");
        System.out.println("Nhập ID bạn cần hiển thị: ");
        int id = Integer.parseInt(scanner.nextLine());
        method_account.displayById(id);
    }

    public void displayAllAccount(){
        method_account.displayAll();
    }

    // --------------------------------Bank-----------------------------

    public static boolean checkAccountNumber(String number){
        String regex = "^0[3-9][1-9]\\d{10}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(number);
        return matcher.find();
    }
    public static boolean checkBankCode(String number){
        String regex = "^\\d{6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(number);
        return matcher.find();
    }
    public Bank addBank(){
        Bank bank = creatBank();
        System.out.println("Liên kết tài khoản thành công !!!");
         method_bank.add(bank);
         return bank;
    }

    public Bank creatBank(){
        System.out.println("-------------------------");
        String accountNumber;
        do {
            System.out.println("Nhập số tài khoản ngân hàng : ");
            accountNumber = scanner.nextLine();
        }while (method_bank.checkAccountNumber(accountNumber) && !checkAccountNumber(accountNumber));
        int money = 0;
        if (method_bank.bankList.size() > 0){
            Bank.ID_Bank = method_bank.bankList.get(method_bank.bankList.size()-1).getId() + 1;
        }
        String code;
        do {
            System.out.println("Nhập mã PIN tài khoản ngân hàng : ");
            code = scanner.nextLine();
        }while (!checkBankCode(code));

        return new Bank(accountNumber,money,code);
    }

    public void updateMoney(){
        System.out.println("-------------------------");
        System.out.println("Nhập ID tài khoản: ");
        int id = Integer.parseInt(scanner.nextLine());
        User user = method_user.getById(id);
        Bank bank = method_bank.getById(id);
        String code;
        int count = 0;
        do {
            System.out.println("Nhập mã PIN tài khoản");
            code = scanner.nextLine();
            if (method_bank.checkPin(code)){
                String money;
                int moneyUpdate;
                do {
                    System.out.println("Nhập số tiền bạn cần nạp : ");
                    money = scanner.nextLine();
                    moneyUpdate = Integer.parseInt(money) + user.getBank().getMoney();
                }while (checkAccountNumber(money));
                user.getBank().setMoney(moneyUpdate);
                bank.setMoney(moneyUpdate);
                method_bank.update(bank);
                method_user.update(user);
                break;
            }else {
                count ++;
            }
        }while (count != 3);
        if (count == 3){
            System.out.println("Bạn đã nhập sai quá 3 lần xin vui lòng thử lại sau !!!");
        }
    }
    public void  displayBank(){
        method_bank.displayAll();
    }

    // Khi nhấn vào thanh toán sẽ có 2 hình thức thanh toán là COD và Online
    // Sau khi nhấn thanh toán sẽ trở ra Menu cửa hàng ban đầu.
    public void statusOrder(String account){
       do {
           System.out.println("Mời bạn chọn hình thức thanh toán: ");
           System.out.println("1. Thanh toán trực tuyến...");
           System.out.println("2. Thanh toán trực tiếp...");
           int choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    payOnline(account);
                    break;
                case 2:
                    payCOD(account);
                    break;
            }
       }while (true);
    }

    // ---------------------------Thanh toán---------------------------------
    public void payCOD(String account){
        System.out.println("Bạn đã đặt hàng thành công!!!");
        System.out.println("Đơn hàng đang được chuyển tới bạn trong vài ngày tới.........");
        updateBillByCOD(account);
        displayBillByAccount(account);
    }
    public void payOnline(String account){
        System.out.println("Bạn đã đặt hàng thành công!!!");
        System.out.println("Đơn hàng đang được chuyển tới bạn trong vài ngày tới.........");
        updateBillByOnline(account);
        displayBillByAccount(account);
    }



    //-----------------------Users---------------------------

    public static boolean checkPhoneByChar(String phone){
        String regex = "^0[3-9][1-9]\\d{7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(phone);
        return matcher.find();
    }

    public User addUser(Account account){
        User user = creatUser(account);
        System.out.println("Tạo mới người dùng thành công !!!");
        method_user.add(user);
        return user;
    }

    public User creatUser(Account account){
        System.out.println("-------------------------");
        System.out.println("Nhập tên người dùng: ");
        String name = scanner.nextLine();
        String phone;
        do {
            System.out.println("Nhập số điện thoại liên hệ: ");
            phone =scanner.nextLine();
        }while (!checkPhoneByChar(phone) && !method_user.checkPhoneInList(phone));
        System.out.println("Nhập địa chỉ liên hệ: ");
        String address = scanner.nextLine();
        if (method_user.UserList.size() > 0){
            User.ID_User = method_user.UserList.get(method_user.getSize()-1).getId() + 1 ;
        }
        System.out.println("---------------------------------");
        System.out.println("Liên kết tài khoản ngân hàng !!!");
        System.out.println("---------------------------------");
        Bank bank = addBank();
        return new User(name,phone,address,account,bank);
    }

    public void editUser(){
        System.out.println("-------------------------");
        System.out.println("Nhập ID người dùng cần thay đổi: ");
        int id = Integer.parseInt(scanner.nextLine());
        User user = method_user.getById(id);
        System.out.println("Nhập tên mới: ");
        String name = scanner.nextLine();
        user.setFullName(name);
        String phone;
        do {
            System.out.println("Nhập số điện thoại liên hệ: ");
            phone =scanner.nextLine();
        }while (!checkPhoneByChar(phone) && method_user.checkPhoneInList(phone));
        user.setPhoneNumber(phone);
        System.out.println("Nhập địa chỉ mới: ");
        String address = scanner.nextLine();
        user.setAddress(address);
        method_user.update(user);
    }

    public void deleteUserById(){
        System.out.println("-------------------------");
        System.out.println("Nhập ID người dùng bạn cần xóa");
        int id = Integer.parseInt(scanner.nextLine());
        User user = method_user.deleteById(id);
        Bank bank = method_bank.deleteById(id);
        Account account = method_account.deleteById(id);
        if (user != null && bank !=null && account != null){
            System.out.println("Xóa người dùng thành công !!!");
        }else {
            System.out.println("Xóa người dùng thất bại do không tìm thấy ID theo yêu cầu !!!");
        }
    }

    public void displayUserByAccount(String account){
        System.out.println("-------------------------");
            for (User user : method_user.UserList){
               if  (user.getAccount().getAccount().equals(account)) {
                   System.out.println(user);
               }
            }

        }

    public void displayUserById(){
        System.out.println("-------------------------");
        System.out.println("Nhập ID người dùng cần hiển thị");
        int id = Integer.parseInt(scanner.nextLine());
        method_user.displayById(id);
    }

    public void displayAllUser(){
        method_user.displayAll();
    }


    //----------------------Brand----------------------------

    public Brand addBrand(){
        Brand brand = creatBrand();
        System.out.println("Tạo mới thương hiệu thành công !!!");
        return method_brand.add(brand);
    }

    public Brand creatBrand(){
        System.out.println("-------------------------");
        System.out.println("Nhập tên thương hiệu laptop: ");
        String nameBrand = scanner.nextLine();
        if (method_brand.brandList.size() > 0){
            Brand.ID_BRAND = method_brand.brandList.get(method_brand.getSize() - 1).getId() + 1;
        }
        return new Brand(nameBrand);
    }

    public void editBrand(){
        System.out.println("-------------------------");
        System.out.println("Nhập ID cần thay đổi : ");
        int id = Integer.parseInt(scanner.nextLine());
        Brand brand = method_brand.getById(id);
        System.out.println("Nhập tên thương hiệu mới : ");
        String name = scanner.nextLine();
        brand.setNameBrand(name);
        method_brand.update(brand);
    }

    public void deleteByIdBrand(){
        System.out.println("-------------------------");
        System.out.println("Nhập ID thương hiệu cần xóa: ");
        int id = Integer.parseInt(scanner.nextLine());
        Brand brand = method_brand.deleteById(id);
        if (brand != null){
            System.out.println("Xóa thương hiệu thành công !!!");
        }else {
            System.out.println("Không tìm thấy ID theo yêu cầu !!!");
        }
    }

    public void displayByIdBrand(){
        System.out.println("-------------------------");
        System.out.println("Nhập ID bạn cần hiển thị: ");
        int id = Integer.parseInt(scanner.nextLine());
        method_brand.displayById(id);
    }

    public void showListBrand(){
        System.out.println("Lựa chọn thương hiệu cho sản phẩm: ");
        ArrayList<Brand> brands = method_brand.getBrandList();
        for (Brand brand : brands){
            System.out.println(brand.getId() + ". " + brand.getNameBrand());
        }
        System.out.println("0. Tạo thương hiệu mới...");
    }

    public void displayAllBrand(){
        method_brand.displayAll();
    }

    //------------------------Products---------------------------

    public Product addProduct(){
        Product product = creatProduct();
        System.out.println("Tạo mới sản phẩm thành công !!!");
        method_product.add(product);
        return product;
    }

    public Product creatProduct(){
        System.out.println("-------------------------");
        System.out.println("Nhập tên sản phẩm: ");
        String name = scanner.nextLine();
        System.out.println("Nhập giá tiền của sản phẩm: ");
        long price = Long.parseLong(scanner.nextLine());
        System.out.println("Nhập số lượng sản phẩm có trong cửa hàng: ");
        int amount = Integer.parseInt(scanner.nextLine());
        System.out.println("Nhập màu sắc cho sản phẩm: ");
        String color = scanner.nextLine();
        Brand brand = null;
        showListBrand();
        do {
            System.out.println("-------------------------");
            System.out.println("Nhập thương hiệu cho sản phẩm: ");
            int choice =Integer.parseInt(scanner.nextLine());
            if  (choice == 0){
                System.out.println("Nhập tên thương hiệu mới: ");
                addBrand();
                showListBrand();
                continue;
            }
            if (method_brand.getById(choice) == null){
                System.out.println("Lựa chọn không đúng !!");
            }
            brand = method_brand.getById(choice);
        }while (brand == null);
        if (method_product.productList.size() > 0){
            Product.ID_Product = method_product.productList.get(method_product.productList.size() - 1).getId() + 1 ;
        }
        return new Product(name,price,amount,color,brand);
    }

    public void editProduct(){
        System.out.println("-------------------------");
        System.out.println("Nhập ID bạn cần sửa thông tin sản phẩm: ");
        int id = Integer.parseInt(scanner.nextLine());
        Product product = method_product.getById(id);
        System.out.println("Nhập tên mới cho sản phẩm: ");
        String name = scanner.nextLine();
        product.setName_product(name);
        System.out.println("Nhập giá mới cho sản phẩm: ");
        long price = Long.parseLong(scanner.nextLine());
        product.setPrice(price);
        System.out.println("Nhập số lượng sản phẩm: ");
        int amount = Integer.parseInt(scanner.nextLine());
        product.setAmount(amount);
        displayAllBrand();
        int choice = Integer.parseInt(scanner.nextLine());
        Brand brand = method_brand.getById(choice);
        product.setBrand(brand);
        method_product.update(product);
    }

    public void deleteByIdProduct(){
        System.out.println("-------------------------");
        System.out.println("Nhập ID bạn cần xóa: ");
        int id = Integer.parseInt(scanner.nextLine());
        Product product = method_product.deleteById(id);
        if (product != null){
            System.out.println("Xóa sản phẩm thành công !!!");
        }
        else {
            System.out.println("Xóa sản phẩm thất bại do không tìm được ID !!!");
        }
    }

    public void displayProductById(){
        System.out.println("-------------------------");
        System.out.println("Nhập ID sản phẩm bạn muốn hiển thị: ");
        int id = Integer.parseInt(scanner.nextLine());
        method_product.displayById(id);
    }

    public void displayAllProduct(){
        method_product.displayAll();
        displayZero();
    }
    // Thay đổi cách hiển thị sản phẩm
    public void displayZero(){
        for (Product product : method_product.productList){
            if (product.getAmount() == 0){
                System.out.println("Product{" +
                        "ID=" + product.getId() +
                        ", Tên sản phẩm: '" + product.getName_product() + '\'' +
                        ", Giá tiền: " + product.getPrice() +
                        ", Số lượng: " + "Tạm thời hết hàng" +
                        ", Màu sắc: '" + product.getColor() + '\'' +
                        ", Thương hiệu: " + product.getBrand().getNameBrand() +
                        '}');
            }
        }
    }


    // Sắp xếp giá tăng dần hoặc giảm dần

    public void displayUp(){
        System.out.println("Giá sản phẩm được sắp xếp tăng dần !!!");
        method_product.productList.sort(method_product.compareUp);
        displayAllProduct();
    }

    public void displayDown(){
        System.out.println("Giá sản phẩm được sắp xếp giảm dần !!!");
        method_product.productList.sort(method_product.compareDown);
        displayAllProduct();
    }

    // Hiển thị theo từng thương hiệu

    public void displayByBrad(Scanner scanner){
        showListBrand();
        Brand brand;
        do {
            System.out.println("-------------------------");
            System.out.println("Nhập thương hiệu cần hiển thị: ");
            int choice =Integer.parseInt(scanner.nextLine());
            if (method_brand.getById(choice) == null){
                System.out.println("Lựa chọn không đúng !!");
            }
            brand = method_brand.getById(choice);
        }while (brand == null);
        for (Product product : method_product.productList){
            if (brand.getNameBrand().equals(product.getBrand().getNameBrand())){
                System.out.println(product);
            }
        }
    }

    public void displayProduct1(){
        boolean flag = false;
        for (Product product : method_product.productList){
            if (product.getPrice() > 15000000 && product.getPrice() < 20000000 ){
                flag = true;
                System.out.println(product);
                System.out.println("------------------------------------------");
            }
        }
        if (!flag){
            System.out.println("Không tìm thấy sản phẩm theo yêu cầu !!!");
            System.out.println("------------------------------------------");
        }
    }

    public void displayProduct2(){
        boolean flag = false;
        for (Product product : method_product.productList){
            if (product.getPrice() > 10000000 && product.getPrice() < 15000000 ){
                flag = true;
                System.out.println(product);
                System.out.println("------------------------------------------");
            }

        }
        if (!flag){
            System.out.println("Không tìm thấy sản phẩm theo yêu cầu !!!");
            System.out.println("------------------------------------------");
        }

    }

    public void displayProduct3(){
        boolean flag = false;
        for (Product product : method_product.productList){
            if (product.getPrice() > 20000000 ){
                flag = true;
                System.out.println(product);
                System.out.println("------------------------------------------");
            }
        }
        if (!flag){
            System.out.println("Hiện không có sản phẩm nào như yêu cầu của bạn !!! ");
            System.out.println("------------------------------------------");

        }
    }

    // Hiển thị theo khoản giá:
    // 15000000 - 20000000;
    // 10000000 - 15000000;
    // > 20000000;

    public void displayByPrice(Scanner scanner){
        int choice;
        do {
            System.out.println("Mời bạn lựa chọn hiển thị");
            System.out.println("1. Từ 10,000,000 - 15,000,000.");
            System.out.println("2. Từ 15,000,000 - 20,000,000.");
            System.out.println("3. Lớn hơn 20,000,000.");
            System.out.println("4. Sắp xếp giảm dần.");
            System.out.println("5. Sắp xếp tăng dần.");
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    displayProduct2();
                    break;
                case 2:
                    displayProduct1();
                    break;
                case 3:
                    displayProduct3();
                    break;
                case 4:
                    displayUp();
                    break;
                case 5:
                    displayDown();
                    break;
            }
        }while (choice != 0);
    }


    // -------------------------Orders-------------------------
    public Order addOrder(String account){
        Order order = creatOrder(account);
        System.out.println(order.toString());
        System.out.println("----------------------------------");
        System.out.println("Bạn đã đặt hàng thành công !!! ");
        System.out.println("----------------------------------");
        method_oder.add(order);
        return order;
    }

    // Kiểm tra số lượng hàng còn trong kho > 0 và số lượng mua phải nhỏ hơn số lương hàng trong kho mới đặt mua được;
    public boolean checkAmount(Product product, long count){
        return count <= product.getAmount() && product.getAmount() != 0;
    }
    public Order creatOrder(String account){
        System.out.println("---------------------------");
        Account account1 = method_account.getAccountByString(account);
        System.out.println("Nhập ID sản phẩm bạn muốn mua: ");
        int idProduct = Integer.parseInt(scanner.nextLine());
        Product product = method_product.getById(idProduct);
        long count;
        do {
            System.out.println("Nhập số lượng cần mua: ");
            count = Long.parseLong(scanner.nextLine());
        }while (!checkAmount(product,count));
        long totalPrice = product.getPrice() * count;
        if (method_oder.orderList.size() > 0){
            Order.ID_Order = method_oder.orderList.get(method_oder.orderList.size() - 1).getId() + 1 ;
        }
        return new Order(count,product,totalPrice,account1);
    }

    // Tìm Order thông qua Account
    public ArrayList<Order> getOrderByAccount(String account){
        ArrayList<Order> orders = new ArrayList<>();
        for (Order order : method_oder.orderList){
            if (account.equals(order.getAccount().getAccount())){
                orders.add(order);
            }
        }
        return orders;
    }


    // Tìm User thông qua Account
    public User getUserByAccount(String account){
        for (User user : method_user.UserList){
            if (account.equals(user.getAccount().getAccount())){
                return user;
            }
        }
        return null;
    }

    // Tính tổng tiền theo tài khoản
    public int totalAllPrice(String account){
        ArrayList<Order> orders = getOrderByAccount(account);
        int totalAllPrice = 0;
        for (Order order : orders) {
            totalAllPrice += order.getTotalPrice();
        }
        return totalAllPrice;
    }
    public void editOrder(){
        System.out.println("-------------------------");
        System.out.println("Nhập ID cần thay đổi: ");
        int id = Integer.parseInt(scanner.nextLine());
        Order order = method_oder.getById(id);
        System.out.println("Nhập số lượng mua hàng: ");
        long count = Long.parseLong(scanner.nextLine());
        order.setCount(count);
        System.out.println("Nhập ID sản phẩm bạn muốn mua: ");
        int idProduct = Integer.parseInt(scanner.nextLine());
        Product product = method_product.getById(idProduct);
        long totalPrice = count * product.getPrice();
        order.setTotalPrice(totalPrice);
    }

    public void deleteOrderByIdz(){
        System.out.println("-------------------------");
        System.out.println("Nhập ID người dùng bạn cần xóa");
        int id = Integer.parseInt(scanner.nextLine());
        Order order = method_oder.deleteById(id);
        if (order != null){
            System.out.println("Xóa đơn hàng thành công !!!");
        }else {
            System.out.println("Xóa đơn hàng thất bại do không tìm thấy ID theo yêu cầu !!!");
        }
    }

    public void displayAllOrder(){
        method_oder.displayAll();
    }

    public void displayOrderById(){
        System.out.println("-------------------------------");
        System.out.println("Nhập ID bạn cần hiển thị: ");
        int id = Integer.parseInt(scanner.nextLine());
        method_oder.displayById(id);
    }

    // Hiển thị thông tin đơn hàng theo người dùng

    public void displayOrderByAccount (String account){
        System.out.println("------------------------");
        for (Order order : method_oder.orderList){
            if (order.getAccount().getAccount().equals(account)){
                System.out.println(order);
            }
        }
    }



    // ---------------------------------------Bills---------------------------------

    public  Bill addBill(String account){
        Bill bill = creatBill(account);
        method_bill.add(bill);
        return bill;
    }

    public Bill creatBill(String account){
        Account account1 = method_account.getAccountByString(account);
        User user = getUserByAccount(account);
        ArrayList<Order> orders = getOrderByAccount(account);
        int totalAllPrice = totalAllPrice(account);
        if (method_bill.BillList.size() > 0){
            Bill.ID_Bill = method_bill.BillList.get(method_bill.BillList.size()-1).getId() + 1 ;
        }
        String paymentStatus = "Chưa thanh toán";
        String orderStatus = "Đang xác thực";
        return new Bill(account1,user,orders,totalAllPrice,paymentStatus,orderStatus);
    }

    // Đặt hàng hộ.
    public Bill creatBillBook(String account){
        Account account1 = method_account.getAccountByString(account);
        ArrayList<Order> orders = getOrderByAccount(account);
        int total = totalAllPrice(account);
        System.out.println("Họ và tên người nhận: ");
        String name = scanner.nextLine();
        System.out.println("Nhập số điện thoại: ");
        String phone;
        do {
            System.out.println("Nhập số điện thoại liên hệ: ");
            phone = scanner.nextLine();
        }while (!checkPhoneByChar(phone) && !method_user.checkPhoneInList(phone));
        System.out.println("Nhập địa chỉ nhận hàng.");
        String address = scanner.nextLine();
        String paymentStatus = "Chưa thanh toán";
        String orderStatus = "Đang xác thực";
        return new Bill(account1,orders,total,paymentStatus,orderStatus,name,phone,address);
    }

    public  Bill addBillBook(String account){
        Bill bill = creatBillBook(account);
        method_bill.add(bill);
        return bill;
    }



    public Bill getBillByAccount(String account){
        for (Bill bill : method_bill.BillList){
            if (bill.getAccount().getAccount().equals(account)){
                return bill;
            }
        }
        return null;
    }
    public void updateBillByCOD(String account){
        Bill bill = getBillByAccount(account);
        bill.setOrderStatus("Đang vận chuyển hàng...");
        bill.setPaymentStatus("Thanh toán khi nhận hàng...");
        for (Order order : bill.getOrder()){
            int amount = order.getProduct().getAmount();
            int count = (int)order.getCount();
            int setAmount = amount - count;
            order.getProduct().setAmount(setAmount);
            method_product.update(order.getProduct());
        }
    }

    public void updateBillByOnline(String account){
        Bill bill = getBillByAccount(account);
        int money = bill.getUser().getBank().getMoney() - bill.getTotalAllPrice();
        bill.getUser().getBank().setMoney(money);
        Bank bank = bill.getUser().getBank();
        bank.setMoney(money);
        bill.setOrderStatus("Đang vận chuyển hàng...");
        bill.setPaymentStatus("Đã thanh toán...");
        for (Order order : bill.getOrder()){
            int amount = order.getProduct().getAmount();
            int count = (int)order.getCount();
            int setAmount = amount - count;
            order.getProduct().setAmount(setAmount);
            method_product.update(order.getProduct());
//           bill.getOrder().remove(order);
        }
    }


    // Hiển thị hóa đơn qua Account
    public void displayBillByAccount (String account){
        Bill bill = getBillByAccount(account);
        System.out.println(bill);
    }

    // Hiển thị tất cả hóa đơn

    public void displayAllBill (){
        method_bill.displayAll();
    }


//    public Bill totalMoney(String account){
//
//
//    }

    // Xác nhận nhận hàng thành công
    public void confirmOrder(String account){
        System.out.println("-------------------------------------");
        System.out.println("Cảm ơn bạn đã mua hàng ở cửa hàng chúng tôi !!!");
        Bill bill = getBillByAccount(account);
        bill.setOrderStatus("Đã nhận hàng thành công...");
        bill.setPaymentStatus("Đã thanh toán...");
    }

    //-------------------- Thanh toán hóa đơn----------------------
    // Kiểm tra phương thức tính tiền (Phương pháp đệ quy)
    public void paymentBill(String account){
        displayBillByAccount(account);
        User user = getUserByAccount(account);
        Bill bill = getBillByAccount(account);
        System.out.println("----------------------------");
        System.out.println("Mời bạn thanh toán hóa đơn");
        System.out.println("-----------------------------");
        System.out.println("Nhập số tiền cần thanh toán: ");
        int money = Integer.parseInt(scanner.nextLine());
        int moneyInBank = user.getBank().getMoney();
       if (moneyInBank >= bill.getTotalAllPrice()){
           if (money == bill.getTotalAllPrice()) {
               payOnline(account);
               method_oder.orderList.removeAll(getOrderByAccount(account));
               try{
                   readAndWrite.writeFile(method_oder.orderList);
               }catch (Exception e){
                   e.printStackTrace();
               }
           } else {
               System.out.println("Thanh toán thất bại!!!");
               paymentBill(account);
           }
       }else {
           System.out.println("Thanh toán thất bạn tài khoản của bạn không đủ để thanh toán");
           updateMoney();
           paymentBill(account);
       }
    }

    public void turnOver(){
        int money = 0;
        for (Bill bill : method_bill.BillList){
            money += bill.getTotalAllPrice();
        }
        System.out.println("Doanh thu của cửa hàng là: " + money + " VNĐ");

    }



    //----------------------- Đăng nhập-------------------------------------------------
    public void login(){
        String account;
        String password;
        int count = 0;
        do {
            System.out.println("Tài khoản: ");
            account = scanner.nextLine();
            System.out.println("Mật khẩu: ");
            password = scanner.nextLine();
            if ( !checkAccount(method_account.accountList, account, password) && !checkAdmin(account,password)){
                System.out.println("Tài khoản hoặc mật khẩu không chính xác !!!");
                count ++;
            }
            if (checkAccount(method_account.accountList, account, password)) {
                System.out.println("1.Hiển thị ");
                System.out.println("2. Đặt hàng");
               int choice = Integer.parseInt(scanner.nextLine());
               do {
                   switch (choice){
                       case 1:
                           displayByPrice(scanner);
                           break;
                       case 2:
                           addOrder(account);
                   }
               }while (choice != 0);
                count = 1;
                break;
            } else if (checkAdmin(account,password)){
                System.out.println("Đây là dòng lệnh được thực thi dưới quyền Admin !!!");
                count = 1;
                break;
            }
        }while (count != 3 && !checkAccount(method_account.accountList, account, password) || (account.equals("admin") || password.equals("admin")));
        if (count == 3){
            System.out.println("Bạn đã nhập sai 3 lần vui lòng thử lại sau !!!");
        }

    }

    public static void main(String[] args) {
        Store_Manage manage  = new Store_Manage();


    }

    public User account(String acc, User user){
        if  (user.getAccount().getAccount().equals(acc)){
            return user;
        }
        return null;
    }

}


