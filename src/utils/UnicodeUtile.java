package utils;

public class UnicodeUtile {
	 public static void main(String args[]){  
	        String aa = "\u8fd9\u662f\u4e00\u4e2a\u4e2d\u6587\u5b57\u7b26\u4e32";  
	        String bb = "";  
	        String cc = "这是一个中文字符串";  
	        String dd = "";  
	        UnicodeUtile cs = new UnicodeUtile();  
	        System.out.println("----------------unicode编码转换为中文：-----------------");  
	        bb=cs.reconvert(aa);  
	        System.out.println(bb);  
	        System.out.println("----------------中文转换为unicode编码：-----------------");  
	        dd=cs.conver(cc);  
	        System.out.println(dd);  
	    }  
	    public String reconvert(String str){  
	         char[]c=str.toCharArray();  
	         String resultStr= "";  
	         for(int i=0;i<c.length;i++)  
	           resultStr += String.valueOf(c[i]);  
	         return resultStr;  
	    }  
	    public String conver(String str) {  
	        String result = "";  
	        for(int i = 0; i < str.length(); i++) {  
	            String temp = "";  
	            int strInt = str.charAt(i);  
	            if(strInt > 127) {  
	                temp += "\\u" + Integer.toHexString(strInt);  
	            } else {  
	                temp = String.valueOf(str.charAt(i));  
	            }  
	              
	            result += temp;  
	        }  
	        return result;  
	    } 

}
