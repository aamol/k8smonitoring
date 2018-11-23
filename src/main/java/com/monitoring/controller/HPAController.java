package com.monitoring.controller;

import java.nio.charset.Charset;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;

import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.monitoring.config.EnvironmentComponent;
import com.monitoring.dto.Environment;

@Controller
@RequestMapping("/hpa")
public class HPAController {
	
	@Autowired
	EnvironmentComponent environmentConifg;
	
	@GetMapping("/getAll")
    public String getDeployment(@RequestParam(name="env", required=true, defaultValue="DEV01") String environmentName,@RequestParam(name="namespace", required=false) String namespace, Model model) throws Exception {
		Environment env = environmentConifg.getEnvironmentDetails(environmentName);
		//RestTemplate  restTemplate = new RestTemplate();
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
		RestTemplate restTemplate = new RestTemplate(requestFactory);
		if(StringUtils.isEmpty(namespace)) {
			namespace=env.getDefaultNameSpace();
		}

		String URL = "https://"+env.getMasterIP()+"/apis/autoscaling/v1/namespaces/"+namespace+"/horizontalpodautoscalers";
		ResponseEntity<Object> hpa = restTemplate.exchange(URL, HttpMethod.GET, new HttpEntity<String>(createHeaders(env.getUser(), env.getPassword())), Object.class);
		model.addAttribute("env", environmentName);
		model.addAttribute("namespace", namespace);
		model.addAttribute("hpa",hpa.getBody());
        return "hpa";
        //return a.getBody();
    }
	
	HttpHeaders createHeaders(String username, String password){
		   return new HttpHeaders() {{
		         String auth = username + ":" + password;
		         byte[] encodedAuth = Base64.encodeBase64( 
		            auth.getBytes(Charset.forName("US-ASCII")) );
		         String authHeader = "Basic " + new String( encodedAuth );
		         set( "Authorization", authHeader );
		      }};
		}

}
