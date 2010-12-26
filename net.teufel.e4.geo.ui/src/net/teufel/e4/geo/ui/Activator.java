package net.teufel.e4.geo.ui;

//import java.net.Authenticator;
//import java.net.PasswordAuthentication;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
		Activator.context = bundleContext;
//	    System.setProperty("http.proxyHost","111.111.111.111") ;
//	    System.setProperty("http.proxyPort", "3128") ;
//		    
//	    Authenticator.setDefault(new Authenticator() {
//	        protected PasswordAuthentication getPasswordAuthentication() {
//	            return new
//	                    PasswordAuthentication("xyz","xyz".toCharArray());
//	        }});
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Activator.context = null;
	}

}
