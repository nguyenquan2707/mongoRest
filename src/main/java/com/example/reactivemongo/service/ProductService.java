package com.example.reactivemongo.service;

import com.example.reactivemongo.dto.ProductDto;
import com.example.reactivemongo.enity.Product;
import com.example.reactivemongo.repo.ProductRepo;
import com.example.reactivemongo.utils.AppUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ProductService {

    @Autowired
    private ProductRepo productRepo;

    public Flux<ProductDto> getProducts() {
        System.out.println(productRepo.findAll());
        return productRepo.findAll().map(AppUtils::mapToDto);
    }

    public Mono<ProductDto> getProduct(String id) {
        return productRepo.findById(id).map(AppUtils::mapToDto);
    }

    public Flux<ProductDto> getProductInRange(double min, double max) {
        return productRepo.findByPriceBetween(Range.closed(min, max));
    }

    public Mono<ProductDto> saveProduct(Mono<ProductDto> productDtoMono) {
        return productDtoMono.map(AppUtils::dtoToEntity)
                .flatMap(productRepo::insert)
                .map(AppUtils::mapToDto);
    }

    public Mono<ProductDto> updateProduct(Mono<ProductDto> productDto, String id) {

        return productRepo.findById(id)
                .flatMap(p -> productDto.map(AppUtils::dtoToEntity)
                        .doOnNext(e -> e.setId(id)))
                        .flatMap(productRepo::save)
                .map(AppUtils::mapToDto);

    }

    public Mono<Void> delete(String id) {
        return productRepo.deleteById(id);
    }
}
