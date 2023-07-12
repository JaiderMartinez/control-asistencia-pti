package com.colegio.asistencia.persistence.jpa.repository;

import com.colegio.asistencia.persistence.jpa.entity.AttendanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAttendanceRepository extends JpaRepository<AttendanceEntity, Long> {
}
