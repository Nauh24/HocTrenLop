package com.nauh.demo.model;

public class Cat {
    private int img;
    private String name, desc;

    private double age;

    public Cat(int img, String name) {
        this.img = img;
        this.name = name;
    }

    public Cat(int img, String name, String desc, double age) {
        this.img = img;
        this.name = name;
        this.desc = desc;
        this.age = age;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public double getAge() {
        return age;
    }

    public void setAge(double age) {
        this.age = age;
    }
}
