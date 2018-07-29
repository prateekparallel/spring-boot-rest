package com.bhaiti.server.main;
//This is a auto generated test class
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRestApplicationTests {

	URL url_ = null;
	HttpsURLConnection connection_ = null;
	int _responseCode = 404;
	
	@Test
	public void contextLoads() {
		//try {
			//url_ = new URL("http://localhost:8083//welcome/user?name=Abhijit");
		//}catch(Exception e) {
			//e.printStackTrace();
		//}
		//setSSLConnection(url_, "POST", "text/xml");
		try {
			url_ = new URL("http://localhost:8083//welcome/user?name=Abhijit");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		sendRequest(url_, "GET", "", "text/xml");
	}
	
	private boolean setSSLConnection(URL url, String method, String msgtype) {
		
		try {
			connection_ = (HttpsURLConnection) url.openConnection();
			if(method == "POST")
				connection_.setRequestMethod(method);
			connection_.setDoOutput(true );
			connection_.setRequestProperty("Content-Type", msgtype /*"text/xml" */ );
			connection_.connect();
			return true;
		} catch (Exception e) {
			connection_.disconnect();
			connection_ = null;
			return false;
		}
	}
	
	public void releaseConnection() {
		connection_.disconnect();
		connection_ = null;
	}
	
	/**
	 * 
	 * @param url
	 * @param method
	 * @param message
	 * @param msgtype json or xml
	 * @return
	 */
	
	public String sendRequest(URL url, String method, String message, String msgtype) {
		
		String response = null;
		
		if (setSSLConnection(url,method,msgtype)) {
		
			try{
				//Sending the request to Remote server
				OutputStreamWriter writer = new OutputStreamWriter(connection_.getOutputStream());
				writer.write(message);
				writer.flush();
				writer.close();			
				_responseCode = connection_.getResponseCode();						
				// reading the response
				InputStreamReader reader = new InputStreamReader(connection_.getInputStream());
				StringBuilder buf = new StringBuilder();
				char[] cbuf = new char[ 2048 ];
				int num;
				while ( -1 != (num = reader.read( cbuf )))
				{
					buf.append(cbuf, 0, num );
				}
			response = buf.toString();
			
		  }catch(Exception e){
			response = "<EXCEPTION>Exception occurred while sending message</EXCEPTION>";
			e.printStackTrace();
		 }

	  }
		releaseConnection();
		return response;
	}

}
