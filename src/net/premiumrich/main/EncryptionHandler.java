package net.premiumrich.main;

import net.premiumrich.cipher.*;
import net.premiumrich.exceptions.*;

public class EncryptionHandler {
	
	public String handle(String encOrDec, int cipherIndex, int ccShift, String vcKey, String input) throws InvalidCipherException, NoInputException, ProgrammingException {
		
		if (cipherIndex == 0) {				// Check if no cipher selected
			throw new InvalidCipherException("Error! No cipher selected. Please try again.");
		} else if (input.equals("")) {		// Check if there is actual input, otherwise throw an exception
			throw new NoInputException("Error! No input. Please try again.");
		}
		
		if (encOrDec.equals("encrypt")) {
			switch (cipherIndex) {
			case 1:			// Caesar cipher
				CaesarCipher cc = new CaesarCipher();
				return cc.encrypt(ccShift, "", input);
			case 2:			// Vigenere cipher
				VigenereCipher vc = new VigenereCipher();
				return vc.encrypt(0, vcKey, input);
			case 3:			// A1Z26 cipher
				A1Z26Cipher a1 = new A1Z26Cipher();
				return a1.encrypt(0, "", input);
			default:
				throw new ProgrammingException("Error in parsing selected cipher! Please inform the programmer. Thank you.");
			}
			
		} else if (encOrDec.equals("decrypt")) {
			switch (cipherIndex) {
			case 1:			// Caesar cipher
				CaesarCipher cc = new CaesarCipher();
				if (ccShift < 0) {		// Check if shift is in other direction
					return cc.encrypt(Math.abs(ccShift), "", input);
				} else {
					return cc.decrypt(ccShift, "", input);
				}
			case 2:			// Vigenere cipher
				VigenereCipher vc = new VigenereCipher();
				return vc.decrypt(0, vcKey, input);
			case 3:			// A1Z26 cipher
				A1Z26Cipher a1 = new A1Z26Cipher();
				return a1.decrypt(0, "", input);
			default:
				throw new ProgrammingException("Error in parsing selected cipher! Please inform the programmer. Thank you.");
			}
		} else {
			throw new ProgrammingException("encOrDec not specified for handle method! Please inform the programmer. Thank you.");
		}
		
	}
	
}
