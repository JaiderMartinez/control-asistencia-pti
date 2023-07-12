package com.colegio.asistencia.persistence.criteria.implementation;

import com.colegio.asistencia.persistence.criteria.IStudentCriteriaRepository;
import com.colegio.asistencia.persistence.criteria.dto.StudentAttendanceDto;
import com.colegio.asistencia.persistence.jpa.entity.AttendanceEntity;
import com.colegio.asistencia.persistence.jpa.entity.AttendanceStudentEntity;
import com.colegio.asistencia.persistence.jpa.entity.AttendanceTypeEnum;
import com.colegio.asistencia.persistence.jpa.entity.EnvironmentPtiEntity;
import com.colegio.asistencia.persistence.jpa.entity.StudentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StudentCriteriaRepositoryImplementation implements IStudentCriteriaRepository {

    private final EntityManager entityManager;

    @Override
    public List<StudentAttendanceDto> getAllStudentAttendanceDetails(Long codeEnvironmentPti) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<StudentAttendanceDto> query = cb.createQuery(StudentAttendanceDto.class);
        Root<AttendanceStudentEntity> attendanceStudentEntityRoot = query.from(AttendanceStudentEntity.class);

        Join<AttendanceStudentEntity, AttendanceEntity> attendanceJoin = attendanceStudentEntityRoot.join("attendanceEntity");
        Join<AttendanceStudentEntity, StudentEntity> studentJoin = attendanceStudentEntityRoot.join("studentEntity");
        Join<StudentEntity, EnvironmentPtiEntity> environmentJoin = studentJoin.join("environmentPtiEntity");

        query.select(cb.construct(
                StudentAttendanceDto.class,
                studentJoin.get("name").alias("nombre"),
                cb.sum(cb.<Integer>selectCase()
                                .when(cb.equal(attendanceJoin.get("attendanceType"), AttendanceTypeEnum.ASISTENCIA.name()), 1)
                                .otherwise(0))
                        .as(Integer.class)
                        .alias("asistencias"),
                cb.sum(cb.<Integer>selectCase()
                                .when(cb.equal(attendanceJoin.get("attendanceType"), AttendanceTypeEnum.INASISTENCIA.name()), 1)
                                .otherwise(0))
                        .as(Integer.class)
                        .alias("inasistencias"),
                cb.sum(cb.<Integer>selectCase()
                                .when(cb.equal(attendanceJoin.get("attendanceType"), AttendanceTypeEnum.EVASION.name()), 1)
                                .otherwise(0))
                        .as(Integer.class)
                        .alias("evasiones"),
                cb.sum(cb.<Integer>selectCase()
                                .when(cb.equal(attendanceJoin.get("attendanceType"), AttendanceTypeEnum.RETRASO.name()), 1)
                                .otherwise(0))
                        .as(Integer.class)
                        .alias("retrasos")
        ));

        query.where(cb.equal(environmentJoin.get("codePti"), codeEnvironmentPti));
        query.groupBy(studentJoin.get("name"));

        return entityManager.createQuery(query).getResultList();
    }
}
