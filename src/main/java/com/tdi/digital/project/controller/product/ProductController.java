package com.tdi.digital.project.controller.product;

import com.tdi.digital.project.controller.product.dto.ProductDTO;
import com.tdi.digital.project.entitiy.ProductEntity;
import com.tdi.digital.project.persistence.repository.product.ProductRepository;
import com.tdi.digital.project.persistence.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public @ResponseBody
    ProductEntity create(@RequestBody ProductDTO input) {
        ProductEntity product = new ProductEntity();
        product.setName(input.getName());
        product.setDescription(input.getDescription());
        product.setPrice(input.getPrice());
        product.setQuantity(input.getQuantity());

        product = productService.createOrReplaceProduct(product);

        return product;
    }

    @GetMapping("/")
    public @ResponseBody Iterable<ProductEntity> getAllProduct() {
        return productService.getAllProducts();
    }

    @GetMapping("/{itemId}")
    public @ResponseBody
    ProductEntity read(@PathVariable("itemId") Integer itemId) throws ResponseStatusException {
        return productService.getProductById(itemId);
    }

    @DeleteMapping("/{itemId}")
    public HttpStatus delete(@PathVariable("itemId") Integer itemId) throws ResponseStatusException {
        productService.deleteProductById(itemId);
        return HttpStatus.FORBIDDEN;
    }

    @PatchMapping("/{itemId}")
    public @ResponseBody ProductEntity update(@RequestBody ProductDTO partialInput,
                                              @PathVariable("itemId") Integer itemId) throws ResponseStatusException{
        ProductEntity partialProduct = new ProductEntity();
        partialProduct.setId(itemId);
        partialProduct.setName(partialInput.getName());
        partialProduct.setDescription(partialInput.getDescription());
        partialProduct.setPrice(partialInput.getPrice());
        partialProduct.setQuantity(partialInput.getQuantity());

        return productService.updateProduct(partialProduct);
    }

    @PutMapping(value = "/{itemId}")
    public @ResponseBody ProductEntity replace(@RequestBody ProductDTO input,
                                               @PathVariable("itemId") Integer itemId) throws ResponseStatusException {
        ProductEntity product = new ProductEntity();
        product.setId(itemId);
        product.setName(input.getName());
        product.setDescription(input.getDescription());
        product.setPrice(input.getPrice());
        product.setQuantity(input.getQuantity());

        return productService.createOrReplaceProduct(product);
    }
}
