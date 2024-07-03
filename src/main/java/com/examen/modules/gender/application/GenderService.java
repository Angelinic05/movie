package com.examen.modules.gender.application;

import java.util.List;
import java.util.Optional;

import com.examen.modules.gender.domain.Gender;
import com.examen.modules.gender.infrastructure.GenderRepository;

public class GenderService {
    private GenderRepository genderRepository;

    public GenderService(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    public void saveGender(Gender gender){
        genderRepository.save(gender);

    }
    public void deleteGender(int id){
        genderRepository.delete(id);
    }

    public void updateGender(Gender gender){
        genderRepository.update(gender);
    }

    public Optional<Gender> findByIdGender(int id){
        return genderRepository.findById(id);
    }

    public List<Gender> findAllGender(){
        return genderRepository.findAll();
    }
}
