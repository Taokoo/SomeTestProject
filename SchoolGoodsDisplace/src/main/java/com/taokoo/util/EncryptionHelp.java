package com.taokoo.util;
/**
 * 加密工具类
 * @author Taokoo
 */
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class EncryptionHelp {

	public static String SHA1(String decript) {
		try {
			MessageDigest digest = java.security.MessageDigest
					.getInstance("SHA-1");
			digest.update(decript.getBytes());
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String SHA(String decript) {
		try {
			MessageDigest digest = java.security.MessageDigest
					.getInstance("SHA");
			digest.update(decript.getBytes());
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

	public static String MD5(String input) {
		try {
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(input.getBytes());
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < md.length; i++) {
				String shaHex = Integer.toHexString(md[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	public static void main(String[] args) {

		String key = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBAKbn9eKKcy3fiLphPCNvzcG2x3JQmpXl2CMaX3e/NAtkijm1xjdER482sDFs//bVtc9HDxwy3UQGWsSxgOv+XhpdcsKt1Bmgc15bfcqSFWhWjh9X2DvKtipI8MmOPgpZCPSpKdYNYcw6awDJQ/2mg1KnSxu7plLzIp3snfpNvsaHAgMBAAECgYB47LJPMbQFVmD/eBdULVrLsJuMDS4S0HXp6N+mKJeScYNcEIDN5eBauGqrRKemtGuHA5rE+W0qLdhxjZE0ahTOpCEe5DAcf257Posm/exkvC6w/j5iwTxWcFYy0qw4e97GQkDE8xNAafNHHhBuLzZdcP+ju0ffJ06hEdtTUNB8cQJBAOVyBCUBoV4e3uSc4vcWFkyIRbNWkRg8yuzmypQcCuZo2uHTkyGZiCAEDWSC6AzCCuWCaNlxC6ARgj051o0zbA0CQQC6OQl4MHY2eWeTDW4sAsn/Tr93lnY9YBEEZCAIfXxTWQoxDx6HdLNFDPtuM95RYxkG1+5mqIO5hIGSRlyDZRPjAkAgSUj4BCC249Utij4N0tfUcSCNudK0/Gj5qQg8iy2len/CY0lC4x3ak3dm+1y33t41qYIjhtrB7XTMNMVSHThJAkAcnpdt+USxqobYer/r06HnstqKLn2NgS4cVqb1pxNsW8rNL12pw62pdw1NKnuIVoqKGrVw4YUdBdhD+rxNFUOxAkEA3OQd8i9gsb6tJ1za+97gtEOULt2Ter5yKmlfd9JyjDFsZMHHtLYCRe3NMzEy9ots2c2mKyOuoXbX1xbaa9bvSw==";

		String orderId = "12312424";
		Long addTime = 1569462983506L;
		String stopDate = "2020-05-01";
		String parkId = "P1";
		String userPhone = "13888888888";
		String email = "one@taokoo.com";
		String stopTime = "1";

		String str = orderId + addTime + stopDate + parkId + userPhone + email + stopTime + key;
		
		System.out.println(MD5(str));
		
		String str2 = orderId + addTime + key;
		System.out.println(MD5(str2));
		
	}
	

	/**
	 * 加密
	 * 
	 * @param content
	 *            需要加密的内容
	 * @param password
	 *            加密密码
	 * @return
	 */
	public static byte[] encryptAES(String content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(byteContent);
			return result; // 加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 解密
	 * 
	 * @param content
	 *            待解密内容
	 * @param password
	 *            解密密钥
	 * @return
	 */
	public static byte[] decryptAES(byte[] content, String password) {
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			kgen.init(128, new SecureRandom(password.getBytes()));
			SecretKey secretKey = kgen.generateKey();
			byte[] enCodeFormat = secretKey.getEncoded();
			SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.DECRYPT_MODE, key);// 初始化
			byte[] result = cipher.doFinal(content);
			return result; // 加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			e.printStackTrace();
		} catch (BadPaddingException e) {
			e.printStackTrace();
		}
		return null;
	}
}
