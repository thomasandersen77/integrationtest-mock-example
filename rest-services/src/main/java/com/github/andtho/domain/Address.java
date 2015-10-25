package com.github.andtho.domain;

import lombok.*;
import lombok.experimental.Builder;

import java.io.Serializable;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Address implements Serializable{
    private String street;
    private int number;
    private String city;
}
