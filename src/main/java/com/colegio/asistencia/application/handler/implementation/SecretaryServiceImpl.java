package com.colegio.asistencia.application.handler.implementation;

import com.colegio.asistencia.application.handler.ISecretaryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class SecretaryServiceImpl implements ISecretaryService {
}
