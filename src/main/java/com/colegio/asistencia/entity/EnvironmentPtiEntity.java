package com.colegio.asistencia.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "ambientesPti")
public class EnvironmentPtiEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long codePti;
    private String name;
    private String description;
    private String classroom;
    private LocalTime startTime;
    private LocalTime endTime;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "UserEntity", referencedColumnName = "idUser")
    private UserEntity userEntity;
}
