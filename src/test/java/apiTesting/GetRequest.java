package apiTesting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class GetRequest {
	
	public void getMethod() throws IOException, URISyntaxException {
		URI uri = new URI("	https://dummy.restapiexample.com/api/v1/employees".trim());
		URL url = uri.toURL();
		HttpURLConnection connection= (HttpURLConnection)url.openConnection();
		connection.setRequestMethod("GET");
		connection.connect();
		
		int statusCode= connection.getResponseCode();
		System.out.println("Status code is "+statusCode);
		
		String statusMessage= connection.getResponseMessage();
		System.out.println("Status Message is "+statusMessage);
		
		InputStream inputStream= connection.getInputStream();
		InputStreamReader inputStreamReader= new InputStreamReader(inputStream);
		BufferedReader bufferedReader= new BufferedReader(inputStreamReader);
		String line;
		StringBuffer buffer= new StringBuffer();
		while((line=bufferedReader.readLine())!=null) {
			buffer.append(line);
		}
		System.out.println(buffer);
	}
	
	public static void main(String[] args) throws IOException, URISyntaxException {
		GetRequest request = new GetRequest();
		 request.getMethod();
		 
	}

}