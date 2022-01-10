package View;

import DAO.ConnectionDao;
import Model.Category;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryView implements iCategory{
    ConnectionDao connection = null;
    private String SELECTALL = "select * from category;";
    @Override
    public List<Model.Category> selectAllCategory() {
        List<Category> list = new ArrayList<>();
        try {
            connection = new ConnectionDao();
            PreparedStatement preparedStatement = connection.getConnection().prepareStatement(SELECTALL);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                list.add(new Category(id,name));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
}
