package com.colegio.asistencia.service.impl;

import com.colegio.asistencia.dto.response.EnvironmentsOfPTIResponseDto;
import com.colegio.asistencia.dto.response.SearchFoundStudentResponseDto;
import com.colegio.asistencia.entity.EnvironmentPtiEntity;
import com.colegio.asistencia.entity.StudentEntity;
import com.colegio.asistencia.exceptions.DataNotFoundException;
import com.colegio.asistencia.mapper.response.IEnvironmentPtiResponseMapper;
import com.colegio.asistencia.mapper.response.IStudentResponseMapper;
import com.colegio.asistencia.repository.IEnvironmentPtiRepository;
import com.colegio.asistencia.repository.IStudentRepository;
import com.colegio.asistencia.service.ICommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommonServiceImpl implements ICommonService {

    private final IStudentRepository studentRepository;
    private final IStudentResponseMapper studentResponseMapper;
    private final IEnvironmentPtiRepository environmentPtiRepository;
    private final IEnvironmentPtiResponseMapper environmentPtiResponseMapper;

    @Override
    public List<SearchFoundStudentResponseDto> findByDniStudentStartingWith(String dniStudent) {
        List<StudentEntity> listOfSearchFoundForStudents = studentRepository.findByDniStudentStartingWith(dniStudent);
        if (listOfSearchFoundForStudents.isEmpty() )
            throw new DataNotFoundException("No se encontro ningun estudiante que en su dni comience con el valor '" + dniStudent + "'");
        return listOfSearchFoundForStudents.stream().map(studentResponseMapper::toSearchFoundStudentResponseDto).toList();
    }

    @Override
    public List<EnvironmentsOfPTIResponseDto> findAllEnvironments() {
        List<EnvironmentPtiEntity> listOfEnvironmentsPtiEntities = environmentPtiRepository.findAll();
        return listOfEnvironmentsPtiEntities.stream().map(environmentPtiResponseMapper::toEnvironmentsOfPTIResponseDto).toList();
    }
}
