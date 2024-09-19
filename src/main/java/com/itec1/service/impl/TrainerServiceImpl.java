package com.itec1.service.impl;

import com.itec1.dto.request.TrainerRequestDto;
import com.itec1.dto.response.TrainerResponseDto;
import com.itec1.model.Trainer;
import com.itec1.repository.TrainerRepository;
import com.itec1.service.TrainerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class TrainerServiceImpl implements TrainerService {

    private final TrainerRepository trainerRepository;

    public TrainerServiceImpl(TrainerRepository trainerRepository) {
        this.trainerRepository = trainerRepository;
    }

    @Override
    public Trainer toEntity(TrainerRequestDto dto) {
        Trainer trainer = new Trainer();
        trainer.setDni(dto.getDni());
        trainer.setName(dto.getName());
        trainer.setLastname(dto.getLastname());
        trainer.setEmail(dto.getEmail());
        trainer.setPhoneNumber(dto.getPhoneNumber());
        trainer.setSpeciality(dto.getSpeciality());
        LocalDate birthDate = LocalDate.parse(dto.getBirthDate());
        trainer.setBirthDate(birthDate);
        return trainer;
    }

    @Override
    public TrainerResponseDto toDto(Trainer trainer) {
        TrainerResponseDto trainerResponseDto = new TrainerResponseDto();
        trainerResponseDto.setId(trainer.getId());
        trainerResponseDto.setDni(trainer.getDni());
        trainerResponseDto.setName(trainer.getName());
        trainerResponseDto.setLastname(trainer.getLastname());
        trainerResponseDto.setEmail(trainer.getEmail());
        trainerResponseDto.setPhoneNumber(trainer.getPhoneNumber());
        trainerResponseDto.setSpeciality(trainer.getSpeciality());
        trainerResponseDto.setBirthDate(trainer.getBirthDate().toString());
        trainerResponseDto.setCreatedAt(trainer.getCreatedAt().toString());
        trainerResponseDto.setEnabled(trainer.isEnabled());
        return trainerResponseDto;
    }

    @Override
    public List<TrainerResponseDto> toDtos(List<Trainer> trainers) {
        return trainers.stream().map(this::toDto).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public TrainerResponseDto findByDni(String dni) throws EntityNotFoundException {
        Optional<Trainer> trainer = trainerRepository.findByDni(dni);
        return trainer.map(this::toDto).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    @Transactional
    public TrainerResponseDto save(TrainerRequestDto trainerRequestDto) {
        Trainer trainer = toEntity(trainerRequestDto);
        trainer = trainerRepository.save(trainer);
        return toDto(trainer);
    }

    @Override
    @Transactional
    public TrainerResponseDto update(String dni, TrainerRequestDto trainerRequestDto) throws EntityNotFoundException {
        if (!trainerRepository.existsByDni(dni)) {
            throw new EntityNotFoundException();
        }
        Trainer trainer = toEntity(trainerRequestDto);
        trainer.setDni(dni);
        trainer = trainerRepository.save(trainer);
        return toDto(trainer);
    }

    @Override
    @Transactional
    public void delete(String dni) throws EntityNotFoundException {
        Optional<Trainer> trainer = trainerRepository.findByDni(dni);
        if (trainer.isEmpty()) {
            throw new EntityNotFoundException();
        }
        trainer.get().setEnabled(false);
        trainerRepository.save(trainer.get());
    }
}
