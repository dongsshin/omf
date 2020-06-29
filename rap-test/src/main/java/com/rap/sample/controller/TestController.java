package com.rap.sample.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rap.omc.api.util.PropertyUtil;
import com.rap.omc.foundation.classes.service.CommonCoreService;
import com.rap.omc.foundation.user.service.FoundationUserDetailsService;
import com.rap.omc.test.service.TransactionTestService;
import com.rap.sample.controller.service.RedisTestService;

import rap.api.object.organization.dom.Users;

@RestController
public class TestController {
	
	@Autowired
	private CommonCoreService commonCoreService ;
	@Autowired
	private TransactionTestService transactionTestService;
	@Autowired
	FoundationUserDetailsService foundationUserDetailsService;
	@Autowired
	private RedisTestService redisTestService;
	
	@ResponseStatus(HttpStatus.CREATED)
	@RequestMapping("/test/getClassName")
	public void required_required() {
		String SYSTEM_DELIMINATOR_VALUE_FOR_JAVA = PropertyUtil.getProperty("SYSTEM_DELIMINATOR_VALUE_FOR_JAVA");
		String className = commonCoreService.getClassNameWithObid("obid");
		System.out.print(className);
		System.out.print(SYSTEM_DELIMINATOR_VALUE_FOR_JAVA);
		
		Users userDom = null;
		try {
			userDom = new Users("5MjzYPlsdOfkN04d48ii");
		}catch(Exception e) {
			e.printStackTrace();
			e.printStackTrace();
		}
//		userDom.getVo().setNames("TEST00001");
//		userDom.createObject();
		//transactionTestService.txnAllTest();
		try {
			transactionTestService.txnMasterTestError();
		}catch(Exception e) {
			;
		}
		transactionTestService.txnFoundationTest();
	}
	
    @RequestMapping("/test/helloAdmin")
    public String helloAdmin() {
        return "hello admin(SessionController)";
    }
	@RequestMapping("/test/")
    public String root_test() throws Exception{
        return "Hello Root(/)";
    }
 
    @RequestMapping("/test/demo")
    public String demo_test() throws Exception{
        return "Hello demo(/demo)";
    }
    @RequestMapping("/test/redisTest")
    public String redisTest() {
    	//redisTestService.redisTest();
    	
    	 return "redisTest";
    }
    
	@RequestMapping("/test/autoLogin")
    public String autoLogin(HttpServletRequest request) throws Exception{
    	UserDetails userDetails = foundationUserDetailsService.loadUserByUsername("XP3866");
    	UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails,userDetails.getPassword());
    	SecurityContext securityContext = SecurityContextHolder.getContext();
    	securityContext.setAuthentication(usernamePasswordAuthenticationToken);
    	HttpSession session = request.getSession(true);
    	session.setAttribute("SPRING_SECURITY_CONTEXY", securityContext);
    	return "Auto Login Success";
    }
    
    
    @RequestMapping("/test/httpClientTest")
    public String httpClientTest() throws Exception{
    	sendGet();
    	return "dd";
    	
    }
    
    public void sendGet() throws ClientProtocolException, IOException {
    	final String USER_AGENT = "Mozila/5.0";
        //final String GET_URL = "http://localhost:8080/demo";    
    	final String GET_URL = "https://blockchain.info/ko"
                + "/rawblock/0000000000000bae09a7a393a8acd"
                + "ed75aa67e46cb81f7acaa5ad94f9eacd103";

        //http client 생성
        CloseableHttpClient httpClient = HttpClients.createDefault();
        //get 메서드와 URL 설정
        HttpGet httpGet = new HttpGet(GET_URL);
        //agent 정보 설정
        httpGet.addHeader("User-Agent", USER_AGENT);
        httpGet.addHeader("Content-type", "application/json");
        
        //get 요청
        CloseableHttpResponse httpResponse = httpClient.execute(httpGet);
        
        System.out.println("GET Response Status");
        System.out.println(httpResponse.getStatusLine().getStatusCode());
        String json = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
        
        System.out.println(json);
        
        httpClient.close();
    }
    
    public void get(String requestURL) {
		try {
			HttpClient client = HttpClientBuilder.create().build(); // HttpClient 생성
			HttpGet getRequest = new HttpGet(requestURL); //GET 메소드 URL 생성
			getRequest.addHeader("x-api-key", "RestTestCommon.API_KEY"); //KEY 입력
			HttpResponse response = client.execute(getRequest);
			if (response.getStatusLine().getStatusCode() == 200) {
				ResponseHandler<String> handler = new BasicResponseHandler();
				String body = handler.handleResponse(response);
				System.out.println(body);
			} else {
				System.out.println("response is error : " + response.getStatusLine().getStatusCode());
			}

		} catch (Exception e){
			System.err.println(e.toString());
		}
	}

    public void post(String requestURL, String jsonMessage) {
		try {
			HttpClient client = HttpClientBuilder.create().build(); // HttpClient 생성
			HttpPost postRequest = new HttpPost(requestURL); //POST 메소드 URL 새성 
			postRequest.setHeader("Accept", "application/json");
			postRequest.setHeader("Connection", "keep-alive");
			postRequest.setHeader("Content-Type", "application/json");
			postRequest.addHeader("x-api-key", "RestTestCommon.API_KEY"); //KEY 입력 
			//postRequest.addHeader("Authorization", token); // token 이용시

			postRequest.setEntity(new StringEntity(jsonMessage)); //json 메시지 입력 
			HttpResponse response = client.execute(postRequest);
			//Response 출력
			if (response.getStatusLine().getStatusCode() == 200) {
				ResponseHandler<String> handler = new BasicResponseHandler();
				String body = handler.handleResponse(response);
				System.out.println(body);
			} else {
				System.out.println("response is error : " + response.getStatusLine().getStatusCode());
			}
		} catch (Exception e){
			System.err.println(e.toString());
		}
	}
}