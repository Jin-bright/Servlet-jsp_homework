package com.sh.mvc.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Base64.Encoder;

public class HelloMvcUtils {

	/** 단방향 암호화 - 복호화 불가능 
	 *  1. 암호화 처리 
	 *  2. 인코딩 Base64Encoder 사용 
	 *  
	 *  salt 비밀번호 암호화에 추가적으로 적용해서 사용자별로 다른 결과값을 얻도록 함 ★
 	 * 
	 * @param rawPassword
	 * @return
	 */
	public static String getEncryptedPassword(String rawPassword, String salt) { // rawPassword => 아직 암호화되기전 
		
		String encryptedPassword = null;
		
		//1. 암호화 처리  - 사용클래스 : MessageDigest 
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			byte[] _rawPassword = rawPassword.getBytes("utf-8");
			byte[] _salt = salt.getBytes("utf-8");
			
			md.update(_salt );
			byte[] _encryptedPassword = md.digest(_rawPassword);
			System.out.println( new String(_encryptedPassword)  );
			
			
		//2. 인코딩 Base64Encoder (영문자, 숫자, +, / ) padding =  
			Encoder encoder = Base64.getEncoder();
			encryptedPassword = encoder.encodeToString(_encryptedPassword); // 문자열로 결과를 돌려준다 
	
			System.out.println( "encryptedPassword  = " + encryptedPassword );
			
			
		} catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
			
			
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return encryptedPassword;
	}

}
