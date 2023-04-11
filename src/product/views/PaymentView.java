package product.views;

import product.model.Payment;
import product.model.Product;
import product.services.PaymentService;
import product.services.ProductService;
import product.utils.ValidateUtils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class PaymentView {
    DecimalFormat format = new DecimalFormat("###,###,###" + " đ");
    Scanner scanner = new Scanner(System.in);
    PaymentService paymentService = new PaymentService();
    ProductService productService = new ProductService();
    List<Payment> list = new ArrayList<>();
    Menu menu = new Menu();
    int id1 = 0;

    int quantityPurchased = 0;

    String name;
    String phone;
    String address;
    String money;
    long revenue;
    int realQuantity;
    int quantity;

    int currentQuantity;


    Product product = null;


    public PaymentView() {
    }


    public void buy() {
        while (true) {

            System.out.println("Nhập mã loại Cổ vật muốn mua");
            System.out.print("\t➺ ");
            try {
                id1 = Integer.parseInt(scanner.nextLine());
                if (id1 > 0) {
                    if (paymentService.existProduct(id1)) {
                        product = paymentService.findProductbyID(id1);
                        break;
                    } else {
                        System.out.println("Cổ vật này không có tại đây");
                    }
                } else {
                    System.out.println("\t \uD83D\uDC80 Mã phải lớn hơn 0 \uD83D\uDC80");
                    System.out.println();
                }
            } catch (Exception e) {
                System.out.println("\t \uD83D\uDC80 Mã phải là 1 số \uD83D\uDC80");
                System.out.println();
            }
        }
        while (true) {
            System.out.println("Nhập số lượng cổ vật: ");
            System.out.print("\t➺ ");
            realQuantity = product.getQuantity();
            try {
                quantity = Integer.parseInt(scanner.nextLine());
                if (quantity >= 0) {
                    if (!(quantity > realQuantity)) {
                        currentQuantity = realQuantity - quantity;
                        quantityPurchased = quantity;
                        break;
                    } else {
                        System.out.println("\uD83D\uDC80 Vượt quá số lượng cổ vật cửa hàng có \uD83D\uDC80");
                        System.out.println();
                    }
                } else {
                    System.out.println("\t \uD83D\uDC80 Số lượng phải lớn hơn 0 \uD83D\uDC80");
                    System.out.println();
                }
            } catch (Exception e) {
                System.out.println("\t \uD83D\uDC80 Số lượng phải là 1 số \uD83D\uDC80");
                System.out.println();
            }
        }
        long price = product.getPrice();
        revenue = price * quantityPurchased;
        Payment p = new Payment(id1, quantityPurchased, revenue);
        if (list.size() == 0) {
            list.add(p);
        } else {
            boolean isUpdate = false;
            boolean isError = false;
            for (Payment t : list) {
                int x = t.getId();
                if (x == id1) {
                    if (quantityPurchased + t.getQuantity() > realQuantity) {
                        isError = true;
                        break;
                    } else {
                        t.setRevenue(t.getQuantity() * price);
                        t.setQuantity(quantityPurchased + t.getQuantity());
                        isUpdate = true;
                    }
                }
            }
            if (isError) {
                System.out.println("\uD83D\uDC80 Vượt quá số lượng cổ vật cửa hàng có \uD83D\uDC80");
                System.out.println();
                buy();
            } else if (!isUpdate) {
                list.add(p);
            }
        }
        option();

    }

    public void showTotal() {
        System.out.println("Danh sách Cổ vật mua");
        System.out.println("______________________");
        long sum = 0;
        for (int i = 0; i < list.size(); i++) {
            long total;
            total = list.get(i).getQuantity() * productService.findProductbyID(list.get(i).getId()).getPrice();
            System.out.printf("Tổng tiền %s là : %s\n", productService.findProductbyID(list.get(i).getId()).getName(), format.format(total));
            sum += total;

        }
        System.out.println("________________________________________");
        System.out.println(" ☛ Số tiền cần thanh toán: " + format.format(sum));
        System.out.println("________________________________________");
        check();
    }

    public void showInformation() {
        System.out.println("Thông tin đơn hàng");
        System.out.println("__________________");
        System.out.println("Tên khách hàng: " + list.get(0).getName());
        System.out.println("Số điện thoại: " + list.get(0).getPhoneNumber());
        System.out.println("Địa chỉ: " + list.get(0).getAddress());
        System.out.println("Danh sách sản phẩm mua");
        System.out.println("Hình thức thanh toán: " + list.get(0).getMoney());
        long sum = 0;
        for (int i = 0; i < list.size(); i++) {
            long total;
            total = list.get(i).getQuantity() * productService.findProductbyID(list.get(i).getId()).getPrice();
            System.out.printf("Tổng tiền %s là : %s\n", productService.findProductbyID(list.get(i).getId()).getName(), format.format(total));
            sum += total;

        }
        System.out.println("________________________________________");
        System.out.println("Số tiền cần thanh toán: " + format.format(sum));
        System.out.println("________________________________________\n\n");
        afterPay();
    }

    public void showAllIncome() {
        System.out.println("===================================");
        System.out.println("＄ Tổng doanh thu: " + format.format(paymentService.showTotal()));
        System.out.println("===================================\n\n");
        menu.boss();
    }

    public void option() {
        System.out.println("\t\t\t\tNhập 1 để mua thêm Cổ vâ");
        System.out.println("\t\t\t\tNhập 2 để xem tổng tiền");
        System.out.print("\t➺ ");
        int options;
        try {
            options = Integer.parseInt(scanner.nextLine());
            switch (options) {
                case 1:
                    buy();
                    break;
                case 2:
                    showTotal();
                    break;
                default:
                    System.out.println("\t\t\tNhập không đúng! Vui lòng nhập lại");
                    option();
            }
        } catch (Exception e) {
            System.out.println("\t \uD83D\uDC80 lựa chọn phải là 1 số \uD83D\uDC80");
            System.out.println();
            option();

        }
    }

    public void check() {
        System.out.println("Bạn có muốn thanh toán đơn hàng này không?");
        System.out.println("nhập 1 để thanh toán");
        System.out.println("nhập 2 để mua lại");
        System.out.print("\t➺ ");
        int options;
        try {
            options = Integer.parseInt(scanner.nextLine());
            switch (options) {
                case 1:
                    pay();
                    return;
                case 2:
                    list.clear();
                    buy();
                    break;
                default:
                    System.out.println("Nhập không đúng! Vui lòng nhập lại");
                    check();
            }
        } catch (Exception e) {
            System.out.println("\t \uD83D\uDC80 lựa chọn phải là 1 số \uD83D\uDC80");
            System.out.println();
            check();
        }
    }

    public void pay() {
        System.out.println(" ☟ Nhập thông tin cá nhân để vẩn chuyển hàng đến nơi an toàn ☟");
        System.out.println("∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘");
        System.out.println("Nhập họ và tên (vd: Trấn Thành) ");
        System.out.print("\t➺ ");
        name = scanner.nextLine();
        while (!ValidateUtils.isNameValid(name)) {
            System.out.println("Tên " + name + " không đúng định dạng." + " Vui lòng nhập lại!" + " (Tên phải viết hoa chữ cái đầu)");
            System.out.println("Nhập tên (vd: Trấn Thành) ");
            System.out.print("\t➺ ");
            name = scanner.nextLine();
        }
        System.out.println("Nhập số điện thoại (vd: 0559941292): ");
        System.out.print("\t➺ ");
        phone = scanner.nextLine();
        while (!ValidateUtils.isPhoneValid(phone)) {
            System.out.println("Số " + phone + " của bạn không đúng định dạng. Vui lòng nhập lại! " + "(Số điện thoại bao gồm 10 số và bắt đầu là số 0)");
            System.out.println("Nhập số điện thoại (vd: 0559941292)");
            System.out.print("\t➺ ");
            phone = scanner.nextLine();
        }
        System.out.println("Nhập địa chỉ (vd: Huế)");
        System.out.print("\t➺ ");
        address = scanner.nextLine();
        while (!ValidateUtils.isAddValid(address)) {
            System.out.println("Nhập địa chỉ ");
            System.out.print("\t➺ ");
            address = scanner.nextLine();
        }
        System.out.println("Chọn phương thức thanh toán");
        System.out.println("Nhập 1 để Thanh toán bằng tiền mặt");
        System.out.println("Nhập 2 để Thanh toán bằng  ");
        System.out.print("\t➺ ");
        int options1;
        options1 = Integer.parseInt(scanner.nextLine());
        switch (options1) {
            case 1:
                tienMat();
                break;
            case 2:
                iBanking();
                break;
            default:
                System.out.println("Nhập không đúng! Vui lòng nhập lại");
                check();
                while (!ValidateUtils.isAddValid(money)) {
                    System.out.println("Nhập địa chỉ ");
                    System.out.print("\t➺ ");
                    money = scanner.nextLine();
                }



        }
    }
    public void tienMat() {

    }

    public void iBanking() {
        System.out.println("Mời bạn chuyển khoản vào stk của Đạt's Store");
        System.out.println("STK: 9856419555");
        System.out.println("1. Xác nhận chuyển khoản thành công");
        System.out.println("2. Mua lại");
        int options2;
        options2 = Integer.parseInt(scanner.nextLine());
        switch (options2) {
            case 1:
                afterPay();
                break;
            case 2:
                list.clear();
                buy();
                break;
            default:
                System.out.println("Nhập không đúng! Vui lòng nhập lại");
                check();
        }
    }


    public void afterPay() {
        for (Payment p : list) {
            p.setName(name);
            p.setPhoneNumber(phone);
            p.setAddress(address);
            p.setMoney(money);
        }
        paymentService.add(list);
        for (Payment u : list) {
            int a = u.getId();
            int c = u.getQuantity();
            int b = productService.getQuantity(a);
            productService.updateQuantity(a, b - c);
        }
        productService.checkExist();
        System.out.println("-------------------Thanh toán thành công-------------------");
        System.out.println("Bạn có muốn kiểm tra thông tin thanh toán không?");
        System.out.println("1. Để xem thông tin thanh toán");
        System.out.println("2. Để quay lại menu");
        System.out.println("3. Để thoát");
        System.out.print("\t➺ ");
        int options;
        try {
            options = Integer.parseInt(scanner.nextLine());
            switch (options) {
                case 1:
                    showInformation();
                    break;
                case 2:
                    menu.guest();
                    break;
                case 3:
                    System.out.println("∼∼∼∼∼∼∼∼∼∼See you again∽∽∽∽∽∽∽∽∽∽\n\n");
                    menu.exit();
                    break;
                default:
                    System.out.println("Nhập không đúng! Vui lòng nhập lại");
                    check();
            }
        } catch (Exception e) {
            System.out.println("\t \uD83D\uDC80 lựa chọn phải là 1 số \uD83D\uDC80");
            System.out.println();
            check();
        }
    }
}