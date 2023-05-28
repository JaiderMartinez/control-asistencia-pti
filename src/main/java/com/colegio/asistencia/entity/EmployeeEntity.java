package com.colegio.asistencia.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
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

