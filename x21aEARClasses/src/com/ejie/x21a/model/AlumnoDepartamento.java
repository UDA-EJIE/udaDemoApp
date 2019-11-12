package com.ejie.x21a.model;

public class AlumnoDepartamento implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	private Alumno alumno;
	
	private Departamento departamento;

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento dep) {
		this.departamento = dep;
	}
	
	
	
}
