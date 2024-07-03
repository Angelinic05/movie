package com.examen.modules.gender.infraestructure;

import java.util.List;
import java.util.Optional;

import com.examen.modules.country.domain.Country;

public interface GenderRepository {
    void save(Country country);
    void update(Country country);
    Optional<Country> findById(int id);
    void delete(int id);
    List<Country> findAll();
}
