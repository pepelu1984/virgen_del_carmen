package com.dynamicdroides.db.virgendelcarmen;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * Usercursos entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.dynamicdroides.db.virgendelcarmen.Usercursos
 * @author MyEclipse Persistence Tools
 */
public class UsercursosDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(UsercursosDAO.class);
	// property constants
	public static final String IDUSER = "iduser";
	public static final String IDCURSO = "idcurso";

	public void save(Usercursos transientInstance) {
		log.debug("saving Usercursos instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Usercursos persistentInstance) {
		log.debug("deleting Usercursos instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Usercursos findById(java.lang.Integer id) {
		log.debug("getting Usercursos instance with id: " + id);
		try {
			Usercursos instance = (Usercursos) getSession().get(
					"com.dynamicdroides.db.virgendelcarmen.Usercursos", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Usercursos instance) {
		log.debug("finding Usercursos instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"com.dynamicdroides.db.virgendelcarmen.Usercursos")
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
		log.debug("finding Usercursos instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Usercursos as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIduser(Object iduser) {
		return findByProperty(IDUSER, iduser);
	}

	public List findByIdcurso(Object idcurso) {
		return findByProperty(IDCURSO, idcurso);
	}

	public List findAll() {
		log.debug("finding all Usercursos instances");
		try {
			String queryString = "from Usercursos";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Usercursos merge(Usercursos detachedInstance) {
		log.debug("merging Usercursos instance");
		try {
			Usercursos result = (Usercursos) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Usercursos instance) {
		log.debug("attaching dirty Usercursos instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Usercursos instance) {
		log.debug("attaching clean Usercursos instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}