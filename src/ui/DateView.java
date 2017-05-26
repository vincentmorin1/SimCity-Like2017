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
	
	private int numberMois;
	private int annee;
	private String[] tableauMois = {"Janvier","Fevrier","Mars","Avril","Mai","Juin","Juillet","Aout","Septembre","Octobre","Novembre","Decembre"};
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

	public void update(Observable o, Object arg){
        assert o instanceof GameBoard;
        
		this.numberMois = GameBoard.ROUNDCOUNTER.get() % 12; //i becomes the remainder of the Euclidean division of ROUNDCOUNTER by 12		
		this.annee = 2017 + GameBoard.ROUNDCOUNTER.get() / 12; //We add to the year of departure the quotient of the Euclidean division of the ROUNDCOUNTER by 12
		this.date.setText(tableauMois[numberMois] + " " + annee);
	}


}
