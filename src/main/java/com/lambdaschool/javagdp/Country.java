package com.lambdaschool.javagdp;

import lombok.Data;

@Data
public class Country {
    private String name;
    private double gdp;

    public Country() {
    }

    public Country(String name, double gdp) {
        this.name = name;
        this.gdp = gdp;
    }
}