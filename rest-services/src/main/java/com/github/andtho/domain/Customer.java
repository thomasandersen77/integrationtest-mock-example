package com.github.andtho.domain;

import lombok.Data;

@Data
public class Customer {
    private String firstname;
    private String lastname;
    private Address address;
    private Boolean single;
}
