package com.github.andtho.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Product {
    private String id;
    private String name;
    private int price;
}
