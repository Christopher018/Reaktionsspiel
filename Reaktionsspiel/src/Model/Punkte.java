package Model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import javax.swing.JPanel;

import Controller.Spiel;

/**
* In dieser Klasse wird definiert wie Punkte dargestellt werden und wie die Punkteanzeige aussieht.
* 
* @author  Christopher Hilfing
* @version 1.0
* @since   15-09-2018
*/

public class Punkte extends JPanel{
	private Spiel spiel;
	
	//koordinaten von Punkteanzeige/punkte
	private int x;
	private int y;
	
	private int lebensdauerZaehler = 50;
	/*
	 * zeigtAn: wenn der Wert dieser Variable 1 ist so zeigt es kurz an wie viele Punkte man bei diesen Klick
	 * kriegte. Sonst wird eine Anzeige erstellt die Zeigt wieviele Punkte man gesammelt hat.
	 */
	private int zeigtAn;
	private double anzahlPunkte;
	
	private Color dunkel_Gruen = new Color(0,153,0);
	
	
	
	public Punkte(Spiel spiel, int x, int y, int zeigtAn, double anzahlPunkte) {
		this.spiel = spiel;
		this.x = x;
		this.y = y;
		this.zeigtAn = zeigtAn;
		this.anzahlPunkte = anzahlPunkte;
	}
	
	public void paint(Graphics2D g){
		if(zeigtAn == 1){
			lebensdauerZaehler--;
			g.setColor(dunkel_Gruen);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 40));
			g.drawString(String.valueOf(String.format("%.0f",anzahlPunkte)), x, y);
			if(lebensdauerZaehler < 1){
				spiel.allePunkte.remove(this);
			}
		}else{
			g.setColor(Color.BLACK);
			g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
			g.drawString("Punkte: "+ String.valueOf(String.format("%.0f",spiel.getAnzahlPunkte())), x, y);
		}
	}
	
}
