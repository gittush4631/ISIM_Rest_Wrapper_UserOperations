package com.ibm.itim.rest.resources;
import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;


public class SDSCreateUser{

    public static void main(String[] args) throws NamingException, IOException {
       
       /* System.out.println("Checking if user is authenticated");
      
        SDSCreateUser cruser=new SDSCreateUser();
        
        String status=cruser.createuser("user3","Test","Stest","etest@test.com","954534334");
        System.out.println("Final Status"+status);*/
        
       

    }



    public String createuser(String UID,String CN,String SN,String EMAIL,String MOBILENO) throws NamingException, IOException {
    	
    		System.out.println("Inside create");
    	
    	String finalDN="";
    	String prefinalDN="";
    	String status="";
    	//get property file
    	 String filename="LdapCon.properties";
         InputStream input = null;        
         input=SDSCreateUser.class.getClassLoader().getResourceAsStream(filename);
         if(input==null){
             System.out.println("Sorry, unable to find " + filename);

         }
         Properties env = new Properties();
         env.load(input);
         System.out.println("with key : "+env.getProperty("PROVIDER_URL"));
         String INITIAL_CONTEXT_FACTORY = env.getProperty("INITIAL_CONTEXT_FACTORY");
         String PROVIDER_URL= env.getProperty("PROVIDER_URL");
         String SECURITY_AUTHENTICATION=env.getProperty("SECURITY_AUTHENTICATION");
         String SECURITY_PRINCIPAL=env.getProperty("SECURITY_PRINCIPAL");
         String SECURITY_CREDENTIALS=env.getProperty("SECURITY_CREDENTIALS");
         System.out.println("Context factory = "+INITIAL_CONTEXT_FACTORY);
         env.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
         env.put(Context.PROVIDER_URL, PROVIDER_URL);
         env.put(Context.SECURITY_AUTHENTICATION, SECURITY_AUTHENTICATION);
         env.put(Context.SECURITY_PRINCIPAL, SECURITY_PRINCIPAL);
         env.put(Context.SECURITY_CREDENTIALS, SECURITY_CREDENTIALS);


         DirContext ctx = new InitialDirContext(env);
         System.out.println("connected inline");
         
         
         
         
         
         //Properties env = new Properties();
         env.load(input);
         String BASE_DN = env.getProperty("BASE_DN");
         String USER_SEARCH_ATT = env.getProperty("USER_SEARCH_ATT");
         String entryDN = "uid="+UID+",dc=com";  

         // entry's attributes  

         Attribute cn = new BasicAttribute("cn", CN);  
         Attribute sn = new BasicAttribute("sn",SN);  
         Attribute mail = new BasicAttribute("mail", EMAIL);  
         Attribute phone = new BasicAttribute("telephoneNumber",MOBILENO);   
             Attribute oc = new BasicAttribute("objectClass");  
         oc.add("top");  
         oc.add("person");  
         oc.add("organizationalPerson");  
         oc.add("inetOrgPerson");  
         //DirContext ctx = null;  

         try {  
             // get a handle to an Initial DirContext  
             ctx = new InitialDirContext(env);  

             // build the entry  
             BasicAttributes entry = new BasicAttributes();  
             entry.put(cn);  
             entry.put(sn);  
             entry.put(mail);  
             entry.put(phone);  

             entry.put(oc);  
             System.out.println( "AddUser calling");  
             // Add the entry  

             ctx.createSubcontext(entryDN, entry);  
             System.out.println( "AddUser: added entry " + entryDN + "."); 
             status="SUCCESS";

         } catch (NamingException e) {  
             System.err.println("AddUser: error adding entry." + e); 
             status="FAIL";
         }  
       
        return status;
        
    	
    }  
    
   
}