
package com.dynamicdroides.virgendelcarmen.manager.jaxws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "checkUserAccessResponse", namespace = "http://manager.virgendelcarmen.dynamicdroides.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "checkUserAccessResponse", namespace = "http://manager.virgendelcarmen.dynamicdroides.com/")
public class CheckUserAccessResponse {

    @XmlElement(name = "return", namespace = "")
    private com.dynamicdroides.db.virgendelcarmen.Usuarios _return;

    /**
     * 
     * @return
     *     returns Usuarios
     */
    public com.dynamicdroides.db.virgendelcarmen.Usuarios getReturn() {
        return this._return;
    }

    /**
     * 
     * @param _return
     *     the value for the _return property
     */
    public void setReturn(com.dynamicdroides.db.virgendelcarmen.Usuarios _return) {
        this._return = _return;
    }

}
