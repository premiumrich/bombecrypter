package net.premiumrich.cipher;

import java.util.ArrayList;

public class A1Z26Cipher implements Cipher {
	
	char abc[] = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	
	public String encrypt(int shift, String key, String in) {
		
		char inChars[] = in.toCharArray();
		ArrayList<String>out = new ArrayList<String>();			// Create new ArrayList
		
		for (int chr = 0; chr < inChars.length; chr++) {
			if (Character.isLetter(inChars[chr])) {				// Filter out non-letters
				int a;
				for (a = 0; a < abc.length; a++) {
					if (Character.toLowerCase(inChars[chr]) == abc[a]) {
						break;
					}
				}
				out.add(String.valueOf(a + 1));					// Append one-indexed value in alphabet
			} else if (Character.isLetter(inChars[chr])){
				out.add(String.valueOf(inChars[chr]));			
			}
			
			if (!(chr == inChars.length - 1) && Character.isLetter(inChars[chr])) {
				out.add("-");				// Add a hyphen
			}
		}
		return printArrayList(out);
	}
	
	public String decrypt(int shift, String key, String in) {
		
		String inStrings[] = in.split("-");		// Split the numbers out
		char inChars[] = new char[inStrings.length];
		
		for (int i = 0; i < inStrings.length; i++) {
			if (inStrings[i].length() == 1) {
				inChars[i] = inStrings[i].charAt(0);
			}
		}

		ArrayList<Character>out = new ArrayList<Character>();
		
		for (int chr = 0; chr < inChars.length; chr++) {
			if (Character.isDigit(inChars[chr]) && inChars[chr] > 0) {
				out.add(abc[inChars[chr] - 49]);
			}
		}

		return printArrayList(out);	
	}
	
	public static String printArrayList(ArrayList in) {		// Print out concatenated elements of ArrayList
		String out = "";
		for (int i = 0; i < in.size(); i++) {
			out += in.get(i);
		}
		return out;
	}
	
}
