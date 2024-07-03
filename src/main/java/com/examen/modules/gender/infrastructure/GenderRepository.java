package com.examen.modules.gender.infrastructure;

import java.util.List;
import java.util.Optional;

import com.examen.modules.gender.domain.Gender;

public interface GenderRepository {
    void save(Gender gender);
    void update(Gender gender);
    Optional<Gender> findById(int id);
    void delete(int id);
    List<Gender> findAll();
}
