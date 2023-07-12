package com.colegio.asistencia.persistence.jpa.repository;

import com.colegio.asistencia.persistence.jpa.entity.AttendanceStudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAttendanceStudentRepository extends JpaRepository<AttendanceStudentEntity, Long> {
}
