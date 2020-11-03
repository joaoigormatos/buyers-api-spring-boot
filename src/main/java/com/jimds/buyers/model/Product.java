package com.jimds.buyers.model;

import net.minidev.json.annotate.JsonIgnore;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private int id;

    @Column
    private String title;

    @Column
    private int stockLimit;

    @Column
    private float price;

    @Column
    private String description;

    @Column
    private String tag;

    @Column

    private String brand;

    @Column
    private String imageURL;

    public  Product(){

    }

    public Product(String title, int stockLimit, float price, String brand,String description, String tag) {
        this.title = title;
        this.stockLimit = stockLimit;
        this.price = price;
        this.description = description;
        this.tag = tag;
        this.brand = brand;
    }


    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getStockLimit() {
        return stockLimit;
    }

    public void setStockLimit(int stockLimit) {
        this.stockLimit = stockLimit;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }


    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", stockLimit=" + stockLimit +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", tag='" + tag + '\'' +
                ", brand='" + brand + '\'' +
                ", imageURL='" + imageURL + '\'' +

                '}';
    }
}
