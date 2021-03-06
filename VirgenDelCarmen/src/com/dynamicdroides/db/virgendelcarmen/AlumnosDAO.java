package com.dynamicdroides.db.virgendelcarmen;

import java.util.List;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.Cacheable;

/**
 * A data access object (DAO) providing persistence and search support for
 * Alumnos entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.dynamicdroides.db.virgendelcarmen.Alumnos
 * @author MyEclipse Persistence Tools
 */
public class AlumnosDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(AlumnosDAO.class);
	// property constants
	public static final String NOMBRE = "nombre";
	public static final String APELLIDOS = "apellidos";
	public static final String CURSO = "curso";
	public static final String CORREOPADRES = "correopadres";
	public static final String COMEDOR = "comedor";
	public static final String HASALERGIAS = "hasalergias";
	public static final String LISTAALERGIAS = "listaalergias";

	public void save(Alumnos transientInstance) {
		log.debug("saving Alumnos instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Alumnos persistentInstance) {
		log.debug("deleting Alumnos instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}
	@Cacheable(value="alumnos.data",key="#id")
	public Alumnos findById(java.lang.Integer id) {
		log.debug("getting Alumnos instance with id: " + id);
		try {
			Alumnos instance = (Alumnos) getSession().get(
					"com.dynamicdroides.db.virgendelcarmen.Alumnos", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Alumnos instance) {
		log.debug("finding Alumnos instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"com.dynamicdroides.db.virgendelcarmen.Alumnos")
					.add(Example.create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Alumnos instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Alumnos as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNombre(Object nombre) {
		return findByProperty(NOMBRE, nombre);
	}

	public List findByApellidos(Object apellidos) {
		return findByProperty(APELLIDOS, apellidos);
	}

	public List findByCurso(Object curso) {
		return findByProperty(CURSO, curso);
	}

	public List findByCorreopadres(Object correopadres) {
		return findByProperty(CORREOPADRES, correopadres);
	}

	public List findByComedor(Object comedor) {
		return findByProperty(COMEDOR, comedor);
	}

	public List findByHasalergias(Object hasalergias) {
		return findByProperty(HASALERGIAS, hasalergias);
	}

	public List findByListaalergias(Object listaalergias) {
		return findByProperty(LISTAALERGIAS, listaalergias);
	}

	public List findAll() {
		log.debug("finding all Alumnos instances");
		try {
			String queryString = "from Alumnos";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Alumnos merge(Alumnos detachedInstance) {
		log.debug("merging Alumnos instance");
		try {
			Alumnos result = (Alumnos) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Alumnos instance) {
		log.debug("attaching dirty Alumnos instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Alumnos instance) {
		log.debug("attaching clean Alumnos instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}