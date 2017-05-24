package test;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.MultipartEntityBuilder;
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
	@Test
	public void testPost() throws IOException {
		
		FileInputStream in = new FileInputStream("d:\\1111.txt");
		byte[] readBytes = new byte[in.available()];
		in.read(readBytes);
		String string4file = new String(readBytes);
		System.out.println(string4file);
		CloseableHttpClient httpClient = HttpClients.createDefault();

		HttpPost httpPost = new HttpPost("http://localhost:8001/fileSystem/upload");

        MultipartEntityBuilder builder = MultipartEntityBuilder.create();
        builder.addBinaryBody("file1", readBytes, ContentType.MULTIPART_FORM_DATA, "1111.txt");// 文件流
        builder.addBinaryBody("file2", readBytes, ContentType.MULTIPART_FORM_DATA, "2222.txt");// 文件流
        builder.addBinaryBody("file3", readBytes, ContentType.MULTIPART_FORM_DATA, "3333.txt");// 文件流
        builder.addBinaryBody("file4", readBytes, ContentType.MULTIPART_FORM_DATA, "4444.txt");// 文件流
        builder.addBinaryBody("file5", readBytes, ContentType.MULTIPART_FORM_DATA, "5555.txt");// 文件流
        builder.addTextBody("filename", "1111.txt");// 类似浏览器表单提交，对应input的name和value
        HttpEntity entity = builder.build();
        httpPost.setEntity(entity);
        HttpResponse response = httpClient.execute(httpPost);// 执行提交
        HttpEntity responseEntity = response.getEntity();
        responseEntity.getContent();
        
	}

}
