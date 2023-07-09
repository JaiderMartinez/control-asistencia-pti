package com.colegio.asistencia.persistence.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "empleados")
public class EmployeeEntity {

	@Id
	private Long dni;
	@Column(name = "nombre")
	private String name;
	@Column(name = "apellido")
	private String lastName;
	@Column(name = "correo")
	private String mail;
	@Column(name = "telefono")
	private String cellPhone;
}

