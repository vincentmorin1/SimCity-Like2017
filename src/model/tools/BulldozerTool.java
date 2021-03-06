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
import model.tiles.BuildingTile;
import model.tiles.Destroyable;
import model.tiles.GrassTile;
import model.tiles.SnowStationTile;
import model.tiles.MountainTile;
import model.tiles.Tile;

/**
 * The Bulldozer Tool can be used to destroy buildings.
 */
public final class BulldozerTool extends Tool implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Constant
	/**
	 * Price to destroy any building
	 */
    private final static int CURRENCY_COST = 10;

    // Status
    /**
     * CanEffect returns true if the given aTarget is Destroyable, false
     * otherwise.
     */
    @Override
    public boolean canEffect(Tile aTarget) {
        return aTarget instanceof BuildingTile;
    }

    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof BulldozerTool;

    }

    /**
     * isAfordable returns true if the user can apply a buildozer to a tile,
     * false otherwise.
     */
//    @Override
    public boolean isAfordable(Tile aTarget, CityResources r) {
        return BulldozerTool.CURRENCY_COST <= r.getCurrency();
    }

    // Access
    @Override
    public int getCost(Tile aTarget) {
        return BulldozerTool.CURRENCY_COST;
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    /**
     * innerEffect apply the bulldozer to the given tile and update the given
     * CityResources.
     */
    @Override
    protected Tile innerEffect(Tile aTarget, CityResources r, int topLeftCornerX ,int topLeftCornerY) {
        assert canEffect(aTarget);
        assert isAfordable(aTarget, r);

        ((Destroyable) aTarget).disassemble(r);
        r.spend(BulldozerTool.CURRENCY_COST);
        
        if (aTarget instanceof SnowStationTile ){
        	return MountainTile.getDefault();
        }
        else{
            return GrassTile.getDefault();
        }
    }

    // Debugging
    @Override
    public String toString() {
        return getClass().getSimpleName();
    }

	@Override
	public  int getDimensionX(){
		return 1;
	}
	
	@Override
	public  int getDimensionY(){
		return 1;
	}
}
