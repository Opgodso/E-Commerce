package com.example.notebook.commercebackend.Controller;

import com.example.notebook.commercebackend.Service.CountryService;
import com.example.notebook.commercebackend.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/countries")
@CrossOrigin(origins = "http://localhost:4200")
public class CountryController {

    private final CountryService countryService;

    @Autowired
    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping
    public List<Country> getAllCountries() {
        return countryService.findAllCountries();
    }

    @GetMapping("/{id}")
    public Country getCountryById(@PathVariable Integer id) {
        return countryService.findById(id);
    }
}
