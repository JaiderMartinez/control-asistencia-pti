package com.colegio.asistencia.services.implementations;

import com.colegio.asistencia.models.Constants;
import com.colegio.asistencia.dtos.request.AuthCredentials;
import com.colegio.asistencia.dtos.request.UserAsEmployeeResponseDto;
import com.colegio.asistencia.dtos.response.EnvironmentOfPTIResponseDto;
import com.colegio.asistencia.dtos.response.SearchFoundStudentResponseDto;
import com.colegio.asistencia.utils.mapper.EnvironmentMapper;
import com.colegio.asistencia.utils.mapper.StudentMapper;
import com.colegio.asistencia.utils.mapper.UserMapper;
import com.colegio.asistencia.models.entity.EnvironmentPtiEntity;
import com.colegio.asistencia.models.entity.StudentEntity;
import com.colegio.asistencia.exceptions.DataNotFoundException;
import com.colegio.asistencia.repositories.jpa.repository.IEnvironmentPtiRepository;
import com.colegio.asistencia.repositories.jpa.repository.IStudentRepository;
import com.colegio.asistencia.repositories.jpa.repository.IUserRepository;
import com.colegio.asistencia.services.ICommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommonServiceImpl implements ICommonService {

    private static final String MESSAGE_STUDENTS_NOT_FOUND = "No se encontro ningun estudiante que en su dni comience con el valor '%s'";
    private final IStudentRepository studentRepository;
    private final IEnvironmentPtiRepository environmentPtiRepository;
    private final IUserRepository userRepository;
    private final AuthenticationManager authenticationManager;

    @Override
    public List<SearchFoundStudentResponseDto> getAllStudentsThatBeginWithDni(String dniStudent) {
        List<StudentEntity> listOfSearchFoundForStudents = studentRepository.findByDniStudentStartingWith(dniStudent);
        if (listOfSearchFoundForStudents.isEmpty() )
            throw new DataNotFoundException(String.format(MESSAGE_STUDENTS_NOT_FOUND, dniStudent));
        return listOfSearchFoundForStudents.stream().map(StudentMapper::toSearchFoundStudentResponseDto).toList();
    }

    @Override
    public List<EnvironmentOfPTIResponseDto> findAllEnvironments() {
        List<EnvironmentPtiEntity> listOfEnvironmentsPtiEntities = environmentPtiRepository.findAll();
        if (listOfEnvironmentsPtiEntities.isEmpty() )
            throw new DataNotFoundException(Constants.MESSAGE_ENVIRONMENTS_OF_PTI_EMPTY);
        return listOfEnvironmentsPtiEntities.stream().map(EnvironmentMapper::toEnvironmentsOfPTIResponseDto).toList();
    }

    @Override
    public UserAsEmployeeResponseDto findUserAuthenticatedByDni(Long dni) {
        return UserMapper.toUserAsEmployeeResponseDto(this.userRepository.findByEmployeeEntityDni(dni).orElse(null));
    }

    @Override
    public Authentication singIn(AuthCredentials authCredentialRequest, HttpServletRequest request) throws AuthenticationException {
        Authentication userAuthenticated = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authCredentialRequest.getUsername(), authCredentialRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(userAuthenticated);
        request.getSession().setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());
        return userAuthenticated;
    }
}
