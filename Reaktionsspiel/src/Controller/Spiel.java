package Controller;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Random;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Model.Punkte;
import Model.Timer;
import Model.Ziele;

/**
* Diese Klasse hat zugriff auf alle Objekte von den Klassen Timer, Ziele und Punkte.
* Diese Klasse sagt alle Objekten, dass sie sich zeichnen sollen und sammelt daten,
* um sie in der Endstatistik anzuzeigen.
* 
* @author  Christopher Hilfing
* @version 1.0
* @since   15-09-2018
*/

public class Spiel extends JPanel {
	private JFrame frame;

	public Vector<Ziele> alleZiele = new Vector<Ziele>(1);
	public Vector<Timer> alleTimer = new Vector<Timer>(1);
	public Vector<Punkte> allePunkte = new Vector<Punkte>(2);

	// alle Variabeln, die mit dem Klicken der Linkenmaustaste zu tun haben
	private double anzahlTreffer;
	private double anzahlKlicks;

	/* 
	 * alle variabeln, die damit zu tun haben was die Durchschnittsdistanz ist
	 * mit diesen Variabeln wird der durchschnitt an Punkten pro Treffer berechnet
	 */
	private double neueDistanz;
	private double durchschnittDistanz = 0;
	private int anzDistanzen = 0;

	// alle variabeln, die mit Punkten zu tun haben
	private double anzahlPunkte = 0;

	// hier wird alles gezeichnet
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		// zeichnet Ziele
		for (int i = 0; i < alleZiele.size(); i++) {
			getZiele(i).paint(g2d);
		}

		// zeichnet Timer
		for (int i = 0; i < alleTimer.size(); i++) {
			getTimer(i).paint(g2d);
		}

		// zeichnet Punkte/Punkteanzeige
		for (int i = 0; i < allePunkte.size(); i++) {
			getPunkt(i).paint(g2d);
		}

	}

	public Spiel(JFrame frame) {
		this.frame = frame;
	}

	// hier werden die Zielscheiben kreiert und zufaellig platziert
	public void runde() {
		Random zufallGenerator = new Random();

		int zufallIntX = zufallGenerator.nextInt(frame.getWidth() - 100 * 2);
		int zufallIntY = zufallGenerator.nextInt((frame.getHeight() - 100 * 2) - 70);

		Ziele ziel = new Ziele(zufallIntX, 70 + zufallIntY);
		alleZiele.add(ziel);
	}

	/*
	 * hier wird jeweils der neue distanz(Punkte) wert zur Zielscheibe eingelesen und
	 * direkt verwendet um die Durchschnittspunktezahl zu berechnen (pro treffer).
	 */
	public void setDistanz(double neueDistanz) {
		this.neueDistanz = neueDistanz;
		anzDistanzen++;
		durchschnittDistanz = ((anzDistanzen - 1) * durchschnittDistanz + neueDistanz) / anzDistanzen;
	}

	// die durchschnittsdistanz wird formatiert fuer die Ausgabe bei der
	// Endstatistik
	public String getDurchschnittDistanz() {
		return String.valueOf(String.format("%.2f", durchschnittDistanz));
	}

	/*
	 * die Klicks auf Zielscheiben werden durch die anzahl insgesamter Klicks
	 * geteilt und als String ausgegeben für die Endstatistik
	 */
	public String getTrefferquote() {
		Double trefferquote = (double) (getAnzahlTreffer() / getAnzahlKlicks());
		return String.valueOf(String.format("%.0f", trefferquote * 100));
	}

	// hier wird der Wert von den gesammelten Punkten addiert
	public void setAnzahlPunkte(double anzahlPunkte) {
		this.anzahlPunkte = anzahlPunkte + this.anzahlPunkte;
	}
	
	public double getAnzahlPunkte() {
		return anzahlPunkte;
	}

	public Ziele getZiele(int index) {
		return alleZiele.get(index);
	}

	public Timer getTimer(int index) {
		return alleTimer.get(index);
	}

	public Punkte getPunkt(int index) {
		return allePunkte.get(index);
	}

	public double getAnzahlTreffer() {
		return anzahlTreffer;
	}

	public void setAnzahlTreffer(int anzahlTreffer) {
		this.anzahlTreffer = anzahlTreffer;
	}

	public double getAnzahlKlicks() {
		return anzahlKlicks;
	}

	public void setAnzahlKlicks(int anzahlKlicks) {
		this.anzahlKlicks = anzahlKlicks;
	}

}