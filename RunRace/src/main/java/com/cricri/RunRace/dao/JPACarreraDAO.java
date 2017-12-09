package com.cricri.RunRace.dao;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.cricri.RunRace.modelo.Carrera;
import com.cricri.RunRace.modelo.EstadoCarrera;

public class JPACarreraDAO implements CarreraDAO {
	
	EntityManagerFactory emf = Persistence.createEntityManagerFactory(DAOFactoria.EM);
	
	@Override
	public Carrera createCarrera(String nombre, String descripcion, Date fecha, Integer distancia, EstadoCarrera estado)
			throws DAOException {

		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		
		Carrera c = new Carrera();
		c.setNombre(nombre);
		c.setDescripcion(descripcion);
		c.setFecha(fecha);
		c.setDistancia(distancia);
		c.setEstado(estado);
		c.setFechaCreacion(new Date());
		
		em.persist(c);
		em.flush();
		em.getTransaction().commit();
		em.close();
		
		return c;
	}

	@Override
	public Carrera findCarreraById(Integer id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Carrera findCarrerasByUsuario(Integer idUsuario) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Carrera> findAll() throws DAOException {

		EntityManager em = emf.createEntityManager();
		Query q = em.createNativeQuery("SELECT * FROM CARRERA",
				Carrera.class);
		List<Carrera> lista = q.getResultList();
		
		em.close();

		return lista;
	}

	@Override
	public void update(Carrera c) throws DAOException {
		// TODO Auto-generated method stub
		
	}

}
