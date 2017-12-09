package com.cricri.RunRace.dao;

import java.util.Date;
import java.util.List;

import com.cricri.RunRace.modelo.Carrera;
import com.cricri.RunRace.modelo.EstadoCarrera;

public interface CarreraDAO {
	
	public Carrera createCarrera(String nombre, String descripcion, Date fecha, Integer distancia, EstadoCarrera estado) throws DAOException;

	public Carrera findCarreraById(Integer id) throws DAOException;
	
	public Carrera findCarrerasByUsuario(Integer idUsuario) throws DAOException;

	public List<Carrera> findAll() throws DAOException;

	public void update(Carrera c) throws DAOException;
}
