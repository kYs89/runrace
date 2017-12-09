package com.cricri.RunRace.servicios;

import java.util.Date;
import java.util.List;

import com.cricri.RunRace.dao.DAOException;
import com.cricri.RunRace.dao.DAOFactoria;
import com.cricri.RunRace.dao.UsuarioDAO;
import com.cricri.RunRace.modelo.Usuario;

public class ServicioUsuarios {
	private static ServicioUsuarios instance;
	private UsuarioDAO dao;

	private ServicioUsuarios() {
		try {
			dao = DAOFactoria.getDAOFactoria(DAOFactoria.JPA).getUsuarioDAO();
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ServicioUsuarios getInstance() {
		if (instance == null) {
			instance = new ServicioUsuarios();
		}
		return instance;
	}

	public boolean loginUsuario(String correo, String pass) {
		try {
			Usuario u = dao.buscarUsuarioCorreo(correo);

			if (u == null)
				return false;
			if (u.getPass().equals(pass))
				return true;

		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}

		return false;
	}

	public boolean registrarUsuario(String nombre, String apellido1, String apellido2, String correo, Date fechaNac,
			String direccion, String localidad, String provincia, String pass) {
		try {
			Usuario u = dao.crearUsuario(nombre, apellido1, apellido2, fechaNac, correo, pass, direccion, localidad, provincia);

			if (u.getId() > 0)
				return true;
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public List<Usuario> buscarUsuario(String nombre, String apellido1, String apellido2, String correo) {
		try {
			return dao.buscarUsuariosComodin(nombre, apellido1, apellido2, correo);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public Usuario buscarUsuarioCorreo(String correo){
		try {
			return dao.buscarUsuarioCorreo(correo);
		} catch (DAOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
