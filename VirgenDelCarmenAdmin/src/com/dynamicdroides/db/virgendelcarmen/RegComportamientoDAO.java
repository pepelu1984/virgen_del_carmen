package com.dynamicdroides.db.virgendelcarmen;

import java.util.Date;
import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * RegComportamiento entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.dynamicdroides.db.virgendelcarmen.RegComportamiento
 * @author MyEclipse Persistence Tools
 */
public class RegComportamientoDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(RegComportamientoDAO.class);
	// property constants
	public static final String IDALUMNO = "idalumno";
	public static final String TIPO = "tipo";
	public static final String VALOR = "valor";
	public static final String OBSERVACIONES = "observaciones";

	public void save(RegComportamiento transientInstance) {
		log.debug("saving RegComportamiento instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(RegComportamiento persistentInstance) {
		log.debug("deleting RegComportamiento instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public RegComportamiento findById(java.lang.Integer id) {
		log.debug("getting RegComportamiento instance with id: " + id);
		try {
			RegComportamiento instance = (RegComportamiento) getSession().get(
					"com.dynamicdroides.db.virgendelcarmen.RegComportamiento",
					id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(RegComportamiento instance) {
		log.debug("finding RegComportamiento instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"com.dynamicdroides.db.virgendelcarmen.RegComportamiento")
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
		log.debug("finding RegComportamiento instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from RegComportamiento as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdalumno(Object idalumno) {
		return findByProperty(IDALUMNO, idalumno);
	}

	public List findByTipo(Object tipo) {
		return findByProperty(TIPO, tipo);
	}

	public List findByValor(Object valor) {
		return findByProperty(VALOR, valor);
	}

	public List findByObservaciones(Object observaciones) {
		return findByProperty(OBSERVACIONES, observaciones);
	}

	public List findAll() {
		log.debug("finding all RegComportamiento instances");
		try {
			String queryString = "from RegComportamiento";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public RegComportamiento merge(RegComportamiento detachedInstance) {
		log.debug("merging RegComportamiento instance");
		try {
			RegComportamiento result = (RegComportamiento) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(RegComportamiento instance) {
		log.debug("attaching dirty RegComportamiento instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(RegComportamiento instance) {
		log.debug("attaching clean RegComportamiento instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}