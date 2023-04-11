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

import static product.views.Menu.productView;

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
    long revenue;
    int realQuantity;
    int quantity;

    int currentQuantity;


    Product product = null;


    public PaymentView() {
    }




    public void buy() {
        while (true) {

            System.out.println("Nhập ID sản phẩm cần mua");
            System.out.print("\t➺ ");
            try {
                id1 = Integer.parseInt(scanner.nextLine());
                if (id1 > 0) {
                    if (paymentService.existProduct(id1)) {
                        product = paymentService.findProductbyID(id1);
                        break;
                    } else {
                        System.out.println("ID này không tồn tại");
                    }
                } else {
                    System.out.println("\t ❌ ID phải lớn hơn 0 ❌");
                    System.out.println();
                }
            } catch (Exception e) {
                System.out.println("\t ❌ ID phải là 1 số ❌");
                System.out.println();
            }
        }
        while (true) {
            System.out.println("Nhập số lượng sản phẩm: ");
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
                        System.out.println("❌ Vượt quá số lượng hàng có sẵn ❌");
                        System.out.println();
                    }
                } else {
                    System.out.println("\t ❌ Số lượng phải lớn hơn 0 ❌");
                    System.out.println();
                }
            } catch (Exception e) {
                System.out.println("\t ❌ Số lượng phải là 1 số ❌");
                System.out.println();
            }
        }
        long price = product.getPrice();
        revenue = price * quantityPurchased;
        Payment p = new Payment(id1, quantityPurchased, revenue);
        if(list.size()==0){
            list.add(p);
        } else {
            boolean isUpdate = false;
            boolean isError = false;
            for (Payment t : list) {
               int x =  t.getId();
               if (x == id1){
                   if (quantityPurchased + t.getQuantity() > realQuantity){
                       isError = true;
                       break;
                   } else {
                       t.setRevenue(t.getQuantity()* price);
                       t.setQuantity(quantityPurchased + t.getQuantity());
                       isUpdate = true;
                   }
               }
            }
            if(isError){
                System.out.println("❌ Vượt quá số lượng hàng có sẵn ❌");
                System.out.println();
                buy();
            } else if (!isUpdate){
                list.add(p);
            }
        }
        option();

    }

    public void showTotal() {
        System.out.println("Danh sách sản phẩm mua");
        System.out.println("______________________");
        long sum = 0;
        for (int i = 0; i < list.size(); i++) {
            long total;
            total = list.get(i).getQuantity() * productService.findProductbyID(list.get(i).getId()).getPrice();
            System.out.printf("Tổng tiền sản phẩm %s là : %s\n", productService.findProductbyID(list.get(i).getId()).getName(), format.format(total));
            sum += total;

        }
        System.out.println("________________________________________");
        System.out.println(" ☛ Số tiền cần thanh toán: " + format.format(sum));
        System.out.println("________________________________________");
        check();
    }

    public void showInformation(){
        System.out.println("Thông tin đơn hàng");
        System.out.println("__________________");
        System.out.println("Tên khách hàng: "+ list.get(0).getName());
        System.out.println("Số điện thoại: "+ list.get(0).getPhoneNumber());
        System.out.println("Địa chỉ: "+ list.get(0).getAddress());
        System.out.println("Danh sách sản phẩm mua");
        long sum = 0;
        for (int i = 0; i < list.size(); i++) {
            long total;
            total = list.get(i).getQuantity() * productService.findProductbyID(list.get(i).getId()).getPrice();
            System.out.printf("Tổng tiền sản phẩm %s là : %s\n", productService.findProductbyID(list.get(i).getId()).getName(), format.format(total));
            sum += total;

        }
        System.out.println("________________________________________");
        System.out.println("Số tiền cần thanh toán: " + format.format(sum));
        System.out.println("________________________________________\n\n");
        afterPay();
    }

    public void showAllIncome (){
        System.out.println("===================================");
        System.out.println("＄ Tổng doanh thu: "+ format.format(paymentService.showTotal()));
        System.out.println("===================================\n\n");
        menu.boss();
    }

    public void option() {
        System.out.println("\t\t\t\tNhập 1 để mua thêm sản phẩm");
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
            System.out.println("\t ❌ lựa chọn phải là 1 số ❌");
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
            System.out.println("\t ❌ lựa chọn phải là 1 số ❌");
            System.out.println();
            check();
        }
    }

    public void pay() {
        System.out.println(" ☟ Nhập thông tin cá nhân để ship hàng ☟");
        System.out.println("∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘∘");
        System.out.println("Nhập họ và tên (vd: Phạm Sinh Nhật) ");
        System.out.print("\t➺ ");
        name = scanner.nextLine();
        while (!ValidateUtils.isNameValid(name)) {
            System.out.println("Tên " + name + " không đúng định dạng." + " Vui lòng nhập lại!" + " (Tên phải viết hoa chữ cái đầu)");
            System.out.println("Nhập tên (vd: Nhật Sinh) ");
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
        for (Payment p : list) {
            p.setName(name);
            p.setPhoneNumber(phone);
            p.setAddress(address);

        }
        paymentService.add(list);
        for (Payment u : list) {
            int a = u.getId();
            int c =u.getQuantity();
            int b = productService.getQuantity(a);
            productService.updateQuantity(a,b-c);
        }
        productService.checkExist();
        System.out.println("Thanh toán thành công");
        afterPay();

    }



    public void afterPay() {
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
            System.out.println("\t ❌ lựa chọn phải là 1 số ❌");
            System.out.println();
            check();
        }
    }


}
