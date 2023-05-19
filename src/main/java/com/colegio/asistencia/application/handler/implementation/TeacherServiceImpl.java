package com.colegio.asistencia.application.handler.implementation;

import com.colegio.asistencia.application.handler.ITeacherService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class TeacherServiceImpl implements ITeacherService {
}
