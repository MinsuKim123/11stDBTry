package com.min11st;
import java.net.URI;
import java.net.URLEncoder;

public class Test {
    public static void main(String[] args) throws Exception {

        // Prints me%40home.com (CORRECT)
        System.out.println(URLEncoder.encode("www.empas.com?°í¸±¶ó", "EUC-KR"));

        // Prints Email+Address (WRONG: Should be Email%20Address)
        System.out.println(URLEncoder.encode("Email Address", "UTF-8"));

        // http://www.home.com/test?Email%20Address=me@home.com
        // (WRONG: it has not encoded the @ in the email address)
        URI uri = new URI("http", "www.home.com", "/test", "Email Address=me@home.com", null);
        System.out.println(uri.toString());
    }
}

/*
public class Test {

	
	// [0xef][0xbf][0xbd][0xe3]?[0xbc][0xef][0xbf][0xbd][0xef][0xbf][0xbd][0xef][0xbf][0xbd]
	
	public static void main (String [] args) {
		
		// %EC%98%A4%EC%A7%95%EC%96%B4
		
		byte[] b = new byte[] { (byte)0xef, (byte)0xbf, (byte)0xbd,	(byte)0xe3,(byte)0xbc,(byte)0xef,(byte)0xbf,(byte)0xbd,(byte)0xef,(byte)0xbf,(byte)0xbd,(byte)0xef,(byte)0xbf,(byte)0xbd,           
		};
		
		byte[] b2 = hexStringToByteArray("EC98A4ECA795EC96B4");
		
		try {
			

			System.out.println(new String (b));
			System.out.println(new String (b, "EUC-KR"));
			System.out.println(new String (b, "UTF-8"));
			System.out.println(new String (b, "UTF-8"));
			System.out.println(new String (b, "ksc5601"));
			System.out.println(new String (b, "x-windows-949"));
			System.out.println(new String (b, "ISO-8859-1"));
			
			System.out.println("----------");
			System.out.println(new String (b2));
			System.out.println(new String (b2, "EUC-KR"));
			System.out.println(new String (b2, "UTF-8"));
			

			
		} catch (Exception e) {}
	}
	
	public static byte[] hexStringToByteArray(String s) {
	    int len = s.length();
	    byte[] data = new byte[len / 2];
	    for (int i = 0; i < len; i += 2) {
	        data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
	                             + Character.digit(s.charAt(i+1), 16));
	    }
	    return data;
	}
	
	
	
}

*/