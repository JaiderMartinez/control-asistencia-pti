package com.colegio.asistencia.repositories.jpa.repository;

import com.colegio.asistencia.models.entity.AttendanceStudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAttendanceStudentRepository extends JpaRepository<AttendanceStudentEntity, Long> {
}
