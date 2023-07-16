package com.colegio.asistencia.repositories.jpa.repository;

import com.colegio.asistencia.models.entity.EmployeeEntity;
import com.colegio.asistencia.models.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByEmployeeEntity(EmployeeEntity employeeEntity);

    Optional<UserEntity> findByEmployeeEntityDni(Long dni);
}
