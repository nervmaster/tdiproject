package com.tdi.digital.project.controller.product.dto;

public class ProductDTO {

    private Integer id;
    private String name;
    private String description;
    private String price;
    private Integer quantity;

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }

}
