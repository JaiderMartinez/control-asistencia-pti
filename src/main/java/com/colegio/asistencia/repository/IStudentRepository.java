package com.colegio.asistencia.repository;

import com.colegio.asistencia.entity.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IStudentRepository extends JpaRepository<StudentEntity, String> {

    List<StudentEntity> findByDniStudentStartingWith(String dniStudent);
}
