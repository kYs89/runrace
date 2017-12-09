package com.cricri.RunRace.dao;

public abstract class DAOFactoria {

	public abstract UsuarioDAO getUsuarioDAO();
	public abstract CarreraDAO getCarreraDAO();
	public abstract GrupoDAO getGrupoDAO();
	public abstract AmistadDAO getAmistadDAO();

	// Declaracion como constantes de los tipos de factoria
	public final static int JPA = 1;
	public final static String EM = "MySQL-RunRace";

	public static DAOFactoria getDAOFactoria(int tipo) throws DAOException {
		switch (tipo) {
		case JPA: {
			try {
				return new JPADAOFactoria();
			} catch (Exception e) {
				throw new DAOException(e.getMessage());
			}
		}
		default: return null;
		}
	}
}
