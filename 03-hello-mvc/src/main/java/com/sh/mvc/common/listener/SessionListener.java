package com.sh.mvc.common.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class SessionListener
 */
@WebListener
public class SessionListener implements HttpSessionListener {

	private int sessionCounter;
	
    /**
     * Default constructor. 
     */
    public SessionListener() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se)  { 

    	sessionCounter++;
    	System.out.println("[sessionCreated - 현재접속자 수(sessionCounter) : " + sessionCounter + " ]");
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
   
    	if(sessionCounter>0 )sessionCounter--;
    	System.out.println("[sessionDestroyed - 현재접속자 수(sessionCounter) : " + sessionCounter + " ]");
    }
	
}
