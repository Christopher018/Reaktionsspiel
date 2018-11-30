import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;

import View.Hauptmenue;

/**
* In dieser Klasse ist das Main. Alle JFrame Einstellungen werden eingestellt,
* ein Objekt der Klasse "Hauptmenue" wird erstellt und dem JFrame hinzugefuegt.
* 
* @author  Christopher Hilfing
* @version 1.0
* @since   14-09-2018
*/

public class Main {

	private static JFrame frame = new JFrame("Reaktionsspiel");

	public static void main(String[] args) throws InterruptedException {
		
		Hauptmenue hauptmenue = new Hauptmenue(frame);
		frame.add(hauptmenue, BorderLayout.CENTER);
		
		//alle einstellungen von JFrame
		frame.setSize(507, 525);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setBackground(Color.WHITE);
		frame.setAlwaysOnTop(true);
		
		//Spiel immer neu zeichnen
		while (true) {
			Thread.sleep(10);
			frame.repaint();
		}

	}
}
