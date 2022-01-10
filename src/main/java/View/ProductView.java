package View;

import DAO.ConnectionDao;
import Model.Product;
import Enum.StatusEnum;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductView implements iProductView {
    ConnectionDao connection = null;
    private String SELECTALL = "SELECT * FROM quanlysanpham.product inner join category where product.idCategory= category.id;";
    private String SELECTACTIVE = "SELECT * FROM quanlysanpham.product inner join category where product.idCategory= category.id && product.status=1;";
    private String ADD = "insert into product(product.name,product.price,product.quantity,product.color,product.desc,product.idCategory) values(?, ?, ?, ?, ?, ?);";
    private String UPDATE = "UPDATE product SET product.name=?, product.price=?, product.quantity=?, product.color=? ,product.desc=?, product.idCategory=? WHERE product.id=?;";
    private String REMOVE = "UPDATE product SET status='0' WHERE id=?;";
    private String ACTIVE = "UPDATE product SET status='1' WHERE id=?;";
    private String SEARCH= "select * from product INNER join category where product.name LIKE ? && product.idCategory= category.id;";

    @Override
    public List<Product> selectAllProduct() {
        StatusEnum status;
        List<Product> list = new ArrayList<>();
        try {
            connection = new ConnectionDao();
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(SELECTALL);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity= rs.getInt("quantity");
                String color= rs.getString("color");
                String desc = rs.getString("desc");
                int idCategory = rs.getInt("idCategory");
                String nameCategory= rs.getString("Category.name");
                status = StatusEnum.parseStatus(rs.getInt("status"));
                list.add(new Product(id,name,price,quantity,color,desc,idCategory,status,nameCategory));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public List<Product> selectActiveProduct() {
        StatusEnum status;
        List<Product> list = new ArrayList<>();
        try {
            connection = new ConnectionDao();
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(SELECTACTIVE);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity= rs.getInt("quantity");
                String color= rs.getString("color");
                String desc = rs.getString("desc");
                int idCategory = rs.getInt("idCategory");
                String nameCategory= rs.getString("Category.name");
                status = StatusEnum.parseStatus(rs.getInt("status"));
                list.add(new Product(id,name,price,quantity,color,desc,idCategory,status,nameCategory));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean addProduct(Product product) {
        boolean check = false;
        connection = new ConnectionDao();
        try {
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(ADD);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3,product.getQuantity());
            preparedStatement.setString(4,product.getColor());
            preparedStatement.setString(5, product.getDesc());
            preparedStatement.setInt(6, product.getIdCategory());
            check = preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return check;
    }

    @Override
    public boolean editProduct(Product product) {
        boolean check = false;
        connection = new ConnectionDao();
        try {
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(UPDATE);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.setInt(3, product.getQuantity());
            preparedStatement.setString(4, product.getColor());
            preparedStatement.setString(5, product.getDesc());
            preparedStatement.setInt(6, product.getIdCategory());
            preparedStatement.setInt(7, product.getId());
            check = preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return check;
    }

    @Override
    public boolean removeProduct(int id) {
        boolean check = false;
        connection = new ConnectionDao();
        try {
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(REMOVE);
            preparedStatement.setInt(1, id);
            check = preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return check;
    }

    @Override
    public boolean activeProduct(int id) {
        boolean check = false;
        connection = new ConnectionDao();
        try {
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(ACTIVE);
            preparedStatement.setInt(1, id);
            check = preparedStatement.executeUpdate() > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return check;
    }

    @Override
    public List<Product> searchProduct(String name) {
        StatusEnum status;
        List<Product> list = new ArrayList<>();
        try {
            connection = new ConnectionDao();
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(SEARCH);
            preparedStatement.setString(1, "%"+ name +"%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String nameS = rs.getString("name");
                double price = rs.getDouble("price");
                int quantity= rs.getInt("quantity");
                String color= rs.getString("color");
                String desc = rs.getString("desc");
                int idCategory = rs.getInt("idCategory");
                String nameCategory= rs.getString("Category.name");
                status = StatusEnum.parseStatus(rs.getInt("status"));
                list.add(new Product(id,nameS,price,quantity,color,desc,idCategory,status,nameCategory));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    @Override
    public Product selectProduct(int id) {
        List<Product> list = selectAllProduct();
        for (Product product : list) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }
}
