package com.cricri.RunRace.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import com.cricri.RunRace.beans.UserBean;
import com.cricri.RunRace.modelo.Amistad;
import com.cricri.RunRace.modelo.EstadoAmistad;
import com.cricri.RunRace.modelo.Usuario;

public class JPAAmistadDAO implements AmistadDAO {
	EntityManagerFactory emf = Persistence.createEntityManagerFactory(DAOFactoria.EM);

	@Override
	public boolean crearAmigo(Usuario amigo) throws DAOException {
		try {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();

			Amistad a1 = new Amistad(UserBean.getUsuario(), amigo, EstadoAmistad.SOLICITADA);
			Amistad a2 = new Amistad(amigo, UserBean.getUsuario(), EstadoAmistad.PENDIENTE);
			
			UserBean.getUsuario().getAmigos().add(a1);
			amigo.getAmigos().add(a2);

			em.persist(a1);
			em.persist(a2);
			em.flush();
			em.getTransaction().commit();
			em.close();
			
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}
	
	@Override
	public boolean cambiarEstado(Amistad amistad, EstadoAmistad estado) throws DAOException {
		try {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();

			amistad.setEstado(EstadoAmistad.ACEPTADA);
			amistad = em.find(Amistad.class, amistad.getAmistadId());
			
			amistad.setEstado(EstadoAmistad.ACEPTADA);
			
			Query q2 = em.createNativeQuery("UPDATE AMISTAD SET ESTADO = '"+ EstadoAmistad.ACEPTADA +"' WHERE USUARIO_ID = ? AND AMIGO_ID = ?");
			q2.setParameter(1, amistad.getAmigo().getId());
			q2.setParameter(2, amistad.getUsuario().getId());

			int filas2 = q2.executeUpdate();

			em.getTransaction().commit();
			em.close();
			
			if (filas2 < 1){
				return false;
			}
			
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}

	@Override
	public boolean eliminarAmistad(Amistad amistad) throws DAOException {
		try {
			EntityManager em = emf.createEntityManager();
			em.getTransaction().begin();

			amistad = em.find(Amistad.class, amistad.getAmistadId());
			em.remove(amistad);
			
			Query q2 = em.createNativeQuery("DELETE FROM AMISTAD WHERE USUARIO_ID = ? AND AMIGO_ID = ?");
			q2.setParameter(1, amistad.getAmigo().getId());
			q2.setParameter(2, amistad.getUsuario().getId());

			int filas2 = q2.executeUpdate();
			
			em.getTransaction().commit();
			em.close();
			
			if (filas2 < 1){
				return false;
			}
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Amistad> buscarAmistadesAceptadas(Integer id) throws DAOException {
		EntityManager em = emf.createEntityManager();
		Query q = em.createNativeQuery(
				"SELECT * FROM AMISTAD WHERE USUARIO_ID = ? AND ESTADO = '" 
		+ EstadoAmistad.ACEPTADA + "'",
				Amistad.class);
		q.setParameter(1, id);
		
		List<Amistad> lista = q.getResultList();
		em.close();
		
		return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Amistad> buscarAmistadesSolicitadas(Integer id) throws DAOException {
		EntityManager em = emf.createEntityManager();
		Query q = em.createNativeQuery(
				"SELECT * FROM AMISTAD WHERE USUARIO_ID = ? AND ESTADO = '"  
		+ EstadoAmistad.SOLICITADA + "'",
				Amistad.class);
		q.setParameter(1, id);
		
		List<Amistad> lista = q.getResultList();
		em.close();
		
		return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Amistad> buscarAmistadesPendientes(Integer id) throws DAOException {
		EntityManager em = emf.createEntityManager();
		Query q = em.createNativeQuery(
				"SELECT * FROM AMISTAD WHERE USUARIO_ID = ? AND ESTADO = '" 
		+ EstadoAmistad.PENDIENTE + "'",
				Amistad.class);
		q.setParameter(1, id);
		
		List<Amistad> lista = q.getResultList();
		em.close();
		
		return lista;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Amistad> buscarAmistadesBloqueadas(Integer id) throws DAOException {
		EntityManager em = emf.createEntityManager();
		Query q = em.createNativeQuery(
				"SELECT * FROM AMISTAD WHERE USUARIO_ID = ? AND ESTADO = '"  + EstadoAmistad.BLOQUEADA + "'",
				Amistad.class);
		q.setParameter(1, id);
		
		List<Amistad> lista = q.getResultList();
		em.close();
		
		return lista;
	}

}
