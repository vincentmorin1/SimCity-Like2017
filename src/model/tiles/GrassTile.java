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

package model.tiles;

import java.io.Serializable;

import model.CityResources;

/**
 * State-less tile that represents grass tiles.
 */
public final class GrassTile extends Tile implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    /**
     * {@link #getTopLeftCornerX()}
     */
    private final int topLeftCornerX;
    
    /**
     * {@link #getTopLeftCornerY()}
     */
    private final int topLeftCornerY;
    
    // Constant
    /**
     * Default instance.
     */
    private final static GrassTile INSTANCE = new GrassTile();

    // Factory
    /**
     * @return Default grass tile.
     */
    public static GrassTile getDefault() {
        // Provide always the same instance since Grass is not changing.
        return GrassTile.INSTANCE;
    }

    // Creation
    /**
     * Prefer use {@link GrassTile#getDefault()} instead.
     */
    private GrassTile() {
    	this.topLeftCornerX = 0;
    	this.topLeftCornerY = 0;
    }

    @Override
	public int getDimensionX(){
		return 1;
	}

    @Override
	public int getDimensionY(){
		return 1;
	}

    @Override
	public int getTopLeftCornerX(){
		return this.topLeftCornerX;
	}

    @Override
	public int getTopLeftCornerY(){
		return this.topLeftCornerY;
	}
	
    // Access
    @Override
    public int hashCode() {
        return 0;
    }

    // Status
    @Override
    public boolean equals(Object o) {
        return this == o || o instanceof GrassTile;
    }

    // Change
    @Override
    public void update(CityResources res) {
        // Do nothings.
    }

    @Override
    public String[] getInformations(){
    	String[] res = new String[1];
    	res[0] = this.getClass().getSimpleName();
    	return res;
    }

}
