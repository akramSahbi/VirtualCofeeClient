
package com.thinkit.service;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour cofeeMeetingScheduler complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="cofeeMeetingScheduler">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="participantNames" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="slots" type="{http://www.w3.org/2001/XMLSchema}int" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cofeeMeetingScheduler", propOrder = {
    "participantNames",
    "slots"
})
public class CofeeMeetingScheduler {

    @XmlElement(nillable = true)
    protected List<String> participantNames;
    @XmlElement(nillable = true)
    protected List<Integer> slots;

    /**
     * Gets the value of the participantNames property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the participantNames property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getParticipantNames().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getParticipantNames() {
        if (participantNames == null) {
            participantNames = new ArrayList<String>();
        }
        return this.participantNames;
    }

    /**
     * Gets the value of the slots property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the slots property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSlots().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Integer }
     * 
     * 
     */
    public List<Integer> getSlots() {
        if (slots == null) {
            slots = new ArrayList<Integer>();
        }
        return this.slots;
    }

}
