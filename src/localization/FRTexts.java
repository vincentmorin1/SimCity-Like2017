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

package localization;

/**
 * United Kingdom localized texts.
 */
public class FRTexts extends LocalizedTexts {

    // Messages
	/**
	 * Error message, displayed when the player is trying to modify a not allowed part of the tiles
	 * 
	 * @return Tool cannot affect a tile
	 */
    @Override
    public String getToolCannotAffectMsg() {
        return "Impossible de modifier cette partie du terrain";
    }
    
    /**
     * @return Current message
     */
    @Override
    public String getCurrencyMsg() {
        return "{0} �";
    }
    
    /**
     * @return String 
     */
    @Override
    public String getEarthQuakeMsg() {
        return "Un s�isme a frapp� aux coordonn�es [ {0} ]";
    }
    
    /**
     * @return String 
     */
    @Override
    public String getMissingResourcesMsg() {
        return "Manque de ressources";
    }
    
    /**
     * @return String 
     */
    @Override
    public String getRoundMsg() {
        return "Round #{0} : {1}";
    }

    // Labels
    
    /**
     * @return String 
     */
    @Override
    public String getCurrencyLabel() {
        return "Cr�dit";
    }
    
    /**
     * @return String 
     */
    @Override
    public String getUnconsumedEnergyLabel() {
        return "Energie non consomm�e";
    }
    
    /**
     * @return String 
     */
    @Override
    public String getUnconsumedMoneyLabel() {
    	return "Argent non utilis�e";
    }
    
    /**
     * @return String 
     */
    @Override
    public String getStoredProductsLabel() {
        return "Produits en stock";
    }
    
    /**
     * @return String 
     */
    @Override
    public String getUnworkingPopulationLabel() {
        return "Population au ch�mage";
    }

}
