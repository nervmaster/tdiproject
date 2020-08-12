package com.tdi.digital.project.controller.product;

import com.tdi.digital.project.controller.product.dto.ProductDTO;
import com.tdi.digital.project.entitiy.ProductEntity;
import com.tdi.digital.project.persistence.service.product.read.ProductReadService;
import com.tdi.digital.project.persistence.service.product.write.ProductWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@CrossOrigin
@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductReadService readProductService;
    @Autowired
    private ProductWriteService writeProductService;

    @PostMapping
    public @ResponseBody
    ProductEntity create(@RequestBody ProductDTO input) {
        ProductEntity product = new ProductEntity();
        product.setName(input.getName());
        product.setPrice(input.getPrice());
        product.setQuantity(input.getQuantity());

        product = writeProductService.createOrReplaceProduct(product);

        return product;
    }

    @GetMapping("/")
    public @ResponseBody Iterable<ProductEntity> getAllProduct() {
        return readProductService.getAllProducts();
    }

    @GetMapping("/{itemId}")
    public @ResponseBody
    ProductEntity read(@PathVariable("itemId") Integer itemId) throws ResponseStatusException {
        return readProductService.getProductById(itemId);
    }

    @DeleteMapping("/{itemId}")
    public HttpStatus delete(@PathVariable("itemId") Integer itemId) throws ResponseStatusException {
        writeProductService.deleteProductById(itemId);
        return HttpStatus.OK;
    }

    @PatchMapping("/{itemId}")
    public @ResponseBody ProductEntity update(@RequestBody ProductDTO partialInput,
                                              @PathVariable("itemId") Integer itemId) throws ResponseStatusException{
        ProductEntity partialProduct = new ProductEntity();
        partialProduct.setId(itemId);
        partialProduct.setName(partialInput.getName());
        partialProduct.setPrice(partialInput.getPrice());
        partialProduct.setQuantity(partialInput.getQuantity());

        return writeProductService.updateProduct(partialProduct);
    }

    @PutMapping(value = "/{itemId}")
    public @ResponseBody ProductEntity replace(@RequestBody ProductDTO input,
                                               @PathVariable("itemId") Integer itemId) throws ResponseStatusException {
        ProductEntity product = new ProductEntity();
        product.setId(itemId);
        product.setName(input.getName());
        product.setPrice(input.getPrice());
        product.setQuantity(input.getQuantity());

        return writeProductService.createOrReplaceProduct(product);
    }
}
