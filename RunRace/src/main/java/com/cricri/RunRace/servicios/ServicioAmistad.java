package com.cricri.RunRace.servicios;

import java.util.List;

import com.cricri.RunRace.dao.AmistadDAO;
import com.cricri.RunRace.dao.DAOException;
import com.cricri.RunRace.dao.DAOFactoria;
import com.cricri.RunRace.modelo.Amistad;
import com.cricri.RunRace.modelo.EstadoAmistad;
import com.cricri.RunRace.modelo.Usuario;

public class ServicioAmistad {
	
	private static ServicioAmistad instance;
	private AmistadDAO dao;

	private ServicioAmistad() {
		try {
			dao = DAOFactoria.getDAOFactoria(DAOFactoria.JPA).getAmistadDAO();
		} catch (DAOException e) {
			e.printStackTrace();
		}
	}

	public static ServicioAmistad getInstance() {
		if (instance == null) {
			instance = new ServicioAmistad();
		}
		return instance;
	}

	public boolean guardarAmigo(Usuario amigo) {
		try {
			return dao.crearAmigo(amigo);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public List<Amistad> buscarAmistadesAceptadas(Integer id){
		try {
			return dao.buscarAmistadesAceptadas(id);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return null;
	}	
	
	public List<Amistad> buscarAmistadesSolicitadas(Integer id){
		try {
			return dao.buscarAmistadesSolicitadas(id);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public List<Amistad> buscarAmistadesPendientes(Integer id){
		try {
			return dao.buscarAmistadesPendientes(id);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public boolean cambiarEstado(Amistad amistad, EstadoAmistad estado){
		try {
			return dao.cambiarEstado(amistad, estado);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean eliminarAmistad(Amistad amistad){
		try {
			return dao.eliminarAmistad(amistad);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		return false;
	}
}
