package com.lambdaschool.javagdp;

public class CountryNotFoundException extends RuntimeException {
    public CountryNotFoundException(Long id) {
        super("Could not find name");
    }
}
