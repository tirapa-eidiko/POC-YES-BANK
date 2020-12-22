package com.yesbank.esb;

import java.util.zip.CRC32; 
import java.util.zip.Checksum; 


public class UtilityJava 
{

	public static String fillZeros(String text)

	
	{
		if(text.length()>=10)
		{
			return text;
		}
		else
		{
			
			return "0000000000".substring(text.length())+text;
		}
	}

	public static String maskdata(String text)
	{
		return text.replaceAll("\\S", "*");
	}
	
	
	      public static String checkSumValue(String input){
	            // get bytes from string 
	            byte bytes[] = input.getBytes(); 
	            Checksum checksum = new CRC32(); 
	            // update the current checksum with the specified array of bytes 
	            checksum.update(bytes, 0, bytes.length); 
	            // get the current checksum value 
	            long checksumValue = checksum.getValue(); 
	      return String.valueOf(checksumValue);   
	      }
	


}
