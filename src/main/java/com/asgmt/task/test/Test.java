package com.asgmt.task.test;

import com.asgmt.task.utils.AES256Util;

public class Test {

	public static void main(String[] args) throws Exception {
		AES256Util aes = new AES256Util("123456789012345678901234567890");
		String key ="testKey!!!";
		System.out.println(key);
		String eKey = aes.encrypt(key);
		System.out.println(eKey);
		String dKey = aes.decrypt(eKey);
		System.out.println(dKey);
	}

}
