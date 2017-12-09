package com.cricri.RunRace.servicios;

import java.util.Date;
import java.util.List;

import com.cricri.RunRace.dao.CarreraDAO;
import com.cricri.RunRace.dao.DAOException;
import com.cricri.RunRace.dao.DAOFactoria;
import com.cricri.RunRace.modelo.Carrera;
import com.cricri.RunRace.modelo.EstadoCarrera;

public class ServicioCarreras {
	private static ServicioCarreras instance;
	private CarreraDAO dao;

	private ServicioCarreras() {
		try {
			dao = DAOFactoria.getDAOFactoria(DAOFactoria.JPA).getCarreraDAO();
		} catch (DAOException e) {
			System.out.println("Error al obtener el DAO");
			e.printStackTrace();
		}
	}

	public static ServicioCarreras getInstance() {
		if (instance == null) {
			instance = new ServicioCarreras();
		}
		return instance;
	}
	
	public boolean guardarCarrera(String nombre, String descripcion, Date fecha, Integer distancia, EstadoCarrera estado) {
		try {
			Carrera c = dao.createCarrera(nombre, descripcion, fecha, distancia, estado);

			if (c.getId() > 0)
				return true;
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public List<Carrera> recuperarTodas(){
		try {
			return dao.findAll();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
