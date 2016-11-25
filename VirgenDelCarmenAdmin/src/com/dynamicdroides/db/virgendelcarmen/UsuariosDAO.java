package com.dynamicdroides.db.virgendelcarmen;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * Usuarios entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.dynamicdroides.db.virgendelcarmen.Usuarios
 * @author MyEclipse Persistence Tools
 */
public class UsuariosDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(UsuariosDAO.class);
	// property constants
	public static final String NOMBRE = "nombre";
	public static final String APELLIDOS = "apellidos";
	public static final String USER = "user";
	public static final String PASSWORD = "password";
	public static final String EMAIL = "email";
	public static final String ISADMIN = "isadmin";

	public void save(Usuarios transientInstance) {
		log.debug("saving Usuarios instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Usuarios persistentInstance) {
		log.debug("deleting Usuarios instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Usuarios findById(java.lang.Integer id) {
		log.debug("getting Usuarios instance with id: " + id);
		try {
			Usuarios instance = (Usuarios) getSession().get(
					"com.dynamicdroides.db.virgendelcarmen.Usuarios", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Usuarios instance) {
		log.debug("finding Usuarios instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"com.dynamicdroides.db.virgendelcarmen.Usuarios")
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
		log.debug("finding Usuarios instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Usuarios as model where model."
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

	public List findByUser(Object user) {
		return findByProperty(USER, user);
	}

	public List findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}

	public List findByEmail(Object email) {
		return findByProperty(EMAIL, email);
	}

	public List findByIsadmin(Object isadmin) {
		return findByProperty(ISADMIN, isadmin);
	}

	public List findAll() {
		log.debug("finding all Usuarios instances");
		try {
			String queryString = "from Usuarios";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Usuarios merge(Usuarios detachedInstance) {
		log.debug("merging Usuarios instance");
		try {
			Usuarios result = (Usuarios) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Usuarios instance) {
		log.debug("attaching dirty Usuarios instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Usuarios instance) {
		log.debug("attaching clean Usuarios instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}