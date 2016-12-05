
package com.dynamicdroides.virgendelcarmen.manager.jaxws;

import java.util.Date;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name = "finishEnvioComportamiento", namespace = "http://manager.virgendelcarmen.dynamicdroides.com/")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "finishEnvioComportamiento", namespace = "http://manager.virgendelcarmen.dynamicdroides.com/", propOrder = {
    "arg0",
    "arg1",
    "arg2"
})
public class FinishEnvioComportamiento {

    @XmlElement(name = "arg0", namespace = "")
    private int arg0;
    @XmlElement(name = "arg1", namespace = "")
    private Date arg1;
    @XmlElement(name = "arg2", namespace = "")
    private Date arg2;

    /**
     * 
     * @return
     *     returns int
     */
    public int getArg0() {
        return this.arg0;
    }

    /**
     * 
     * @param arg0
     *     the value for the arg0 property
     */
    public void setArg0(int arg0) {
        this.arg0 = arg0;
    }

    /**
     * 
     * @return
     *     returns Date
     */
    public Date getArg1() {
        return this.arg1;
    }

    /**
     * 
     * @param arg1
     *     the value for the arg1 property
     */
    public void setArg1(Date arg1) {
        this.arg1 = arg1;
    }

    /**
     * 
     * @return
     *     returns Date
     */
    public Date getArg2() {
        return this.arg2;
    }

    /**
     * 
     * @param arg2
     *     the value for the arg2 property
     */
    public void setArg2(Date arg2) {
        this.arg2 = arg2;
    }

}
