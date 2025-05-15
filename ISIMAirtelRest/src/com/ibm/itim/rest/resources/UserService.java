package com.ibm.itim.rest.resources;

import java.io.IOException;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import com.ibm.itim.util.PersonOperations;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@Path("/user")
public class UserService {
	
   //UserDao userDao = new UserDao();
   private static final String SUCCESS_RESULT="<result>success</result>";
   private static final String FAILURE_RESULT="<result>failure</result>\n<status>1</status>";
   String status;
   String[] sessiondata;
   String sessiondata1;
   String clientsession;
   String sessionid;
   @POST
   @Path("/disable")
   @Produces(MediaType.APPLICATION_XML)
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   public String createUser(@FormParam("uid") String uid,
      //@FormParam("sname") String sname,
      //@FormParam("fname") String fname,
      //@FormParam("mobileno") String mobileno,
      //@FormParam("email") String email,
      @Context HttpServletResponse servletResponse) throws IOException{
      PersonOperations po=new PersonOperations();
     
	try {
		status = po.SuspendPerson(uid);
		System.out.println("final status : "+status);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
	  
      if(status!=null){
         return "<requestID>"+status+"</requestID>\n<status>0</status>";
      }else {
      return FAILURE_RESULT;
   }
   
}
   
   @POST
   @Path("/delete")
   @Produces(MediaType.APPLICATION_XML)
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   public String deleteUser(@FormParam("uid") String uid,
      
      @Context HttpServletResponse servletResponse) throws IOException{
      PersonOperations po=new PersonOperations();
     
	try {
		status = po.deletePerson(uid);
		System.out.println("final deletion status : "+status);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
	  
      if(status!=null){
         return "<requestID>"+status+"</requestID>\n<status>0</status>";
      }else {
      return FAILURE_RESULT;
   }
   
     
}
   
   @POST
   @Path("/restore")
   @Produces(MediaType.APPLICATION_XML)
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   public String restoreUser(@FormParam("uid") String uid, @FormParam("password") String password,
      
      @Context HttpServletResponse servletResponse) throws IOException{
      PersonOperations po=new PersonOperations();
     
	try {
		status = po.restorePerson(uid, password);
		System.out.println("final restoration status : "+status);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
	  
      if(status!=null){
         return "<requestID>"+status+"</requestID>\n<status>0</status>";
      }else {
      return FAILURE_RESULT;
   }
   
}

   @POST
   @Path("/disablenew")
   @Produces(MediaType.APPLICATION_XML)
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   public String disablenew(@FormParam("uid") String uid, @FormParam("clientsession") String clientsession,@FormParam("sessionid") String sessionid,
      
      @Context HttpServletResponse servletResponse) throws IOException{
      PersonOperations po=new PersonOperations();
     
	try {
		status = po.SuspendPersonNew(uid, clientsession, sessionid);
		System.out.println("final disable status : "+status);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
	  
      if(status!=null){
         return "<requestID>"+status+"</requestID>\n<status>0</status>";
      }else {
      return FAILURE_RESULT;
   }
   
     
}
   
   
   
   @POST
   @Path("/login")
   @Produces(MediaType.APPLICATION_XML)
   @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
   public String LoginUser(@FormParam("uid") String uid, @FormParam("password") String password,
      
      @Context HttpServletResponse servletResponse) throws IOException{
      PersonOperations po=new PersonOperations();
     
	try {
		 sessiondata1 = po.Login(uid, password);
		 clientsession=sessiondata1.substring(0,sessiondata1.indexOf("sessionID"));
		 
	    sessionid=sessiondata1.substring(sessiondata1.indexOf("sessionID")+9);
		System.out.println("login client ID : "+clientsession);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   
	  
      if(sessiondata1!=null){
         return "<clientSession>"+clientsession+"</clientSession>\n<SessionID>"+sessionid+"</SessionID>\n<status>0</status>";
      }else {
      return FAILURE_RESULT;
   }
   
}
}

