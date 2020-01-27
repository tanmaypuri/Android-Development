package com.example.carride.carride;

public class Product {

    private int id;
    private String name,gender;
    private  String phone;
    private String leaving,going;

    public Product(int id,  String name, String gender, String phone, String leaving, String going) {
        this.id = id;
        this.name = name;
        this.phone = phone;

        this.gender = gender;
        this.leaving = leaving;
        this.going = going;
    }

    public Product(String name, String phone, String gender, String leaving, String going) {
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

    public String getLeaving() {
        return  leaving;
    }

    public String getGoing() {
        return  going;
    }

}
