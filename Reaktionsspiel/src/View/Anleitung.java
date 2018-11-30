package View;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controller.Spiel;
import Eingabe.EingabeMaus;

/**
* In dieser Klasse wird definiert wie das Fenster mit der Anleitung, wie man das Spiel spielt aussieht.
* In dieser Klasse wird zudem auf druecken des Knopfes "startKnopf" ein Objekt der klasse "Spiel" erstellt
* und so das Spiel gestartet.
* 
* @author  Christopher Hilfing
* @version 1.0
* @since   14-09-2018
*/

public class Anleitung extends JPanel{
	private JFrame frame;
	private JPanel panel;

	private JButton startKnopf;
	
	public Anleitung(JFrame frame) {
		this.frame = frame;

		// panel Layout wird gesetzt
		panel = (JPanel) frame.getContentPane();
		panel.setLayout(null);

		// inizialisieren von allen Gui-Elementen
		startKnopf = new JButton("<html><span style='font-size:12px'>Spiel starten</html></span>");

		// alles hinzufuegen aussern inkorrekt
		panel.add(startKnopf);

		//gesamter Text hinzufuegen
		String pfad = new File("").getAbsolutePath();
		ImageIcon i = new ImageIcon(pfad+"/src/Ressourcen/Anleitung.jpg");
		System.out.println(pfad);
		JLabel l = new JLabel();
		l.setIcon(i);
		panel.add(l);
		l.setBounds(0, 0, 500, 500);

		// Groesse erfahren von den Gui-Elementen
		Dimension groesseKnopf = startKnopf.getPreferredSize();

		// Groesse festlegen und Position festlegen
		startKnopf.setBounds(151, 436, groesseKnopf.width + 70, groesseKnopf.height + 20);

		// Listener hinzufügen
		startKnopf.addActionListener(new Knopfaction());
	}
	
	class Knopfaction implements ActionListener {
		//startet das Spiel und vergroessert das Fenster
		@Override
		public void actionPerformed(ActionEvent arg0) {
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
	}
}
