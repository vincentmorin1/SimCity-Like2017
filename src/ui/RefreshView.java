/**
 * TNCity
 * Copyright (c) 2017
 *  Jean-Philippe Eisenbarth,
 *  Victorien Elvinger
 *  Martine Gautier,
 *  Quentin Laporte-Chabasse
 *
 *  This file is part of TNCity.
 *
 *  TNCity is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 *
 *  TNCity is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.

 *  You should have received a copy of the GNU General Public License
 *  along with TNCity.  If not, see <http://www.gnu.org/licenses/>.
 */
package ui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.Box;

import model.GameBoard;

public class RefreshView extends JPanel implements ActionListener{

    // Constant
    private static final long serialVersionUID = 1L;
    
    private int vitesse;   

    // Creation
    
    /**
     * RefreshView constructor
     * 
     * @param w
     */
    public RefreshView(GameBoard w) {
        super();     
        
        this.setBorder(BorderFactory.createBevelBorder(1, Color.GRAY,
        Color.BLACK));        
        this.setName("Refresh");
        this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
      
        
        GridBagConstraints c = new GridBagConstraints();
        
        
        JToggleButton button = new JToggleButton (">");
        button.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				vitesse = 0;
				Date timeToRun = new Date(System.currentTimeMillis());
				Timer timer = new Timer();
				timer.schedule(new TimerTask() {
					public void run(){
						w.nextState();
						if (vitesse != 0){   //Si on clique sur un autre bouton, la boucle s'arrête 
							timer.cancel();
						}
					}
				}, timeToRun,8000); //On répète la boucle toutes les 8 secondes
				
			}
        	
        });
        c.fill = GridBagConstraints.HORIZONTAL;        
        c.gridx = 0;
        c.gridy = 0;   
        
        JToggleButton button2 = new JToggleButton (">>");
        button2.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				vitesse = 1;
				Date timeToRun = new Date(System.currentTimeMillis());
				Timer timer = new Timer();
				timer.schedule(new TimerTask() {
					public void run() {
						w.nextState();
						if (vitesse != 1){
							timer.cancel();
						}
					}
				}, timeToRun,4000);
				
			}
        	
        });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        
        JToggleButton button3 = new JToggleButton (">>>");
        button3.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				vitesse = 2;
				Date timeToRun = new Date(System.currentTimeMillis());
				Timer timer = new Timer();
				timer.schedule(new TimerTask() {
					public void run() {
						w.nextState();
						if (vitesse != 2){
							timer.cancel();
						}
					}
				}, timeToRun,2000);
						
			}
        	
        });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        
        JToggleButton button4 = new JToggleButton ("||");
        button4.addActionListener(new ActionListener() {
        	
			@Override
			public void actionPerformed(ActionEvent e) {
				vitesse = 3;
				
				
			}
        	
        });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;        
        
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(button);
        buttonGroup.add(button2);
        buttonGroup.add(button3);
        buttonGroup.add(button4);
        
        
        this.setBorder(new TitledBorder(new EtchedBorder(), "Refresh"));
        
        Box top = Box.createVerticalBox();
        top.add(button, c);
        top.add(Box.createVerticalStrut(10));
        top.add(button2, c);
        top.add(Box.createVerticalStrut(10));
        top.add(button3, c);
        top.add(Box.createVerticalStrut(10));
        top.add(button4, c);
        
        
        this.add(top, c);
        
    }

    @Override
	public void actionPerformed(ActionEvent e) {}
    
}