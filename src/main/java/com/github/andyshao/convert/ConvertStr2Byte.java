package com.github.andyshao.convert;

import java.util.Objects;

/**
 * 
 * Descript:<br>
 * Copyright: Copyright(c) Aug 7, 2014<br>
 * Encoding:UNIX UTF-8
 * @author Andy.Shao
 *
 */
public class ConvertStr2Byte implements Convert<String, Byte[]>{

	@Override
	public Byte[] convert(String in) {
		return hexStringToBytes(in);
	}

	public static Byte[] hexStringToBytes(String hexString){
		if(Objects.isNull(hexString) || hexString.isEmpty()) return null;
		
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		Byte[] d = new Byte[length];
		for(int i=0; i<length; i++){
			int pos = i*2;
			d[i] = (byte) (char)(charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		
		return d;
	}
	
	static byte charToByte(char c){
		return (byte) "0123456789ABCDEF".indexOf(c);
	}
}
