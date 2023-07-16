package com.colegio.asistencia.repositories.jpa.repository;

import com.colegio.asistencia.models.entity.AttendanceEntity;
import com.colegio.asistencia.models.entity.AttendanceTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAttendanceRepository extends JpaRepository<AttendanceEntity, Long> {

    AttendanceEntity findByAttendanceType(AttendanceTypeEnum attendanceTypeEnum);
}
