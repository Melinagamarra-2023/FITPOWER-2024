package com.itec1.service;

import com.itec1.dto.request.TrainerRequestDto;
import com.itec1.dto.response.TrainerResponseDto;
import com.itec1.model.Trainer;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface TrainerService {
    Trainer toEntity(TrainerRequestDto dto);
    TrainerResponseDto toDto(Trainer trainer);

    List<TrainerResponseDto> toDtos(List<Trainer> trainers);
    TrainerResponseDto findByDni(String dni) throws EntityNotFoundException;
    TrainerResponseDto save(TrainerRequestDto trainerRequestDto);
    TrainerResponseDto update(String dni, TrainerRequestDto trainerRequestDto) throws EntityNotFoundException;
    void delete(String dni) throws EntityNotFoundException;
}
