package com.cricri.RunRace.dao;

import java.util.Date;
import java.util.List;

import com.cricri.RunRace.modelo.Usuario;

public interface UsuarioDAO {

	public Usuario crearUsuario(String nombre, String apellido1, String apellido2, Date fechaNac, String correo,
			String pass, String direccion, String localidad, String provincia) throws DAOException;

	public Usuario buscarUsuarioId(Integer id) throws DAOException;

	public Usuario buscarUsuarioCorreo(String correo) throws DAOException;

	public List<Usuario> buscarTodos() throws DAOException;

	public List<Usuario> buscarUsuariosComodin(String nombre, String apellido1, String apellido2, String correo)
			throws DAOException;

	public Usuario modificarUsuario(Usuario u) throws DAOException;

	public Usuario borrarUsuario(Usuario u) throws DAOException;
}
