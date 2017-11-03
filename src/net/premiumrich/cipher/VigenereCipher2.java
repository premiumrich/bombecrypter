package net.premiumrich.cipher;

// (C) 2017 Richard Yang

public class VigenereCipher2 {
	
	public String encrypt(String key, String in) {
		return shift(key, in, "encrypt");
	}
	
	public String decrypt(String key, String in) {
		return shift(key, in, "decrypt");
	}
	
	public String shift(String key, String in, String mode) {
		CaesarCipher cc = new CaesarCipher();
		
		if (!key.equals("") && !in.equals("") && key.length() <= in.length()) {
			int l = 0;
			while (in.length() != key.length()) {
				key += key.substring(l, l + 1);
				l++;
			}
			char inChars[] = in.toCharArray();
			char keyChars[] = key.toLowerCase().toCharArray();
			int offset = 0;
			for (int chr = 0; chr < keyChars.length; chr++) {
				boolean upperCase = false;
				if (Character.isUpperCase(inChars[chr])) {
					upperCase = true;
				}
				
				if (!Character.isLetter(inChars[chr])) {
					offset += 1;
				} else if (mode.equals("encrypt")) {
					inChars[chr] = cc.shift(new String(cc.abc).indexOf(keyChars[chr - offset]), Character.toString(inChars[chr])).charAt(0);
				} else if (mode.equals("decrypt")) {
					inChars[chr] = cc.shift(-(new String(cc.abc).indexOf(keyChars[chr - offset])), Character.toString(inChars[chr])).charAt(0);
				}

				if (upperCase) {
					inChars[chr] = Character.toUpperCase(inChars[chr]);
				}
			}
			
			return new String(inChars);
			
		} else {
			return "Error in arguments";
		}
	}
}