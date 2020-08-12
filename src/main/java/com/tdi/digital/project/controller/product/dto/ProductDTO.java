package com.tdi.digital.project.controller.product.dto;

public class ProductDTO {

    private Integer id;
    private String name;
    private Integer price;
    private Integer quantity;

    public String getName() {
        return name;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
