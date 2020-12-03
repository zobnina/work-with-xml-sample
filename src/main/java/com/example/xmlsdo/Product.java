package com.example.xmlsdo;

import java.util.Map;

public class Product {
    private String name;
    private double price;
    private boolean availability;
    private Map<String, String> reviews;

    public Product() {
    }

    public Product(String name, double price, boolean availability, Map<String, String> reviews) {
        this.name = name;
        this.price = price;
        this.availability = availability;
        this.reviews = reviews;
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

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public Map<String, String> getReviews() {
        return reviews;
    }

    public void setReviews(Map<String, String> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", availability=" + availability +
                ", reviews=" + reviews +
                '}';
    }
}
