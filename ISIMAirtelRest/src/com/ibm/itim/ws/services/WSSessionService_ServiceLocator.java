/**
 * WSSessionService_ServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package com.ibm.itim.ws.services;

import java.io.IOException;
import java.util.Properties;

import com.ibm.itim.util.ReadProperties;

public class WSSessionService_ServiceLocator extends org.apache.axis.client.Service implements com.ibm.itim.ws.services.WSSessionService_Service {
	static String ISIM_URL;
    public WSSessionService_ServiceLocator()  {
    	try {
    		Properties env=ReadProperties.getProperties();
    		    	
    		         ISIM_URL = env.getProperty("isimurl");
    		        System.out.println("isimurl"+ISIM_URL);
    		    	}
    		    	catch (Exception e) {
    					// TODO: handle exception
    				}
    }


    public WSSessionService_ServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public WSSessionService_ServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for WSSessionServicePort
    private java.lang.String WSSessionServicePort_address = ISIM_URL+"services/WSSessionService";

    public java.lang.String getWSSessionServicePortAddress() {
    	System.out.println("isim port address:"+WSSessionServicePort_address);
        return WSSessionServicePort_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String WSSessionServicePortWSDDServiceName = "WSSessionServicePort";

    public java.lang.String getWSSessionServicePortWSDDServiceName() {
        return WSSessionServicePortWSDDServiceName;
    }

    public void setWSSessionServicePortWSDDServiceName(java.lang.String name) {
        WSSessionServicePortWSDDServiceName = name;
    }

    public com.ibm.itim.ws.services.WSSessionService_PortType getWSSessionServicePort() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(WSSessionServicePort_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getWSSessionServicePort(endpoint);
    }

    public com.ibm.itim.ws.services.WSSessionService_PortType getWSSessionServicePort(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            com.ibm.itim.ws.services.WSSessionServiceSoapBindingStub _stub = new com.ibm.itim.ws.services.WSSessionServiceSoapBindingStub(portAddress, this);
            _stub.setPortName(getWSSessionServicePortWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setWSSessionServicePortEndpointAddress(java.lang.String address) {
        WSSessionServicePort_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (com.ibm.itim.ws.services.WSSessionService_PortType.class.isAssignableFrom(serviceEndpointInterface)) {
                com.ibm.itim.ws.services.WSSessionServiceSoapBindingStub _stub = new com.ibm.itim.ws.services.WSSessionServiceSoapBindingStub(new java.net.URL(WSSessionServicePort_address), this);
                _stub.setPortName(getWSSessionServicePortWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("WSSessionServicePort".equals(inputPortName)) {
            return getWSSessionServicePort();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://services.ws.itim.ibm.com", "WSSessionService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://services.ws.itim.ibm.com", "WSSessionServicePort"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("WSSessionServicePort".equals(portName)) {
            setWSSessionServicePortEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
