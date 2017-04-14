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
    @Override
    public String getToolCannotAffectMsg() {
        return "Impossible de modifier cette partie du terrain";
    }

    @Override
    public String getCurrencyMsg() {
        return "{0} €";
    }

    @Override
    public String getEarthQuakeMsg() {
        return "Un séisme a frappé aux coordonnées [ {0} ]";
    }

    @Override
    public String getMissingResourcesMsg() {
        return "Manque de ressources";
    }

    @Override
    public String getRoundMsg() {
        return "Round #{0} : {1}";
    }

    // Labels
    @Override
    public String getCurrencyLabel() {
        return "Crédit";
    }

    @Override
    public String getUnconsumedEnergyLabel() {
        return "Energie non consommée";
    }
    
    @Override
    public String getUnconsumedMoneyLabel() {
    	return "Argent non utilisée";
    }
    
    @Override
    public String getStoredProductsLabel() {
        return "Produits en stock";
    }

    @Override
    public String getUnworkingPopulationLabel() {
        return "Population au chômage";
    }

}
