package com.example.pc.quanlitaichinhcanhan;

public class ListChooseIncome {
    private String title;
    private String money;
    private String date;
    private int id;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isCheck() {
        return check;
    }

    public void setCheck(boolean check) {
        this.check = check;
    }

    private boolean check;

    public ListChooseIncome(String title, String money, String date, int id, boolean check) {
        this.title = title;
        this.money = money;
        this.date = date;
        this.id = id;
        this.check = check;
    }

}
