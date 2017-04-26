package com.ejie.x21a.mdb;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.ejb.MessageDrivenBean;
import javax.ejb.MessageDrivenContext;
import javax.interceptor.Interceptors;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ejb.interceptor.SpringBeanAutowiringInterceptor;


import com.ejie.x38.log.LoggingEditor;
import com.ejie.x38.log.model.LogModel;





	/**
	 * Message-Driven Bean implementation class for: x21MdbBean
	 *
	 *@author UDA
	 */
	@MessageDriven(
			activationConfig = { @ActivationConfigProperty(
					propertyName = "destinationType", propertyValue = "javax.jms.Queue"
			) }, 
			mappedName = "x21a.x21aJMSCacheTopic", 
			messageListenerInterface = MessageListener.class)
	@Interceptors(SpringBeanAutowiringInterceptor.class)
	public class X21aMDBBean implements MessageListener, MessageDrivenBean {
		
		/**
		 * Serial
		 */
		private static final long serialVersionUID = 1L;

		private static final Logger logger = LoggerFactory.getLogger(X21aMDBBean.class);
		
		


	    /**
	     * Default constructor. 
	     */
	    public X21aMDBBean() {
	        // TODO Auto-generated constructor stub
	    }

		/**
	     * @see MessageDrivenBean#ejbRemove()
	     */
	    public void ejbRemove() {
	        // TODO Auto-generated method stub
	    }

		/**
	     * @see MessageDrivenBean#setMessageDrivenContext(MessageDrivenContext)
	     * 
	     * @param arg0 MessageDrivenContext
	     */
	    public void setMessageDrivenContext(MessageDrivenContext arg0) {
	        // TODO Auto-generated method stub
	    }
		
		/**
		 * @param message
		 * 		 Message
		 * 
	     * @see MessageListener#onMessage(Message)
	     */
	    public void onMessage(Message message) {
	    	
	    	if (message instanceof ObjectMessage) {
	    		X21aMDBBean.logger.info("x21MdbBean.onMessage: Inicio");

				try {
					// Parseamos el texto del mensaje a la clase que necesitamos
					//TextMessage txtMessage = (TextMessage) message;
					
					ObjectMessage objMsg = (ObjectMessage) message;
					LogModel logMod= (LogModel) objMsg.getObject();
					X21aMDBBean.logger.info("ObjectMessage beans: "
							+ logMod.getNameLog() + " " + logMod.getLevelLog());
				
					
					X21aMDBBean.logger.error("en este nodo el logger su level es " +LoggingEditor.getLogger(logMod.getNameLog()).getLevelLog());
					if(!LoggingEditor.getLogger(logMod.getNameLog()).getLevelLog().equalsIgnoreCase(logMod.getLevelLog()))
						LoggingEditor.setLogLevel(logMod.getNameLog(), logMod.getLevelLog());

				
					logger.trace("onMessage logLevel trace");
					logger.debug("onMessage logLevel debug");
					logger.info("onMessage logLevel info");
					logger.warn("onMessage logLevel warn");
					logger.error("onMessage logLevel error");	
						
	    	
	    		} catch (JMSException e) {
	    			X21aMDBBean.logger
					.error("\n -- x21MdbBean.onMessage: ERROR: JMSException --> "
							+ e.getMessage());
	    		}catch (Exception e) {
	    			X21aMDBBean.logger
					.error("\n -- x21MdbBean.onMessage: ERROR: Exception--> "
							+ e.getMessage());
				}
	    	}        
	    }
	    
	
		
	
		
	
		

	}
	

