package com.tdi.digital.project.controller;

import com.tdi.digital.project.controller.dto.ProductDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product")
public class ProductController {

    @PostMapping
    public String create(@RequestBody ProductDTO product) {
        return "Creating product " + product.getId() + "\n";
    }

    @GetMapping("/{itemId}")
    public String read(@PathVariable("itemId") String itemId) {
        return "Reading product " + itemId + "\n";
    }

    @DeleteMapping("/{itemId}")
    public String delete(@PathVariable("itemId") String itemId) {
        return "Deleting product " + itemId + "\n";
    }

    @PatchMapping("/{itemId}")
    public String update(@PathVariable("itemId") String itemId) {
        return "Updating product " + itemId + "\n";
    }

    @PutMapping(value = "/{itemId}")
    public String replace(@PathVariable("itemId") String itemId) {
        return "Replacing product " + itemId + "\n";
    }
}
