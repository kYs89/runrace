package com.cricri.RunRace.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.cricri.RunRace.beans.UserBean;
import com.cricri.RunRace.modelo.Usuario;

public class JPAUsuarioDAO implements UsuarioDAO {

	public Usuario crearUsuario(String nombre, String apellido1, String apellido2, Date fechaNac, String correo,
			String pass, String direccion, String localidad, String provincia) throws DAOException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(DAOFactoria.EM);
		EntityManager em = emf.createEntityManager();

		try {
			Usuario u = new Usuario(nombre, apellido1, apellido2, fechaNac, correo, pass, direccion, localidad,
					provincia);

			em.getTransaction().begin();
			try {
				em.persist(u);
				em.flush();
				em.getTransaction().commit();
			} catch (Exception e) {
				em.getTransaction().rollback();
			}
			em.close();
			emf.close();

			return u;
		} catch (Exception e) {

			em.getTransaction().rollback();

			em.close();
			emf.close();
			return null;
		}
	}

	@Override
	public Usuario buscarUsuarioId(Integer id) throws DAOException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(DAOFactoria.EM);
		EntityManager em = emf.createEntityManager();

		Usuario u = em.find(Usuario.class, id);

		em.close();
		emf.close();
		return u;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Usuario buscarUsuarioCorreo(String correo) throws DAOException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(DAOFactoria.EM);
		EntityManager em = emf.createEntityManager();
		Query q = em.createNativeQuery("SELECT * FROM USUARIO WHERE CORREO = ?", Usuario.class);
		q.setParameter(1, correo);
		List<Usuario> lista = q.getResultList();

		em.close();
		emf.close();

		Usuario u = null;
		if (!lista.isEmpty()) {
			u = lista.get(0);
		}
		return u;
	}

	@Override
	public List<Usuario> buscarTodos() throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Usuario> buscarUsuariosComodin(String nombre, String apellido1, String apellido2, String correo)
			throws DAOException {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory(DAOFactoria.EM);
		EntityManager em = emf.createEntityManager();

		String consulta = "SELECT * FROM USUARIO WHERE id <> " + UserBean.getUsuario().getId();
		if (nombre != null && !nombre.equals(""))
			consulta += " AND NOMBRE LIKE '%" + nombre + "%'";
		if (apellido1 != null && !apellido1.equals(""))
			consulta += " AND APELLIDO_1 LIKE '%" + apellido1 + "%'";
		if (apellido2 != null && !apellido2.equals(""))
			consulta += " AND APELLIDO_2 LIKE '%" + apellido2 + "%'";
		if (correo != null && !correo.equals(""))
			consulta += " AND CORREO LIKE '%" + correo + "%'";

		Query q = em.createNativeQuery(consulta, Usuario.class);
		System.out.println(q.toString());
		List<Usuario> lista = q.getResultList();

		em.close();
		emf.close();

		return lista;
	}

	@Override
	public Usuario modificarUsuario(Usuario u) throws DAOException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(DAOFactoria.EM);
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		try {
			em.find(Usuario.class, u);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			em.getTransaction().rollback();
		}

		em.close();
		emf.close();

		return u;
	}

	@Override
	public Usuario borrarUsuario(Usuario u) throws DAOException {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory(DAOFactoria.EM);
		EntityManager em = emf.createEntityManager();

		em.getTransaction().begin();
		try {
			em.remove(u);
			em.flush();
			em.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			em.getTransaction().rollback();
		}

		em.close();
		emf.close();

		return null;
	}
}
