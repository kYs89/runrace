package com.cricri.RunRace.dao;

import com.cricri.RunRace.modelo.Grupo;


public interface GrupoDAO {
	public Grupo createGrupo(String nombre) throws DAOException;

	public Grupo findGrupoById(Integer id) throws DAOException;
	
	public Grupo findGruposByUsuario(Integer idUsuario) throws DAOException;

	public java.util.Collection<Grupo> findAll() throws DAOException;

	public void update(Grupo g) throws DAOException;
}
