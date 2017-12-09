package com.cricri.RunRace.modelo;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;

@Entity
@IdClass(AmistadId.class)
public class Amistad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	private Usuario usuario;

	@Id
	private Usuario amigo;

	@Enumerated(EnumType.STRING)
	private EstadoAmistad estado;

	@Temporal(TemporalType.DATE)
	private Date fecha;
	
	public Amistad (Usuario usuario, Usuario amigo, EstadoAmistad estado){
		this.usuario = usuario;
		this.amigo = amigo;
		this.estado = estado;
		fecha = new Date();
	}
	
	public Amistad() {}
	
	public Usuario getUsuario() {
		return usuario;
	}
	
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getAmigo() {
		return amigo;
	}

	public void setAmigo(Usuario amigo) {
		this.amigo = amigo;
	}

	public EstadoAmistad getEstado() {
		return estado;
	}

	public void setEstado(EstadoAmistad estado) {
		this.estado = estado;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public AmistadId getAmistadId() {
		AmistadId amistadId = new AmistadId();
		amistadId.setUsuario(usuario.getId());
		amistadId.setAmigo(amigo.getId());
		return amistadId;
	}
	
}

class AmistadId {
	private Integer usuario;
	private Integer amigo;

	public Integer getAmigo() {
		return amigo;
	}

	public void setAmigo(Integer amigo) {
		this.amigo = amigo;
	}

	public Integer getUsuario() {
		return usuario;
	}

	public void setUsuario(Integer usuario) {
		this.usuario = usuario;
	}

}