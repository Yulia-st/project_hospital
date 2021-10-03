package com.my.utils;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
/**
 * Implementing the generation MD5 method 
 *
 */

public class PasswordHash {
	private static final Logger log = Logger.getLogger(PasswordHash.class);
	public static String md5Custom(String st) {
	    MessageDigest messageDigest = null;
	    byte[] digest = new byte[0];

	    try {
	        messageDigest = MessageDigest.getInstance("MD5");
	        messageDigest.reset();
	        messageDigest.update(st.getBytes());
	        digest = messageDigest.digest();
	    } catch (NoSuchAlgorithmException e) {
	        log.log(Level.DEBUG, "MD5 doesnt exist: ", e);
	    }

	    BigInteger bigInt = new BigInteger(1, digest);
	    String md5Hex = bigInt.toString(16);

	    while( md5Hex.length() < 32 ){
	        md5Hex = "0" + md5Hex;
	    }

	    return md5Hex;
	}
	
	public static String md5Apache(String st) {
	    String md5Hex = DigestUtils.md5Hex(st);

	    return md5Hex;
	}
}
