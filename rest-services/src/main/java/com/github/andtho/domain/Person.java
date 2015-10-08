package com.github.andtho.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class Person implements Serializable{
    private String firstname;
    private String lastname;
    private String ssn;
    private Address address;
}
