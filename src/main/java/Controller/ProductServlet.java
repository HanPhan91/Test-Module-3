package Controller;

import Model.Category;
import Model.Product;
import View.CategoryView;
import View.ProductView;
import View.iCategory;
import View.iProductView;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/products")
public class ProductServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private iProductView productView = new ProductView();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null)
            action = "";
        switch (action) {
            case "create":
                showCreateForm(req, resp);
                break;
            case "edit":
                showEditForm(req, resp);
                break;
            case "remove":
                removeProduct(req, resp);
                break;
//            case "active":
//                activeProduct(req, resp);
//                break;

            default:
                listProduct(req, resp);
                break;
        }
    }

    private void searchProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String search= req.getParameter("search");
        List<Product> list = productView.searchProduct(search);
        System.out.println(list.size());
        req.setAttribute("listProduct", list);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/searchProduct.jsp");
        dispatcher.forward(req, resp);
    }

    private void showCreateForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        iCategory category = new CategoryView();
        List<Category> list = category.selectAllCategory();
        req.setAttribute("listCategory", list);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/createForm.jsp");
        dispatcher.forward(req, resp);
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        iCategory category = new CategoryView();
        List<Category> list = category.selectAllCategory();
        req.setAttribute("listCategory", list);
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = productView.selectProduct(id);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/editForm.jsp");
        req.setAttribute("product", product);
        dispatcher.forward(req, resp);
    }

    private void removeProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        boolean check = false;
        int id = Integer.parseInt(req.getParameter("id"));
        check = productView.removeProduct(id);
        resp.sendRedirect(req.getContextPath() + "/products?check=" + check);
    }

    private void listProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Product> list = productView.selectActiveProduct();
        String check = req.getParameter("check");
        if (check != null)
            req.setAttribute("check", check);
        req.setAttribute("listProduct", list);
        RequestDispatcher dispatcher = req.getRequestDispatcher("/index.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null)
            action = "";
        switch (action) {
            case "create":
                createProduct(req, resp);
                break;
            case "edit":
                editProduct(req, resp);
                break;
            case "search":
                searchProduct(req, resp);
                break;
        }
    }

    private void createProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        boolean check = false;
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        String color = req.getParameter("color");
        String desc = req.getParameter("desc");
        int idCategory = Integer.parseInt(req.getParameter("category"));
        Product newProduct = new Product(name, price, quantity, color, desc, idCategory);
        check = productView.addProduct(newProduct);
        resp.sendRedirect(req.getContextPath() + "/products?check=" + check);
    }

    private void editProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        boolean check = false;
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        String color = req.getParameter("color");
        String desc = req.getParameter("desc");
        int idCategory = Integer.parseInt(req.getParameter("category"));
        Product editProduct = new Product(id, name, price, quantity, color, desc, idCategory);
        check = productView.editProduct(editProduct);
        resp.sendRedirect(req.getContextPath() + "/products?check=" + check);
    }
}

