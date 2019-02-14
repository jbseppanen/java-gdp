package com.lambdaschool.javagdp;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Country {

    private @Id
    @GeneratedValue
    Long id;
    private String name;
    private Long gdp;

    public Country() {
    }

    public Country(String name, Long gdp) {
        this.name = name;
        this.gdp = gdp;
    }

    @JsonProperty("country")
    public String getName() {
        return name;
    }
}