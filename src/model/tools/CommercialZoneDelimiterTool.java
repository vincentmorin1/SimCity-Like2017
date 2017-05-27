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

package model.tools;

import java.io.Serializable;

import model.CityResources;
import model.tiles.GrassTile;
import model.tiles.CommercialTile;
import model.tiles.Tile;

/**
 *
 * @author Victorien Elvinger
 *
 */
public final class CommercialZoneDelimiterTool extends Tool implements Serializable {

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Constant
	/**
	 * Price to build a commercial area.
	 */
	private final static int CURRENCY_COST = 20;

// Status
	// Status
	/**
     * canEffect returns true if the given Tile is buildable, false otherwise.
     */
	@Override
	public boolean canEffect (Tile aTarget) {
		return aTarget instanceof GrassTile;
	}

	@Override
	public boolean equals (Object o) {
		return this == o || o instanceof CommercialZoneDelimiterTool;
		
	}

	/**
     * isAfordable returns true if the user can apply the Commercial Tool, false
     * otherwise.
     */
	@Override
	public boolean isAfordable (Tile aTarget, CityResources r) {
		return CommercialZoneDelimiterTool.CURRENCY_COST <= r.getCurrency();
	}

// Access
	@Override
	public int getCost (Tile aTarget) {
		return CommercialZoneDelimiterTool.CURRENCY_COST;
	}

	@Override
	public int hashCode () {
		return getClass().hashCode();
	}

	/**
     * innerEffect apply the Residential tool to the given tile and update the
     * given CityResources.
     */
	@Override
	protected Tile innerEffect (Tile aTarget, CityResources r, int topLeftCornerX ,int topLeftCornerY) {
		assert canEffect(aTarget);
		assert isAfordable(aTarget, r);

		r.spend(CommercialZoneDelimiterTool.CURRENCY_COST);

		return new CommercialTile(CommercialTile.DEFAULT_CONSUMPTION_CAPACITY, topLeftCornerX ,topLeftCornerY);
	}


// Debugging
	@Override
	public String toString () {
		return getClass().getSimpleName();
	}

	@Override
	public  int getDimensionX(){
		return CommercialTile.DIMENSION_WIDTH;
	}
	
	@Override
	public  int getDimensionY(){
		return CommercialTile.DIMENSION_HEIGHT;
	}
}
