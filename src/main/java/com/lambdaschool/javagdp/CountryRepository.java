package com.lambdaschool.javagdp;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
    Country findCountryByNameIgnoreCase(String name);
}
