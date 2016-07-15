package com.ufgov.zc.client.applet.local;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//import Test;

public class DigestUtil {

	private static MessageDigest md;

	static {

		try {

			md = MessageDigest.getInstance("SHA");

		} catch (NoSuchAlgorithmException e) {

			throw new RuntimeException(e.getMessage(), e);

		}

	}

	static synchronized public void update(byte[] input) {

		md.update(input);

	}

	static synchronized public String digest() {

		byte[] digest = md.digest();

		return new String(Base64.encode(digest));

	}

	static synchronized public String digest(byte[] input)
			throws UnsupportedEncodingException {
		if (input == null) {
			return "";
		}
		byte[] digest = md.digest(input);
		return new String(Base64.encode(digest), "UTF-8");
	}

	static synchronized public byte[] digestToByte(byte[] input) {
		if (input == null) {
			return new byte[] {};
		}
		return md.digest(input);
	}

	static public String digest(Object object) {
		try {
			return digest(ObjectUtil.objectToBytes(object));
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}

	}

}
