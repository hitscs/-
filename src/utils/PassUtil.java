package utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Calendar;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.IvParameterSpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class PassUtil {
	/*
	 * urlencode解码
	 */

	public static String urlDEcode(String content) throws Exception {
		byte[] pass = null; // 密钥
		byte[] IV = null; // IV初始向量
		Calendar c = Calendar.getInstance();
		c.set(1900, 0, 1, 0, 0, 0);
		c.set(Calendar.MILLISECOND, 0);
		Calendar d = Calendar.getInstance();
		d.set(d.get(Calendar.YEAR), d.get(Calendar.MONTH), d.get(Calendar.DATE), 0, 0, 0);
		// d.set(2015, 1,16,0,0,0);
		System.out.println(d.get(Calendar.YEAR) + "/" + d.get(Calendar.MONTH) + 1 + "/" + d.get(Calendar.DATE));
		d.set(Calendar.MILLISECOND, 0);
		// System.out.println(c.getTime().getTime()/1000);
		// System.out.println(d.getTime().getTime()/1000);
		Long difftime = (d.getTime().getTime() / 1000 - c.getTime().getTime() / 1000);
		System.out.println("时间差:" + difftime);

		// MD5加密
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(difftime.toString().getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			System.out.println("MD5加密后:" + buf.toString());
			pass = getKey(buf.toString().substring(0, 16));
			IV = getKey(buf.toString().substring(16));

			// System.out.println("MD5(" + difftime + ",16) = " +
			// buf.toString());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			System.out.println("出错啦！！！！！！！！！！");
			e.printStackTrace();
		}

		/*
		 * url解码,base64解密,des解密
		 */
		IvParameterSpec iv = new IvParameterSpec(IV);
		DESKeySpec dks = new DESKeySpec(pass);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey key = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, key, iv);
		System.out.println("URLDecoder:" + URLDecoder.decode(content));
		System.out.println("base64Decoder:" + new String(base64Decoder(URLDecoder.decode(content)),"utf-8"));
		byte[] s = cipher.doFinal(base64Decoder(URLDecoder.decode(content)));
		// 获取用户名
		String b;
		String c1 = "";

		System.out.println("解密后的用户名为:" + new String(s,"utf-8"));
		return c1;
	}
	/*
	 * urlencode编码
	 */

	public static String urlEncode(String content) throws Exception {
		byte[] pass = null; // 密钥
		byte[] IV = null; // IV初始向量
		Calendar c = Calendar.getInstance();
		c.set(1900, 0, 1, 0, 0, 0);
		c.set(Calendar.MILLISECOND, 0);
		Calendar d = Calendar.getInstance();
		d.set(d.get(Calendar.YEAR), d.get(Calendar.MONTH), d.get(Calendar.DATE), 0, 0, 0);
		// d.set(2015, 1,16,0,0,0);
		System.out.println(d.get(Calendar.YEAR) + "/" + d.get(Calendar.MONTH) + 1 + "/" + d.get(Calendar.DATE));
		d.set(Calendar.MILLISECOND, 0);
		// System.out.println(c.getTime().getTime()/1000);
		// System.out.println(d.getTime().getTime()/1000);
		Long difftime = (d.getTime().getTime() / 1000 - c.getTime().getTime() / 1000);
		System.out.println("时间差:" + difftime);

		// MD5加密
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("MD5");
			md.update(difftime.toString().getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			System.out.println("MD5加密后:" + buf.toString());
			pass = getKey(buf.toString().substring(0, 16));
			IV = getKey(buf.toString().substring(16));

			// System.out.println("MD5(" + difftime + ",16) = " +
			// buf.toString());
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			System.out.println("出错啦！！！！！！！！！！");
			e.printStackTrace();
		}

		/*
		 * url加码,base64加密,des加密
		 */
		IvParameterSpec iv = new IvParameterSpec(IV);
		DESKeySpec dks = new DESKeySpec(pass);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
		SecretKey key = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");  
           
        //加密   
        cipher.init(Cipher.ENCRYPT_MODE, key,iv); 		
		
		
		byte[] s = cipher.doFinal(content.getBytes("utf-8"));
		System.out.println("加密后的用户名为:" + new String(s,"utf-8"));
		System.out.println("base64Encoder为:" + base64Encoder(new String(s)));
		System.out.println("URLEncoder为:" + URLEncoder.encode(base64Encoder(new String(s))));
		
		
		
		
		
		
		
		
		
		
		return URLEncoder.encode(base64Encoder(new String(s,"utf-8")));
		//return c1;
	}
	/*
	 * 解密base64
	 */
/*	public static byte[] base64Decoder(String content) {
		BASE64Decoder dcoder = new BASE64Decoder();
		byte[] b = null;
		try {
			b = dcoder.decodeBuffer(content);
		} catch (IOException e) {
			System.out.println("base64解析出错解析前内容为:" + content);
			e.printStackTrace();
		}
		return b;
	}*/



	/*
	 * 加密base64
	 */
/*	public static String base64Encoder(String content) {
		BASE64Encoder encoder = new BASE64Encoder();
		byte[] b = null;
		b = content.getBytes();
		return encoder.encode(b);
	}*/
	
    // 加密  
    public static String base64Encoder(String str) {  
        byte[] b = null;  
        String s = null;  
        try {  
            b = str.getBytes("utf-8");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
        if (b != null) {  
            s = new BASE64Encoder().encode(b);  
        }  
        return s;  
    }  
  
    // 解密  
    public static byte[] base64Decoder(String s) {  
        byte[] b = null;  
        String result = null;  
        if (s != null) {  
            BASE64Decoder decoder = new BASE64Decoder();  
            try {  
                b = decoder.decodeBuffer(s);  
                result = new String(b, "utf-8");  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        return b;  
    } 
	
	
	
	
	
	
	

	public static void main(String args[]) {
		try {
			//System.out.println(base64Encoder("test"));
			//System.out.println(new String(base64Decoder(base64Encoder("你好")), "utf-8"));
			//System.out.println(urlEncode("test"));
			System.out.println(urlDEcode(urlEncode("test")));
			// jORJgVyVpxKCWLIwfHXEMQ==
		} catch (Exception e) {
			// TODO z-generated

		}
	}

	/*
	 * 获取MD5加密后前8为密钥，后8位为IV初始向量
	 */
	public static byte[] getKey(String keyBase) {
		StringBuffer buffer = new StringBuffer();
		String temp = keyBase.substring(0, 16);
		byte[] barry = new byte[8];
		int j = 0;
		for (int i = 0; i < temp.length();) {
			barry[j] = (byte) Integer.parseInt(temp.substring(i, i + 2), 16);
			i = i + 2;
			j++;
		}
		return barry;
	}

}
