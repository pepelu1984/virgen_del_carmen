package com.dynamicdroides.db.virgendelcarmen;



/**
 * Usuariosalumnos entity. @author MyEclipse Persistence Tools
 */
public class Usuariosalumnos extends AbstractUsuariosalumnos implements java.io.Serializable {

    // Constructors

    /** default constructor */
    public Usuariosalumnos() {
    }

    
    /** full constructor */
    public Usuariosalumnos(Integer idalumno, Integer idusuario, Integer idcurso) {
        super(idalumno, idusuario, idcurso);        
    }
   
}
