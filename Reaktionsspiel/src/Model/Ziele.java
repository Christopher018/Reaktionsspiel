package Model;

import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.io.File;
import javax.swing.JPanel;

/**
* In dieser Klasse wird definiert wie ein Ziel aussieht und wie gross dieses ist.
* 
* @author  Christopher Hilfing
* @version 1.0
* @since   15-09-2018
*/

public class Ziele extends JPanel{
	private int x;
	private int y;
	
	private int mitteX;
	private int mitteY;
	
	private int groesse = 100;
	
	public Ziele(int x, int y) {
		super();
		this.x = x;
		this.y = y;
		//die koordinaten der Mitte der Zielscheibe werden berechnet
		mitteX = x + groesse/2;
		mitteY = y + groesse/2;
	}

	public void paint(Graphics2D g){
		String pfad = new File("").getAbsolutePath();
		g.drawImage(Toolkit.getDefaultToolkit().getImage(pfad+"/src/Ressourcen/Zielscheibe.png"), x, y, this);
	}


	public int getMitteX() {
		return mitteX;
	}


	public int getMitteY() {
		return mitteY;
	}


	public int getGroesse() {
		return groesse;
	}


	public void setX(int x) {
		this.x = x;
	}


	public void setY(int y) {
		this.y = y;
	}
	
	
}
