package Model;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.Spiel;
import View.Endstatistik;

/**
* Diese Klasse wird benutzt um die Zeit anzuzeigen, die man noch hat bevor das Spiel um ist
* und wenn der Timer 0 erreicht  wird ein Objekt der Klasse Endstatistik erstellt und dem 
* JFrame hinzugefuegt. 
* 
* @author  Christopher Hilfing
* @version 1.0
* @since   15-09-2018
*/

public class Timer extends JPanel{
	//variable welche Zeitpunkt des ausfuehrens von anfangen() speichert zum berechnen der vergangenen Zeit
	private double erstelltMillis;
	
	//koordianten von dem Timer
	private int x;
	private int y;
	
	private Spiel spiel;
	private JFrame frame;
	private JPanel panel;
	
	//Variabeln zur rotfaerbung des Timer bei 10s Restzeit
	private Color farbe;
	private int rotfaerbung = 25;

	public Timer(JFrame frame, JPanel panel, Spiel spiel, int x, int y) {
		this.frame = frame;
		this.panel = panel;
		this.spiel = spiel;
		this.x = x;
		this.y = y;
	}
	
	public void paint(Graphics2D g){
		//falls < 11s Rest Zeit anfangen mit rot faerbung
		int zeitInt = getAlterInSekDouble().intValue();
		if(getAlterInSekDouble() > 11){
			farbe = new Color(0, 0, 0);
		}else{
			farbe = new Color(rotfaerbung * 10-zeitInt, 0, 0);
		}
		
		g.setColor(farbe);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 50));
		g.drawString(getAlterInSekString(), x, y);
	}
	
	public void anfangen(){
		erstelltMillis = System.currentTimeMillis();
	}
	
	//rechnet die vergangenen Sek vom ausfuehren von anfangen() bis jetzt aus
	public String getAlterInSekString() {
		double jetztMillis = System.currentTimeMillis();
		return String.valueOf(String.format("%.2f", (20-(jetztMillis - this.erstelltMillis) / 1000)));
	}
	
	/*rechnet die vergangenen Sek vom ausfuehren von anfangen() bis jetzt aus
	 * und stoppt das Spiel wenn die Zeit abgelaufen ist und oeffnet die 
	 * Endstatistik.
	 */
	public Double getAlterInSekDouble() {
		double jetztMillis = System.currentTimeMillis();
		if ((20-(jetztMillis - this.erstelltMillis) / 1000) < 0.03){
			
			panel.removeAll();
			panel.repaint();
			panel.revalidate();
			
			Endstatistik endstatistik = new Endstatistik(frame,spiel);
			
			panel.add(endstatistik);
			panel.repaint();
			panel.revalidate();
			frame.repaint();
			
			spiel.alleZiele.removeAllElements();
			spiel.alleTimer.removeAllElements();
			
		}
		return  (20-(jetztMillis - this.erstelltMillis) / 1000);
	}
}
