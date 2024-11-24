package apiTesting;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class PostRequest {
	
	public void postMethod() throws IOException, URISyntaxException {
		URI uri = new URI("https://dummy.restapiexample.com/api/v1/create".trim());
		URL url = uri.toURL();
		HttpURLConnection connection= (HttpURLConnection)url.openConnection();
		connection.setRequestMethod("POST");
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setDoOutput(true);
		
		String jsonBody="{\"name\":\"paulson\",\"age\":\"24\"}";
		byte[] inputJson=jsonBody.getBytes();
		OutputStream outputStream= connection.getOutputStream();
		outputStream.write(inputJson);
		
		System.out.println("Response Code "+connection.getResponseCode());
		System.out.println("Response Message "+connection.getResponseMessage());
		
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
		PostRequest request = new PostRequest();
		request.postMethod();

	}

}
