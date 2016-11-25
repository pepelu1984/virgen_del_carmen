package com.dynamicdroides.db.virgendelcarmen;

import java.util.List;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 	* A data access object (DAO) providing persistence and search support for Usuariosalumnos entities.
 			* Transaction control of the save(), update() and delete() operations 
		can directly support Spring container-managed transactions or they can be augmented	to handle user-managed Spring transactions. 
		Each of these methods provides additional information for how to configure it for the desired type of transaction control. 	
	 * @see com.dynamicdroides.db.virgendelcarmen.Usuariosalumnos
  * @author MyEclipse Persistence Tools 
 */
public class UsuariosalumnosDAO extends BaseHibernateDAO  {
	     private static final Logger log = LoggerFactory.getLogger(UsuariosalumnosDAO.class);
		//property constants
	public static final String IDALUMNO = "idalumno";
	public static final String IDUSUARIO = "idusuario";
	public static final String IDCURSO = "idcurso";



    
    public void save(Usuariosalumnos transientInstance) {
        log.debug("saving Usuariosalumnos instance");
        try {
            getSession().save(transientInstance);
            log.debug("save successful");
        } catch (RuntimeException re) {
            log.error("save failed", re);
            throw re;
        }
    }
    
	public void delete(Usuariosalumnos persistentInstance) {
        log.debug("deleting Usuariosalumnos instance");
        try {
            getSession().delete(persistentInstance);
            log.debug("delete successful");
        } catch (RuntimeException re) {
            log.error("delete failed", re);
            throw re;
        }
    }
    
    public Usuariosalumnos findById( java.lang.Integer id) {
        log.debug("getting Usuariosalumnos instance with id: " + id);
        try {
            Usuariosalumnos instance = (Usuariosalumnos) getSession()
                    .get("com.dynamicdroides.db.virgendelcarmen.Usuariosalumnos", id);
            return instance;
        } catch (RuntimeException re) {
            log.error("get failed", re);
            throw re;
        }
    }
    
    
    public List findByExample(Usuariosalumnos instance) {
        log.debug("finding Usuariosalumnos instance by example");
        try {
            List results = getSession()
                    .createCriteria("com.dynamicdroides.db.virgendelcarmen.Usuariosalumnos")
                    .add(Example.create(instance))
            .list();
            log.debug("find by example successful, result size: " + results.size());
            return results;
        } catch (RuntimeException re) {
            log.error("find by example failed", re);
            throw re;
        }
    }    
    
    public List findByProperty(String propertyName, Object value) {
      log.debug("finding Usuariosalumnos instance with property: " + propertyName
            + ", value: " + value);
      try {
         String queryString = "from Usuariosalumnos as model where model." 
         						+ propertyName + "= ?";
         Query queryObject = getSession().createQuery(queryString);
		 queryObject.setParameter(0, value);
		 return queryObject.list();
      } catch (RuntimeException re) {
         log.error("find by property name failed", re);
         throw re;
      }
	}

	public List findByIdalumno(Object idalumno
	) {
		return findByProperty(IDALUMNO, idalumno
		);
	}
	
	public List findByIdusuario(Object idusuario
	) {
		return findByProperty(IDUSUARIO, idusuario
		);
	}
	
	public List findByIdcurso(Object idcurso
	) {
		return findByProperty(IDCURSO, idcurso
		);
	}
	

	public List findAll() {
		log.debug("finding all Usuariosalumnos instances");
		try {
			String queryString = "from Usuariosalumnos";
	         Query queryObject = getSession().createQuery(queryString);
			 return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}
	
    public Usuariosalumnos merge(Usuariosalumnos detachedInstance) {
        log.debug("merging Usuariosalumnos instance");
        try {
            Usuariosalumnos result = (Usuariosalumnos) getSession()
                    .merge(detachedInstance);
            log.debug("merge successful");
            return result;
        } catch (RuntimeException re) {
            log.error("merge failed", re);
            throw re;
        }
    }

    public void attachDirty(Usuariosalumnos instance) {
        log.debug("attaching dirty Usuariosalumnos instance");
        try {
            getSession().saveOrUpdate(instance);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
    
    public void attachClean(Usuariosalumnos instance) {
        log.debug("attaching clean Usuariosalumnos instance");
        try {
            getSession().lock(instance, LockMode.NONE);
            log.debug("attach successful");
        } catch (RuntimeException re) {
            log.error("attach failed", re);
            throw re;
        }
    }
}