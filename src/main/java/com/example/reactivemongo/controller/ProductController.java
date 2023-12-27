package com.example.reactivemongo.controller;

import com.example.reactivemongo.dto.ProductDto;
import com.example.reactivemongo.service.ProductService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/products")
public class ProductController {


    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public Flux<ProductDto> products() {
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public Mono<ProductDto> getProduct(@PathVariable("id") String id) {
        return productService.getProduct(id);
    }

    @GetMapping("/product-range")
    public Flux<ProductDto> getProductRange(@RequestParam("min") double min, @RequestParam("max") double max) {
        return productService.getProductInRange(min, max);
    }


    @PostMapping
    public Mono<ProductDto> saveProduct(@RequestBody Mono<ProductDto> productDto) {
        return productService.saveProduct(productDto);

    }

    @PutMapping("/update/{id}")
    public Mono<ProductDto> updateProduct(@RequestBody Mono<ProductDto> productDto, @PathVariable String id) {

        return productService.updateProduct(productDto, id);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteProduct(@PathVariable("id") String id) {
        return productService.delete(id);
    }

}
