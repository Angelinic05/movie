package com.examen.modules.country.application;

import java.util.List;
import java.util.Optional;

import com.examen.modules.country.domain.Country;
import com.examen.modules.country.infrastructure.CountryRepository;


public class CountryService {
    private CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public void saveCountry(Country country){
        countryRepository.save(country);

    }
    public void deleteCountry(int id){
        countryRepository.delete(id);
    }

    public void updateCountry(Country model){
        countryRepository.update(model);
    }

    public Optional<Country> findByIdCountry(int id){
        return countryRepository.findById(id);
    }

    public List<Country> findAllCountry(){
        return countryRepository.findAll();
    }
    
}
