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

import java.text.MessageFormat;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import localization.LocalizedTexts;
import model.GameBoard;

public class PropertiesView extends JPanel implements Observer {

    private static final long serialVersionUID = 1L;

    /**
     * {@link GameBoard#getCurrency()}
     */
    private JLabel currency;
    
    /**
     * {@link GameBoard#getEnergy()}
     */
    private JLabel energy;
    
    /**
     * {@link GameBoard#getUnworkingPopulation()}
     */
    private JLabel unworkingSeniorPop;
    
    /**
     * {@link GameBoard#getProducts()}    
     */
    private JLabel products;
    
    /**
     * {@link GameBoard#getProducts()}    
     */
    private JLabel unworkingStudentPop;

    /**
     * PropertiesView constructor
     * 
     * @param w
     * @param texts
     */
    public PropertiesView(GameBoard w, LocalizedTexts texts) {
        super();
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(new JLabel(texts.getCurrencyLabel()));
        this.currency = new JLabel(Integer.toString(w.getCurrency()));
        this.add(this.currency);

        this.add(new JLabel(texts.getUnconsumedEnergyLabel()));
        this.energy = new JLabel(Integer.toString(w.getEnergy()));
        this.add(this.energy);
        
        this.add(new JLabel(texts.getUnworkingStudentPopulationLabel()));
        this.unworkingStudentPop = new JLabel(w.getUnworkingStudentPopulation() + " / "+ w.getStudentPopulation());
        this.add(this.unworkingStudentPop);
        
        this.add(new JLabel(texts.getUnworkingSeniorPopulationLabel()));
        this.unworkingSeniorPop = new JLabel(w.getUnworkingSeniorPopulation() + " / " + w.getSeniorPopulation());
        this.add(this.unworkingSeniorPop);

        this.add(new JLabel(texts.getStoredProductsLabel()));
        this.products = new JLabel(w.getProducts() +" / "+ w.getCityResources().getProductsCapacity());
        this.add(this.products);
    }

    @Override
    public void update(Observable o, Object arg) {
        assert o instanceof GameBoard;
        GameBoard world = (GameBoard) o;

        this.currency.setText(MessageFormat.format(world.getTexts().getCurrencyMsg(), world.getCurrency()));
        this.energy.setText("" + world.getEnergy()); 
        this.unworkingStudentPop.setText(world.getUnworkingStudentPopulation() + " / "+ world.getStudentPopulation());
        this.unworkingSeniorPop.setText(world.getUnworkingSeniorPopulation() + " / " + world.getSeniorPopulation());
        this.products.setText("" + world.getProducts()+" / "+world.getCityResources().getProductsCapacity());
    }

}
