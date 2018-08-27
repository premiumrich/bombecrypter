package net.premiumrich.cipher;

public interface Cipher {
	
	String encrypt(int shift, String key, String in);
	String decrypt(int shift, String key, String in);
	
}
