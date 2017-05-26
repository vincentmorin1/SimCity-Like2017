package ui;

import java.awt.Color;
import java.awt.Label;
import java.io.Serializable;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import model.GameBoard;

public class DateView extends JPanel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public DateView (GameBoard w){
		super();
		
		this.setBorder(BorderFactory.createBevelBorder(1, Color.GRAY, Color.GRAY));        
		this.setName("Date");
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setBorder(new TitledBorder(new EtchedBorder(), "Date"));
		
		Label date = new Label();     
		
		int i = GameBoard.ROUNDCOUNTER.getAndIncrement() % 12; //i becomes the remainder of the Euclidean division of ROUNDCOUNTER by 12
		String tableauMois[] = {"Janvier","Février","Mars","Avril","Mai","Juin","Juillet","Août","Septembre","Octobre","Novembre","Décembre"};
		
		int annee = 2017;
		annee += GameBoard.ROUNDCOUNTER.get() / 12; //We add to the year of departure the quotient of the Euclidean division of the ROUNDCOUNTER by 12
		
		date.setText(tableauMois[i] + " " + annee);
		
		this.add(date);			
	}

	

}
