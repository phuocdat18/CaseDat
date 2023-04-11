package product.views;

import product.model.PType;
import product.model.Product;
import product.model.Status;
import product.utils.PriceSortASC;
import product.utils.PriceSortDESC;
import product.services.ProductService;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

public class ProductView {
    DecimalFormat format = new DecimalFormat("###,###,###" + " đ");
    Scanner scanner = new Scanner(System.in);
    Menu menu = new Menu();
    ProductService productService = new ProductService();


    public ProductView() {

    }

    public void add() {
        int id;
        while (true) {
            Scanner input = new Scanner(System.in);
            System.out.println("Nhập ID sản phẩm: ");
            System.out.print("\t➺ ");
            try {
                id = input.nextInt();
                if (id > 0) {
                    if (productService.existProduct(id)) {
                        System.out.println(" ❌ ID này đã tồn tại ❌");
                    } else {
                        break;
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
        PType type = null;
        int luachon;
        while (true) {
            boolean check = false;
            System.out.println("=     = Loại sản phẩm =    =");
            System.out.println("∥      1.  SKINCARE        ∥");
            System.out.println("∥      2.  BODYCARE        ∥");
            System.out.println("∥      3.  HAIRCARE        ∥");
            System.out.println("∥      4.  LIPCARE         ∥");
            System.out.println("∥      5.  FUNCTIONALFOODS ∥");
            System.out.println("= = = = = = = = = = = = = ");
            System.out.println("Nhập tên loại sản phẩm ");
            System.out.print("\t ➺ ");
            try {
                luachon = Integer.parseInt(scanner.nextLine());
                switch (luachon) {
                    case 1:
                        type = PType.SKINCARE;
                        check = true;
                        break;
                    case 2:
                        type = PType.BODYCARE;
                        check = true;
                        break;
                    case 3:
                        type = PType.HAIRCARE;
                        check = true;
                        break;
                    case 4:
                        type = PType.LIPCARE;
                        check = true;
                        break;
                    case 5:
                        type = PType.FUNCTIONALFOODS;
                        check = true;
                        break;
                    default:
                        System.out.println("\t❌ Nhập không đúng! Vui lòng nhập lại ❌");
                        System.out.println("\t=====================================");
                        break;
                }
                if (check) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("\t ❌ Lựa chọn phải là 1 số ❌");
                System.out.println("\t=========================");
            }
        }
        String name;
        while (true) {
            System.out.println("Nhập tên sản phẩm: ");
            System.out.print("\t➺ ");
            String check = scanner.nextLine();
            if (productService.existProductName(check)) {
                System.out.println(" ❌ Tên này đã tồn tại xin nhập tên khác ❌");
            } else {
                name = check;
                break;
            }
        }
        int quantity;
        while (true) {
            System.out.println("Nhập số lượng sản phẩm: ");
            System.out.print("\t➺ ");
            try {
                quantity = Integer.parseInt(scanner.nextLine());
                if (quantity > 0) {
                    break;
                }
                System.out.println("\t ❌ Số lượng phải lớn hơn 0 ❌");
                System.out.println();
            } catch (Exception e) {
                System.out.println("\t ❌ Số lượng phải là 1 số ❌");
                System.out.println();
            }
        }
        long price;
        while (true) {
            System.out.println("Nhập giá sản phẩm: ");
            System.out.print("\t➺ ");
            try {
                price = Long.parseLong(scanner.nextLine());
                if (price > 0) {
                    break;
                }
                System.out.println("\t❌ Giá phải lớn hơn 0 ❌");
                System.out.println();
            } catch (Exception e) {
                System.out.println("\t❌ Giá phải là 1 số ❌");
                System.out.println();
            }
        }
        System.out.println("Nhập mô tả sản phấm: ");
        System.out.print(" \t➺ ");
        String description = scanner.nextLine();
        Status status = Status.INSTOCK;
        Product product = new Product(id, type, name, quantity, price, description, status);
        productService.add(product);
        System.out.println("✔ Bạn đã thêm sản phẩm thành công ✔\n");
        System.out.println("\t\t\t\t=========================================================================================================================\n\n");
        menu.boss();
    }

    public void showProductBoss() {
        List<Product> products = productService.getProducts();
        System.out.println("Danh sách sản phẩm : ");
        System.out.println("\t\t\t\t=========================================================================================================================");
        System.out.printf("\t\t\t\t%-10s %-25s %-30s %-15s %-25s %-25s\n", "ID", "Loại sản phẩm", "Tên sản phẩm", "Số lượng", "Giá", "Trạng thái");
        for (Product product : products) {
            System.out.printf("\t\t\t\t%-10d %-25s %-30s %-15d %-25s %-25s\n", product.getId(), product.getType(), product.getName(), product.getQuantity(), format.format(product.getPrice()), product.getStatus());
        }
        System.out.println("\t\t\t\t=========================================================================================================================\n\n");
        menu.boss();
    }


    public void showProductGuest() {

        List<Product> products = productService.getProducts();
        System.out.println("Danh sách sản phẩm");
        System.out.println("\t\t\t\t=========================================================================================================================");
        System.out.printf("\t\t\t\t%-10s %-25s %-30s %-15s %-25s %-25s\n", "ID", "Loại sản phẩm", "Tên sản phẩm", "Số lượng", "Giá", "Trạng thái");
        for (Product product : products) {
            System.out.printf("\t\t\t\t%-10d %-25s %-30s %-15d %-25s %-25s\n", product.getId(), product.getType(), product.getName(), product.getQuantity(), format.format(product.getPrice()), product.getStatus());
        }
        System.out.println("\t\t\t\t=========================================================================================================================\n\n");
        menu.guest();
    }

    public void showProductDescriptionBoss() {
        List<Product> products = productService.getProducts();
        System.out.println("Mô tả sản phẩm");
        System.out.println("\t\t\t\t=========================================================================================================================");
        System.out.printf("\t\t\t\t%-10s %-25s %-30s %-50s\n", "ID", "Loại sản phẩm", "Tên sản phẩm", "Mô tả");
        for (Product product : products) {
            System.out.printf("\t\t\t\t%-10s %-25s %-30s %-50s\n", product.getId(), product.getType(), product.getName(), product.getDescription());
        }
        System.out.println("\t\t\t\t=========================================================================================================================\n\n");
        menu.boss();
    }

    public void showProductDescriptionGuest() {
        List<Product> products = productService.getProducts();
        System.out.println("Mô tả sản phẩm");
        System.out.println("\t\t\t\t=========================================================================================================================");
        System.out.printf("\t\t\t\t%-10s %-25s %-30s %-50s\n", "ID", "Loại sản phẩm", "Tên sản phẩm", "Mô tả");
        for (Product product : products) {
            System.out.printf("\t\t\t\t%-10s %-25s %-30s %-50s\n", product.getId(), product.getType(), product.getName(), product.getDescription());
        }
        System.out.println("\t\t\t\t=========================================================================================================================\n\n");
        menu.guest();
    }

    public void sortASC() {
        List<Product> p = productService.getProducts();
        p.sort(new PriceSortASC());
        System.out.println("Danh sách sản phẩm");
        System.out.println("\t\t\t\t=========================================================================================================================");
        System.out.printf("\t\t\t\t%-10s %-25s %-30s %-15s %-25s %-25s\n", "ID", "Loại sản phẩm", "Tên sản phẩm", "Số lượng", "Giá", "Trạng thái");
        for (Product product : p) {
            System.out.printf("\t\t\t\t%-10d %-25s %-30s %-15d %-25s %-25s\n", product.getId(), product.getType(), product.getName(), product.getQuantity(), format.format(product.getPrice()), product.getStatus());
        }
        System.out.println("\t\t\t\t=========================================================================================================================\n\n");
        menu.guest();
    }

    public void sortDESC() {
        List<Product> p = productService.getProducts();
        p.sort(new PriceSortDESC());
        System.out.println("Danh sách sản phẩm");
        System.out.println("\t\t\t\t=========================================================================================================================");
        System.out.printf("\t\t\t\t%-10s %-25s %-30s %-15s %-25s %-25s\n", "ID", "Loại sản phẩm", "Tên sản phẩm", "số lượng", "giá", "trạng thái");
        for (Product product : p) {
            System.out.printf("\t\t\t\t%-10d %-25s %-30s %-15d %-25s %-25s\n", product.getId(), product.getType(), product.getName(), product.getQuantity(), format.format(product.getPrice()), product.getStatus());
        }
        System.out.println("\t\t\t\t=========================================================================================================================\n\n");
        menu.guest();
    }


    public void findProductbyTypeGuest() {
        System.out.println("= = = = = = = = = = = = = ");
        System.out.println("∥  1. SKINCARE         ∥");
        System.out.println("∥  2. BODYCARE         ∥");
        System.out.println("∥  3. HAIRCARE         ∥");
        System.out.println("∥  4. LIPCARE          ∥");
        System.out.println("∥  5. FUNCTIONALFOODS  ∥");
        System.out.println("= = = = = = = = = = = = = ");
        System.out.print("Nhập số tương ứng với loại sản phẩm cần tìm (1-5): ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        String type;
        switch (choice) {
            case 1:
                type = "SKINCARE";
                break;
            case 2:
                type = "BODYCARE";
                break;
            case 3:
                type = "HAIRCARE";
                break;
            case 4:
                type = "LIPCARE";
                break;
            case 5:
                type = "FUNCTIONALFOODS";
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ!");
                return;
        }
        boolean found = false;
        List<Product> products = productService.getProducts();
        System.out.println("Danh sách sản phẩm");
        System.out.println("\t\t\t\t=========================================================================================================================");
        System.out.printf("\t\t\t\t%-10s %-25s %-30s %-15s %-25s %-25s\n", "ID", "Loại sản phẩm", "Tên sản phẩm", "Số lượng", "Giá", "Trạng thái");
        for (Product product : products) {
            if (product.getType().equalsIgnoreCase(type)) {
                System.out.printf("\t\t\t\t%-10d %-25s %-30s %-15d %-25s %-25s\n", product.getId(), product.getType(), product.getName(), product.getQuantity(), format.format(product.getPrice()), product.getStatus());
                found = true;
            }
        }
        if (!found) {
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t ✖ Không có sản phẩm này ✖");
        }
        System.out.println("\t\t\t\t=========================================================================================================================\n\n");
        menu.guest();
    }


    public void findProductbyTypeBoss() {
        System.out.println("= = = = = = = = = = = = = ");
        System.out.println("∥  1. SKINCARE         ∥");
        System.out.println("∥  2. BODYCARE         ∥");
        System.out.println("∥  3. HAIRCARE         ∥");
        System.out.println("∥  4. LIPCARE          ∥");
        System.out.println("∥  5. FUNCTIONALFOODS  ∥");
        System.out.println("= = = = = = = = = = = = = ");
        System.out.print("Nhập số tương ứng với loại sản phẩm cần tìm (1-5): ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        String type;
        switch (choice) {
            case 1:
                type = "SKINCARE";
                break;
            case 2:
                type = "BODYCARE";
                break;
            case 3:
                type = "HAIRCARE";
                break;
            case 4:
                type = "LIPCARE";
                break;
            case 5:
                type = "FUNCTIONALFOODS";
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ!");
                return;
        }
        boolean found = false;
        List<Product> products = productService.getProducts();
        System.out.println("Danh sách sản phẩm");
        System.out.println("\t\t\t\t=========================================================================================================================");
        System.out.printf("\t\t\t\t%-10s %-25s %-30s %-15s %-25s %-25s\n", "ID", "Loại sản phẩm", "Tên sản phẩm", "Số lượng", "Giá", "Trạng thái");
        for (Product product : products) {
            if (product.getType().equalsIgnoreCase(type)) {
                System.out.printf("\t\t\t\t%-10d %-25s %-30s %-15d %-25s %-25s\n", product.getId(), product.getType(), product.getName(), product.getQuantity(), format.format(product.getPrice()), product.getStatus());
                found = true;
            }
        }
        if (!found) {
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t ✖ Không có sản phẩm này ✖");
        }
        System.out.println("\t\t\t\t=========================================================================================================================\n\n");
        menu.boss();
    }

    public void findProductbyNameBoss() {
        System.out.print("Nhập tên sản phẩm cần tìm: ");
        String name = scanner.nextLine().toUpperCase();
        boolean found = false;
        List<Product> p = productService.getProducts();
        System.out.println("Danh sách sản phẩm");
        System.out.printf("\t\t\t\t%-10s %-25s %-30s %-15s %-25s %-25s\n", "ID", "Loại sản phẩm", "Tên sản phẩm", "số lượng", "giá", "trạng thái");
        for (Product product : p) {
            if (product.getName().toUpperCase().contains(name)) {
                System.out.printf("\t\t\t\t%-10d %-25s %-30s %-15d %-25s %-25s\n", product.getId(), product.getType(), product.getName(), product.getQuantity(), format.format(product.getPrice()), product.getStatus());
                found = true;
            }
        }
        if (!found) {
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t ✖ Không có sản phẩm này ✖");
        }
        System.out.println("\t\t\t\t=========================================================================================================================");
        menu.boss();
    }

    public void findProductbyNameGuest() {
        System.out.print("Nhập tên sản phẩm cần tìm: ");
        String name = scanner.nextLine().toUpperCase();
        boolean found = false;
        List<Product> p = productService.getProducts();
        System.out.println("Danh sách sản phẩm");
        System.out.printf("\t\t\t\t%-10s %-25s %-30s %-15s %-25s %-25s\n", "ID", "Loại sản phẩm", "Tên sản phẩm", "Số lượng", "Giá", "Trạng thái");
        for (Product product : p) {
            if (product.getName().toUpperCase().contains(name)) {
                System.out.println("\t\t\t\t=========================================================================================================================\n");
                System.out.printf("\t\t\t\t%-10d %-25s %-30s %-15d %-25s %-25s\n", product.getId(), product.getType(), product.getName(), product.getQuantity(), format.format(product.getPrice()), product.getStatus());
                found = true;
            }
        }
        if (!found) {
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t ✖ Không có sản phẩm này ✖");
        }
        System.out.println("\t\t\t\t=========================================================================================================================\n");
        menu.guest();
    }

    public void updateProduct() {
        int id = 0;
        Product update = null;
        while (true) {
            System.out.println("Nhập ID sản phẩm cần sửa");
            System.out.print("\t➺ ");
            try {
                id = Integer.parseInt(scanner.nextLine());
                if (id > 0) {
                    if (productService.existProduct(id)) {
                        update = productService.findProductbyID(id);
                        break;
                    } else {
                        System.out.println(" ❌ ID này không tồn tại ❌");
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
        noChange();
        setPType(update);
        noChange();
        while (true) {
            System.out.println("Nhập tên cần sửa");
            System.out.print("\t➺ ");
            String name = scanner.nextLine();
            if (!name.equals("-1")) {
                if (productService.existProductName(name)) {
                    System.out.println(" ❌ Tên này đã tồn tại xin nhập tên khác ❌");
                } else {
                    update.setName(name);
                    break;
                }
            } else {
                break;
            }
        }
        noChange();
        while (true) {
            System.out.println("Nhập số lượng sản phẩm: ");
            System.out.print("\t➺ ");
            try {
                int quantity = Integer.parseInt(scanner.nextLine());
                if (!(quantity == -1)) {
                    if (quantity >= 0) {
                        update.setQuantity(quantity);
                        break;
                    }
                    System.out.println("\t ❌ Số lượng phải lớn hơn 0 ❌");
                    System.out.println();
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("\t ❌ Số lượng phải là 1 số ❌");
                System.out.println();
            }
        }
        noChange();
        while (true) {
            System.out.println("Nhập giá sản phẩm: ");
            System.out.print("\t➺ ");
            try {
                long price = Long.parseLong(scanner.nextLine());
                if (!(price == -1)) {
                    if (price >= 0) {
                        update.setPrice(price);
                        break;
                    }
                    System.out.println("\t ❌ Giá phải lớn hơn 0 ❌");
                    System.out.println();
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("\t ❌ Giá phải là 1 số ❌");
                System.out.println();
            }
        }
        noChange();
        while (true) {
            System.out.println("Nhập mô tả sản phấm: ");
            System.out.print(" \t➺ ");
            String description = scanner.nextLine();
            if (!(description.equals("-1"))) {
                update.setDescription(description);
                break;
            } else {
                break;
            }
        }
        productService.update(id, update);
        System.out.println("✔ Bạn đã cập nhật sản phẩm thành công ✔\n");
        System.out.println("\t\t\t\t=========================================================================================================================");
        productService.checkExist();
        menu.boss();
    }


    public void setPType(Product product) {
        System.out.println("=       = SET TYPE =     =");
        System.out.println("∥   1. SKINCARE          ∥");
        System.out.println("∥   2. BODYCARE          ∥");
        System.out.println("∥   3. HAIRCARE          ∥");
        System.out.println("∥   4. LIPCARE           ∥");
        System.out.println("∥   5. FUNCTIONALFOODS   ∥");
        System.out.println("= = = = = = = = = = = = = ");
        System.out.println("Chọn Role: ");
        System.out.print("\t➺ ");
        int options;
        try {
            options = Integer.parseInt(scanner.nextLine());
            switch (options) {
                case -1:
                    break;
                case 1:
                    product.setType(PType.SKINCARE);
                    break;
                case 2:
                    product.setType(PType.BODYCARE);
                    break;
                case 3:
                    product.setType(PType.HAIRCARE);
                    break;
                case 4:
                    product.setType(PType.LIPCARE);
                    break;
                case 5:
                    product.setType(PType.FUNCTIONALFOODS);
                    break;
                default:
                    System.out.println("Nhập không đúng! Vui lòng nhập lại");
                    setPType(product);
            }
        } catch (Exception e) {
            System.out.println("\t ❌ Lựa chọn phải là 1 số ❌");
            System.out.println("\t=========================");
            setPType(product);
        }
    }


    public void setStatus(Product product) {
        System.out.println("=       = SET STATUS =     =");
        System.out.println("∥   1. IN STOCK            ∥");
        System.out.println("∥   2. OUT OF STOCK        ∥");
        System.out.println("= = = = = = = = = = = = = = ");
        System.out.println("Chọn Status: ");
        System.out.print(" ⭆ ");
        int options;
        try {
            options = Integer.parseInt(scanner.nextLine());
            switch (options) {
                case -1:
                    break;
                case 1:
                    product.setStatus(Status.INSTOCK);
                    break;
                case 2:
                    product.setStatus(Status.OUTOFSTOCK);
                    break;
                default:
                    System.out.println("Nhập không đúng! Vui lòng nhập lại");
                    setStatus(product);
            }

        } catch (Exception e) {
            System.out.println("\t ❌ Lựa chọn phải là 1 số ❌");
            System.out.println("\t=========================");
            setStatus(product);
        }

    }

    public void noChange() {
        System.out.println(" ⦿ Nếu không thay đổi gì thì nhập: -1 ⦿ ");
    }
}
