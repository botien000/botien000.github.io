package com.poly.myasm.DTO;

import java.util.Date;

public class Income_Expense {
    private int id;
    private String name;
    private String description;
    private Date date;
    private double money;
    private int flag;

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    private int category_id;
    public Income_Expense(){}

    public Income_Expense(int id, String name, String description, Date date,double money, int flag, int category_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.money = money;
        this.flag = flag;
        this.category_id = category_id;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getCategory_id() {
        return category_id;
    }

    public void setCategory_id(int category_id) {
        this.category_id = category_id;
    }
}
