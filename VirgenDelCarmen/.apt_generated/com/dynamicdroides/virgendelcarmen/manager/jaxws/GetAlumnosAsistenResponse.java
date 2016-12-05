
package com.dynamicdroides.virgendelcarmen.manager.jaxws;

import java.util.ArrayList;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "getAlumnosAsistenResponse", namespace = "http://manager.virgendelcarmen.dynamicdroides.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getAlumnosAsistenResponse", namespace = "http://manager.virgendelcarmen.dynamicdroides.com/")
public class GetAlumnosAsistenResponse {

    @XmlElement(name = "return", namespace = "")
    private ArrayList _return;

    /**
     * 
     * @return
     *     returns ArrayList
     */
    public ArrayList getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(ArrayList _return) {
        this._return = _return;
    }

}
