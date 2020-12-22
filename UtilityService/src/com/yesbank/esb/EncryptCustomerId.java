package com.yesbank.esb;

import java.io.UnsupportedEncodingException; 
import java.security.MessageDigest; 
import java.security.NoSuchAlgorithmException; 


public class EncryptCustomerId {

	public static String hashedCode(String CustomerId) 
    {
		 StringBuffer buf = new StringBuffer();
      try {
		MessageDigest md;
		  md = MessageDigest.getInstance("SHA-1");
		  byte[] sha1hash = new byte[40];
		 
		  md.update(CustomerId.getBytes("iso-8859-1"), 0, CustomerId.length());
		  sha1hash = md.digest();
		  for (int i = 0; i < sha1hash.length; i++) { 
		    int halfbyte = (sha1hash[i] >>> 4) & 0x0F;
		    int two_halfs = 0;
		    do { 
		        if ((0 <= halfbyte) && (halfbyte <= 9)) 
		            buf.append((char) ('0' + halfbyte));
		        else 
		            buf.append((char) ('a' + (halfbyte - 10)));
		        halfbyte = sha1hash[i] & 0x0F;
		    } while(two_halfs++ < 1);
		} 
		  
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      return buf.toString();

    }


	
}
