package com.example.reactivemongo.dto;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto {

    private String id;

    private String name;

    private int quantity;


    private double price;

}
