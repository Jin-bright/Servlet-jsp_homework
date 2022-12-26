package com.sh.mvc.common.listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class SessionListener
 * 저 밑에 어노테이션 의미가 web.xml에서 listener 이런거 안써도 된다는 거임 
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
         // 세션이 만들어질때 한번  === 이게 이벤트 핸들러임 
    	sessionCounter++;
    	System.out.println("[sessionCreated - 현재접속자 수(sessionCounter) : " + sessionCounter + " ]");
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se)  { 
         // 세션이 없어질때마다 
    	if(sessionCounter>0 )sessionCounter--;
    	System.out.println("[sessionDestroyed - 현재접속자 수(sessionCounter) : " + sessionCounter + " ]");
    }
	
}
