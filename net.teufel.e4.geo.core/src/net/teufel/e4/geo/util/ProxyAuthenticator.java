package net.teufel.e4.geo.util;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

public class ProxyAuthenticator {

	private final static String PROXY_HOST = "";
	private final static String PROXY_PORT = "";
	private final static String PROXY_USER = "";
	private final static String PROXY_PASS = "";

	public static void authenticate() {
		System.setProperty("http.proxyHost",PROXY_HOST) ;
		System.setProperty("http.proxyPort", PROXY_PORT) ;
		Authenticator.setDefault(new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(PROXY_USER,PROXY_PASS.toCharArray());
			}
		});
	}
}
