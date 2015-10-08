package com.github.andtho.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class Address implements Serializable{
    private String street;
    private int number;
    private String city;

}
