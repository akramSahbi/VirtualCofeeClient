
package com.thinkit.service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "VirtualCoffeeService", targetNamespace = "http://service.thinkit.com/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
@XmlSeeAlso({
    ObjectFactory.class
})
public interface VirtualCoffeeService {


    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns com.thinkit.service.CofeeMeetingScheduler
     * @throws MalformedURLException_Exception
     */
    @WebMethod
    @WebResult(partName = "return")
    @Action(input = "http://service.thinkit.com/VirtualCoffeeService/getGuestsAvailabilityForCoffeeRequest", output = "http://service.thinkit.com/VirtualCoffeeService/getGuestsAvailabilityForCoffeeResponse", fault = {
        @FaultAction(className = MalformedURLException_Exception.class, value = "http://service.thinkit.com/VirtualCoffeeService/getGuestsAvailabilityForCoffee/Fault/MalformedURLException")
    })
    public CofeeMeetingScheduler getGuestsAvailabilityForCoffee(
        @WebParam(name = "arg0", partName = "arg0")
        String arg0,
        @WebParam(name = "arg1", partName = "arg1")
        String arg1,
        @WebParam(name = "arg2", partName = "arg2")
        String arg2,
        @WebParam(name = "arg3", partName = "arg3")
        String arg3)
        throws MalformedURLException_Exception
    ;

}
