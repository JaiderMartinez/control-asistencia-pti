package com.colegio.asistencia.infrastructure.adapters.driven.repository;

import com.colegio.asistencia.infrastructure.adapters.driven.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long> {
}
