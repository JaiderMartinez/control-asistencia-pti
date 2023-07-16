package com.colegio.asistencia.models.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "usuarios")
public class UserEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUser;
    @Column(name = "cargo")
    private String employeeRole;
    @Column(name = "password")
    private String password;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dni", referencedColumnName = "dni")
    @Cascade(CascadeType.ALL)
    private EmployeeEntity employeeEntity;
}
