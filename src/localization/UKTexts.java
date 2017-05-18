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
public class UKTexts extends LocalizedTexts {

    // Messages
	/**
	 * @see LocalizedTexts#getToolCannotAffectMsg()
	 */
    @Override
    public String getToolCannotAffectMsg() {
        return "Cannot effect this tile";
    }
    
    /**
     * @see LocalizedTexts#getCurrencyMsg()
     */
    @Override
    public String getCurrencyMsg() {
        return "£ {0}";
    }
    
    /**
     * @see LocalizedTexts#getEarthQuakeMsg()
     */
    @Override
    public String getEarthQuakeMsg() {
        return "An earthquake occured at coordinates [ {0} ]";
    }
    
    /**
     * @see LocalizedTexts#getMissingResourcesMsg()
     */
    @Override
    public String getMissingResourcesMsg() {
        return "Missing resources";
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
        return "Currency";
    }
    
    /**
     * @see LocalizedTexts#getUnconsumedEnergyLabel()
     */
    @Override
    public String getUnconsumedEnergyLabel() {
        return "Unconsumed energy";
    }
    
    /**
     * @see LocalizedTexts#getUnconsumedMoneyLabel()
     */
    @Override
    public String getUnconsumedMoneyLabel() {
    	return "Unconsumed money";
    }
    
    /**
     * @see LocalizedTexts#getStoredProductsLabel()
     */
    @Override
    public String getStoredProductsLabel() {
        return "Stored products";
    }
    
    /**
     * @see LocalizedTexts#getUnworkingPopulationLabel()
     */
    @Override
    public String getUnworkingPopulationLabel() {
        return "Unworking population";
    }

    /**
     * @see LocalizedTexts#getEfficiencyAtWorkLabel()
     */
	@Override
	public String getEfficiencyAtWorkLabel() {
		return "Efficiency at work";
	}

	/**
	 * @see LocalizedTexts#getEconomyLabel()
	 */
	@Override
	public String getEconomyLabel() {
		return "Economy";
	}

	/**
	 * @see LocalizedTexts#getHappinessLabel()
	 */
	@Override
	public String getHappinessLabel() {
		return "Happiness";
	}

}
