package com.itec1.repository;

import com.itec1.model.Trainer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TrainerRepository extends JpaRepository<Trainer, Long> {
    Optional<Trainer> findByDni(String dni);
    boolean existsByDni(String dni);
    Optional<Trainer> findByEmail(String email);
    boolean existsByEmail(String email);
}
