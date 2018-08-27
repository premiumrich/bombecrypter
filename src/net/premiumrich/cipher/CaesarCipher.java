package net.premiumrich.cipher;

public class CaesarCipher implements Cipher {
	
	char abc[] = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	
	public String encrypt(int shift, String key, String in) {
		char inChars[] = in.toCharArray();
		
		for (int chr = 0; chr < inChars.length; chr++) {						// iterate through input
			boolean upperCase = false;
			if (Character.isUpperCase(inChars[chr])) {
				upperCase = true;
			}
			for (int n = 0; n < abc.length; n++) {	 						// iterate through alphabet
				if (Character.toLowerCase(inChars[chr]) == abc[n]) {			// match letter to find index
					n += shift;											// change index by shift
					if (n < 0) {												// handle underflow
						n = n + abc.length*Math.abs((int)(n/abc.length));
						if (n == 0) {
						} else {
							n = abc.length - Math.abs(n);
						}
					} else if (n > abc.length - 1) {							// handle overflow
						n = n - abc.length*(int)(n/abc.length);
					}
					
					if (upperCase) {											// check capitalization
						inChars[chr] = Character.toUpperCase(abc[n]);
					} else {
						inChars[chr] = abc[n];
					}
					break;
				}
			}
		}
		
		return new String(inChars);
	}
	
	public String decrypt(int shift, String key, String in) {
		return encrypt(-shift, "", in);
	}
}
