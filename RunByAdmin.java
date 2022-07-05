import service.Store_Manage;

import java.util.InputMismatchException;
import java.util.Scanner;

public class RunByAdmin {
    Store_Manage manage = new Store_Manage();
    Scanner scanner = new Scanner(System.in);
    Login login = new Login();

    public RunByAdmin() {
    }
    public void menuAdmin(){
        try {
            int choice ;
            do {
                System.out.println("---------- QUYỀN ADMIN ----------");
                System.out.println("1. Thêm sản phẩm");
                System.out.println("2. Sản phẩm"); // CRUD Brand và Product
                System.out.println("3. Xóa sản phẩm");
                System.out.println("4. Hiển thị sản phẩm"); // tạo lựa chọn theo cách hiển thị
                System.out.println("5. Thông tin người dùng");
                System.out.println("6. Thông tin đơn hàng (Bill)");
                System.out.println("7. Tổng doanh thu");
//        System.out.println("7. Nhân viên");
//        System.out.println("8. Tổng doanh thu");
                System.out.println("0. Đăng xuất...");
                System.out.println("Mời bạn nhập lựa chọn !!!");
                choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        manage.addProduct();
                        break;
                    case 2:
                        choiceProduct();
                        break;
                    case 3:
                        manage.deleteByIdProduct();
                        break;
                    case 4:
                        displayProducts();
                        break;
                    case 5:
                        userByAdmin();
                        break;
                    case 6:
                        billByAdmin();
                        break;
                    case 7:
                        manage.turnOver();
                        break;
                    case 0:
                        login.login();
                        break;
                }
            } while (true);
        }catch (InputMismatchException e){
            System.out.println("---------------------------");
            System.out.println("Bạn đã nhập sai dữ liệu. Vui lòng nhập lại...");
            menuAdmin();
        }
    }

    private void choiceProduct(){
        int choice;
      do {
          System.out.println("----------Product----------");
          System.out.println("1. Sửa sản phẩm");
          System.out.println("2. Xóa sản phẩm");
          System.out.println("3. Sửa thương hiệu");
          System.out.println("0. Trở lại Menu");
          System.out.println("Mời bạn nhập lựa chọn !!!");
          choice = Integer.parseInt(scanner.nextLine());
          switch (choice){
              case 1:
                  manage.editProduct();
                  break;
              case 2:
                  manage.deleteByIdProduct();
                  break;
              case 3:
                  manage.editBrand();
                  break;
              case 0:
                  menuAdmin();
                  break;
          }
      }while (true);

    }

    private void displayProducts(){
        int choice;
        do {
            System.out.println("---------- Display Product ----------");
            System.out.println("1. Hiển thị tất cả");
            System.out.println("2. Hiển thị sản phẩm đã hết hàng");
            System.out.println("0. Trở lại Menu");
            System.out.println("Mời bạn nhập lựa chọn !!!");
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    manage.displayAllProduct();
                    break;
                case 2:
                    manage.displayZero();
                    break;
                case 0:
                    menuAdmin();
                    break;
            }
        }while (true);
    }

    public void userByAdmin(){
        int choice;
        do {
            System.out.println("---------- Display Users ----------");
            System.out.println("1. Hiển thị tất cả");
            System.out.println("2. Xóa người dùng");
            System.out.println("0. Trở lại Menu");
            System.out.println("Mời bạn nhập lựa chọn !!!");
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    manage.displayAllUser();
                    break;
                case 2:
                    manage.deleteUserById();
                    break;
                case 0:
                    menuAdmin();
                    break;
            }
        }while (true);
    }

    public void billByAdmin(){
        int choice;
        do {
            System.out.println("---------- Display Bills ----------");
            System.out.println("1. Hiển thị tất cả");
            System.out.println("2. Hiển thị hóa đơn theo tài khoản");
            System.out.println("0. Trở lại Menu");
            System.out.println("Mời bạn nhập lựa chọn !!!");
            choice = Integer.parseInt(scanner.nextLine());
            switch (choice){
                case 1:
                    manage.displayAllBill();
                    break;
                case 2:
                    System.out.println("Nhập tài khoản bạn cần kiểm tra: ");
                    String account = scanner.nextLine();
                    manage.displayBillByAccount(account);
                    break;
                case 0:
                    menuAdmin();
                    break;
            }
        }while (true);
    }

}
