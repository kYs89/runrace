package com.cricri.RunRace.beans;

import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import com.cricri.RunRace.RunRace;
import com.cricri.RunRace.modelo.Amistad;
import com.cricri.RunRace.modelo.EstadoAmistad;
import com.cricri.RunRace.modelo.Usuario;
import com.cricri.RunRace.servicios.ServicioAmistad;
import com.cricri.RunRace.servicios.ServicioUsuarios;

@ManagedBean
@ViewScoped
public class AmistadesBean {
	private String nombre;
	private String apellido1;
	private String apellido2;
	private Date fechaNac;
	private String correo;
	private List<Usuario> usuarios;
	private List<Amistad> amigos;
	private Usuario usuarioSeleccionado;

	private ServicioUsuarios servicioUsuarios;
	private ServicioAmistad servicioAmistad;

	public AmistadesBean() {
		servicioUsuarios = ServicioUsuarios.getInstance();
		servicioAmistad = ServicioAmistad.getInstance();

		amigos = UserBean.getUsuario().getAmigos();
	}

	public String buscarUsuarios() {
		usuarios = servicioUsuarios.buscarUsuario(nombre, apellido1, apellido2, correo);
		return null;
	}

	public String crearAmigo() {
		FacesMessage message = null;

		if (usuarioSeleccionado == null) {
			return RunRace.NAVEGACION_FALLO;
		}

		if (servicioAmistad.guardarAmigo(usuarioSeleccionado)) {
			amigos = UserBean.getUsuario().getAmigos();
			message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Hecho!",
					"Solicitud de amistad enviada a " + usuarioSeleccionado.getNombre());
			FacesContext.getCurrentInstance().addMessage(null, message);
			return RunRace.NAVEGACION_AMISTADES;
		} else {
			message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",
					usuarioSeleccionado.getNombre() + " ya está en tu lista de amigos");
			FacesContext.getCurrentInstance().addMessage(null, message);
		}
		return null;
	}

	public List<Amistad> getAmistadesAceptadas() {
		return servicioAmistad.buscarAmistadesAceptadas(UserBean.getUsuario().getId());
	}

	public List<Amistad> getAmistadesPendientes() {
		return servicioAmistad.buscarAmistadesPendientes(UserBean.getUsuario().getId());
	}

	public List<Amistad> getAmistadesSolicitadas() {
		return servicioAmistad.buscarAmistadesSolicitadas(UserBean.getUsuario().getId());
	}

	public String aceptarAmistad(Amistad amistad) {
		if (servicioAmistad.cambiarEstado(amistad, EstadoAmistad.ACEPTADA)) {
			return null;
		}
		return RunRace.NAVEGACION_FALLO;
	}

	public String eliminarAmistad(Amistad amistad) {
		if (servicioAmistad.eliminarAmistad(amistad)) {
			amigos = UserBean.getUsuario().getAmigos();
			return null;
		}
		return RunRace.NAVEGACION_FALLO;
	}
	
	public String getRowStyle(Amistad amistad){
		switch (amistad.getEstado()) {
		case ACEPTADA:
			return "amistadAceptada";
		case SOLICITADA:
			return "amistadSolicitada";
		case PENDIENTE:
			return "amistadPendiente";
		default:
			return "";
		}
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public Date getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario getUsuarioSeleccionado() {
		return usuarioSeleccionado;
	}

	public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
		this.usuarioSeleccionado = usuarioSeleccionado;
	}

	public List<Amistad> getAmigos() {
		return amigos;
	}

	public void setAmigos(List<Amistad> amigos) {
		this.amigos = amigos;
	}

	public boolean isAmistades() {
		return !getAmistadesAceptadas().isEmpty();
	}
	
	public boolean isPendientes() {
		return !getAmistadesPendientes().isEmpty();
	}
	
	public boolean isSolicitadas() {
		return !getAmistadesSolicitadas().isEmpty();
	}
}
