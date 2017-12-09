package com.cricri.RunRace.beans;

import java.util.Date;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.cricri.RunRace.RunRace;
import com.cricri.RunRace.servicios.ServicioUsuarios;

@ManagedBean
@ViewScoped
public class RegistroBean {
	private String correo;
	private String pass;
	private String pass2;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String direccion;
	private Date fechaCreacion;
	private Date fechaNac;
	private String localidad;
	private String provincia;

	public String registrar() {
		try {
			java.sql.Date fecha = new java.sql.Date(fechaNac.getTime());
			if (pass.equals(pass2)) {
				if (ServicioUsuarios.getInstance().registrarUsuario(nombre, apellido1, apellido2, correo, fecha, direccion,
						localidad, provincia, pass)) {
					limpiarAtributos();
					return RunRace.NAVEGACION_LOGIN;
				}
			}
			limpiarAtributos();
			return RunRace.NAVEGACION_FALLO;
		} catch (Exception e) {
			return RunRace.NAVEGACION_FALLO;
		}
	}
	
	private void limpiarAtributos() {
		correo = new String();
		pass = new String();
		pass2 = new String();
		nombre = new String();
		apellido1 = new String();
		apellido2 = new String();
		direccion = new String();
		fechaCreacion = new Date();
		fechaNac = new Date();
		localidad = new String();
		provincia = new String();
	}
	
	public String getApellido1() {
		return this.apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return this.apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public String getCorreo() {
		return this.correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccion() {
		return this.direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Date getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Date getFechaNac() {
		return this.fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getLocalidad() {
		return this.localidad;
	}

	public void setLocalidad(String localidad) {
		this.localidad = localidad;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getPass2() {
		return pass2;
	}

	public void setPass2(String pass2) {
		this.pass2 = pass2;
	}

	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}
}
