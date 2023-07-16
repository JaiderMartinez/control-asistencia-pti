package com.colegio.asistencia.repositories.jpa.repository;

import com.colegio.asistencia.models.entity.EnvironmentPtiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IEnvironmentPtiRepository extends JpaRepository<EnvironmentPtiEntity, Long> {

    List<EnvironmentPtiEntity> getAllByUserEntityIdUser(Long idTeacher);
}
