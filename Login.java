import service.Store_Manage;

import java.util.Scanner;

public class Login {


    public static void main(String[] args) {
        Store_Manage manage = new Store_Manage();
//        manage.addAccount();
        Login login = new Login();
        login.login();
    }
    public void login(){
        Store_Manage manage = new Store_Manage();
        Scanner scanner = new Scanner(System.in);
        RunByAdmin admin = new RunByAdmin();
        RunByUser user = new RunByUser();
        String account;
        String password;
        int count = 0;
        do {
            System.out.println("Tài khoản: ");
            account = scanner.nextLine();
            System.out.println("Mật khẩu: ");
            password = scanner.nextLine();
            if ( !manage.checkAccount(manage.method_account.accountList, account, password) && !manage.checkAdmin(account,password)){
                System.out.println("----------------------------------------------");
                System.out.println("Tài khoản hoặc mật khẩu không chính xác !!!");
                count ++;
            }
            if (manage.checkAccount(manage.method_account.accountList, account, password)) {
                user.menuUser(account);
                count = 1;
                break;
            } else if (manage.checkAdmin(account,password)){
                admin.menuAdmin();
                count = 1;
                break;
            }
        }while (count != 3 && !manage.checkAccount(manage.method_account.accountList, account, password) || (account.equals("admin") || password.equals("admin")));
        if (count == 3){
            System.out.println("----------------------------------------------");
            System.out.println("Bạn đã nhập sai 3 lần vui lòng thử lại sau !!!");
        }

    }
}


