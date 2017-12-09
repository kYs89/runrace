package com.cricri.RunRace.dao;

public class JPADAOFactoria extends DAOFactoria {

	public UsuarioDAO getUsuarioDAO() {
		return (UsuarioDAO) new JPAUsuarioDAO();
	}

	@Override
	public CarreraDAO getCarreraDAO() {
		return (CarreraDAO) new JPACarreraDAO();
	}

	@Override
	public GrupoDAO getGrupoDAO() {
		return (GrupoDAO) new JPAGrupoDAO();
	}

	@Override
	public AmistadDAO getAmistadDAO() {
		return (AmistadDAO) new JPAAmistadDAO();
	}
}
