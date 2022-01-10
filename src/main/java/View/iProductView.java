package View;

import Model.Product;

import java.util.List;

public interface iProductView {
    public List<Product> selectAllProduct();
    public List<Product> selectActiveProduct();
    public boolean addProduct(Product product);
    public boolean editProduct(Product product);
    public Product selectProduct(int id);
    public boolean removeProduct(int id);
    public boolean activeProduct(int id);
    public List<Product> searchProduct(String name);
}
