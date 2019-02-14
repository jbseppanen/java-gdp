package com.lambdaschool.javagdp;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
    public List<Country> getAllSortedAlphabetically() {
        ArrayList<Country> countries = (ArrayList<Country>) countryRepo.findAll();
        countries.sort((e1, e2) -> e1.getCountry().compareToIgnoreCase(e2.getCountry()));
        return countries;
    }

    @GetMapping("/economy")
    public List<Country> getAllSortedByEconomy() {
        ArrayList<Country> countries = (ArrayList<Country>) countryRepo.findAll();
        countries.sort((e1, e2)-> e2.getGdp().compareTo(e1.getGdp()));
        return countries;
    }

    @GetMapping("/total")
    public ObjectNode getTotalGdp() {
        ArrayList<Country> countries = (ArrayList<Country>) countryRepo.findAll();
        Long total = 0L;
        for (Country country:countries) {
            total+=country.getGdp();
        }
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode node = mapper.createObjectNode();
        node.put("Total", total);
        return node;
    }


    @PostMapping("/names")
    public List<Country> loadCountries(@RequestBody List<Country> countriesToUpload) {
        return countryRepo.saveAll(countriesToUpload);
    }


}