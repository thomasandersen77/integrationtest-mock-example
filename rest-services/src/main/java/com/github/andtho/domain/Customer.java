package com.github.andtho.domain;

import lombok.Data;

@Data
public class Customer {
    private String firstname;
    private String lastname;
    private String ssn;
    private Address address;
}
