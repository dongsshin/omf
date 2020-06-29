package com.rap.sample.WebClient;

import java.net.URI;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

/**
 * A example that demonstrates how HttpClient APIs can be used to perform
 * form-based logon.
 */
public class ClientFormLogin {

    public static void main(String[] args) throws Exception {
    	
    	
    	
    	
    	
    	
//    	CredentialsProvider provider = new BasicCredentialsProvider();
//    	UsernamePasswordCredentials credentials
//    	 = new UsernamePasswordCredentials("user1", "user1Pass");
//    	provider.setCredentials(AuthScope.ANY, credentials);
//    	  
//    	HttpClient client = HttpClientBuilder.create()
//    	  .setDefaultCredentialsProvider(provider)
//    	  .build();
//    	 
//    	HttpResponse response = client.execute(
//    	  new HttpGet(URL_SECURED_BY_BASIC_AUTHENTICATION));
//    	int statusCode = response.getStatusLine()
//    	  .getStatusCode();
//    	  
//    	assertThat(statusCode, equalTo(HttpStatus.SC_OK));
    	
        BasicCookieStore cookieStore = new BasicCookieStore();
        CloseableHttpClient httpclient = HttpClients.custom()
                .setDefaultCookieStore(cookieStore)
                .build();
        try {
            //HttpGet httpget = new HttpGet("http://someportal/");
            HttpGet httpget = new HttpGet("http://localhost:8080/redisTest");
            CloseableHttpResponse response1 = httpclient.execute(httpget);
            try {
                HttpEntity entity = response1.getEntity();

                System.out.println("Login form get: " + response1.getStatusLine());
                EntityUtils.consume(entity);

                System.out.println("Initial set of cookies:");
                List<Cookie> cookies = cookieStore.getCookies();
                if (cookies.isEmpty()) {
                    System.out.println("None");
                } else {
                    for (int i = 0; i < cookies.size(); i++) {
                        System.out.println("- " + cookies.get(i).toString());
                    }
                }
            } finally {
                response1.close();
            }
            HttpUriRequest login = RequestBuilder.post()
                    .setUri(new URI("http://localhost:8080/login"))
                    .addParameter("username", "XP3866")
                    .addParameter("password", "XP3866")
                    .build();
            CloseableHttpResponse response2 = httpclient.execute(login);

            Header[] headers = response2.getAllHeaders();
            if(headers!=null && headers.length>0){

             String sessionId=headers[0].getValue();

            }
            try {
                HttpEntity entity = response2.getEntity();
                System.out.println("Login form get: " + response2.getStatusLine());
                EntityUtils.consume(entity);

                System.out.println("Post logon cookies:");
                List<Cookie> cookies = cookieStore.getCookies();
                if (cookies.isEmpty()) {
                    System.out.println("None");
                } else {
                    for (int i = 0; i < cookies.size(); i++) {
                        System.out.println("- " + cookies.get(i).toString());
                    }
                }
            } finally {
                response2.close();
            }
        } finally {
            httpclient.close();
        }
    }
}