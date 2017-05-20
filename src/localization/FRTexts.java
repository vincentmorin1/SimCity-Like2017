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
        return "Un seisme a frappe aux coordonnees [ {0} ]";
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
        return "Credit";
    }
    
    /**
     * @see LocalizedTexts#getUnconsumedEnergyLabel()
     */
    @Override
    public String getUnconsumedEnergyLabel() {
        return "Energie non consommee";
    }
    
    /**
     * @see LocalizedTexts#getUnconsumedMoneyLabel()
     */
    @Override
    public String getUnconsumedMoneyLabel() {
    	return "Argent non utilisee";
    }
    
    /**
     * @see LocalizedTexts#getStoredProductsLabel()
     */
    @Override
    public String getStoredProductsLabel() {
        return "Produits en stock";
    }
    
    /**
     * @see LocalizedTexts#getUnstudyingPopLabel()
     */
    @Override
    public String getUnworkingStudentPopulationLabel() {
        return "Etudiant sans école!";
    }
    
    /**
     * @see LocalizedTexts#getUnworkingPopulationLabel()
     */
    @Override
    public String getUnworkingSeniorPopulationLabel() {
        return "Population au chomage";
    }

    /**
     * @see LocalizedTexts#getEfficiencyAtWorkLabel()
     */
	@Override
	public String getEfficiencyAtWorkLabel() {
		return "Efficacite au travail";
	}

	/**
	 * @see LocalizedTexts#getEconomyLabel()
	 */
	@Override
	public String getEconomyLabel() {
		return "Economie";
	}

	/**
	 * @see LocalizedTexts#getHappinessLabel()
	 */
	@Override
	public String getHappinessLabel() {
		return "Bonheur";
	}

}
