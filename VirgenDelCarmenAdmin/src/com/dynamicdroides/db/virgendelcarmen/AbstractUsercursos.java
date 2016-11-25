package com.dynamicdroides.db.virgendelcarmen;



/**
 * AbstractUsercursos entity provides the base persistence definition of the Usercursos entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractUsercursos  implements java.io.Serializable {


    // Fields    

     private Integer idusercurso;
     private Integer iduser;
     private Integer idcurso;


    // Constructors

    /** default constructor */
    public AbstractUsercursos() {
    }

    
    /** full constructor */
    public AbstractUsercursos(Integer iduser, Integer idcurso) {
        this.iduser = iduser;
        this.idcurso = idcurso;
    }

   
    // Property accessors

    public Integer getIdusercurso() {
        return this.idusercurso;
    }
    
    public void setIdusercurso(Integer idusercurso) {
        this.idusercurso = idusercurso;
    }

    public Integer getIduser() {
        return this.iduser;
    }
    
    public void setIduser(Integer iduser) {
        this.iduser = iduser;
    }

    public Integer getIdcurso() {
        return this.idcurso;
    }
    
    public void setIdcurso(Integer idcurso) {
        this.idcurso = idcurso;
    }
   








}