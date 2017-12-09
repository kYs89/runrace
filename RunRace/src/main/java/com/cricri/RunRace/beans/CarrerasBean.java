package com.cricri.RunRace.beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.cricri.RunRace.RunRace;
import com.cricri.RunRace.modelo.Carrera;
import com.cricri.RunRace.modelo.EstadoCarrera;
import com.cricri.RunRace.modelo.Grupo;
import com.cricri.RunRace.servicios.ServicioCarreras;

@ManagedBean
@ViewScoped
public class CarrerasBean {
	private Integer id;
	private String nombre;
	private String descripcion;
	private Date fecha;
	private Integer distancia;
	private EstadoCarrera estado;
	private String estadoAux;
	private List<Grupo> grupos;
	private List<Carrera> carreras;
	private List<Carrera> carrerasUsuario;
	
	public CarrerasBean(){
		carreras = ServicioCarreras.getInstance().recuperarTodas();
	}

	public String guardar() {
		if (ServicioCarreras.getInstance().guardarCarrera(nombre, descripcion, fecha, distancia, estado)) {
			carreras = ServicioCarreras.getInstance().recuperarTodas();
			return RunRace.NAVEGACION_CARRERAS;
		}
		return RunRace.NAVEGACION_FALLO;
	}

	public List<String> getEstados(){
		List<String> l = new ArrayList<>();
		for (EstadoCarrera s: EstadoCarrera.values()){
			l.add(s.name());
		}
		return l;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Integer getDistancia() {
		return distancia;
	}

	public void setDistancia(Integer distancia) {
		this.distancia = distancia;
	}

	public EstadoCarrera getEstado() {
		return estado;
	}

	public void setEstado(EstadoCarrera estado) {
		this.estado = estado;
	}
	
	public String getEstadoAux() {
		estadoAux = String.valueOf(estado);
		return estadoAux;
	}
	
	public void setEstadoAux(String estadoAux) {
		estado = EstadoCarrera.valueOf(estadoAux);
		this.estadoAux = estadoAux;
	}

	public List<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(List<Grupo> grupos) {
		this.grupos = grupos;
	}

	public List<Carrera> getCarreras() {
		return carreras;
	}

	public void setCarreras(List<Carrera> carreras) {
		this.carreras = carreras;
	}

	public List<Carrera> getCarrerasUsuario() {
		return carrerasUsuario;
	}

	public void setCarrerasUsuario(List<Carrera> carrerasUsuario) {
		this.carrerasUsuario = carrerasUsuario;
	}
}
