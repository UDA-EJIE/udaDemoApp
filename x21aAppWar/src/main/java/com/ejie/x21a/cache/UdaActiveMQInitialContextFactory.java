package com.ejie.x21a.cache;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.spi.InitialContextFactory;

public class UdaActiveMQInitialContextFactory  implements InitialContextFactory {

	/**
	 * Creates an initial context with {@inheritDoc}
	 */
	@Override
	public Context getInitialContext(Hashtable environment)
			throws NamingException {

//		Hashtable env = new Hashtable(5);
//		   env.put(Context.INITIAL_CONTEXT_FACTORY,
//		           "weblogic.jndi.WLInitialContextFactory");
//		   env.put(Context.PROVIDER_URL,
//		           "t3://localhost:7001");
//		   Context ctx = new InitialContext(env);
//		   
//		   return ctx;
		
		
		Context ctx = new InitialContext();
		return ctx;
	}
}