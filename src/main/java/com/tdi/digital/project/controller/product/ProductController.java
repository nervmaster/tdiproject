package com.tdi.digital.project.controller.product;

import com.tdi.digital.project.controller.product.dto.ProductDTO;
import com.tdi.digital.project.entitiy.ProductEntity;
import com.tdi.digital.project.persistence.repository.product.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired private ProductRepository productRepository;

    @PostMapping
    public @ResponseBody
    ProductEntity create(@RequestBody ProductDTO input) {
        ProductEntity product = new ProductEntity();
        product.setName(input.getName());
        product.setDescription(input.getDescription());
        product.setPrice(input.getPrice());
        product.setQuantity(input.getQuantity());

        product = productRepository.save(product);

        return product;
    }

    @GetMapping("/")
    public @ResponseBody Iterable<ProductEntity> getAllProduct() {
        return productRepository.findAll();
    }

    @GetMapping("/{itemId}")
    public @ResponseBody
    ProductEntity read(@PathVariable("itemId") Integer itemId) {
        Optional<ProductEntity> response = productRepository.findById(itemId);
        if(!response.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found");
        }
        return response.get();
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
