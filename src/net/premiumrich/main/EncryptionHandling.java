package net.premiumrich.main;

import net.premiumrich.cipher.*;

public class EncryptionHandling {
	
	public String handle(String encOrDec, int cipherIndex, int ccShift, String vcKey, String input) {
		
		if (encOrDec.equals("encrypt")) {
			switch (cipherIndex) {
			case 0:
				return "errEncOnDefault!";
			case 1:	// Caesar cipher
				CaesarCipher cc = new CaesarCipher();
				return cc.shift(ccShift, input);
			case 2:	// Vigenere cipher
				VigenereCipher2 vc = new VigenereCipher2();
				return vc.encrypt(vcKey, input);
			case 3: // A1Z26 cipher
				A1Z26 a1 = new A1Z26();
				return a1.encrypt(input);
			default:
				return "errNull!";
			}
			
		} else if (encOrDec.equals("decrypt")) {
			switch (cipherIndex) {
			case 0:
				return "errEncOnDefault!";
			case 1:	// Caesar cipher
				CaesarCipher cc = new CaesarCipher();
				if (ccShift < 0) {
					return cc.shift(Math.abs(ccShift), input);
				} else {
					return cc.shift(-ccShift, input);
				}
				
			case 2:	// Vigenere cipher
				VigenereCipher2 vc = new VigenereCipher2();
				return vc.decrypt(vcKey, input);
			case 3: // A1Z26 cipher
				A1Z26 a1 = new A1Z26();
				return a1.decrypt(input);
			default:
				return "errNull!";
			}
		} else {
			return "errNull!";
		}
		
	}
	
}
