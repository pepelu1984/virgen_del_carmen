package com.dynamicdroides.db.virgendelcarmen;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSeeAlso;


import java.io.Serializable;

@XmlRootElement
@XmlSeeAlso(CursoBean.class)
public class CursoBean extends AbstractCursos implements Serializable {
	
}