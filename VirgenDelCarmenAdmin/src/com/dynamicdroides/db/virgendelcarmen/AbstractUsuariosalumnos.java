package com.dynamicdroides.db.virgendelcarmen;



/**
 * AbstractUsuariosalumnos entity provides the base persistence definition of the Usuariosalumnos entity. @author MyEclipse Persistence Tools
 */

public abstract class AbstractUsuariosalumnos  implements java.io.Serializable {


    // Fields    

     private Integer idrow;
     private Integer idalumno;
     private Integer idusuario;
     private Integer idcurso;


    // Constructors

    /** default constructor */
    public AbstractUsuariosalumnos() {
    }

    
    /** full constructor */
    public AbstractUsuariosalumnos(Integer idalumno, Integer idusuario, Integer idcurso) {
        this.idalumno = idalumno;
        this.idusuario = idusuario;
        this.idcurso = idcurso;
    }

   
    // Property accessors

    public Integer getIdrow() {
        return this.idrow;
    }
    
    public void setIdrow(Integer idrow) {
        this.idrow = idrow;
    }

    public Integer getIdalumno() {
        return this.idalumno;
    }
    
    public void setIdalumno(Integer idalumno) {
        this.idalumno = idalumno;
    }

    public Integer getIdusuario() {
        return this.idusuario;
    }
    
    public void setIdusuario(Integer idusuario) {
        this.idusuario = idusuario;
    }

    public Integer getIdcurso() {
        return this.idcurso;
    }
    
    public void setIdcurso(Integer idcurso) {
        this.idcurso = idcurso;
    }
   








}