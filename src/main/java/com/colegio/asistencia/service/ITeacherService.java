package com.colegio.asistencia.service;

import com.colegio.asistencia.dto.response.EnvironmentOfPTIResponseDto;
import com.colegio.asistencia.exceptions.DataNotFoundException;
import com.itextpdf.text.DocumentException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.io.FileNotFoundException;
import java.util.List;

public interface ITeacherService {

    void generatedReportInFormatPdf(Long codeEnvironmentPti, String pathToSaveFile) throws FileNotFoundException, DocumentException, UsernameNotFoundException, DataNotFoundException, IllegalArgumentException;

    List<EnvironmentOfPTIResponseDto> getAllEnvironmentsByDniOfTheTeacher(Long dniTeacher);
}
