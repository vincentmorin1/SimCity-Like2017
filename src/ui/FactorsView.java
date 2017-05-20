package ui;

import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import localization.LocalizedTexts;
import model.GameBoard;

public class FactorsView extends JPanel implements Observer {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * {@link GameBoard#getHappiness()}
	 */
	private JLabel Happiness;
	
	/**
	 * {@link GameBoard#getEfficiencyAtWork()}
	 */
	private JLabel EfficiencyAtWork;
	
	/**
	 * {@link GameBoard#getEconomy()}
	 */
	private JLabel Economy;
	
	/**
	 * FactorsView constructor
	 * 
	 * @param w
	 * @param texts
	 */
	public FactorsView(GameBoard w, LocalizedTexts texts) {
		super();
		this.setLayout(new GridLayout(1,3));		
		this.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
				
		this.add(new JLabel(texts.getHappinessLabel()));
		this.Happiness = new JLabel(Integer.toString(w.getHappiness()));
		this.add(this.Happiness);
		
		this.add(new JLabel(texts.getEfficiencyAtWorkLabel()));
		this.EfficiencyAtWork = new JLabel(Integer.toString(w.getEfficiencyAtWork()));
		this.add(this.EfficiencyAtWork);
		
		this.add(new JLabel(texts.getEconomyLabel()));
		this.Economy = new JLabel(Integer.toString(w.getEconomy()));
		this.add(this.Economy);
		
		
	}
	
	
	@Override
	public void update(Observable o, Object arg) {
		assert o instanceof GameBoard;
        GameBoard world = (GameBoard) o;

        this.Happiness.setText(world.getHappiness() + "%");
        this.EfficiencyAtWork.setText(world.getEfficiencyAtWork() + "%"); 
        this.Economy.setText(world.getEconomy() + "%");
        
	}
	
	

}
