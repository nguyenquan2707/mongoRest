package com.example.reactivemongo.utils;

import com.example.reactivemongo.dto.ProductDto;
import com.example.reactivemongo.enity.Product;
import org.springframework.beans.BeanUtils;

public class AppUtils {

    public static ProductDto mapToDto(Product product) {

        ProductDto productDto = new ProductDto();
        BeanUtils.copyProperties(product, productDto);

        return productDto;
    }

    public static Product dtoToEntity(ProductDto productDto) {
        Product product = new Product();
        BeanUtils.copyProperties(productDto, product);
        return product;
    }

}
