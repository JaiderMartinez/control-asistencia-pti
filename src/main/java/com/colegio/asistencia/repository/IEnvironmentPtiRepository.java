package com.colegio.asistencia.repository;

import com.colegio.asistencia.entity.EnvironmentPtiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IEnvironmentPtiRepository extends JpaRepository<EnvironmentPtiEntity, Long> {
}
