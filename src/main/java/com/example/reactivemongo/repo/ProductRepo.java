package com.example.reactivemongo.repo;

import com.example.reactivemongo.dto.ProductDto;
import com.example.reactivemongo.enity.Product;
import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ProductRepo extends ReactiveMongoRepository<Product, String> {
    Flux<ProductDto> findByPriceBetween(Range<Double> priceRange);
}
