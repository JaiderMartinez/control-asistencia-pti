package com.colegio.asistencia.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "estudiantes")
public class StudentEntity {

    @Id
    private String dniStudent;
    private String name;
    private String grado;
    private String contactNumber;
    private String residenceAddress;
    private LocalDate birthdate;
}
