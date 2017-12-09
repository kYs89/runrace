package com.cricri.RunRace.dao;

import java.util.List;

import com.cricri.RunRace.modelo.Amistad;
import com.cricri.RunRace.modelo.EstadoAmistad;
import com.cricri.RunRace.modelo.Usuario;

public interface AmistadDAO {
	public boolean crearAmigo (Usuario amigo) throws DAOException;
	
	public boolean cambiarEstado(Amistad amistad, EstadoAmistad estado) throws DAOException;
	
	public boolean eliminarAmistad(Amistad amistad) throws DAOException;
	
	public List<Amistad> buscarAmistadesAceptadas(Integer id) throws DAOException;
	
	public List<Amistad> buscarAmistadesSolicitadas(Integer id) throws DAOException;
	
	public List<Amistad> buscarAmistadesPendientes(Integer id) throws DAOException;
	
	public List<Amistad> buscarAmistadesBloqueadas(Integer id) throws DAOException;
}
