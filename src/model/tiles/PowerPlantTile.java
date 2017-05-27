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

public class PowerPlantTile extends BuildingTile implements Serializable {

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
	 * Dimension x of the tile 
	 */
	public final static int DIMENSION_WIDTH = 1;
	
	/**
	 * Dimension y of the tile
	 */
	public final static int DIMENSION_HEIGHT = 1;
	
    /**
     * Extra energy produces for each new update. In the limit of the capacity
     * {@link #getProductionCapacity()}.
     */
    public final static int EXTRA_ENERGY_PRODUCTION = 15;

    /**
     * Default value of {@link PowerPlantTile2#getProductionCapacity()}
     */
    public final static int DEFAULT_PRODUCTION_CAPACITY = 70;

    // Implementation
    /**
     * {@link #getProduction()}
     */
    protected int production;

    /**
     * {@link #getProductionCapacity()}
     */
    protected final int productionCapacity;

    /**
     * Evolution state
     */
    protected boolean isDestroyed;

    // Creation
    /**
     * @param capacity
     *            
     *            
     *            
    
    /**
     * 
     * @param productionCapacity 
     * 				- {@link #getProductionCapacity()}
     * @param topLeftCornerX 
     * 				- {@link #getTopLeftCornerX()}
     * @param topLeftCornerY 
     * 				- {@link #getTopLeftCornerY()}
     */
    public PowerPlantTile(int productionCapacity, int topLeftCornerX ,int topLeftCornerY) {
        super();
        this.productionCapacity = productionCapacity;
        this.production = 0;
        this.isDestroyed = false;
    	this.topLeftCornerX = topLeftCornerX;
    	this.topLeftCornerY = topLeftCornerY;
    }

    /**
     * Create with default settings.
     */
    public PowerPlantTile() {
        this(PowerPlantTile.DEFAULT_PRODUCTION_CAPACITY, 0, 0);
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
    /**
     * @return Current production.
     */
    public int getProduction() {
        return this.production;
    }

    /**
     * @return Stock maximum.
     */
    public int getProductionCapacity() {
        return this.productionCapacity;
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = result * 17 + this.production;
        result = result * 17 + this.productionCapacity;
        result = result * 17 + Boolean.hashCode(this.isDestroyed);
        return result;
    }

    // Status
    @Override
    public boolean equals(Object o) {
        return o instanceof PowerPlantTile && this.equals((PowerPlantTile) o);
    }
    
    @Override
    public boolean equals(BuildingTile o1) {
    	if (o1 instanceof PowerPlantTile){
    		PowerPlantTile o = (PowerPlantTile) o1;
    		return this == o || o.production == this.production && o.productionCapacity == this.productionCapacity && o.isDestroyed == this.isDestroyed;
    	}
    	else{
    		return false;
    	}
    }
    

    @Override
    public boolean isDestroyed() {
        return this.isDestroyed;
    }

    // Change
    @Override
    public void disassemble(CityResources res) {
        if (!this.isDestroyed) {
            res.decreaseEnergyProduction(this.productionCapacity);
            this.isDestroyed = true;
        }
    }

    @Override
    public void update(CityResources res) {
        if (!this.isDestroyed) {
            // Double production
            final int extraProduction = Math.min(PowerPlantTile.EXTRA_ENERGY_PRODUCTION, this.productionCapacity - this.production);

            this.production = this.production + extraProduction;
            res.increaseEnergyProduction(extraProduction);
        }
    }
    
    @Override
    public void setLinked(boolean b){  	
    }

	@Override
	public boolean getLinked() {
		return true;
	}

	@Override
	public boolean getIsEnergyMissing() {
		return false;
	}

    @Override
    public String[] getInformations(){
    	String[] res = new String[3];
    	res[0] = this.getClass().getSimpleName();
    	res[1] = "Electricity production : " + this.getProduction();
    	res[2] = "Linked by road : " + this.getLinked();
    	return res;
    }
}
