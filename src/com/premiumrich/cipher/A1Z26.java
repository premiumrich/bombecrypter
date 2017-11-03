package com.premiumrich.cipher;

public class A1Z26 {
	
	char abc[] = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	
	public String encrypt(String in) {
		
		char inChars[] = in.toCharArray();
		String out = "";
		
		for (int chr = 0; chr < inChars.length; chr++) {
			if (Character.isLetter(inChars[chr])) {
				int a;
				for (a = 0; a < abc.length; a++) {
					if (Character.toLowerCase(inChars[chr]) == abc[a]) {
						break;
					}
				}
				out += a + 1;
			} else if (Character.isLetter(inChars[chr])){
				out += inChars[chr];
			}
			
			if (!(chr == inChars.length - 1) && Character.isLetter(inChars[chr])) {
				out += "-";
			}
		}
		return out;
	}
	
	public String decrypt(String in) {
		
		String inStrings[] = in.split("-");
		char inChars[] = new char[inStrings.length];
		
		for (int i = 0; i < inStrings.length; i++) {
			if (inStrings[i].length() == 1) {
				inChars[i] = inStrings[i].charAt(0);
			}
		}

		String out = "";
		
		for (int chr = 0; chr < inChars.length; chr++) {
			if (Character.isDigit(inChars[chr]) && inChars[chr] > 0) {
				out += abc[inChars[chr] - 49];
			}
		}

		return out;	
	}
	
}
