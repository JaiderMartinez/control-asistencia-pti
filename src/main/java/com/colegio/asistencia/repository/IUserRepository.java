package com.colegio.asistencia.repository;

import com.colegio.asistencia.entity.EmployeeEntity;
import com.colegio.asistencia.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByEmployeeEntity(EmployeeEntity employeeEntity);

    UserEntity findByEmployeeEntityDni(Long dni);
}
