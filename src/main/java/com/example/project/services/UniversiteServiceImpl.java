package com.example.project.services;

import com.example.project.Entities.Foyer;
import com.example.project.Entities.Universite;
import com.example.project.repository.FoyerRepository;
import com.example.project.repository.UniversiteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UniversiteServiceImpl implements IUniversite {

    private final UniversiteRepository universiteRepository;
    private final FoyerRepository foyerRepository;

    @Override
    public Universite create(Universite universite) {
        return universiteRepository.save(universite);
    }

    @Override
    public Universite update(Long id, Universite universite) {
        Universite existingUniversite = universiteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Université introuvable avec ID : " + id));

        // Mise à jour conditionnelle comme dans BlocServiceImpl
        if (universite.getNomUniversite() != null) {
            existingUniversite.setNomUniversite(universite.getNomUniversite());
        }

        if (universite.getAdresse() != null) {
            existingUniversite.setAdresse(universite.getAdresse());
        }

        if (universite.getFoyer() != null) {
            existingUniversite.setFoyer(universite.getFoyer());
        }

        return universiteRepository.save(existingUniversite);
    }

    @Override
    public void delete(Long id) {
        if (!universiteRepository.existsById(id)) {
            throw new RuntimeException("Université introuvable avec ID : " + id);
        }
        universiteRepository.deleteById(id);
    }

    @Override
    public List<Universite> getAll() {
        return universiteRepository.findAll();
    }

    @Override
    public Universite getById(Long id) {
        return universiteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Université introuvable avec ID : " + id));
    }

}
