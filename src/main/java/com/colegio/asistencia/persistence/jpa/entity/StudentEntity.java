package com.colegio.asistencia.persistence.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "estudiantes")
public class StudentEntity {

    @Id
    @Column(name = "dni")
    private String dniStudent;
    @Column(name = "nombre")
    private String name;
    @Column(name = "grado")
    private String grade;
    @Column(name = "numero_contacto_familiar")
    private String contactNumber;
    @Column(name = "direccion")
    private String residenceAddress;
    @Column(name = "fecha_nacimiento")
    private LocalDate birthDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codigo_pti", referencedColumnName = "codigo_pti")
    private EnvironmentPtiEntity environmentPtiEntity;
}
