package com.colegio.asistencia.infrastructure.adapters.driven.repository;

import com.colegio.asistencia.infrastructure.adapters.driven.entity.EnvironmentPtiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEnvironmentPtiRepository extends JpaRepository<EnvironmentPtiEntity, Long> {
}
