package ui;

import java.awt.Color;
import java.awt.Label;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import model.GameBoard;

public class DateView extends JPanel implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Number of the actual month.
	 */
	private int numberMois;
	
	/**
	 * Actual year.
	 */
	private int annee;
	
	/**
	 * Array containing the months' name.
	 */
	private String[] tableauMois = {"January","February","March","April","May","June","July","August","September","October","November","December"};
	
	/**
	 * Label containing the date.
	 */
	private Label date;
	
	public DateView (GameBoard w){
		super();
		
		this.setBorder(BorderFactory.createBevelBorder(1, Color.GRAY, Color.GRAY));        
		this.setName("Date");
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setBorder(new TitledBorder(new EtchedBorder(), "Date"));
		
		this.date = new Label();     
		this.numberMois = GameBoard.ROUNDCOUNTER.get() % 12; //i becomes the remainder of the Euclidean division of ROUNDCOUNTER by 12
		this.annee = 2017 + GameBoard.ROUNDCOUNTER.get() / 12; //We add to the year of departure the quotient of the Euclidean division of the ROUNDCOUNTER by 12
		
		date.setText(tableauMois[numberMois] + " " + annee);
		
		this.add(date);			
	}
	
	/**
	 * Update the date (month and year) according to the round's number.
	 */
	public void update(Observable o, Object arg){
        assert o instanceof GameBoard;
        
		this.numberMois = GameBoard.ROUNDCOUNTER.get() % 12; //i becomes the remainder of the Euclidean division of ROUNDCOUNTER by 12		
		this.annee = 2017 + GameBoard.ROUNDCOUNTER.get() / 12; //We add to the year of departure the quotient of the Euclidean division of the ROUNDCOUNTER by 12
		this.date.setText(tableauMois[numberMois] + " " + annee);
	}


}
