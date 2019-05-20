package com.monitoring.utility;

import java.nio.charset.Charset;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.monitoring.dto.Environment;

@Service
public class APIUtilitiy {
	
	public HttpComponentsClientHttpRequestFactory getRequestFactory()
			throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException {
		TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;
		SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
		        .loadTrustMaterial(null, acceptingTrustStrategy)
		        .build();
		SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);
		CloseableHttpClient httpClient = HttpClients.custom()
		        .setSSLSocketFactory(csf)
		        .build();
		HttpComponentsClientHttpRequestFactory requestFactory =
		        new HttpComponentsClientHttpRequestFactory();
		requestFactory.setHttpClient(httpClient);
		return requestFactory;
	}
	
	public HttpHeaders createHeaders(String username, String password){
		   return new HttpHeaders() {{
		         String auth = username + ":" + password;
		         byte[] encodedAuth = Base64.encodeBase64( 
		            auth.getBytes(Charset.forName("US-ASCII")) );
		         String authHeader = "Basic " + new String( encodedAuth );
		         set( "Authorization", authHeader );
		      }};
		}
	
	public ResponseEntity<Object> makeAPICall (String apiURL, HttpMethod httpMethod, Environment environment) throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
//		String apiURL = "https://"+ environment.getMasterIP() +"/apis/apps/v1/namespaces/" + namespace + "/" + apiName;
		RestTemplate restTemplate = new RestTemplate(getRequestFactory());
		ResponseEntity<Object> response = restTemplate.exchange(apiURL, httpMethod, new HttpEntity<String>(createHeaders(environment.getUser(), environment.getPassword())), Object.class);
		return response;
	}
	
	public ResponseEntity<Object> getNameSpaces (HttpMethod httpMethod, Environment environment) throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException {
		String URL = "https://"+ environment.getMasterIP() +"/api/v1/namespaces";
		RestTemplate restTemplate = new RestTemplate(getRequestFactory());
		ResponseEntity<Object> response = restTemplate.exchange(URL, httpMethod, new HttpEntity<String>(createHeaders(environment.getUser(), environment.getPassword())), Object.class);
		return response;
	}
	
	/*
	 * public ResponseEntity<Object> makeAPICall (String URL, Environment
	 * environment) throws KeyManagementException, NoSuchAlgorithmException,
	 * KeyStoreException { RestTemplate restTemplate = new
	 * RestTemplate(getRequestFactory()); ResponseEntity<Object> response =
	 * restTemplate.exchange(URL, HttpMethod.GET, new
	 * HttpEntity<String>(createHeaders(environment.getUser(),
	 * environment.getPassword())), Object.class); return response; }
	 */
	
	

}
