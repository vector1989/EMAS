package com.evmtv.epg.mail;

import javax.mail.*;  

/**
 * @author Lei<helei@evmtv.com>
 * @data 2013-7-11 下午4:51:07
 */

public class MyAuthenticator extends Authenticator{  
    String userName=null;  
    String password=null;  
       
    public MyAuthenticator(){  
    }  
    public MyAuthenticator(String username, String password) {   
        this.userName = username;   
        this.password = password;   
    }   
    protected PasswordAuthentication getPasswordAuthentication(){  
        return new PasswordAuthentication(userName, password);  
    }  
} 
