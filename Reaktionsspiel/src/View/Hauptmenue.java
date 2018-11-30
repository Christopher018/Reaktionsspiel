package View;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.*;

import Controller.Spiel;
import Eingabe.EingabeMaus;

/**
* In dieser Klasse wird definiert wie das Fenster des Hauptmenues aussieht. Es wird durch Knopf druck
* ein Objekt der Klasse "Spiel" oder "Anleitung" erstellt und dem JFrame hinzugefuegt. 
* 
* @author  Christopher Hilfing
* @version 1.0
* @since   14-09-2018
*/

public class Hauptmenue extends JPanel {
	private JFrame frame;
	private JPanel panel;

	private JButton startKnopf;
	private JButton infoKnopf;

	public Hauptmenue(JFrame frame) {
		this.frame = frame;

		// panel Layout wird gesetzt
		panel = (JPanel) frame.getContentPane();
		panel.setLayout(null);

		// inizialisieren von allen Gui-Elementen
		startKnopf = new JButton("<html><span style='font-size:12px'>Spiel starten</html></span>");
		infoKnopf = new JButton("<html><span style='font-size:12px'>Anleitung</html></span>");

		// alles hinzufuegen aussern inkorrekt
		panel.add(startKnopf);
		panel.add(infoKnopf);

		// hintergrund hinzufuegen
		String pfad = new File("").getAbsolutePath();
		ImageIcon i = new ImageIcon(pfad+"/src/Ressourcen/Hintergrund.jpg");
		JLabel l = new JLabel();
		l.setIcon(i);
		panel.add(l);
		l.setBounds(0, 0, 500, 500);

		// Groesse erfahren von den Gui-Elementen
		Dimension groesseKnopf = startKnopf.getPreferredSize();

		// Groesse festlegen und Position festlegen
		startKnopf.setBounds(162, 200, groesseKnopf.width + 70, groesseKnopf.height + 20);
		infoKnopf.setBounds(162, 260, groesseKnopf.width + 70, groesseKnopf.height + 20);

		System.out.println(250 - (groesseKnopf.width + 50) / 2);
		System.out.println((groesseKnopf.width + 50) / 2);

		// Listener hinzufügen
		startKnopf.addActionListener(new Knopfaction());
		infoKnopf.addActionListener(new Knopfaction());
	}

	class Knopfaction implements ActionListener {
		//startet das Spiel und vergroessert das Fenster
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getSource() == startKnopf) {
				panel.removeAll();
				panel.repaint();
				panel.revalidate();

				frame.setSize(1000, 1000);
				
				Spiel spiel = new Spiel(frame);
				
				EingabeMaus eingabeMaus = new EingabeMaus(frame, panel, spiel);
				spiel.addMouseListener(eingabeMaus);
				spiel.requestFocusInWindow();

				panel.add(spiel);
				spiel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
				spiel.runde();

				panel.repaint();
				panel.revalidate();
				frame.repaint();
			}
			else{
				panel.removeAll();
				panel.repaint();
				panel.revalidate();
				
				Anleitung anleitung = new Anleitung(frame);
				panel.add(anleitung);

				panel.repaint();
				panel.revalidate();
				frame.repaint();
			}
		}

	}
}
