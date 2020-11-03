package com.jimds.buyers.dto;

import com.jimds.buyers.model.Product;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Column;
import javax.persistence.Transient;
import javax.validation.constraints.NotBlank;

public class ProductDTO {

    @Column
    @NotBlank
    private String title;

    @Column
    @NotBlank
    private int stockLimit;

    @Column
    @NotBlank
    private float price;

    @Column
    @NotBlank
    private String description;

    @Column
    @NotBlank
    private String tag;

    @Column
    @NotBlank
    private String brand;

    @NotBlank
    private MultipartFile imageFile;

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



    public MultipartFile getImageFile() {
        return imageFile;
    }

    public void setImageFile(MultipartFile imageFile) {
        this.imageFile = imageFile;
    }

    public Product toProduct(){
        return new Product(title,
                stockLimit,price,brand,description,tag);
    }

}
