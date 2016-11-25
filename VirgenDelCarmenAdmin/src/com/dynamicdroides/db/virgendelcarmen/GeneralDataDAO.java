package com.dynamicdroides.db.virgendelcarmen;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A data access object (DAO) providing persistence and search support for
 * GeneralData entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.dynamicdroides.db.virgendelcarmen.GeneralData
 * @author MyEclipse Persistence Tools
 */
public class GeneralDataDAO extends BaseHibernateDAO {
	private static final Logger log = LoggerFactory
			.getLogger(GeneralDataDAO.class);
	// property constants
	public static final String DESCRIPTION = "description";
	public static final String NAME = "name";
	public static final String VALUE = "value";

	public void save(GeneralData transientInstance) {
		log.debug("saving GeneralData instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(GeneralData persistentInstance) {
		log.debug("deleting GeneralData instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public GeneralData findById(java.lang.Integer id) {
		log.debug("getting GeneralData instance with id: " + id);
		try {
			GeneralData instance = (GeneralData) getSession().get(
					"com.dynamicdroides.db.virgendelcarmen.GeneralData", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(GeneralData instance) {
		log.debug("finding GeneralData instance by example");
		try {
			List results = getSession()
					.createCriteria(
							"com.dynamicdroides.db.virgendelcarmen.GeneralData")
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
		log.debug("finding GeneralData instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from GeneralData as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByDescription(Object description) {
		return findByProperty(DESCRIPTION, description);
	}

	public List findByName(Object name) {
		return findByProperty(NAME, name);
	}

	public List findByValue(Object value) {
		return findByProperty(VALUE, value);
	}

	public List findAll() {
		log.debug("finding all GeneralData instances");
		try {
			String queryString = "from GeneralData";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public GeneralData merge(GeneralData detachedInstance) {
		log.debug("merging GeneralData instance");
		try {
			GeneralData result = (GeneralData) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(GeneralData instance) {
		log.debug("attaching dirty GeneralData instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(GeneralData instance) {
		log.debug("attaching clean GeneralData instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}