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

package launcher;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;

import localization.FRTexts;
import localization.LocalizedTexts;
import localization.UKTexts;
import model.GameBoard;
import ui.ToolsView;
import ui.MessagesView;
import ui.PropertiesView;
import ui.RefreshView;
import ui.DateView;
import ui.FactorsView;
import ui.GameBoardView;

import java.applet.Applet;
import java.applet.AudioClip;
import java.net.URL;


public final class SimCityUI extends JFrame implements ActionListener{

    // Constants
    private final static long serialVersionUID = 1L;

    private final static int DEFAULT_HEIGHT = 40;

    private final static int DEFAULT_WIDTH = 40;
    
    /**
     * Buttons from the first frame menu
     * 
     * @see SimCityUI#SimCityUI(int, int)
     * @see JButton 
     */
    private JButton button;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JButton button5;
    
    /**
     * height and width, the both dimensions of the game frame
     * 
     * @see SimCityUI#SimCityUI(int, int)
     */
    private int hauteur ;
    private int largeur ;
    
    private LocalizedTexts texts;
    
    // Entry point
    /**
     * Run without arguments or with two arguments:
     *
     * First argument: height Second argument: width
     *
     * @param args
     * 
     * @see SimCityUI
     * @see SimCityUI#SimCityUI(int, int)
     */
    public static void main(String[] args) {
        final int height;
        final int width;
        
        if (args.length == 2) {
            final Scanner hSc = new Scanner(args[0]);
            final Scanner wSc = new Scanner(args[1]);

            if (hSc.hasNextInt()) { // if it is an integer
                height = hSc.nextInt();
            } else {
                height = SimCityUI.DEFAULT_HEIGHT;
                System.err.println("pasing: First argument must be an integer");
            }

            if (wSc.hasNextInt()) { // if it is an integer
                width = wSc.nextInt();
            } else {
                width = SimCityUI.DEFAULT_WIDTH;
                System.err.println("pasing: Second argument must be an integer");
            }

            hSc.close();
            wSc.close();
        } else {
            height = SimCityUI.DEFAULT_HEIGHT;
            width = SimCityUI.DEFAULT_WIDTH;

            if (args.length != 0) {
                System.err.println("pasing: Wrong number of arguments");
            }
        }

        // So that it is the graphical thread that builds the graphics components
        SwingUtilities.invokeLater(() -> new SimCityUI(height, width));
        
    }
    /**
     * SimCityUI constructor
     * Display a frame that contains buttons to configure the game : sound, language, tutorial
     * Run the game
     * 
     * @param height
     * @param widht
     * 
     * @see SimCityUI#hauteur
     * @see SimCityUI#largeur
     * @see JFrame
     */
    // Creation
    public SimCityUI(int hauteur, int largeur) {
        super("SimCityTélécom");
        
        this.hauteur = hauteur;
        this.largeur = largeur;
       		
        //Menu creation
        this.setTitle("Menu");
        this.setSize(1260,725);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(new Panneau());
        this.setVisible(true);
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        button = new JButton("Play");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        button.addActionListener(this);
        this.add(button, c);
        
        button2 = new JButton("Language UK");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        button2.addActionListener(this);
        this.add(button2, c);
        
        button3 = new JButton("Load");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        button3.addActionListener(this);
        this.add(button3, c);
                
        button4 = new JButton("Sound YES");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 3;
        button4.addActionListener(this);
        this.add(button4, c);
        
        button5 = new JButton("Quit");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 4;
        button5.addActionListener(this);
        this.add(button5, c);
    }
    /**
     * Apply the different action that depends of the user's actions.
     * Use an ActionEvent in parameter
     * @param e 
     * 
     * @see SimCityUI#button
     * @see SimCityUI#button2
     * @see SimCityUI#button3
     * @see SimCityUI#button4
     * @see SimCityUI#button5
     * @see SimCityUI#hauteur
     * @see SimCityUI#largeur
     */
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		
		URL url = SimCityUI.class.getResource("/resources/Music.wav");
		AudioClip ac = Applet.newAudioClip(url);
		
		
		if (source == button) {
			if (button4.getText() == "Sound YES") {
				ac.loop();
			}
			else {
				ac.stop();
			}
			if (button2.getText() == "Language UK") {
				this.texts = new UKTexts();
			}
			else {
				this.texts = new FRTexts();
			}
			// World creation				
			this.dispose();
			JFrame jeu = new JFrame();				
			jeu.setTitle("TNCYTY");
			GameBoard monde = new GameBoard(hauteur, largeur, texts);
	        // GameView creation, displayed on the middle of the frame
	        GameBoardView vm = new GameBoardView(monde);
	        monde.addObserver(vm);
	        JScrollPane sp = new JScrollPane (vm);
	        jeu.add(sp, BorderLayout.CENTER);
	        
	        
	        JButton save = new JButton("Save");
			save.addActionListener (new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
			            FileOutputStream fileoutputstream = new FileOutputStream("Save");
			            BufferedOutputStream bufferoutputstream = new BufferedOutputStream(fileoutputstream);
			            ObjectOutputStream objectoutputstream = new ObjectOutputStream(bufferoutputstream);
			            objectoutputstream.writeObject(monde);
			            objectoutputstream.flush();
			            objectoutputstream.close();
			            fileoutputstream.close();
			        } catch (IOException ioe) {
			        	ioe.printStackTrace();
			        } 
				
				}
	        	
	        });		        

	        // Tools creation, displayed on left side of the frame
	        ToolsView ve = new ToolsView(monde);
	        monde.addObserver(ve);
	        jeu.add(ve, BorderLayout.WEST);
	        
	        // Board of information, on the right side of the frame
	        PropertiesView vi = new PropertiesView(monde, texts);
	        monde.addObserver(vi);
	        
	        // Refresh panel creation
	        RefreshView rv = new RefreshView(monde);
	        JPanel right = new JPanel();
	        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
	        right.add(vi);
	        right.add(Box.createVerticalGlue());
	        right.add(rv);
	        right.add(save);
	        
	        //Date panel creation
	        DateView dv = new DateView(monde);
	        monde.addObserver(dv);
	        right.add(dv);
	        jeu.add(right, BorderLayout.EAST);
	        
		    // MessageView creation
	        MessagesView mv = new MessagesView();
	        monde.addObserver(mv);
	        jeu.add(mv, BorderLayout.SOUTH);
	        
	        jeu.setDefaultCloseOperation(EXIT_ON_CLOSE);
	        jeu.pack();
	        
	        FactorsView fv = new FactorsView(monde, texts);
	        monde.addObserver(fv);
	        jeu.add(fv, BorderLayout.NORTH);
	        

	        jeu.setResizable(false);
	        jeu.setVisible(true);
			
		} else if (source == button2){
			if (button2.getText() == "Language UK") {
				button2.setText("Language FR");
			}
			else {
				button2.setText("Language UK");
			}
		}
		else if (source == button3) {
			if (button4.getText() == "Sound YES") {
				ac.loop();
			}
			else {
				ac.stop();
			}
			if (button2.getText() == "Language UK") {
				this.texts = new UKTexts();
			}
			else {
				this.texts = new FRTexts();
			}
			// World creation				
			this.dispose();
			JFrame jeu = new JFrame();				
			jeu.setTitle("TNCYTY");
			GameBoard monde = new GameBoard("Save", texts);
	        // GameView creation, displayed on the middle of the frame
	        GameBoardView vm = new GameBoardView(monde);
	        monde.addObserver(vm);
	        JScrollPane sp = new JScrollPane (vm);
	        jeu.add(sp, BorderLayout.CENTER);
	        
	        
	        JButton save = new JButton("Save");
			save.addActionListener (new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent e) {
					try {
			            FileOutputStream fileoutputstream = new FileOutputStream("Save");
			            BufferedOutputStream bufferoutputstream = new BufferedOutputStream(fileoutputstream);
			            ObjectOutputStream objectoutputstream = new ObjectOutputStream(bufferoutputstream);
			            objectoutputstream.writeObject(monde);
			            objectoutputstream.flush();
			            objectoutputstream.close();
			            fileoutputstream.close();
			        } catch (IOException ioe) {
			        	ioe.printStackTrace();
			        } 
				
				}
	        	
	        });		        

	        // Tools creation, displayed on left side of the frame
	        ToolsView ve = new ToolsView(monde);
	        monde.addObserver(ve);
	        jeu.add(ve, BorderLayout.WEST);
	        
	        // Board of information, on the right side of the frame
	        PropertiesView vi = new PropertiesView(monde, texts);
	        monde.addObserver(vi);
	        
	        // Refresh panel creation
	        RefreshView rv = new RefreshView(monde);
	        JPanel right = new JPanel();
	        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
	        right.add(vi);
	        right.add(Box.createVerticalGlue());
	        right.add(rv);
	        right.add(save);
	        	        
	        //Date panel creation
	        DateView dv = new DateView(monde);
	        right.add(dv);
	        
	        jeu.add(right, BorderLayout.EAST);
	        
		    // MessageView creation
	        MessagesView mv = new MessagesView();
	        monde.addObserver(mv);
	        jeu.add(mv, BorderLayout.SOUTH);
	        
	        jeu.setDefaultCloseOperation(EXIT_ON_CLOSE);
	        jeu.pack();
	        
	        FactorsView fv = new FactorsView(monde, texts);
	        monde.addObserver(fv);
	        jeu.add(fv, BorderLayout.NORTH);
	        

	        jeu.setResizable(false);
	        jeu.setVisible(true);		
						
		}
		else if (source == button4 && button4.getText() == "Sound YES") {
			button4.setText("Sound NO");
		}
		else if (source == button4 && button4.getText() == "Sound NO") {
			button4.setText("Sound YES");
		}
		else if (source == button5) {
			this.dispose();
			
		}
		
	}

}