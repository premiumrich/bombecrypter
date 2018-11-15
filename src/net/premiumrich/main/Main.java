package net.premiumrich.main;

import java.awt.EventQueue;

public class Main {

	public static void main(String args[]) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Bombecrypter appWindow = new Bombecrypter();
					appWindow.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
}
