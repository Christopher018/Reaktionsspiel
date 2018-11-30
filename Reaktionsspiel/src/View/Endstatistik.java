package View;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import Controller.Spiel;
import Eingabe.EingabeMaus;

/**
* In dieser Klasse wird definiert wie das Fenster mit der Endstatistik aussieht. 
* Es werden daten von dem letzten Spiel durch die Klasse "Spiel" geholt und in einem
* textareal ausgegeben.
* 
* @author  Christopher Hilfing
* @version 1.0
* @since   15-09-2018
*/

public class Endstatistik extends JPanel {
	private JFrame frame;
	private JPanel panel;
	private Spiel letztesSpiel;

	private JTextArea textareal;
	private JButton startKnopf;
	private JButton BeendenKnopf;

	public Endstatistik(JFrame frame, Spiel letztesSpiel) {
		this.frame = frame;
		this.letztesSpiel = letztesSpiel;

		// panel Layout wird gesetzt
		panel = (JPanel) frame.getContentPane();
		panel.setLayout(null);

		// inizialisieren von allen Gui-Elementen
		startKnopf = new JButton("<html><span style='font-size:12px'>Spiel neustarten</html></span>");
		BeendenKnopf = new JButton("<html><span style='font-size:12px'>Beenden</html></span>");
		textareal = new JTextArea(30,20); 

		// alles hinzufuegen
		panel.add(startKnopf);
		panel.add(BeendenKnopf);
		panel.add(textareal);


		// Groesse erfahren von den Gui-Elementen
		Dimension groesseKnopf = startKnopf.getPreferredSize();
		Dimension groesseTextareal = textareal.getPreferredSize();

		// Groesse festlegen und Position festlegen
		startKnopf.setBounds(766, 909, groesseKnopf.width + 70, groesseKnopf.height + 20);
		BeendenKnopf.setBounds(0, 909, groesseKnopf.width + 70, groesseKnopf.height + 20);
		textareal.setBounds(0, 0, frame.getWidth(), frame.getHeight());
		
		//textareal anpassen
		textareal.setFont(new Font("TimesRoman", Font.PLAIN, 50));
		textareal.setEditable(false);
		
		//text zum textareal hinzufuegen
		textareal.setText(	"\t   Die Zeit ist um \n\n\n"+
							"Anzahl Punkte: "+(int) letztesSpiel.getAnzahlPunkte()+"\n"+
							"Klicks: "+(int) letztesSpiel.getAnzahlKlicks()+"\n"+
							"Treffer : "+(int) letztesSpiel.getAnzahlTreffer()+"\n"+
							"Trefferquote: "+letztesSpiel.getTrefferquote()+"% \n"+
							"Ø Punkte pro Treffer : "+letztesSpiel.getDurchschnittDistanz()+"\n");

		// Listener hinzufügen
		startKnopf.addActionListener(new Knopfaction());
		BeendenKnopf.addActionListener(new Knopfaction());
	}

	class Knopfaction implements ActionListener {
		/*falls man BeendenKnopf drueckt wird die Applikation geschlossen.
		 * Wenn man den startKnopf drueckt wird das Spiel erneut gestartet.
		 */
		@Override
		public void actionPerformed(ActionEvent arg0) {
			if (arg0.getSource() == startKnopf) {
				panel.removeAll();
				panel.repaint();
				panel.revalidate();
				
				
				
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
				System.exit(0);
			}
		}

	}
}
