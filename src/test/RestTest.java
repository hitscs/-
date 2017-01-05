package test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

public class RestTest {

	@Test
	public void setDataTest() throws Exception {
		FileInputStream in = new FileInputStream("f:\\demo.xml");
		byte[] readBytes = new byte[in.available()];
		in.read(readBytes);
		String string4file = new String(readBytes);

		HttpClient client = new HttpClient();

		PostMethod method = new PostMethod("http://localhost:8080/gxdataCenter/searchRest/setData.action");
		method.addRequestHeader("Accept", "application/json");
		method.setRequestEntity(new StringRequestEntity(string4file, "text/xml","UTF-8")); 
        //设置返回的字符编码
		//client.getParams().setContentCharset("UTF-8");
		
		client.executeMethod(method);
		//String charSet =  method.getResponseCharSet();
		//String response =new String(method.getResponseBody(),charSet );
		String response =method.getResponseBodyAsString();
		System.out.println(response);
		method.releaseConnection();
	}
	@Test
	public void getNumTest() throws Exception {

		HttpClient client = new HttpClient();
		
        //String url = "http://localhost:8080/gxdataCenter/searchRest/getNum.action";
        String url = "http://localhost:8080/gxdataCenter/searchRest/getNum.json";
        String param = "keywords="+URLEncoder.encode("", "utf-8")+"";
        GetMethod method = new GetMethod(url+"?"+param); 	

		method.addRequestHeader("Accept", "application/json");
        //设置返回的字符编码
		//client.getParams().setContentCharset("UTF-8");
		
		client.executeMethod(method);
		//String charSet =  method.getResponseCharSet();
		//String response =new String(method.getResponseBody(),charSet );
		String response =method.getResponseBodyAsString();
		System.out.println(response);
		method.releaseConnection();
	}
	@Test
	public void getTotalNumTest() throws Exception {
		
		
		//httpClient4.3
		
/*		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpGet httpGet = new HttpGet(url);

		httpGet.addHeader("Accept", "application/json");

		CloseableHttpResponse rep = httpClient.execute(httpGet);

		HttpEntity repEntity = rep.getEntity();

		InputStream resStream = repEntity.getContent();
		BufferedReader br = new BufferedReader(new InputStreamReader(resStream, "GB2312"));
		StringBuffer resBuffer = new StringBuffer();
		String resTemp = "";
		while ((resTemp = br.readLine()) != null) {
			resBuffer.append(resTemp);
		}

		rep.close();
		httpClient.close();*/
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

		HttpClient client = new HttpClient();
		
        //String url = "http://localhost:8080/gxdataCenter/searchRest/getNum.action";
        String url = "http://localhost:8080/gxdataCenter/searchRest/getTotalNum.action";
        GetMethod method = new GetMethod(url); 	

		method.addRequestHeader("Accept", "application/json");
        //设置返回的字符编码
		//client.getParams().setContentCharset("UTF-8");
		
		client.executeMethod(method);
		//String charSet =  method.getResponseCharSet();
		//String response =new String(method.getResponseBody(),charSet );
		InputStream resStream = method.getResponseBodyAsStream();  
        BufferedReader br = new BufferedReader(new InputStreamReader(resStream,"GB2312"));
        StringBuffer resBuffer = new StringBuffer();  
        String resTemp = "";  
        while((resTemp = br.readLine()) != null){  
            resBuffer.append(resTemp);  
        }  
        String response = resBuffer.toString();  
		System.out.println(response);
		method.releaseConnection();
	}	
	

}
