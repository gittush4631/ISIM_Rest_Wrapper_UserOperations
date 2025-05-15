package com.ibm.itim.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import com.ibm.itim.ws.services.WSSessionServiceProxy;
import com.ibm.itim.ws.services.WSSessionService_Service;
import com.ibm.itim.ws.services.WSSystemUserServiceProxy;

import com.ibm.itim.ws.exceptions.WSInvalidLoginException;
import com.ibm.itim.ws.exceptions.WSLoginServiceException;
import com.ibm.itim.ws.model.WSAttribute;
import com.ibm.itim.ws.model.WSPerson;
import com.ibm.itim.ws.model.WSRequest;
import com.ibm.itim.ws.model.WSSession;
import com.ibm.itim.ws.model.WSSystemUser;
import com.ibm.itim.ws.services.WSPersonServiceProxy;
import com.ibm.itim.ws.services.WSSessionService;

public class PersonOperations {
	private String ISIM_USERNAME,ISIM_PASSWORD;
	String[] sessiondata;
	 
public static void main(String[] args) {
	
	//PersonOperations po=new PersonOperations();
	//po.SuspendPerson("tpatil");
	
			
}

public String SuspendPerson(String uid) throws IOException {
	
    
	WSSessionServiceProxy pr=new WSSessionServiceProxy();
	WSPersonServiceProxy per=new WSPersonServiceProxy();
	WSSystemUserServiceProxy sysuser=new WSSystemUserServiceProxy();
	WSSession session;
	String[] attvalues;
	Properties env=ReadProperties.getProperties();
	
     ISIM_USERNAME = env.getProperty("isimuser");
     ISIM_PASSWORD= env.getProperty("isimpassword");
     System.out.println("isim user "+ISIM_USERNAME);
	
	String filter="(eruid="+uid+")";
	System.out.println("Filter = "+filter);
	try {
		session=pr.login(ISIM_USERNAME, ISIM_PASSWORD);
		System.out.println("login successful");
		float ver=pr.getItimVersion();
		System.out.println("version "+ver);
		System.out.println("session details"+session.getClientSession()+session.getSessionID());
		WSSystemUser[] wsperson=sysuser.searchSystemUsers(session, filter);
		System.out.println("person attributes"+wsperson.toString());
		WSSystemUser person1 = wsperson[0];
		String personDN="";
		WSAttribute[] attributes=person1.getAttributes();
		for(WSAttribute s:attributes) {
			if(s.getName().contains("owner")) {
			attvalues=s.getValues();
			 personDN=attvalues[0];
			System.out.println("final person dn"+personDN);
			}
			
		}
		
		System.out.println("person details " +person1.getName());
		WSRequest wsRequest=per.suspendPerson(session, personDN, "Suspend it");
		System.out.println("Suspended. Request ID "+wsRequest.getRequestId());
		return String.valueOf(wsRequest.getRequestId());
		
	} catch (WSInvalidLoginException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	} catch (WSLoginServiceException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
	
}


public String Login(String uid,String password) throws IOException {
	
    
	WSSessionServiceProxy pr=new WSSessionServiceProxy();
	WSPersonServiceProxy per=new WSPersonServiceProxy();
	WSSystemUserServiceProxy sysuser=new WSSystemUserServiceProxy();
	WSSession session;
	String[] attvalues;
	Properties env=ReadProperties.getProperties();
	
     ISIM_USERNAME = env.getProperty("isimuser");
     ISIM_PASSWORD= env.getProperty("isimpassword");
     ISIM_USERNAME=uid;
     ISIM_PASSWORD=password;
     System.out.println("isim user "+ISIM_USERNAME);
	

	
	try {
		session=pr.login(ISIM_USERNAME, ISIM_PASSWORD);
		System.out.println("login successful");
		float ver=pr.getItimVersion();
		System.out.println("version "+ver);
		System.out.println("session client details"+session.getClientSession());
		System.out.println("session id "+session.getSessionID());
		long sessionid=session.getSessionID();
		String sessionidstr=String.valueOf(sessionid);
		String clientsession=session.getClientSession();
		
		//sessiondata[0]=session.getClientSession();;
		//sessiondata[1]=sessionidstr;
		
				
		return clientsession+"sessionID"+sessionidstr;
			
		}
		

	 catch (WSInvalidLoginException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	} catch (WSLoginServiceException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
	
}

public String deletePerson(String uid) throws IOException {
	WSSessionServiceProxy pr=new WSSessionServiceProxy();
	WSPersonServiceProxy per=new WSPersonServiceProxy();
	WSSystemUserServiceProxy sysuser=new WSSystemUserServiceProxy();
	WSSession session;
	String[] attvalues;
	Properties env=ReadProperties.getProperties();
	
    ISIM_USERNAME = env.getProperty("isimuser");
    ISIM_PASSWORD= env.getProperty("isimpassword");
    System.out.println("isim user "+ISIM_USERNAME);
	String filter="(eruid="+uid+")";
	System.out.println("Filter = "+filter);
	try {
		session=pr.login(ISIM_USERNAME, ISIM_PASSWORD);
		System.out.println("login successful");
		float ver=pr.getItimVersion();
		System.out.println("version "+ver);
		
		
		WSSystemUser[] wsperson=sysuser.searchSystemUsers(session, filter);
		//WSPerson[] lstWSPersons = per.searchPersonsFromRoot(session, filter,null );
		System.out.println("person attributes"+wsperson.toString());
		WSSystemUser person1 = wsperson[0];
		String personDN="";
		//String personDN = person1.getItimDN();
		WSAttribute[] attributes=person1.getAttributes();
		for(WSAttribute s:attributes) {
			if(s.getName().contains("owner")) {
			attvalues=s.getValues();
			 personDN=attvalues[0];
			System.out.println("final person dn"+personDN);
			}
		}
		
		System.out.println("person itim dn"+personDN);
		System.out.println("person details " +person1.getName());
		Calendar date = Calendar.getInstance();
		WSRequest wsRequest=per.deletePerson(session, personDN, date, "Deletion from REST");
		System.out.println("Deleted. Request ID "+wsRequest.getRequestId());
		return String.valueOf(wsRequest.getRequestId());
		
	} catch (WSInvalidLoginException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	} catch (WSLoginServiceException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
	
}

public String restorePerson(String uid,String password) throws IOException {
	WSSessionServiceProxy pr=new WSSessionServiceProxy();
	WSPersonServiceProxy per=new WSPersonServiceProxy();
	WSSystemUserServiceProxy sysuser=new WSSystemUserServiceProxy();
	WSSession session;
	String[] attvalues;
	Properties env=ReadProperties.getProperties();
	
    ISIM_USERNAME = env.getProperty("isimuser");
    ISIM_PASSWORD= env.getProperty("isimpassword");
  // String SECURITY_AUTHENTICATION=env.getProperty("SECURITY_AUTHENTICATION");
    System.out.println("isim user "+ISIM_USERNAME);
	String filter="(eruid="+uid+")";
	System.out.println("Filter = "+filter);
	try {
		session=pr.login(ISIM_USERNAME, ISIM_PASSWORD);
		System.out.println("login successful");
		float ver=pr.getItimVersion();
		System.out.println("version "+ver);
		WSSystemUser[] wsperson=sysuser.searchSystemUsers(session, filter);
		System.out.println("person attributes"+wsperson.toString());
		WSSystemUser person1 = wsperson[0];
		String personDN="";
		WSAttribute[] attributes=person1.getAttributes();
		for(WSAttribute s:attributes) {
			if(s.getName().contains("owner")) {
			attvalues=s.getValues();
			 personDN=attvalues[0];
			System.out.println("final person dn"+personDN);
			}
		}
			
		Calendar date = Calendar.getInstance();
		System.out.println("person itim dn"+personDN);
		System.out.println("person details " +person1.getName());
		WSRequest wsRequest=per.restorePerson(session, personDN, true, password, date, "Restore from REST");
		
		System.out.println("Restored. Request ID "+wsRequest.getRequestId());
		return String.valueOf(wsRequest.getRequestId());
		
	} catch (WSInvalidLoginException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	} catch (WSLoginServiceException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}

}
public static XMLGregorianCalendar long2Gregorian(long date) {
	DatatypeFactory dataTypeFactory;
	try {
		dataTypeFactory = DatatypeFactory.newInstance();
	} catch (DatatypeConfigurationException e) {
		throw new RuntimeException(e);
	}
	GregorianCalendar gc = new GregorianCalendar();
	System.out.println("Date ="+date);
	gc.setTimeInMillis(date);

	return dataTypeFactory.newXMLGregorianCalendar(gc);
}


public String SuspendPersonNew(String uid,String clientsession,String sessionid) throws IOException {
	
    
	WSSessionServiceProxy pr=new WSSessionServiceProxy();
	WSPersonServiceProxy per=new WSPersonServiceProxy();
	WSSystemUserServiceProxy sysuser=new WSSystemUserServiceProxy();
	WSSession session = new WSSession();
	String[] attvalues;
	Properties env=ReadProperties.getProperties();
	
     ISIM_USERNAME = env.getProperty("isimuser");
     ISIM_PASSWORD= env.getProperty("isimpassword");
     //System.out.println("isim user "+uid);
	
	String filter="(eruid="+uid+")";
	System.out.println("Filter = "+filter);
	try {
		//session=pr.login(ISIM_USERNAME, ISIM_PASSWORD);
		session.setClientSession(clientsession);
		Long sessionIDL=Long.parseLong(sessionid); 
		session.setSessionID(sessionIDL);
		//System.out.println("login successful");
		float ver=pr.getItimVersion();
		System.out.println("version "+ver);
		System.out.println("session details"+session.getClientSession()+session.getSessionID());
		WSSystemUser[] wsperson=sysuser.searchSystemUsers(session, filter);
		System.out.println("person attributes"+wsperson.toString());
		WSSystemUser person1 = wsperson[0];
		String personDN="";
		WSAttribute[] attributes=person1.getAttributes();
		for(WSAttribute s:attributes) {
			if(s.getName().contains("owner")) {
			attvalues=s.getValues();
			 personDN=attvalues[0];
			System.out.println("final person dn"+personDN);
			}
			
		}
		
		System.out.println("person details " +person1.getName());
		WSRequest wsRequest=per.suspendPerson(session, personDN, "Suspend it");
		System.out.println("Suspended. Request ID "+wsRequest.getRequestId());
		return String.valueOf(wsRequest.getRequestId());
		
	} catch (WSInvalidLoginException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	} catch (WSLoginServiceException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	} catch (RemoteException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return null;
	}
	
}


}
