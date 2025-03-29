package com.example.notebook.commercebackend.Service;

import com.example.notebook.commercebackend.Dao.CountryRepository;
import com.example.notebook.commercebackend.entity.Country;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    private final CountryRepository countryRepository;

    @Autowired
    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Country> findAllCountries() {
        return countryRepository.findAll();
    }

    public Country findById(Integer id) {
        return countryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Country not found with id: " + id));
    }
}

