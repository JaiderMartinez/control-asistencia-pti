package com.colegio.asistencia.infrastructure.configuration;

import com.colegio.asistencia.domain.api.IAdminUseCasePort;
import com.colegio.asistencia.domain.spi.IEmployeePersistencePort;
import com.colegio.asistencia.domain.spi.IUserPersistencePort;
import com.colegio.asistencia.domain.usecase.AdminUseCase;
import com.colegio.asistencia.infrastructure.adapters.driven.mapper.IEmployeeEntityMapper;
import com.colegio.asistencia.infrastructure.adapters.driven.mapper.IUserEntityMapper;
import com.colegio.asistencia.infrastructure.adapters.driven.persistence.EmployeePersistenceAdapter;
import com.colegio.asistencia.infrastructure.adapters.driven.persistence.UserPersistenceAdapter;
import com.colegio.asistencia.infrastructure.adapters.driven.repository.IEmployeeRepository;
import com.colegio.asistencia.infrastructure.adapters.driven.repository.IUserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@AllArgsConstructor
@Configuration
public class AdminBeanConfiguration {

    private final IUserRepository userRepository;
    private final IUserEntityMapper userEntityMapper;
    private final IEmployeeRepository employeeRepository;
    private final IEmployeeEntityMapper employeeEntityMapper;

    @Bean
    public IUserPersistencePort userPersistencePort() {
        return new UserPersistenceAdapter(userRepository, userEntityMapper);
    }

    @Bean
    public IEmployeePersistencePort employeePersistencePort() {
        return new EmployeePersistenceAdapter(employeeRepository, employeeEntityMapper);
    }

    @Bean
    public IAdminUseCasePort adminUseCasePort() {
        return new AdminUseCase(userPersistencePort(), employeePersistencePort());
    }
}
