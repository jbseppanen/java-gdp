package com.lambdaschool.javagdp;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
public class CountryController {

    private final CountryRepository countryRepo;
    private final RabbitTemplate rTemplate;

    public CountryController(CountryRepository countryRepo, RabbitTemplate rTemplate) {
        this.countryRepo = countryRepo;
        this.rTemplate = rTemplate;
    }

    @GetMapping("/names")
    public List<Country> getAll() {
        ArrayList<Country> countries = (ArrayList<Country>) countryRepo.findAll();
        countries.sort((e1, e2) -> e1.getCountry().compareToIgnoreCase(e2.getCountry()));
        return countries;
    }

    @PostMapping("/names")
    public List<Country> loadCountries(@RequestBody List<Country> countriesToUpload) {
        return countryRepo.saveAll(countriesToUpload);
    }
}