package product.services;
import product.model.Product;
import product.model.Status;
import product.utils.CSVUtils;
import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService{
    List<Product> productList = new ArrayList<>();
    public static  String path = "src/data/product.csv";

    @Override
    public void add(Product newProduct) {
        productList.add(newProduct);
        CSVUtils.write(path, productList);
    }

    @Override
    public void update(int id, Product updateProduct) {
        getProducts();
        for (int i = 0; i < productList.size(); i++) {
            if(productList.get(i).getId() == id){
                productList.set(i,updateProduct);
                CSVUtils.write(path,productList);
            }
        }
    }

    @Override
    public List<Product> getProducts() {
        List<Product> newProductList = new ArrayList<>();
        List<String> reads = CSVUtils.read(path);
        for (String read:reads) {
            newProductList.add(new Product(read));
        }
        return productList = newProductList;
    }

    @Override
    public void updateQuantity(int id, int quantity) {
        getProducts();
        for (int i = 0; i < productList.size(); i++) {
            if(productList.get(i).getId() == id){
                productList.get(i).setQuantity(quantity);
                CSVUtils.write(path,productList);
            }
        }
    }

    public int getQuantity(int id) {
        getProducts();
        int soluong = 0;
        for (Product product : productList) {
            if (product.getId() == id) {
                soluong = product.getQuantity();
            }
        }
        return soluong;
    }

    public boolean existProduct(int id){
        getProducts();
        for (Product product: productList) {
            if (product.getId() == id){
                return true;
            }
        }
        return false;
    }

    public boolean existProductName(String name){
        getProducts();
        for (Product product: productList) {
            if (product.getName().equals(name)){
                return true;
            }
        }
        return false;
    }


    public Product findProductbyID(int id){
        getProducts();
        for (Product product : productList) {
            if (product.getId() == id){
                return product;
            }
        }
        System.out.println("\t\t\t\tKhông có sản phẩm này");
        return null;
    }


    public void checkExist (){
        getProducts();
        for (Product p :productList) {
            if (p.getQuantity() == 0){
                p.setStatus(Status.OUTOFSTOCK);
                CSVUtils.write(path,productList);
            } else {
                p.setStatus(Status.INSTOCK);
                CSVUtils.write(path,productList);
            }
        }
    }
}
