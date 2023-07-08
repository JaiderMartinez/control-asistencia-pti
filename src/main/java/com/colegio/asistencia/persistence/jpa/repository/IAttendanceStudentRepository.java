package com.colegio.asistencia.persistence.jpa.repository;

import com.colegio.asistencia.persistence.jpa.entity.AttendanceStudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IAttendanceStudentRepository extends JpaRepository<AttendanceStudentEntity, Long> {

    List<AttendanceStudentEntity> findAllByAttendanceEntityId(Long idAttendance);
}
