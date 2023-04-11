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
            System.out.println("Nhập mã cổ vật: ");
            System.out.print("\t➺ ");
            try {
                id = input.nextInt();
                if (id > 0) {
                    if (productService.existProduct(id)) {
                        System.out.println(" \uD83D\uDC80 mã này đã tồn tại \uD83D\uDC80");
                    } else {
                        break;
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
        PType type = null;
        int luachon;
        while (true) {
            boolean check = false;
            System.out.println("\u001B[38;2;255;165;0m╔═══════NGUỒN GỐC═══════╗");
            System.out.println("\u001B[38;2;255;165;0m║   1. EUROPE           ║");
            System.out.println("\u001B[38;2;255;165;0m║   2. ASIA             ║");
            System.out.println("\u001B[38;2;255;165;0m║   3. AMERICA          ║");
            System.out.println("\u001B[38;2;255;165;0m║   4. AFRICA           ║");
            System.out.println("\u001B[38;2;255;165;0m╚═══════════════════════╝");
            System.out.println("Nhập tên loại sản phẩm ");
            System.out.print("\t ➺ ");
            try {
                luachon = Integer.parseInt(scanner.nextLine());
                switch (luachon) {
                    case 1:
                        type = PType.EUROPE;
                        check = true;
                        break;
                    case 2:
                        type = PType.ASIA;
                        check = true;
                        break;
                    case 3:
                        type = PType.AMERICA;
                        check = true;
                        break;
                    case 4:
                        type = PType.AFRICA;
                        check = true;
                        break;
                    default:
                        System.out.println("\t\uD83D\uDC80 Nhập không đúng! Vui lòng nhập lại \uD83D\uDC80");
                        System.out.println("\t═════════════════════════════════════");
                        break;
                }
                if (check) {
                    break;
                }
            } catch (Exception e) {
                System.out.println("\t \uD83D\uDC80 Lựa chọn phải là 1 số \uD83D\uDC80");
                System.out.println("\t═════════════════════════");
            }
        }
        String name;
        while (true) {
            System.out.println("Nhập tên Cổ vật: ");
            System.out.print("\t➺ ");
            String check = scanner.nextLine();
            if (productService.existProductName(check)) {
                System.out.println(" \uD83D\uDC80 Tên này đã tồn tại xin nhập tên khác \uD83D\uDC80");
            } else {
                name = check;
                break;
            }
        }
        int quantity;
        while (true) {
            System.out.println("Nhập số lượng cổ vật: ");
            System.out.print("\t➺ ");
            try {
                quantity = Integer.parseInt(scanner.nextLine());
                if (quantity > 0) {
                    break;
                }
                System.out.println("\t \uD83D\uDC80 Số lượng phải lớn hơn 0 \uD83D\uDC80");
                System.out.println();
            } catch (Exception e) {
                System.out.println("\t \uD83D\uDC80 Số lượng phải là 1 số \uD83D\uDC80");
                System.out.println();
            }
        }
        long price;
        while (true) {
            System.out.println("Nhập giá cổ vật: ");
            System.out.print("\t➺ ");
            try {
                price = Long.parseLong(scanner.nextLine());
                if (price > 0) {
                    break;
                }
                System.out.println("\t \uD83D\uDC80 Giá phải lớn hơn 0 \uD83D\uDC80");
                System.out.println();
            } catch (Exception e) {
                System.out.println("\t \uD83D\uDC80 Giá phải là 1 số \uD83D\uDC80");
                System.out.println();
            }
        }
        System.out.println("Nhập mô tả cổ vật: ");
        System.out.print(" \t➺ ");
        String description = scanner.nextLine();
        Status status = Status.SOLD;
        Product product = new Product(id, type, name, quantity, price, description, status);
        productService.add(product);
        System.out.println("✔ Bạn đã thêm cổ vật thành công ✔\n");
        System.out.println("\t\t\t\t═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");
        menu.boss();
    }

    public void showProductBoss() {
        List<Product> products = productService.getProducts();
        System.out.println("Danh sách cổ vật : ");
        System.out.println("\t\t\t\t═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");
        System.out.printf("\t\t\t\t%-10s %-25s %-30s %-15s %-25s %-25s\n", "Mã", "Nguồn gốc cổ vật", "Tên cổ vật", "Số lượng", "Giá", "Trạng thái");
        for (Product product : products) {
            System.out.printf("\t\t\t\t%-10d %-25s %-30s %-15d %-25s %-25s\n", product.getId(), product.getType(), product.getName(), product.getQuantity(), format.format(product.getPrice()), product.getStatus());
        }
        System.out.println("\t\t\t\t═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");
        menu.boss();
    }


    public void showProductGuest() {

        List<Product> products = productService.getProducts();
        System.out.println("Danh sách cổ vật");
        System.out.println("\t\t\t\t═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");
        System.out.printf("\t\t\t\t%-10s %-25s %-30s %-15s %-25s %-25s\n", "Mã", "Nguồn gốc cổ vật", "Tên cổ vật", "Số lượng", "Giá", "Trạng thái");
        for (Product product : products) {
            System.out.printf("\t\t\t\t%-10d %-25s %-30s %-15d %-25s %-25s\n", product.getId(), product.getType(), product.getName(), product.getQuantity(), format.format(product.getPrice()), product.getStatus());
        }
        System.out.println("\t\t\t\t═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");
        menu.guest();
    }

    public void showProductDescriptionBoss() {
        List<Product> products = productService.getProducts();
        System.out.println("Mô tả cổ vật");
        System.out.println("\t\t\t\t═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");
        System.out.printf("\t\t\t\t%-10s %-25s %-30s %-50s\n", "Mã", "Loại cổ vật", "Tên cổ vật", "Mô tả");
        for (Product product : products) {
            System.out.printf("\t\t\t\t%-10s %-25s %-30s %-50s\n", product.getId(), product.getType(), product.getName(), product.getDescription());
        }
        System.out.println("\t\t\t\t═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");
        menu.boss();
    }

    public void showProductDescriptionGuest() {
        List<Product> products = productService.getProducts();
        System.out.println("Mô tả cổ vật");
        System.out.println("\t\t\t\t═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");
        System.out.printf("\t\t\t\t%-10s %-25s %-30s %-50s\n", "Mã", "Loại cổ vật", "Tên cổ vật", "Mô tả");
        for (Product product : products) {
            System.out.printf("\t\t\t\t%-10s %-25s %-30s %-50s\n", product.getId(), product.getType(), product.getName(), product.getDescription());
        }
        System.out.println("\t\t\t\t═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");
        menu.guest();
    }

    public void sortASC() {
        List<Product> p = productService.getProducts();
        p.sort(new PriceSortASC());
        System.out.println("Danh sách cổ vật");
        System.out.println("\t\t\t\t═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");
        System.out.printf("\t\t\t\t%-10s %-25s %-30s %-15s %-25s %-25s\n", "Mã", "Nguồn gốc cổ vật", "Tên cổ vật", "Số lượng", "Giá", "Trạng thái");
        for (Product product : p) {
            System.out.printf("\t\t\t\t%-10d %-25s %-30s %-15d %-25s %-25s\n", product.getId(), product.getType(), product.getName(), product.getQuantity(), format.format(product.getPrice()), product.getStatus());
        }
        System.out.println("\t\t\t\t═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");
        menu.guest();
    }

    public void sortDESC() {
        List<Product> p = productService.getProducts();
        p.sort(new PriceSortDESC());
        System.out.println("Danh sách cổ vật");
        System.out.println("\t\t\t\t═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");
        System.out.printf("\t\t\t\t%-10s %-25s %-30s %-15s %-25s %-25s\n", "Mã", "Nguồn gốc cổ vật", "Tên cổ vật", "số lượng", "giá", "trạng thái");
        for (Product product : p) {
            System.out.printf("\t\t\t\t%-10d %-25s %-30s %-15d %-25s %-25s\n", product.getId(), product.getType(), product.getName(), product.getQuantity(), format.format(product.getPrice()), product.getStatus());
        }
        System.out.println("\t\t\t\t═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");
        menu.guest();
    }


    public void findProductbyTypeGuest() {
        System.out.println("\u001B[38;2;255;165;0m╔═══════════════════════╗");
        System.out.println("\u001B[38;2;255;165;0m║   1. EUROPE           ║");
        System.out.println("\u001B[38;2;255;165;0m║   2. ASIA             ║");
        System.out.println("\u001B[38;2;255;165;0m║   3. AMERICA          ║");
        System.out.println("\u001B[38;2;255;165;0m║   4. AFRICA           ║");
        System.out.println("\u001B[38;2;255;165;0m╚═══════════════════════╝");
        System.out.print("Nhập số tương ứng với Nguồn gốc cổ vật cần tìm (1-4): ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        String type;
        switch (choice) {
            case 1:
                type = "EUROPE";
                break;
            case 2:
                type = "ASIA";
                break;
            case 3:
                type = "AMERICA";
                break;
            case 4:
                type = "AFRICA";
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ!");
                return;
        }
        boolean found = false;
        List<Product> products = productService.getProducts();
        System.out.println("Danh sách cổ vật");
        System.out.println("\t\t\t\t═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");
        System.out.printf("\t\t\t\t%-10s %-25s %-30s %-15s %-25s %-25s\n", "Mã", "Nguồn gốc cổ vật", "Tên cổ vật", "Số lượng", "Giá", "Trạng thái");
        for (Product product : products) {
            if (product.getType().equalsIgnoreCase(type)) {
                System.out.printf("\t\t\t\t%-10d %-25s %-30s %-15d %-25s %-25s\n", product.getId(), product.getType(), product.getName(), product.getQuantity(), format.format(product.getPrice()), product.getStatus());
                found = true;
            }
        }
        if (!found) {
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t ✖ Không có cổ vật này ✖");
        }
        System.out.println("\t\t\t\t═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");
        menu.guest();
    }


    public void findProductbyTypeBoss() {
        System.out.println("\u001B[38;2;255;165;0m╔═══════════════════════╗");
        System.out.println("\u001B[38;2;255;165;0m║   1. EUROPE           ║");
        System.out.println("\u001B[38;2;255;165;0m║   2. ASIA             ║");
        System.out.println("\u001B[38;2;255;165;0m║   3. AMERICA          ║");
        System.out.println("\u001B[38;2;255;165;0m║   4. AFRICA           ║");
        System.out.println("\u001B[38;2;255;165;0m╚═══════════════════════╝");
        System.out.print("Nhập số tương ứng với Nguồn gốc cổ vật cần tìm (1-4): ");
        int choice = scanner.nextInt();
        scanner.nextLine();
        String type;
        switch (choice) {
            case 1:
                type = "EUROPE";
                break;
            case 2:
                type = "ASIA";
                break;
            case 3:
                type = "AMERICA";
                break;
            case 4:
                type = "AFRICA";
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ!");
                return;
        }
        boolean found = false;
        List<Product> products = productService.getProducts();
        System.out.println("Danh sách cổ vật");
        System.out.println("\t\t\t\t═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");
        System.out.printf("\t\t\t\t%-10s %-25s %-30s %-15s %-25s %-25s\n", "Mã", "Nguồn gốc cổ vật", "Tên cổ vật", "Số lượng", "Giá", "Trạng thái");
        for (Product product : products) {
            if (product.getType().equalsIgnoreCase(type)) {
                System.out.printf("\t\t\t\t%-10d %-25s %-30s %-15d %-25s %-25s\n", product.getId(), product.getType(), product.getName(), product.getQuantity(), format.format(product.getPrice()), product.getStatus());
                found = true;
            }
        }
        if (!found) {
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t ✖ Không có cổ vật này ✖");
        }
        System.out.println("\t\t\t\t═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");
        menu.boss();
    }

    public void findProductbyNameBoss() {
        System.out.print("Nhập tên cổ vật cần tìm: ");
        String name = scanner.nextLine().toUpperCase();
        boolean found = false;
        List<Product> p = productService.getProducts();
        System.out.println("Danh sách cổ vật");
        System.out.printf("\t\t\t\t%-10s %-25s %-30s %-15s %-25s %-25s\n", "Mã", "Nguồn gốc cổ vật", "Tên cổ vật", "số lượng", "giá", "trạng thái");
        for (Product product : p) {
            if (product.getName().toUpperCase().contains(name)) {
                System.out.printf("\t\t\t\t%-10d %-25s %-30s %-15d %-25s %-25s\n", product.getId(), product.getType(), product.getName(), product.getQuantity(), format.format(product.getPrice()), product.getStatus());
                found = true;
            }
        }
        if (!found) {
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t ✖ Không có cổ vật này ✖");
        }
        System.out.println("\t\t\t\t═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");
        menu.boss();
    }

    public void findProductbyNameGuest() {
        System.out.print("Nhập tên cổ vật cần tìm: ");
        String name = scanner.nextLine().toUpperCase();
        boolean found = false;
        List<Product> p = productService.getProducts();
        System.out.println("Danh sách cổ vật");
        System.out.printf("\t\t\t\t%-10s %-25s %-30s %-15s %-25s %-25s\n", "Mã", "Nguồn gốc cổ vật", "Tên cổ vật", "Số lượng", "Giá", "Trạng thái");
        for (Product product : p) {
            if (product.getName().toUpperCase().contains(name)) {
                System.out.println("\t\t\t\t═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");
                System.out.printf("\t\t\t\t%-10d %-25s %-30s %-15d %-25s %-25s\n", product.getId(), product.getType(), product.getName(), product.getQuantity(), format.format(product.getPrice()), product.getStatus());
                found = true;
            }
        }
        if (!found) {
            System.out.println("\t\t\t\t\t\t\t\t\t\t\t\t ✖ Không có cổ vật này ✖");
        }
        System.out.println("\t\t\t\t═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");
        menu.guest();
    }

    public void updateProduct() {
        int id = 0;
        Product update = null;
        while (true) {
            System.out.println("Nhập ID cổ vật cần sửa");
            System.out.print("\t➺ ");
            try {
                id = Integer.parseInt(scanner.nextLine());
                if (id > 0) {
                    if (productService.existProduct(id)) {
                        update = productService.findProductbyID(id);
                        break;
                    } else {
                        System.out.println(" \uD83D\uDC80 Mã này không tồn tại \uD83D\uDC80");
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
        noChange();
        setPType(update);
        noChange();
        while (true) {
            System.out.println("Nhập tên cần sửa");
            System.out.print("\t➺ ");
            String name = scanner.nextLine();
            if (!name.equals("-1")) {
                if (productService.existProductName(name)) {
                    System.out.println(" \uD83D\uDC80 Tên này đã tồn tại xin nhập tên khác \uD83D\uDC80");
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
            System.out.println("Nhập số lượng cổ vật: ");
            System.out.print("\t➺ ");
            try {
                int quantity = Integer.parseInt(scanner.nextLine());
                if (!(quantity == -1)) {
                    if (quantity >= 0) {
                        update.setQuantity(quantity);
                        break;
                    }
                    System.out.println("\t \uD83D\uDC80 Số lượng phải lớn hơn 0 \uD83D\uDC80");
                    System.out.println();
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("\t \uD83D\uDC80 Số lượng phải là 1 số \uD83D\uDC80");
                System.out.println();
            }
        }
        noChange();
        while (true) {
            System.out.println("Nhập giá cổ vật: ");
            System.out.print("\t➺ ");
            try {
                long price = Long.parseLong(scanner.nextLine());
                if (!(price == -1)) {
                    if (price >= 0) {
                        update.setPrice(price);
                        break;
                    }
                    System.out.println("\t \uD83D\uDC80 Giá phải lớn hơn 0 \uD83D\uDC80");
                    System.out.println();
                } else {
                    break;
                }
            } catch (Exception e) {
                System.out.println("\t \uD83D\uDC80 Giá phải là 1 số \uD83D\uDC80");
                System.out.println();
            }
        }
        noChange();
        while (true) {
            System.out.println("Nhập mô tả cổ vật: ");
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
        System.out.println("✔ Bạn đã cập nhật cổ vật thành công ✔\n");
        System.out.println("\t\t\t\t═════════════════════════════════════════════════════════════════════════════════════════════════════════════════════════");
        productService.checkExist();
        menu.boss();
    }


    public void setPType(Product product) {
        System.out.println("\u001B[38;2;255;165;0m╔═══════SET TYPE════════╗");
        System.out.println("\u001B[38;2;255;165;0m║   1. EUROPE           ║");
        System.out.println("\u001B[38;2;255;165;0m║   2. ASIA             ║");
        System.out.println("\u001B[38;2;255;165;0m║   3. AMERICA          ║");
        System.out.println("\u001B[38;2;255;165;0m║   4. AFRICA           ║");
        System.out.println("\u001B[38;2;255;165;0m╚═══════════════════════╝");
        System.out.println("Chọn Role: ");
        System.out.print("\t➺ ");
        int options;
        try {
            options = Integer.parseInt(scanner.nextLine());
            switch (options) {
                case -1:
                    break;
                case 1:
                    product.setType(PType.EUROPE);
                    break;
                case 2:
                    product.setType(PType.ASIA);
                    break;
                case 3:
                    product.setType(PType.AMERICA);
                    break;
                case 4:
                    product.setType(PType.AFRICA);
                    break;
                default:
                    System.out.println("Nhập không đúng! Vui lòng nhập lại");
                    setPType(product);
            }
        } catch (Exception e) {
            System.out.println("\t \uD83D\uDC80 Lựa chọn phải là 1 số \uD83D\uDC80");
            System.out.println("\t═════════════════════════");
            setPType(product);
        }
    }


    public void setStatus(Product product) {
        System.out.println("\u001B[38;2;255;165;0m╔═══════SET STATUS══════╗");
        System.out.println("\u001B[38;2;255;165;0m║   1. SOLD             ║");
        System.out.println("\u001B[38;2;255;165;0m║   2. NOT SOLD         ║");
        System.out.println("\u001B[38;2;255;165;0m╚═══════════════════════╝");
        System.out.println("Chọn Status: ");
        System.out.print(" ⭆ ");
        int options;
        try {
            options = Integer.parseInt(scanner.nextLine());
            switch (options) {
                case -1:
                    break;
                case 1:
                    product.setStatus(Status.SOLD);
                    break;
                case 2:
                    product.setStatus(Status.NOTSOLD);
                    break;
                default:
                    System.out.println("Nhập không đúng! Vui lòng nhập lại");
                    setStatus(product);
            }

        } catch (Exception e) {
            System.out.println("\t \uD83D\uDC80 Lựa chọn phải là 1 số \uD83D\uDC80");
            System.out.println("\t═════════════════════════");
            setStatus(product);
        }

    }

    public void noChange() {
        System.out.println(" \uD83D\uDC80 Nếu không thay đổi gì thì nhập: -1 \uD83D\uDC80 ");
    }
}