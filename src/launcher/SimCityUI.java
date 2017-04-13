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
import java.awt.Button;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import localization.LocalizedTexts;
import localization.UKTexts;
import model.GameBoard;
import ui.ToolsView;
import ui.MessagesView;
import ui.PropertiesView;
import ui.RefreshView;
import ui.GameBoardView;

public final class SimCityUI extends JFrame implements ActionListener{

    // Constants
    private final static long serialVersionUID = 1L;

    private final static int DEFAULT_HEIGHT = 1000;

    private final static int DEFAULT_WIDTH = 750;
    
    private JFrame menu = new JFrame();
    private JButton button;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    
    private int hauteur ;
    private int largeur ;
    
    // Entry point
    /**
     * Run without arguments or with two arguments:
     *
     * First argument: height Second argument: width
     *
     * @param args
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

        // Pour que ce soit le thread graphique qui construise les composants
        // graphiques
        SwingUtilities.invokeLater(() -> new SimCityUI(height, width));
    }
    
    // Creation
    public SimCityUI(int hauteur, int largeur) {
        super("SimCityTélécom");
        
        this.hauteur = hauteur;
        this.largeur = largeur;
        // Choix de la langue
        final LocalizedTexts texts = new UKTexts();
        
        
        //Crétion du menu
        this.setTitle("Menu");
        this.setSize(new Dimension(DEFAULT_HEIGHT, DEFAULT_WIDTH));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(new Panneau());
        this.setVisible(true);
        this.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        
        button = new JButton("Jouer");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 0;
        button.addActionListener(this);
        this.add(button, c);
        
        button2 = new JButton("Param�tres");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 1;
        button2.addActionListener(this);
        this.add(button2, c);
        
        button3 = new JButton("Aide");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 2;
        button3.addActionListener(this);
        this.add(button3, c);
        
        button4 = new JButton("Quitter");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.gridy = 3;
        button4.addActionListener(this);
        this.add(button4, c);
        
    }

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		final LocalizedTexts texts = new UKTexts();
		if (source == button) {
			// Création du monde
			this.dispose();
			JFrame jeu = new JFrame();
			jeu.setTitle("TNCYTY");
			jeu.setLocationRelativeTo(null);
			GameBoard monde = new GameBoard(hauteur, largeur, texts);

	        // Création de la vue du monde, placée au centre de la fenêtre
	        GameBoardView vm = new GameBoardView(monde);
	        monde.addObserver(vm);
	        jeu.add(vm, BorderLayout.CENTER);

	        // Création de la palette des éléments de jeu, placée à gauche
	        ToolsView ve = new ToolsView(monde);
	        monde.addObserver(ve);
	        jeu.add(ve, BorderLayout.WEST);

	        // Création du panneau d'informations
	        PropertiesView vi = new PropertiesView(monde, texts);
	        monde.addObserver(vi);

	        // Création du panneau de rafraichissement
	        RefreshView rv = new RefreshView(monde);
	        JPanel right = new JPanel();
	        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
	        right.add(vi);
	        right.add(Box.createVerticalGlue());
	        right.add(rv);
	        jeu.add(right, BorderLayout.EAST);

	        // Création du panneau de message
	        MessagesView mv = new MessagesView();
	        monde.addObserver(mv);
	        jeu.add(mv, BorderLayout.SOUTH);
	     
	        jeu.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	        
	        jeu.pack();
	        jeu.setResizable(true);
	        jeu.setVisible(true);
		} else if (source == button2){
			//� finir
		}
		else if (source == button4) {
			this.dispose();
		}
		
	}

}
