package Model;

import Enum.StatusEnum;

public class Product {
    private int id;
    private String name;
    private double price;
    private int quantity;
    private String color;
    private String desc;
    private int idCategory;
    private StatusEnum status;
    private String nameCategory;

    public String getNameCategory() {
        return nameCategory;
    }

    public String getPriceString() {
        return String.format("%.1f",this.price);
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public Product(int id, String name, double price, int quantity, String color, String desc, int idCategory) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.color = color;
        this.desc = desc;
        this.idCategory = idCategory;
    }

    public Product(int id, String name, double price, int quantity, String color, String desc, int idCategory, StatusEnum status, String nameCategory) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.color = color;
        this.desc = desc;
        this.idCategory = idCategory;
        this.status = status;
        this.nameCategory = nameCategory;
    }

    public Product(int id, String name, double price, int quantity, String color, String desc, int idCategory, StatusEnum status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.color = color;
        this.desc = desc;
        this.idCategory = idCategory;
        this.status = status;

    }

    public Product(String name, double price, int quantity, String color, String desc, int idCategory) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.color = color;
        this.desc = desc;
        this.idCategory = idCategory;
    }

    public Product() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
