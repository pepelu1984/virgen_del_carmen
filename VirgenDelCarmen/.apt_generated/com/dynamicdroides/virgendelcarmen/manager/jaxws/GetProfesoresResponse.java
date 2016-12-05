
package com.dynamicdroides.virgendelcarmen.manager.jaxws;

import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getProfesoresResponse", namespace = "http://manager.virgendelcarmen.dynamicdroides.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getProfesoresResponse", namespace = "http://manager.virgendelcarmen.dynamicdroides.com/")
public class GetProfesoresResponse {

    @XmlElement(name = "return", namespace = "")
    private List<com.dynamicdroides.db.virgendelcarmen.Profesores> _return;

    /**
     * 
     * @return
     *     returns List<Profesores>
     */
    public List<com.dynamicdroides.db.virgendelcarmen.Profesores> getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(List<com.dynamicdroides.db.virgendelcarmen.Profesores> _return) {
        this._return = _return;
    }

}
