package com.colegio.asistencia.repositories.jpa.repository;

import com.colegio.asistencia.models.entity.StudentEntity;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStudentRepository extends JpaRepository<StudentEntity, String> {

    List<StudentEntity> findByDniStudentStartingWith(String dniStudent);

    List<StudentEntity> findAllByEnvironmentPtiEntityCodePti(Long codePti, Sort sort);
}
