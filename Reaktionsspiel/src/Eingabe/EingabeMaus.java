package Eingabe;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.Spiel;
import Model.Punkte;
import Model.Timer;

public class EingabeMaus extends MouseAdapter {
	//x,y vom Mauszeiger
	private int x;
	private int y;
	//distanz zur mitte von der Zielscheibe
	private double distanz;
	
	//anzahl angeklicke Zielscheiben
	private int anzahlTreffer;
	//anzahl klicks
	private int anzahlKlicks;

	private Spiel spiel;
	private JFrame frame;
	private JPanel panel;

	public EingabeMaus(JFrame frame,JPanel panel, Spiel spiel) {
		this.spiel = spiel;
		this.frame = frame;
		this.panel = panel;
	}

	public void mouseClicked(MouseEvent arg0) {
		if (arg0.getButton() == 1) {
			//berechnung von distanz zum zentrum der Zielscheibe
			x = arg0.getX();
			y = arg0.getY(); 

			int distanzX = spiel.alleZiele.get(0).getMitteX() - x;
			int distanzY = spiel.alleZiele.get(0).getMitteY() - y;
			distanz = distanzX * distanzX + distanzY * distanzY;
			distanz = Math.sqrt(distanz);

			anzahlKlicks ++;
			if (distanz < spiel.alleZiele.get(0).getGroesse() / 2) {
				spiel.alleZiele.removeAllElements();
				spiel.runde();
				anzahlTreffer++;
				/*falls es die erste Zielscheibe war werden nun der Timer und die Punkteanzeige 
				 * hinzugefuegt
				 */
				if (anzahlTreffer == 1) {
					Timer timer = new Timer(frame, panel, spiel, 430, 50);
					spiel.alleTimer.add(timer);
					timer.anfangen();
					
					Punkte punkteAnzeige = new Punkte(spiel, 10, 50, 2, 0);
					spiel.allePunkte.add(punkteAnzeige);
				}
				//punkte fuer die angeklickte Zielscheibe werden berechnet und gespeichert
				Punkte punkteAnzeige = new Punkte(spiel, x-20, y+10, 1, (50-distanz)*2);
				spiel.allePunkte.add(punkteAnzeige);
				spiel.setAnzahlPunkte((50-distanz)*2);
				//fuer das Berechnen vom durchschnitt
				spiel.setDistanz((50-distanz)*2);
			}
			
			// fuer Endstatistik werden werte gespeichert fuer anzahlklicks und treffer
			spiel.setAnzahlKlicks(anzahlKlicks);
			spiel.setAnzahlTreffer(anzahlTreffer);
		}
	}	 
}