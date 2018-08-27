package net.premiumrich.cipher;

public class VigenereCipher implements Cipher {
	
	char abc[][] = {
			{'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'},
			{'b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','a'},
			{'c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','a','b'},
			{'d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','a','b','c'},
			{'e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','a','b','c','d'},
			{'f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','a','b','c','d','e'},
			{'g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','a','b','c','d','e','f'},
			{'h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','a','b','c','d','e','f','g'},
			{'i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','a','b','c','d','e','f','g','h'},
			{'j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','a','b','c','d','e','f','g','h','i'},
			{'k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','a','b','c','d','e','f','g','h','i','j'},
			{'l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','a','b','c','d','e','f','g','h','i','j','k'},
			{'m','n','o','p','q','r','s','t','u','v','w','x','y','z','a','b','c','d','e','f','g','h','i','j','k','l'},
			{'n','o','p','q','r','s','t','u','v','w','x','y','z','a','b','c','d','e','f','g','h','i','j','k','l','m'},
			{'o','p','q','r','s','t','u','v','w','x','y','z','a','b','c','d','e','f','g','h','i','j','k','l','m','n'},
			{'p','q','r','s','t','u','v','w','x','y','z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o'},
			{'q','r','s','t','u','v','w','x','y','z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p'},
			{'r','s','t','u','v','w','x','y','z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q'},
			{'s','t','u','v','w','x','y','z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r'},
			{'t','u','v','w','x','y','z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s'},
			{'u','v','w','x','y','z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t'},
			{'v','w','x','y','z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u'},
			{'w','x','y','z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v'},
			{'x','y','z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w'},
			{'y','z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x'},
			{'z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y'}
	};
	
	public String encrypt(int shift, String key, String in) {
		int l = 0;
		while (in.length() != key.length()) {		// Need to check if key.length() > in.length()
			key += key.substring(l, l+1);
			l++;
		}
		
		char inChars[] = in.toCharArray();
		char keyChars[] = key.toCharArray();
		int offset = 0;
		
		for (int chr = 0; chr < inChars.length; chr++) {			// Iterate through input chars
			if (!Character.isLetter(inChars[chr])) {				// Exclude letters, increment offset
				offset += 1;
			} else {
				boolean upperCase = false;
				if (Character.isUpperCase(inChars[chr])) {		// Check case
					upperCase = true;
				}
				
				int column;																// Find abc index of input char, store it as the new column
				for (column = 0; column < abc[0].length; column++) {
					if (Character.toLowerCase(inChars[chr]) == abc[0][column]) {
						break;
					}
				}
				System.out.println(column);
				
				int row;
				for (row = 0; row < abc.length; row++) {									// Find abc index of key char, store it as the new row
					if (Character.toLowerCase(keyChars[chr - offset]) == abc[row][0]) {
						break;
					}
				}
				System.out.println(row);
				
				inChars[chr] = abc[row][column];											// Replace original char with encrypted
				
				
				if (upperCase) {															// Check case
					inChars[chr] = Character.toUpperCase(inChars[chr]);
				}
			}
		}

		return new String(inChars);
	}
	
	
	public String decrypt(int shift, String key, String in) {
		int l = 0;
		while (in.length() != key.length()) {		// Need to check if key.length() > in.length()
			key += key.substring(l, l+1);
			l++;
		}
		
		char inChars[] = in.toCharArray();
		char keyChars[] = key.toCharArray();
		int offset = 0;
		
		for (int chr = 0; chr < inChars.length; chr++) {
			if (!Character.isLetter(inChars[chr])) {
				offset += 1;
			} else {
				boolean upperCase = false;
				if (Character.isUpperCase(inChars[chr])) {
					upperCase = true;
				}
				char[] tempABC = {};														// Temporary sub-array
				
				int row;
				for (row = 0; row < abc.length; row++) {									// Find row
					if (Character.toLowerCase(keyChars[chr - offset]) == abc[0][row]) {
						tempABC = abc[row];												// Set the sub-array
					}
				}
				
				
				int column;
				for (column = 0; column < tempABC.length; column++) {						// Find column
					if (Character.toLowerCase(inChars[chr]) == tempABC[column]) {
						inChars[chr] = abc[0][column];
						break;
					}
				}
				
				if (upperCase) {															// Check case
					inChars[chr] = Character.toUpperCase(inChars[chr]);
				}
			}
		}

		return new String(inChars);
	}
}
