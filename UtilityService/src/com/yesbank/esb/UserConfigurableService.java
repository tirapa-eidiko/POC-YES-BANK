package com.yesbank.esb;
import java.util.Properties;

import com.ibm.broker.plugin.*;
import com.ibm.broker.config.proxy.*;
import com.ibm.integration.admin.model.IntegrationServerModel;
import com.ibm.integration.admin.model.rm.IntegrationServerResourceManager;
import com.ibm.integration.admin.model.server.IntegrationServers;
import com.ibm.integration.admin.proxy.IntegrationAdminException;
import com.ibm.integration.admin.proxy.IntegrationServerProxy;

public class UserConfigurableService{
	
	private static  BrokerProxy brokerProxyInstance;
	private static final Object lockObject = new Object();
	

	private static void initialize()   {	 
		if(brokerProxyInstance==null) {
	    
		
		try {
			synchronized (lockObject) {
				brokerProxyInstance = BrokerProxy.getLocalInstance();
				while(!brokerProxyInstance.hasBeenPopulatedByBroker()) { Thread.sleep(100); } 
			}
		}catch(InterruptedException e){                           
			
		}
       catch(ConfigManagerProxyLoggedException e){                           
			
		}
	}
	}
	
	/*public static String getUserDefinedConfigServProp(String serviceName, String propertyName)  {
		ConfigurableService service;
		
		try {
			initialize();
			service = brokerProxyInstance.getConfigurableService("UserDefined", serviceName);
			Properties properties = service.getProperties();
			return properties.getProperty(propertyName);
			
		} catch (ConfigManagerProxyPropertyNotInitializedException e) {
			return null; 
		}
	}
	*/
	public static String getEGUUID() 
	{
		try {
			IntegrationServerProxy EG = IntegrationServerProxy.class.newInstance();
			return EG.getName();
		} catch (IllegalAccessException | InstantiationException | IntegrationAdminException e) {
			// TODO Auto-generated catch block
			return e.toString();
			
		}
		
		
	}
	public static String getPolicyProperty(String PolicyName, String propertyName)  {
		//ConfigurableService service;
		
		
		try {
			/*initialize();
			service = brokerProxyInstance.getConfigurableService("UserDefined", serviceName);
			Properties properties = service.getProperties();
			return properties.getProperty(propertyName);
			*/
			MbPolicy policy = MbPolicy.getPolicy("UserDefined", PolicyName);
			return policy.getPropertyValueAsString(propertyName);

		} catch ( MbException e) {
			return null; 
		}
	}
	
	public static String  getEGProperty(String egName, String propertKey)  {
		
		initialize();
		String propertyValue= null;
		try {
			propertyValue= brokerProxyInstance.getExecutionGroupByName(egName).getRuntimeProperty(propertKey);
		} catch (ConfigManagerProxyPropertyNotInitializedException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("ConfigManagerProxyPropertyNotInitializedException", e); 

		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException("IllegalArgumentException", e); 
		}
		return propertyValue;
			
		
	}
	
}