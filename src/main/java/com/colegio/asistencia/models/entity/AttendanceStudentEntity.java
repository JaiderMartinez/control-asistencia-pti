package com.colegio.asistencia.models.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "asistencias_estudiantes")
public class AttendanceStudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "codigo_asistencia")
    private AttendanceEntity attendanceEntity;

    @ManyToOne
    @JoinColumn(name = "dni_estudiante")
    private StudentEntity studentEntity;

    @Column(name = "fecha_hora")
    private LocalDateTime dateTime;
    @Column(name = "observaciones")
    private String observations;
}
