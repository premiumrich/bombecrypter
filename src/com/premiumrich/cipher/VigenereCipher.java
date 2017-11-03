package com.premiumrich.cipher;

// (C) 2017 Richard Yang

public class VigenereCipher {
	
	// Game plan
	// 0. Convert original string and key to char arrays +
	// 1. Find length of original string char array and repeat the key char array until the lengths match
	// 2. Check index of original string char array against abcA[] to find index in alphabet
	// 3. Check uppercase/lowercase
	// 4. Check index of key char array with switch (a,b,c,...), each case uses a different alphabet char array to store new value
	
	// Indices		0	1	2	3	4	5	6	7	8	9	10	11	12	13	14	15	16	17	18	19	20	21	22	23	24	25
	char abcA[] = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	char abcB[] = {'b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','a'};
	char abcC[] = {'c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','a','b'};
	char abcD[] = {'d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','a','b','c'};
	char abcE[] = {'e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','a','b','c','d'};
	char abcF[] = {'f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','a','b','c','d','e'};
	char abcG[] = {'g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','a','b','c','d','e','f'};
	char abcH[] = {'h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','a','b','c','d','e','f','g'};
	char abcI[] = {'i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','a','b','c','d','e','f','g','h'};
	char abcJ[] = {'j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','a','b','c','d','e','f','g','h','i'};
	char abcK[] = {'k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','a','b','c','d','e','f','g','h','i','j'};
	char abcL[] = {'l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','a','b','c','d','e','f','g','h','i','j','k'};
	char abcM[] = {'m','n','o','p','q','r','s','t','u','v','w','x','y','z','a','b','c','d','e','f','g','h','i','j','k','l'};
	char abcN[] = {'n','o','p','q','r','s','t','u','v','w','x','y','z','a','b','c','d','e','f','g','h','i','j','k','l','m'};
	char abcO[] = {'o','p','q','r','s','t','u','v','w','x','y','z','a','b','c','d','e','f','g','h','i','j','k','l','m','n'};
	char abcP[] = {'p','q','r','s','t','u','v','w','x','y','z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o'};
	char abcQ[] = {'q','r','s','t','u','v','w','x','y','z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p'};
	char abcR[] = {'r','s','t','u','v','w','x','y','z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q'};
	char abcS[] = {'s','t','u','v','w','x','y','z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r'};
	char abcT[] = {'t','u','v','w','x','y','z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s'};
	char abcU[] = {'u','v','w','x','y','z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t'};
	char abcV[] = {'v','w','x','y','z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u'};
	char abcW[] = {'w','x','y','z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v'};
	char abcX[] = {'x','y','z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w'};
	char abcY[] = {'y','z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x'};
	char abcZ[] = {'z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y'};
	
	public String encrypt(String key, String in) {
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
				int indx;
				for (indx = 0; indx < abcA.length; indx++) {						// Find column
					if (Character.toLowerCase(inChars[chr]) == abcA[indx]) {
						break;
					}
				}
				switch (Character.toLowerCase(keyChars[chr - offset])) {			// Cases for row
				case 'a':
					inChars[chr] = abcA[indx];
					break;
				case 'b':
					inChars[chr] = abcB[indx];
					break;
				case 'c':
					inChars[chr] = abcC[indx];
					break;
				case 'd':
					inChars[chr] = abcD[indx];
					break;
				case 'e':
					inChars[chr] = abcE[indx];
					break;
				case 'f':
					inChars[chr] = abcF[indx];
					break;
				case 'g':
					inChars[chr] = abcG[indx];
					break;
				case 'h':
					inChars[chr] = abcH[indx];
					break;
				case 'i':
					inChars[chr] = abcI[indx];
					break;
				case 'j':
					inChars[chr] = abcJ[indx];
					break;
				case 'k':
					inChars[chr] = abcK[indx];
					break;
				case 'l':
					inChars[chr] = abcL[indx];
					break;
				case 'm':
					inChars[chr] = abcM[indx];
					break;
				case 'n':
					inChars[chr] = abcN[indx];
					break;
				case 'o':
					inChars[chr] = abcO[indx];
					break;
				case 'p':
					inChars[chr] = abcP[indx];
					break;
				case 'q':
					inChars[chr] = abcQ[indx];
					break;
				case 'r':
					inChars[chr] = abcR[indx];
					break;
				case 's':
					inChars[chr] = abcS[indx];
					break;
				case 't':
					inChars[chr] = abcT[indx];
					break;
				case 'u':
					inChars[chr] = abcU[indx];
					break;
				case 'v':
					inChars[chr] = abcV[indx];
					break;
				case 'w':
					inChars[chr] = abcW[indx];
					break;
				case 'x':
					inChars[chr] = abcX[indx];
					break;
				case 'y':
					inChars[chr] = abcY[indx];
					break;
				case 'z':
					inChars[chr] = abcZ[indx];
					break;
				}
				if (upperCase) {
					inChars[chr] = Character.toUpperCase(inChars[chr]);
				}
			}
		}

		return new String(inChars);
	}
	
	
	public String decrypt(String key, String in) {
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
				char[] tempABC = {};
				switch (Character.toLowerCase(keyChars[chr - offset])) {			// Cases for row
				case 'a':
					tempABC = abcA;
					break;
				case 'b':
					tempABC = abcB;
					break;
				case 'c':
					tempABC = abcC;
					break;
				case 'd':
					tempABC = abcD;
					break;
				case 'e':
					tempABC = abcE;
					break;
				case 'f':
					tempABC = abcF;
					break;
				case 'g':
					tempABC = abcG;
					break;
				case 'h':
					tempABC = abcH;
					break;
				case 'i':
					tempABC = abcI;
					break;
				case 'j':
					tempABC = abcJ;
					break;
				case 'k':
					tempABC = abcK;
					break;
				case 'l':
					tempABC = abcL;
					break;
				case 'm':
					tempABC = abcM;
					break;
				case 'n':
					tempABC = abcN;
					break;
				case 'o':
					tempABC = abcO;
					break;
				case 'p':
					tempABC = abcP;
					break;
				case 'q':
					tempABC = abcQ;
					break;
				case 'r':
					tempABC = abcR;
					break;
				case 's':
					tempABC = abcS;
					break;
				case 't':
					tempABC = abcT;
					break;
				case 'u':
					tempABC = abcU;
					break;
				case 'v':
					tempABC = abcV;
					break;
				case 'w':
					tempABC = abcW;
					break;
				case 'x':
					tempABC = abcX;
					break;
				case 'y':
					tempABC = abcY;
					break;
				case 'z':
					tempABC = abcZ;
					break;
				}
				
				int indx;
				for (indx = 0; indx < tempABC.length; indx++) {						// Find column
					if (Character.toLowerCase(inChars[chr]) == tempABC[indx]) {
						inChars[chr] = abcA[indx];
						break;
					}
				}
				
				if (upperCase) {
					inChars[chr] = Character.toUpperCase(inChars[chr]);
				}
			}
		}

		return new String(inChars);
	}
}
