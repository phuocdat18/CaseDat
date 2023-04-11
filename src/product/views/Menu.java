package product.views;
import product.model.User;
import java.util.InputMismatchException;
import java.util.Scanner;


public class Menu {
    static ProductView productView = new ProductView();
    static PaymentView paymentView = new PaymentView();
    static Scanner scanner = new Scanner(System.in);

    private static User currentUser = null;
    private static boolean isFinished = false;


    public static void boss() {
        boolean isLoggedIn = checkLogin();
        if (!isLoggedIn) {
            login();
            return;
        }
        boolean isFinished = false;
        Scanner scanner = new Scanner(System.in);
        menuBoss();
        while (!isFinished) {
            try {
                System.out.println("\nChọn chức năng ");
                System.out.print("\t➺ ");
                int number = scanner.nextInt();
                switch (number) {
                    case 1:
                        productView.showProductBoss();
                        break;
                    case 2:
                        productView.showProductDescriptionBoss();
                        break;
                    case 3:
                        productView.add();
                        break;
                    case 4:
                        productView.updateProduct();
                        break;
                    case 5:
                        productView.findProductbyNameBoss();
                        break;
                    case 6:
                        productView.findProductbyTypeBoss();
                        break;
                    case 7:
                        paymentView.showAllIncome();
                        break;
                    case 8:
                        chon();
                        break;
                    case 9:
                        exit();
                        isFinished = true;
                        break;
                    default:
                        System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại");
                }
            } catch (InputMismatchException e) {
                System.out.println("Nhập sai! vui lòng nhập lại");
                scanner.nextLine();
            }
        }
    }


    public static User login() {
        Loginview loginView = new Loginview();
        boolean isLoggedIn = false;
        User user = null;
        while (!isLoggedIn) {
            try {
                user = loginView.login();
                isLoggedIn = true;
                currentUser = user;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Có lỗi khi đăng nhập. Vui lòng thử lại.");
                return null;
            }
        }
        return user;
    }

    public static boolean checkLogin() {
        if (currentUser == null) {
            System.out.println("Vui lòng đăng nhập trước khi sử dụng chức năng này!");
            return false;
        }
        return true;
    }


    public static void guest() {
        menuGuest();
        try {
            System.out.println("\nChọn chức năng ");
            System.out.print("\t➺ ");
            int number = scanner.nextInt();
            switch (number) {
                case 1:
                    productView.showProductGuest();
                    break;
                case 2:
                    productView.showProductDescriptionGuest();
                    break;
                case 3:
                    productView.findProductbyNameGuest();
                    break;
                case 4:
                    productView.findProductbyTypeGuest();
                    break;
                case 5:
                    productView.sortASC();
                    break;
                case 6:
                    productView.sortDESC();
                    break;
                case 7:
                    paymentView.buy();
                    break;
                case 8:
                    chon();
                    break;
                case 9:
                    exit();
                    break;
                default:
                    System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại");
                    guest();
            }
        } catch (Exception e) {
            System.out.println("Nhập sai! vui lòng nhập lại");
            guest();
        }
    }


    public static void chon() {
        do {
            menuMain();
            try {
                Scanner scanner = new Scanner(System.in);
                System.out.println("\nChọn chức năng ");
                System.out.print("\t➺ ");
                int number = scanner.nextInt();
                switch (number) {
                    case 1:
                        boss();
                        break;
                    case 2:
                        guest();
                        break;
                    case 3:
                        exit();
                        break;
                    default:
                        System.out.println("Chọn chức năng không đúng! Vui lòng chọn lại");
                        chon();
                }

            } catch (InputMismatchException io) {
                System.out.println("Nhập sai! Vui lòng nhập lại");
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } while (true);
    }

    public static void menuMain() {
        System.out.println();
        System.out.println("* * * * --Giao diện-- * * * * * *");
        System.out.println("*                               *");
        System.out.println("*       1.    Chủ               *");
        System.out.println("*       2.    Khách             *");
        System.out.println("*       3.    Thoát             *");
        System.out.println("*                               *");
        System.out.println("* * * * * * * * * * * * * * * * *");
    }

    public static void exit() {
        System.out.println("\t\t\t\t\t\tCám ơn quý khách");
        System.out.println("\t\t\t\t\t\t ✌ Hẹn gặp lại ✌");

        System.exit(0);
    }

    public static void menuBoss() {
        System.out.println("\u001B[35m╔══════════════════════════════════════════╗");
        System.out.println("\u001B[35m║              \u001B[1m\u001B[36mGiao diện chủ\u001B[0m               \u001B[35m║");
        System.out.println("\u001B[35m║                                          ║");
        System.out.println("\u001B[35m║   \u001B[1m1. Hiển thị danh sách Cổ Vật           \u001B[0m\u001B[35m║");
        System.out.println("\u001B[35m║   \u001B[1m2. Hiển thị mô tả Cổ vật               \u001B[0m\u001B[35m║");
        System.out.println("\u001B[35m║   \u001B[1m3. Thêm Cổ vật vào danh sách           \u001B[0m\u001B[35m║");
        System.out.println("\u001B[35m║   \u001B[1m4. Sửa thông tin Cổ vật                \u001B[0m\u001B[35m║");
        System.out.println("\u001B[35m║   \u001B[1m5. Tìm kiếm Cổ vật theo tên            \u001B[0m\u001B[35m║");
        System.out.println("\u001B[35m║   \u001B[1m6. Tìm kiếm Cổ vật theo loại           \u001B[0m\u001B[35m║");
        System.out.println("\u001B[35m║   \u001B[1m7. Xem doanh thu                       \u001B[0m\u001B[35m║");
        System.out.println("\u001B[35m║   \u001B[1m8. Quay lại                            \u001B[0m\u001B[35m║");
        System.out.println("\u001B[35m║   \u001B[1m9. Thoát                               \u001B[0m\u001B[35m║");
        System.out.println("\u001B[35m║                                          ║");
        System.out.println("\u001B[35m╚══════════════════════════════════════════╝\u001B[0m");
    }

    public static void menuGuest() {
        System.out.println("\u001B[35m╔══════════════════════════════════════════╗");
        System.out.println("\u001B[35m║            \u001B[1m\u001B[36mGiao diện Khách\u001B[0m               \u001B[35m║");
        System.out.println("\u001B[35m║                                          ║");
        System.out.println("\u001B[35m║   \u001B[1m1. Hiển thị danh sách Cổ vật           \u001B[0m\u001B[35m║");
        System.out.println("\u001B[35m║   \u001B[1m2. Hiển thị mô tả Cổ vật               \u001B[0m\u001B[35m║");
        System.out.println("\u001B[35m║   \u001B[1m3. Tìm kiếm Cổ vật theo tên            \u001B[0m\u001B[35m║");
        System.out.println("\u001B[35m║   \u001B[1m4. Tìm kiếm Cổ vật theo loại           \u001B[0m\u001B[35m║");
        System.out.println("\u001B[35m║   \u001B[1m5. Sắp xếp Cổ vật theo giá tăng dần    \u001B[0m\u001B[35m║");
        System.out.println("\u001B[35m║   \u001B[1m6. Sắp xếp Cổ vật theo giá giảm dần    \u001B[0m\u001B[35m║");
        System.out.println("\u001B[35m║   \u001B[1m7. Mua Cổ vật                          \u001B[0m\u001B[35m║");
        System.out.println("\u001B[35m║   \u001B[1m8. Quay lại                            \u001B[0m\u001B[35m║");
        System.out.println("\u001B[35m║   \u001B[1m9. Thoát                               \u001B[0m\u001B[35m║");
        System.out.println("\u001B[35m║                                          ║");
        System.out.println("\u001B[35m╚══════════════════════════════════════════╝\u001B[0m");
    }

}
