package com.dynamicdroides.db.virgendelcarmen;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * Cursos entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.dynamicdroides.db.virgendelcarmen.Cursos
 * @author MyEclipse Persistence Tools
 */
public class CursosDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory.getLogger(CursosDAO.class);
	// property constants
	public static final String NOMBRE = "nombre";
	public static final String RESPONSABLE = "responsable";
	public static final String ISPRIMARIA = "isprimaria";

	public int save(Cursos transientInstance) {
		log.debug("saving Cursos instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
		return transientInstance.getIdcurso();
	}

	public void delete(Cursos persistentInstance) {
		log.debug("deleting Cursos instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Cursos findById(java.lang.Integer id) {
		log.debug("getting Cursos instance with id: " + id);
		try {
			Cursos instance = (Cursos) getSession().get(
					"com.dynamicdroides.db.virgendelcarmen.Cursos", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Cursos instance) {
		log.debug("finding Cursos instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"com.dynamicdroides.db.virgendelcarmen.Cursos")
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
		log.debug("finding Cursos instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Cursos as model where model."
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

	public List findByResponsable(Object responsable) {
		return findByProperty(RESPONSABLE, responsable);
	}

	public List findByIsprimaria(Object isprimaria) {
		return findByProperty(ISPRIMARIA, isprimaria);
	}

	public List findAll() {
		log.debug("finding all Cursos instances");
		try {
			String queryString = "from Cursos";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Cursos merge(Cursos detachedInstance) {
		log.debug("merging Cursos instance");
		try {
			Cursos result = (Cursos) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Cursos instance) {
		log.debug("attaching dirty Cursos instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Cursos instance) {
		log.debug("attaching clean Cursos instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}