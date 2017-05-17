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
	 * @see LocalizedTexts#getToolCannotAffectMsg()
	 */
    @Override
    public String getToolCannotAffectMsg() {
        return "Impossible de modifier cette partie du terrain";
    }
    
    /**
     * @see LocalizedTexts#getCurrencyMsg()
     */
    @Override
    public String getCurrencyMsg() {
        return "{0} �";
    }
    
    /**
     * @see LocalizedTexts#getEarthQuakeMsg()
     */
    @Override
    public String getEarthQuakeMsg() {
        return "Un s�isme a frapp� aux coordonn�es [ {0} ]";
    }
    
    /**
     * @see LocalizedTexts#getMissingResourcesMsg()
     */
    @Override
    public String getMissingResourcesMsg() {
        return "Manque de ressources";
    }
    
    /**
     * @see LocalizedTexts#getRoundMsg()
     */
    @Override
    public String getRoundMsg() {
        return "Round #{0} : {1}";
    }

    // Labels
    
    /**
     * @see LocalizedTexts#getCurrencyLabel()
     */
    @Override
    public String getCurrencyLabel() {
        return "Cr�dit";
    }
    
    /**
     * @see LocalizedTexts#getUnconsumedEnergyLabel()
     */
    @Override
    public String getUnconsumedEnergyLabel() {
        return "Energie non consomm�e";
    }
    
    /**
     * @see LocalizedTexts#getUnconsumedMoneyLabel()
     */
    @Override
    public String getUnconsumedMoneyLabel() {
    	return "Argent non utilis�e";
    }
    
    /**
     * @see LocalizedTexts#getStoredProductsLabel()
     */
    @Override
    public String getStoredProductsLabel() {
        return "Produits en stock";
    }
    
    /**
     * @see LocalizedTexts#getUnworkingPopulationLabel()
     */
    @Override
    public String getUnworkingPopulationLabel() {
        return "Population au ch�mage";
    }

}
