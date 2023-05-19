package com.colegio.asistencia.infrastructure.adapters.driven.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "empleados")
public class EmployeeEntity {

	@Id
	private Long dni;
	private String name;
	private String lastName;
	private String mail;
	private String cellPhone;
}

